package com.nave.segundaguerra.servidorecliente.cliente;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Angulator;
import com.nave.segundaguerra.game.Player;
import com.nave.segundaguerra.game.Sprite;
import com.nave.segundaguerra.game.Tiro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;


public class PlayerCliente 
{

	protected int x;
	protected int y;
	private Paint paint = new Paint();
	DadosDoCliente dadosCliente;
	
	private Bitmap imagemPlayer;
	
	
	// Grupo do Thyago 
	protected String nome;
	private Sprite sprite;
	public PlayerCliente(String nome, PointF position)
	{
		
		paint.setColor(Color.BLACK);

		this.nome = nome;
		this.x = (int) position.x;
		this.y = (int) position.y;
		
		this.imagemPlayer = GerenciadorActivity.GetInstance().getImagePlayer();
		
		
		sprite = new Sprite(this.imagemPlayer, 1, 3);
		
		
	}
	
	public void draw(Canvas canvas)
	{
		this.sprite.Update(30);
		
		if(this.getImage() != null){
			
			int tempX = this.x - this.getImage().getWidth()/10;
			int tempY = this.y - this.getImage().getHeight()/10;
			//canvas.drawCircle(x, y, 10, paint);
			this.sprite.Draw(canvas, new Rect(tempX,tempY,x+this.getImage().getWidth()/10,y+this.getImage().getHeight()/10));
			//canvas.drawBitmap(this.getImage(), tempX, tempY, paint);
		}else{
			canvas.drawCircle(x, y, 10, paint);
		}
	}
	

	public Bitmap getImage(){
		return imagemPlayer;
	}
	
	public void setContext(Context context){
		
		try {
			InputStream is = context.getAssets().open("player.png");
			imagemPlayer = BitmapFactory.decodeStream(is);
			//imagemPlayer = Bitmap.createBitmap(b, 0, 0, 10, 10);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	

	
}