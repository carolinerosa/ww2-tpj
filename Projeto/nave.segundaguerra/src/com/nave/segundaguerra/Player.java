package com.nave.segundaguerra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Player 
{
	private PointF position;
	private PointF destinationPosition;
	private Paint paint = new Paint();
	private float speed = 3;

	public Player(PointF position)
	{
		this.position = position;
		paint.setColor(Color.MAGENTA);
	}
	
	public void update()
	{
		moveTo(destinationPosition);
	}
	
	public void Draw(Canvas canvas)
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
		setPosition(destinationPosition);	
	}
}
