package com.nave.segundaguerra;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ViewTiro extends View implements Runnable {
	private static final int MAX_SPEED = 5;
	private long time = 30;
	private float cx;
	private float cy;
	Bitmap monaLisa;

	private Paint paint;
	private int iy2;
	private int ix2;
	private int w;
	private int h;
	private int dx;
	private int dy;
	List<Tiro> listTiro = new CopyOnWriteArrayList(); 
	public boolean removeu = true;
	public ViewTiro(Context context) {
		super(context);

		paint = new Paint();
		paint.setColor(Color.BLACK); 
		paint.setTextSize(20); 
		
		randomDirection();

		try {
			// imagem esta na pasta assets !!!
			// 
			InputStream is = context.getAssets().open("quadros/monalisa.png");
			monaLisa = BitmapFactory.decodeStream(is);
			if (monaLisa != null) {
				ix2 = monaLisa.getWidth() / 2;
				iy2 = monaLisa.getHeight() / 2;
			}
		} catch (IOException e) {
			Log.e(BatalhaActivity.TAG, "Erro carregando imagem");
		}

		Thread thread = new Thread(this);
		thread.start();
	}
	private void randomDirection() {
		Random r = new Random();
		dx = r.nextInt(MAX_SPEED) + 1;
		dy = r.nextInt(MAX_SPEED) + 1;
	}
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		this.w = getWidth();
		this.h = getHeight();
		cx = w / 2;
		cy = h / 2;
	}
	public void draw(Canvas canvas) {
		super.draw(canvas);

		if (monaLisa != null) {
			canvas.drawBitmap(monaLisa, cx - ix2, cy - iy2, paint);
			/*canvas.drawText("Toque para mudar", 10, 30, paint);
			canvas.drawText("dx : " + dx , 10, 60, paint);			
			canvas.drawText("dy : " + dy , 10, 90, paint);*/
		} else {
			Log.e(BatalhaActivity.TAG, "Alguem roubou a MONA LISA !!!");
		}
		
		    /*switch(removeu){ 
		   
		    case true:
			for(Tiro t : listTiro){  
		          t.DrawTiro(canvas, monaLisa, paint);

					Log.e(MainActivity.TAG, "Desenho tiro !!");
		    } removeu = false; break;
		    
		    case false : break;}*/
		for(Tiro t : listTiro){  
		          t.DrawTiro(canvas, monaLisa, paint);

					Log.e("TAG", "Desenho tiro !!");
		    }
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				Log.e(BatalhaActivity.TAG, "interrupcao do run()");
			}
			update();
			postInvalidate();
		}

	}
	private void update() {

		for(Tiro t : listTiro){
			
			if (t != null)
			{
	          t.update();
				Log.e(BatalhaActivity.TAG, "Atirou update()");
				if(t.posicaoY < 0){
					listTiro.remove(t);
					removeu = true;
				}
			}
	    }
		/*cx += dx;
		cy += dy;

		if (cx + ix2 > w || cx - ix2 < 0) {
			dx *= -1;
			cx += dx;
		}

		if (cy + iy2 > h || cy - iy2 < 0) {
			dy *= -1;
			cy += dy;
		}*/
	}
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			//randomDirection();
			Tiro tiro = new Tiro(super.getContext());
			listTiro.add(tiro);

			Log.e("TAG", "Chegou aqui hahaha !!");
			/*Tiro tiro = new Tiro(1);
			tiro.DrawTiro(, monaLisa, paint);*/
		}
		return super.onTouchEvent(event);
	}

}
