package com.nave.segundaguerra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameLoop extends View implements Runnable 
{
	private static final int INTERVAL = 30;
	private static final String TAG = "Segunda Guerra";
	private boolean running = true;
	public Player player;
	
	public GameLoop(Context context) 
	{
		super(context);
		start();
	}
	
	public void start()
	{
		player = new Player(new PointF(100, 100));
	}

	@Override
	public void run() 
	{
		while (running) 
		{
			try 
			{
				Thread.sleep(INTERVAL);
			} 
			catch (InterruptedException e) 
			{
				Log.e(TAG, "main loop finished");
			}
			
			update();
			postInvalidate();
		}
	}

	private void update() 
	{
		player.update();
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		
		Log.i(TAG, "pintou");
		
		if(player != null)
		{
			player.Draw(canvas);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			PointF destinationPosition = new PointF(event.getX(), event.getY());
			player.moveTo(destinationPosition);
			
			Log.i(TAG, "pegou o toquinho boladao");
			
		}
		
		return super.onTouchEvent(event);
	}
}
