package com.nave.segundaguerra.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite
{
	private static final String TAG = "SPRITE SHEET DRAW";
	
	private Bitmap bitmap;
	private Rect selectRect;
	private int frameNr;
	private int currentFrame;
	private long spriteTime;
	private double framePeriod;
	
	private int spriteWidth;
	private int spriteHeight;
	private long cronometro;
	
	private Paint paint;
	
	public Sprite(Bitmap bitmap,int frameNr, int fps)
	{
		this.spriteWidth = bitmap.getWidth() / frameNr;
		this.spriteHeight = bitmap.getHeight();
		this.frameNr = frameNr;
		this.framePeriod = 1000/fps;
		this.spriteTime = 00;
		this.currentFrame = 0;
		this.selectRect = new Rect(0,0, spriteWidth / frameNr, spriteHeight);
		this.bitmap = bitmap;
		
		paint = new Paint();
	}
	public void setConfig(Bitmap bitmap,int frameNr, int fps){
		this.spriteWidth = bitmap.getWidth() / frameNr;
		this.spriteHeight = bitmap.getHeight();
		this.frameNr = frameNr;
		this.framePeriod = 1000/fps;
		this.selectRect = new Rect(0,0, spriteWidth / frameNr, spriteHeight);
		this.bitmap = bitmap;
	}
	
	
	public void Update(long deltaTime)
	{
		this.cronometro += deltaTime;
		
		if(cronometro >= this.spriteTime + framePeriod)
		{
			this.spriteTime = cronometro;
			
			currentFrame++;
			
			if(currentFrame >= frameNr)
			{
				currentFrame = 0;
			}
			
			this.selectRect.left = this.currentFrame * this.spriteWidth;
			this.selectRect.right = this.selectRect.left + this.spriteWidth;
		}
		
	}
	public void Draw(Canvas canvas, Rect destRect)
	{
		canvas.drawBitmap(this.bitmap, this.selectRect, destRect, paint);
	}
	
	
}
