package com.nave.segundaguerra.game;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RectClasse {
	
	private int x;
	private int y;
	private int height;
	private int width;
	private Paint paint;
	public RectClasse(int x, int y, int height, int width, Paint paint) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.paint = paint;
	}
	
	public void draw(Canvas canvas) 
	{
		canvas.drawRect(x,y,height,width,this.paint);
	}
	
	
	public boolean colide(int x2, int y2) {
		if (x2>x+width) return false;
		if (y2>y+height) return false;
		if (x2<x) return false;
		if (y2<y) return false;
		return true;
	}

}
