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
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

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
	//DadosDoCliente dadosCliente;
	
	private Bitmap imagemPlayer;
	
	protected String time;
	
	protected SpritePlayer playerSprite;
	
	// Grupo do Thyago 
	protected String nome;
	private String minhaClasse;
	//private Sprite sprite;
	public PlayerCliente(){
		
	}
	public PlayerCliente(String nome, Point position, String minhaClasse)
	{
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		this.minhaClasse = minhaClasse;
		
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
		
		//this.imagemPlayer = ImageLibrary.getInstance().getImage("Soldado");
		
		playerSprite = new SpritePlayer(minhaClasse, 1, 3);
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
	
	public void CarregarImagem(){
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Bitmap getImage(){
		return imagemPlayer;
	}
	public void setImage(Bitmap bitmap){
		this.imagemPlayer = bitmap;
	}
	
	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time = time;
	}
	
	public String toString() {
		return "Jogador [nome=" + nome + ", x=" + x + ", y=" + y + "]";
	}
	public String toStringCSV() {
		return nome + "," + x + "," + y + ";";
	}
	
	public Point getPosition() {
		return new Point(this.x, this.y);
	}
	public void setPosition(Point pos) {
	this.x = pos.x;
	this.y = pos.y;
	playerSprite.setPosition(pos);
	}

	public void sendTiro(DadosDoCliente dadosCliente, Point toque){
	}
}