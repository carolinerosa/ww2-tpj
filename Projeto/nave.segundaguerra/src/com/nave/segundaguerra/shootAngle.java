package com.nave.segundaguerra;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;

public class ShootAngle {

	float playerX, playerY, touchX, touchY;
	public PointF speed = new PointF();
	
	
	public ShootAngle(float x, float y, float touchX, float touchY) {
		this.playerX = x;
		this.playerY = y;
		this.touchX = touchX;
		this.touchY = touchY;
		
		speed = getSpeed();
	}


	public PointF getSpeed()
	{
		float distanciaX = touchX - playerX;
		float distanciaY = touchY - playerY;
		
		float divisor;

        if (distanciaX < 0)
        {
            distanciaX *= -1;
        }

        if (distanciaY < 0)
        {
            distanciaY *= -1;
        }

        if (touchX < playerX)
        {
            distanciaX *= -1;
        }

        if (touchY < playerY)
        {
            distanciaY *= -1;
        }

        if (distanciaX > distanciaY)
        {
            divisor = distanciaX;
        }
        else
        {
            divisor = distanciaY;
        }
        


        speed.x = distanciaX / 60;
        speed.y = distanciaY / 60;
		
		return speed;
	}
	
	
}