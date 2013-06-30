package com.nave.segundaguerra.servidorecliente.cliente;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Angulator;
import com.nave.segundaguerra.game.Player;
import com.nave.segundaguerra.game.Sprite;
import com.nave.segundaguerra.game.SpritePlayer;
import com.nave.segundaguerra.game.Tiro;
import com.nave.segundaguerra.servidorecliente.util.Const;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;


public class PlayerCliente 
{

	protected int x;
	protected int y;
	private Paint paint;
	DadosDoCliente dadosCliente;
	
	private Bitmap imagemPlayer;
	
	protected String time;
	
	protected SpritePlayer playerSprite;
	
	// Grupo do Thyago 
	protected String nome;
	//private Sprite sprite;
	
	public PlayerCliente(String nome, Point position)
	{
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		
		if(time == Const.TIMEVERMELHO)
		{
			paint.setColor(Color.RED);
		}else 
			if(time == Const.TIMEAZUL)
		{
			paint.setColor(Color.BLUE);
		}else{
			paint.setColor(Color.WHITE);
		}
		this.nome = nome;
		this.x = (int) position.x;
		this.y = (int) position.y;
		
		this.imagemPlayer = GerenciadorActivity.GetInstance().getImagePlayer();
		
		playerSprite = new SpritePlayer();
		//sprite = new Sprite(this.imagemPlayer, 1, 3);
		
	}
	
	public void draw(Canvas canvas)
	{
		
		if(time == Const.TIMEVERMELHO)
		{
			paint.setColor(Color.RED);
		}else 
			if(time == Const.TIMEAZUL)
		{
			paint.setColor(Color.BLUE);
		}else{
			paint.setColor(Color.BLACK);
		}
		
		//this.sprite.Update(30);
		playerSprite.Update(30);
		
		if(this.getImage() != null){
			
			
			
			//int tempX = this.x - this.getImage().getWidth()/10;
			//int tempY = this.y - this.getImage().getHeight()/10;
			//canvas.drawCircle(x, y, 10, paint);
			//this.sprite.Draw(canvas, new Rect(tempX,tempY,x+this.getImage().getWidth()/10,y+this.getImage().getHeight()/10));
			//canvas.drawBitmap(this.getImage(), tempX, tempY, paint);
			
			playerSprite.Draw(canvas);
			
		}else{
			canvas.drawCircle(x, y, 10, paint);
		}
		
		canvas.drawText("<" + nome + ">", x - 30
				, y + 30, paint);
		
	}
	

	public Bitmap getImage(){
		return imagemPlayer;
	}
	
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public void setPosition(Point pos) {
	this.x = pos.x;
	this.y = pos.y;
	playerSprite.setPosition(pos);
	}

	
}