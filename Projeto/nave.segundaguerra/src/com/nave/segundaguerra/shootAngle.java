package com.nave.segundaguerra;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;

public class shootAngle extends View {

	float playerX, playerY, touchX, touchY;
	PointF speed;
	
	
	public shootAngle(Context context, float x, float y, float touchX, float touchY) {
		super(context);
		this.playerX = x;
		this.playerY = y;
		this.touchX = touchX;
		this.touchY = touchY;
	}


	public PointF getSpeed()
	{
		float tempX = touchX - playerX;
		float tempY = touchY - playerY;
		float distance = 0;
		
		//tempX = tempX*tempX;
		//tempY = tempY*tempY;
		
		
		
		/*if (tempX > tempY)
		{
			speed.x = 3;
			speed.y = tempY/tempX;
		}else
		{
			speed.y = 3;
			speed.x = tempX/tempY;
		}*/
		
		return speed;
	}
	
	
}