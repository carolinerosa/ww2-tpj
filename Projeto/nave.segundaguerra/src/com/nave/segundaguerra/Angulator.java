package com.nave.segundaguerra;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;

public class Angulator {

	public PointF player, touch, speed;
	
	public Angulator(PointF playerPosition, PointF touch) {
		this.player = playerPosition;
		this.touch = touch;
		
		speed = getSpeed();
	}


	public PointF getSpeed()
	{
		float distanciaX = touch.x - player.x;
		float distanciaY = touch.y - player.y;
		
		float divisor;

        if (distanciaX < 0)
        {
            distanciaX *= -1;
        }

        if (distanciaY < 0)
        {
            distanciaY *= -1;
        }

        if (touch.x < player.x)
        {
            distanciaX *= -1;
        }

        if (touch.y < player.y)
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