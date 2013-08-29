package com.nave.segundaguerra.activitys.views;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nave.segundaguerra.activitys.ConectActivity;
import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Tiro;
import com.nave.segundaguerra.servidorecliente.cliente.ControleDeUsuariosCliente;
import com.nave.segundaguerra.servidorecliente.cliente.DadosDoCliente;
import com.nave.segundaguerra.servidorecliente.cliente.GeneralCliente;
import com.nave.segundaguerra.servidorecliente.cliente.MapaCliente;
import com.nave.segundaguerra.servidorecliente.cliente.PlayerCliente;
import com.nave.segundaguerra.servidorecliente.cliente.TiroCliente;
import com.nave.segundaguerra.servidorecliente.cliente.ViewPort;
import com.nave.segundaguerra.servidorecliente.servidor.GeneralServer;
import com.nave.segundaguerra.servidorecliente.servidor.PlayerServer;
import com.nave.segundaguerra.servidorecliente.servidor.TiroServer;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class ViewDeRede extends View implements Runnable, Killable {
	
	private static final String TAG = "view-rede";
	private static final int UPDATE_TIME = 100;
	private Paint paint;
	private long time = 30;

	private ControleDeUsuariosCliente tratadorDeDadosDoCliente;
	private DadosDoCliente dadosDoCliente;

	private float raio = 20;
	private int margem = 5;
	private int fontSize = 20;
	private boolean ativo = true;
	
	public ViewPort viewPort;
	public MapaCliente mapa;
	
	private Point[] dedos = new Point[2];
	PlayerCliente meuJogador;
	

	PlayerServer JS;
	TiroServer TS;
	
	public ViewDeRede(Context context, Conexao cliente,
			ControleDeUsuariosCliente tratadorDeDadosDoCliente) {

		super(context);
		ElMatador.getInstance().add(this);
		
		this.tratadorDeDadosDoCliente = tratadorDeDadosDoCliente;
		this.tratadorDeDadosDoCliente.iniciarJogo();
		
		// envia estado atual do cliente para o servidor
		dadosDoCliente = new DadosDoCliente(cliente, UPDATE_TIME);
		Thread threadDados = new Thread(dadosDoCliente);
		threadDados.start();
		

		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(fontSize);

		
		meuJogador = GerenciadorActivity.GetInstance().getPlayer();
		cliente.write(Protocolo.PROTOCOL_ID + "," + meuJogador.getNome() + ","+ meuJogador.getPosition().x + "," + meuJogador.getPosition().y 
				+ "," + meuJogador.getTime());
		//meuJogador = setMeuJogador(cliente);
		
		this.viewPort = new ViewPort(meuJogador);
		this.mapa = new MapaCliente();
		
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		Thread thread = new Thread(this);
		thread.start();
		
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);

		viewPort.setTela(this.getHeight(), this.getWidth());
		viewPort.drawInViewPort(mapa, canvas);
		
		ConcurrentHashMap<String, PlayerCliente> jogadores = tratadorDeDadosDoCliente
				.getJogadores();
		
		List<TiroCliente> tiros = tratadorDeDadosDoCliente.getListTiros();
		
		Iterator<String> iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			PlayerCliente jogador = jogadores.get(key);
			
				this.viewPort.drawInViewPort(jogador, canvas);
				
				if(jogador.getImage() != null)
				{
					raio = jogador.getImage().getHeight()/2;
				}
		}
		
		for(TiroCliente t : tiros)
		{  
			this.viewPort.drawInViewPort(t, canvas);
	    }

	}

	public void run() {

		while (ativo) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				Log.e(ConectActivity.TAG, "interrupcao do run()");
			}
			update();
			postInvalidate();
		}

	}

	private void update() {
		this.viewPort.update();
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		Log.i("onTouch", "ontouch: " + action);

		int id = 0;
		Point playerDraw = viewPort.getPosPlayerDraw();

		
		for(int i = 0; i < dedos.length; i++)
		{
			if (i < event.getPointerCount())
			{
				id = event.getPointerId(i);
				dedos[i] = new Point((int)(event.getX(id) - playerDraw.x),
						(int) (event.getY(id) - playerDraw.y));
			}
		}

		switch (action & MotionEvent.ACTION_MASK) 
		{
		case MotionEvent.ACTION_DOWN:
			dadosDoCliente.setX(dedos[0].x);
			dadosDoCliente.setY(dedos[0].y);
			Log.i("Toque", "Primeiro toque");
			break;
			
		case MotionEvent.ACTION_MOVE:
			dadosDoCliente.setX(dedos[0].x);
			dadosDoCliente.setY(dedos[0].y);
			//Log.i("Toque", "Movendo toque");
			break;
		
		case MotionEvent.ACTION_UP:
			//dadosDoCliente.sendTiro(dedos[0]);
			//meuJogador.sendTiro(this.dadosDoCliente, dedos[0]);
			dadosDoCliente.setX(0);
			dadosDoCliente.setY(0);
			Log.i("Toque", "Retirado 1 toque");
			break;
		
        case MotionEvent.ACTION_POINTER_DOWN:
            //dadosDoCliente.sendTiro(dedos[1]);
        	meuJogador.sendTiro(this.dadosDoCliente, dedos[1]);
            Log.i("Toque", "Segundo toque");
			break;

		default:
			break;
		}
		
		return super.onTouchEvent(event);
	}
	
	public void killMeSoftly() {
		ativo = false;
	}
	
	

}
