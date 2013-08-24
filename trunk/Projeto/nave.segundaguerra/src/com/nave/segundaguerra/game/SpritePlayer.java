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

	public SpritePlayer(String nomeBitmap,int frameNr, int fps) 
	{
		super(ImageLibrary.getInstance().getImage(nomeBitmap), 1, 3);
		playerImage = ImageLibrary.getInstance().getImage(nomeBitmap);
		
		rect = RectLibrary.getInstance().getRect(nomeBitmap);
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
