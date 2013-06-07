package com.nave.segundaguerra;

import android.graphics.PointF;

public class Angulator {

	public PointF player, touch, speed;
	
	public Angulator(PointF playerPosition, PointF touch) {
		this.player = playerPosition;
		this.touch = touch;
		setSpeed();
	}


	public void setSpeed()
	{
		float distanciaX = touch.x - player.x;
		float distanciaY = touch.y - player.y;

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
        speed = new PointF(distanciaX / 15,distanciaY / 15);
	}
	public PointF getSpeed(){
		return speed;
	}
	
	
}