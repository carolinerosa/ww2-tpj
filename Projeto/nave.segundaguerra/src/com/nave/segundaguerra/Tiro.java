package com.nave.segundaguerra;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

public class Tiro {
	Bitmap tiro;
	shootAngle shooter;
	private float posicaoInicialY;
	PointF posicaoPersonagem;
	float velocidadeX;
	float velocidadeY;
	float posicaoY, posicaoX, posicaoAtualizadaX, posicaoAtualizadaY;
	
	public Tiro(Context context, PointF pos, float touchX, float touchY){
		shooter = new shootAngle(context, pos.x, pos.y, touchX, touchY);
		posicaoPersonagem = pos;
		this.velocidadeX = 5;
		this.velocidadeY = 10;
		try {
			InputStream is = context.getAssets().open("projetil.png");
			tiro = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	public void DrawTiro(Canvas canvas, PointF posicaoPersonagem){
		posicaoX = posicaoPersonagem.x + posicaoAtualizadaX;
		posicaoY = posicaoPersonagem.y + posicaoAtualizadaY;
		canvas.drawBitmap(tiro, posicaoX, posicaoY, new Paint());

		Log.i("Tiro", "Estou no draww !!!");
	}
	void update(){
		posicaoAtualizadaX += 5;
		posicaoAtualizadaY += 0;
		Log.i("Tiro", "Estou no update !!!");
	}
}
