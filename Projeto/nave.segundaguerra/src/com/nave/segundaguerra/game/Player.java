package com.nave.segundaguerra.game;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;


public class Player 
{
	public PointF position = new PointF();
	public float dano;
	private PointF destinationPosition = new PointF();
	private Paint paint = new Paint();
	private Bitmap imagemPlayer;
	private float speed = 3;
	private int ammo = 20;
	private int life;
	Tiro lastDagameTaken;
	Pontos pontos;
	public Angulator anguloTiro;
	
	// Grupo do Thyago 
	public String name;

	public Player(Context context, PointF position, String name)
	{
		paint.setColor(Color.MAGENTA);
		
		this.position = position;
		destinationPosition = position;
		this.life = 100;
		try {
			InputStream is = context.getAssets().open("player.png");
			imagemPlayer = BitmapFactory.decodeStream(is);
			//imagemPlayer = Bitmap.createBitmap(b, 0, 0, 10, 10);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
		this.name = name;
		
		pontos = new Pontos();
	}
	
	public void update()
	{
		moveTo(destinationPosition);
		
		//checa se está abaixo do ponto destino no eixo x
				if(position.x < destinationPosition.x)
				{
					position.x += speed;
				}
				
				//checa se está abaixo do ponto destino no eixo x
				if(position.x > destinationPosition.x)
				{
					position.x -= speed;
				}
				
				//checa se está abaixo do ponto destino no eixo y
				if(position.y < destinationPosition.y)
				{
					position.y += speed;
				}
						
				//checa se está abaixo do ponto destino no eixo y
				if(position.y > destinationPosition.y)
				{
					position.y -= speed;
				}
				
				//morreu, morreu, MORREU!
				if(life <= 0)
				{
					if(lastDagameTaken.getOwner() == this)
					{
						lastDagameTaken.getOwner().pontos.Perda();
					}
					else
					{
						lastDagameTaken.getOwner().pontos.Ganho();
					}
				}
	}
	
	
	
	public void draw(Canvas canvas)
	{
		//canvas.drawCircle(position.x, position.y, 10, paint);
		canvas.drawBitmap(this.getImage(), position.x, position.y, paint);
	}
	
	public void setPosition(PointF position)
	{
		this.position = position;
	}

	public void moveTo(PointF destinationPosition) 
	{
		this.destinationPosition = destinationPosition;
	}
	
	public int getLife()
	{
		return life;
	}
	public void definirAngulo(PointF touch){
		anguloTiro = new Angulator(getPosition(), touch);
	}
	public int getMunicao(){
		return ammo;
	}
	
	public PointF getPosition()
	{
		return position;
	}
	
	public String getName()
	{
		return name;
	}
	public Bitmap getImage(){
		return imagemPlayer;
	}
	
	public Angulator getAngulo(){
		return anguloTiro;
	}
	public void diminuirMunicao(){
		this.ammo --;
	}
	public void Recarregar(){
		ammo = 20;
	}
	private void respawn(int largura, int altura, Player player) {
		Random random = new Random();
		PointF novaPosicao = new PointF();
		novaPosicao.x = random.nextInt(largura);
		novaPosicao.y = random.nextInt(altura);
		player.setPosition(novaPosicao);
	}
	public void collisionTiro(Tiro tiro,int dano, int largura, int altura)
	{
		lastDagameTaken = tiro;
		life -= dano;
		
		if(life <= 0)
		respawn(largura - this.getImage().getWidth(), altura - this.getImage().getHeight(),this);
	}
}