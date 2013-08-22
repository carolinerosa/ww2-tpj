package com.nave.segundaguerra.servidorecliente.cliente;

import java.io.IOException;
import java.io.InputStream;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Player;
import com.nave.segundaguerra.game.SpriteBala;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

public class TiroCliente {

	private Bitmap tiro;
	private Point posicaoTiro;
	private SpriteBala balaSprite;
	private Paint paint;
	
	public TiroCliente(Point pos){

		this.posicaoTiro = pos;
		this.tiro = ImageLibrary.getInstance().getImage("Soldado");
		balaSprite = new SpriteBala();
		balaSprite.setPosition(pos);
		
		balaSprite.setPosition(new Point((int)posicaoTiro.x, (int)posicaoTiro.y));
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		
	}
	
	public void DrawTiro(Canvas canvas){
		if(tiro != null){
			balaSprite.setPosition(new Point((int)posicaoTiro.x, (int)posicaoTiro.y));
			balaSprite.Draw(canvas);
		}else {
			canvas.drawCircle(posicaoTiro.x, posicaoTiro.y, 5, paint);
		}
		//Log.i("Tiro", "Estou no draww !!!");
	}
	
	public Point getPosition(){
		return posicaoTiro;
	}
	
	public void setPosition(Point pos){
		this.posicaoTiro = pos;
		balaSprite.setPosition(pos);
	}
	
}
