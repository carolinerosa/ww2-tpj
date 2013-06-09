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
import com.nave.segundaguerra.game.Tiro;
import com.nave.segundaguerra.servidorecliente.cliente.ControleDeUsuariosCliente;
import com.nave.segundaguerra.servidorecliente.cliente.DadosDoCliente;
import com.nave.segundaguerra.servidorecliente.cliente.JogadorCliente;
import com.nave.segundaguerra.servidorecliente.cliente.TiroCliente;
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

	public ViewDeRede(Context context, Conexao cliente,
			ControleDeUsuariosCliente tratadorDeDadosDoCliente) {

		super(context);
		ElMatador.getInstance().add(this);
		
		
		// envia estado atual do cliente para o servidor
		dadosDoCliente = new DadosDoCliente(cliente, UPDATE_TIME);
		Thread threadDados = new Thread(dadosDoCliente);
		threadDados.start();

		this.tratadorDeDadosDoCliente = tratadorDeDadosDoCliente;

		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(fontSize);

		cliente.write(Protocolo.PROTOCOL_ID + "," + cliente.getId() + ",0,0");

		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		Thread thread = new Thread(this);
		thread.start();
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);

		ConcurrentHashMap<String, JogadorCliente> jogadores = tratadorDeDadosDoCliente
				.getJogadores();

		List<TiroCliente> tiros = tratadorDeDadosDoCliente.getListTiros();
		
		Iterator<String> iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			JogadorCliente jogador = jogadores.get(key);
			
			jogador.draw(canvas);
			
			for(TiroCliente t : tiros)
			{  
		          t.DrawTiro(canvas);
		    }
			
			canvas.drawText("<" + jogador.getNome() + ">", jogador.getX()
					- raio, jogador.getY() + raio + margem + fontSize, paint);
				
			
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
		
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		Log.i(TAG, "ontouch: " + action);

		int id = event.getPointerId(event.getAction());
		//dadosDoCliente.sendMove((int) event.getX(id), (int) event.getY(id));
		
		//dadosDoCliente.setX((int) event.getX(id));
		//dadosDoCliente.setY((int) event.getY(id));

		switch (action) 
		{
		case MotionEvent.ACTION_DOWN:
			dadosDoCliente.setX((int) event.getX(id));
			dadosDoCliente.setY((int) event.getY(id));
			break;
			
		case MotionEvent.ACTION_MOVE:
			dadosDoCliente.setX((int) event.getX(id));
			dadosDoCliente.setY((int) event.getY(id));
			break;
			
		case MotionEvent.ACTION_UP:
			dadosDoCliente.sendTiro(new Point((int)event.getX(id), (int)event.getY(id)));
			
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
