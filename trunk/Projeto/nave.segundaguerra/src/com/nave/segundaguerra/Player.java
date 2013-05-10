package com.nave.segundaguerra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;


public class Player 
{
	private PointF position = new PointF();
	private PointF destinationPosition = new PointF();
	private Paint paint = new Paint();
	private float speed = 3;
	
	// Grupo do Thyago 
	public static String name;
	Nickname nick;

	public Player(PointF position)
	{
		paint.setColor(Color.MAGENTA);
		
		this.position = position;
		destinationPosition = position;
		
		// Grupo do Thyago 
		nick = new Nickname();
		name = nick.setName();
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
}