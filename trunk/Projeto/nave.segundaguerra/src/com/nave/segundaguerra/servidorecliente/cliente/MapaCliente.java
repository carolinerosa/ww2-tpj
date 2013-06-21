package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.nave.segundaguerra.activitys.GerenciadorActivity;

public class MapaCliente {

	private int x = 0;
	private int y = 0;
	private Bitmap imagemMapa;
	private int Largura;
	private int Altura;
	private Paint paint = new Paint();
	
	public MapaCliente(){
		
		this.imagemMapa = GerenciadorActivity.GetInstance().getImageMapa();
		paint.setColor(Color.BLACK);
	}
	
	
	public void update(){
		this.Largura = this.imagemMapa.getWidth() + x;
		this.Altura = this.imagemMapa.getHeight() + y;
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(this.imagemMapa, x, y, paint);
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getLargura(){
		return Largura;
	}
	
	public int getAltura(){
		return Altura;				
	}
	
}
