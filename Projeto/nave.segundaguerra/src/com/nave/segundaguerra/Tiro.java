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
	Angulator shooter;
	private float posicaoInicialY;
	PointF posicaoPersonagem;
	PointF posicaoTiro;
	PointF velocidade;
	public float dano;
	public Player owner;
	
	public Tiro(Context context, PointF pos, float touchX, float touchY, float dano, Player owner){
		shooter = new Angulator(context, pos.x, pos.y, touchX, touchY);
		this.posicaoTiro = new PointF(pos.x, pos.y);
		this.velocidade = new PointF(5,0);
		this.dano = dano;
		this.owner = owner;
		
		try {
			InputStream is = context.getAssets().open("projetil.png");
			tiro = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	public void DrawTiro(Canvas canvas){
		canvas.drawBitmap(tiro, posicaoTiro.x, posicaoTiro.y, new Paint());

		//Log.i("Tiro", "Estou no draww !!!");
	}
	void update(){
		posicaoTiro.x += this.velocidade.x; 
		posicaoTiro.y += this.velocidade.y;
		//Log.i("Tiro", "Estou no update !!!");
	}
	void shotAngle()
	{
		
	}
}
