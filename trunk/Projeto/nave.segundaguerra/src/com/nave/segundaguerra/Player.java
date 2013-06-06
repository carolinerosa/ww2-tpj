package com.nave.segundaguerra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;


public class Player 
{
	public PointF position = new PointF();
	public float dano;
	private PointF destinationPosition = new PointF();
	private Paint paint = new Paint();
	private float speed = 3;
	private int life;
	Tiro lastDagameTaken;
	Pontos pontos;
	
	// Grupo do Thyago 
	public static String name;
	Nickname nick;

	public Player(PointF position)
	{
		paint.setColor(Color.MAGENTA);
		
		this.position = position;
		destinationPosition = position;
		this.life = 100;
		
		name = "";
		
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
					if(lastDagameTaken.owner == this)
					{
						lastDagameTaken.owner.pontos.Perda();
					}
					else
					{
						lastDagameTaken.owner.pontos.Ganho();
					}
				}
	}
	
	
	
	public void draw(Canvas canvas)
	{
		canvas.drawCircle(position.x, position.y, 10, paint);
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
	
	public void collisionTiro(Tiro tiro)
	{
		
		life -= tiro.dano;
	}
}