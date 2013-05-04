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
	public static final String TAG = "Segunda Guerra";
	private boolean running = true;
	public Player player;
	
	public GameLoop(Context context) 
	{
		super(context);
		start();
	}
	
	public void start()
	{
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);
		
		player = new Player(new PointF(100, 100));
		running = true;
		
		Thread gameLoop = new Thread(this);
		gameLoop.start();
	}

	@Override
	public void run() 
	{
		Log.i(TAG, "iniciou a trhead");
		
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
		if(player != null)
		{
			player.update();
		}
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		
		if(player != null)
		{
			player.draw(canvas);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getActionMasked();
		PointF destinationPosition;
		
		if(player != null)
		{
			switch (action) 
			{
			case MotionEvent.ACTION_DOWN:
				destinationPosition = new PointF(event.getX(), event.getY());
				player.moveTo(destinationPosition);
				break;
				
			case MotionEvent.ACTION_MOVE:
				destinationPosition = new PointF(event.getX(), event.getY());
				player.moveTo(destinationPosition);
				break;
				
			default:
				break;
			}
		}
		
		return super.onTouchEvent(event);
	}
}
