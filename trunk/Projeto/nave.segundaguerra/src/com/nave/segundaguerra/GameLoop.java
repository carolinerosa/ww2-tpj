package com.nave.segundaguerra;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	List<Tiro> listTiro = new CopyOnWriteArrayList(); 
	
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

		for(Tiro t : listTiro){
			t.update();
				if(t.posicaoY < 0 || t.posicaoX < 0 || t.posicaoY > this.getHeight() || t.posicaoX > this.getWidth()){
					listTiro.remove(t);
					Log.i(TAG, "removeu tiro !!!");
				}
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

		for(Tiro t : listTiro)
		{  
	          t.DrawTiro(canvas,player.position);
	    }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
		PointF destinationPosition;
		
		if(player != null)
		{
			switch (action) 
			{
			case MotionEvent.ACTION_DOWN:
				/*destinationPosition = new PointF(event.getX(), event.getY());
				player.moveTo(destinationPosition);*/
				Tiro tiro = new Tiro(super.getContext(), player.position, event.getX(), event.getY());
				listTiro.add(tiro);
				break;
				
			case MotionEvent.ACTION_MOVE:
				destinationPosition = new PointF(event.getX(), event.getY());
				player.moveTo(destinationPosition);
				break;
				
			case MotionEvent.ACTION_POINTER_2_DOWN:
				/*Tiro tiro = new Tiro(super.getContext(), player.position, event.getX(), event.getY());
				listTiro.add(tiro);*/
				
				break;
				
			default:
				break;
			}
		}
		
		return super.onTouchEvent(event);
	}
}
