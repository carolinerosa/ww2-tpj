package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.CalculoImagem;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;

public class MapaCliente {

	private static int x = 0;
	private static int y = 0;
	private Bitmap imagemMapa;
	private Paint paint = new Paint();
	private static MapaCliente instance;
	
	public MapaCliente(){
		
		this.imagemMapa = ImageLibrary.getInstance().getImage("Cenario");
		paint.setColor(Color.BLACK);
	}
	

	public static MapaCliente getInstance(){
		if(instance == null)
		{
			instance = new MapaCliente();
		}
		return instance;
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(this.imagemMapa, x, y, paint);
	}
	
	public int getX(){
		return x;
	}

	public static void setX(int X){
		x = X;
	}
	
	public int getY(){
		return y;
	}
	
	public static void setY(int Y){
		y = Y;
	}
	
	public int getLargura(){
		return imagemMapa.getWidth();
	}
	
	public int getAltura(){
		return imagemMapa.getHeight();				
	}
	public void setImage(float width, float height){
		this.imagemMapa = CalculoImagem.getInstance().RedimensionarImagem(this.imagemMapa, width, height);
	}
	
}
