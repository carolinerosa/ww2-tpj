package com.nave.segundaguerra;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class Tiro {
	Bitmap tiro;
	shootAngle shooter;
	private float posicaoInicialY;
	float velocidadeX;
	float velocidadeY;
	float posicaoY, posicaoX;
	
	public Tiro(Context context, PointF pos, float touchX, float touchY){
		shooter = new shootAngle(context, pos.x, pos.y, touchX, touchY);
		velocidadeX = shooter.getSpeed().x;
		velocidadeY = shooter.getSpeed().y;
		try {
			InputStream is = context.getAssets().open("projetil.png");
			tiro = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	public void DrawTiro(Canvas canvas, float posx, float posy){
		posicaoX = posx + 15;
		posicaoY = posy / 2;
		canvas.drawBitmap(tiro, posicaoX, posicaoY, new Paint());
	}
	void update(){
		posicaoX += velocidadeX;
		posicaoY += velocidadeY;
	}
}
