package com.nave.segundaguerra.servidorecliente.cliente;

import java.io.IOException;
import java.io.InputStream;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class TiroCliente {

	private Bitmap tiro;
	private PointF posicaoTiro;
	
	public TiroCliente(PointF pos){

		this.posicaoTiro = pos;
		this.tiro = GerenciadorActivity.GetInstance().getImageBala(); 
	}
	
	public void DrawTiro(Canvas canvas){
		canvas.drawBitmap(tiro, posicaoTiro.x, posicaoTiro.y, new Paint());

		//Log.i("Tiro", "Estou no draww !!!");
	}
	
	public PointF getPosition(){
		return posicaoTiro;
	}
	
	public void setPosition(PointF pos){
		this.posicaoTiro = pos;
	}
	
}
