package com.nave.segundaguerra.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

public class SpriteCaixa extends Sprite {

	private int x;
	private int y;
	private Rect rect;
	private Bitmap balaImage;

	public SpriteCaixa() 
	{
		super(ImageLibrary.getInstance().getImage("Caixa"), 1, 3);
		
		balaImage = ImageLibrary.getInstance().getImage("Caixa");
		
		rect = RectLibrary.getInstance().getRect("Caixa");
	
	}

	public void Draw(Canvas canvas){
		super.Draw(canvas, rect);
	}
	
	public Rect getRect(){
		return rect;
	}
	
	public Point getPosition(){
		return new Point(x, y);
	}
	
	public void setPosition(Point pos){
		this.x = pos.x;
		this.y = pos.y;
		
		int tempX = this.x;
				//- this.balaImage.getWidth()/5;
		int tempY = this.y;
		//- this.balaImage.getHeight()/5;
		
		rect.set(tempX, tempY, x + this.balaImage.getWidth()/3, y + this.balaImage.getHeight()/3);
	}
	
}