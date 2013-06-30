package com.nave.segundaguerra.activitys.views;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.R.drawable;
import com.nave.segundaguerra.game.RectClasse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TelaEscolhaDetTime extends View implements Runnable {

	private final static int INTERVAL = 10;
	private boolean running = true;
	private Bitmap bmpFundo;
	private Paint paint_Vermelho;
	private Paint paint_Azul;
	private boolean jogoIniciado = false;
	private RectClasse botao_vermelho;
	private RectClasse botao_azul;
	private String Time = null;
	private static boolean clicou;

	public TelaEscolhaDetTime(Context context) {
		super(context);

		paint_Vermelho = new Paint();
		paint_Azul = new Paint();
		botao_vermelho = new RectClasse(50, 100, 80, 130, paint_Vermelho);
		botao_azul = new RectClasse(230, 100, 260, 130, paint_Azul);
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
	}

	public void run() {

		while (running) {
			try {
				Thread.sleep(INTERVAL);
			} catch (Exception e) {
				Log.e("Jogo", "Sleep da Thread");
			}
			update();
		}

	}

	private void update() {
		/*
		 * if(Time == "vermelho") { System.out.println("Sou do time vermelho");
		 * 
		 * } if(Time == "azul") { System.out.println("Sou do time azul"); }
		 */

	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		paint_Vermelho.setColor(Color.RED);
		paint_Azul.setColor(Color.BLUE);

		if (jogoIniciado == false) {
			iniciaJogo();
		}
		canvas.drawBitmap(bmpFundo, 0, 0, paint_Vermelho);
		botao_vermelho.draw(canvas);
		botao_azul.draw(canvas);

	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		int x = (int) event.getX();
		int y = (int) event.getY();

		if (action == MotionEvent.ACTION_DOWN) {
			if (botao_vermelho.colide(x, y)) {
				Time = "vermelho";
				clicou = true;
			}

			if (botao_azul.colide(x, y)) {
				Time = "azul";
				clicou = true;

			}

		} else if (action == MotionEvent.ACTION_UP) {

		} else if (action == MotionEvent.ACTION_MOVE) {

		}

		return super.onTouchEvent(event);
	}

	private void iniciaJogo() {
		bmpFundo = BitmapFactory.decodeResource(getResources(),
				R.drawable.fundo);
		bmpFundo = Bitmap.createScaledBitmap(bmpFundo, getWidth(), getHeight(),
				true);

	}

	public static boolean getClicou() {
		return clicou;
	}

	public static void setClicou(boolean clicou) {
		TelaEscolhaDetTime.clicou = clicou;
	}

}
