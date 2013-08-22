package com.nave.segundaguerra.game;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class SpritePlayer extends Sprite {

	private int x;
	private int y;
	private Rect rect;
	private Bitmap playerImage;

	public SpritePlayer() 
	{
		super(ImageLibrary.getInstance().getImage("Soldado"), 1, 3);
		playerImage = ImageLibrary.getInstance().getImage("Soldado");
		
		rect = RectLibrary.getInstance().getRect("Soldado");
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
		
		int tempX = this.x - this.playerImage.getWidth()/10;
		int tempY = this.y - this.playerImage.getHeight()/10;
		
		rect.set(tempX, tempY, x + this.playerImage.getWidth()/10, y + this.playerImage.getHeight()/10);
	}

}
