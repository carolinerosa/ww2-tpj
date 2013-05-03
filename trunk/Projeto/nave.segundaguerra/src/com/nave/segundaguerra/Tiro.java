package com.example.tiro;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Tiro {
	Bitmap tiro;
	private float posicaoInicialY;
	float velocidadeTiro;
	float posicaoY, posicaoX;
	public Tiro(Context context, float velocidade){
		this.velocidadeTiro = velocidade;
		try {
			InputStream is = context.getAssets().open("quadros/monalisa.png");
			tiro = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e(MainActivity.TAG, "Erro carregando imagem");
		}
	}
	public void DrawTiro(Canvas canvas, Bitmap minhaArma, Paint paint){
		posicaoX = minhaArma.getWidth() + 15;
		posicaoY = minhaArma.getHeight() / 2 - posicaoInicialY;
		canvas.drawBitmap(tiro, posicaoX, posicaoY, paint);
	}
	void update(){
		posicaoInicialY += velocidadeTiro;
	}
}
