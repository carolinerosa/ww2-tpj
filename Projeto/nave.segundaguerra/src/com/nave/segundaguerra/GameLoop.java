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
	private Player player2;
	List<Tiro> listTiro = new CopyOnWriteArrayList(); 
	
	public GameLoop(Context context, Player player) 
	{
		super(context);
		this.player = player;
		player2 = new Player(context,new PointF(200, 200), "Nuno");
		start();
	}
	
	public void start()
	{
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);		
		
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
				if(t.getPosition().y < 0 || t.getPosition().x < 0 || t.getPosition().y >= this.getHeight() || t.getPosition().x >= this.getWidth()){
					listTiro.remove(t);
					Log.i(TAG, "removeu tiro !!!");
				}
				if(t.checarColisao(this.player2)){
					player2.collisionTiro(t,20,this.getWidth(), this.getHeight());
					listTiro.remove(t);
					Log.i(TAG, "removeu tiro batendo no player!!!");
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
		
		if(player2 != null){
			player2.draw(canvas);
		}

		for(Tiro t : listTiro)
		{  
	          t.DrawTiro(canvas);
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
				destinationPosition = new PointF(event.getX(), event.getY());
				player.moveTo(destinationPosition);
				break;
				
			case MotionEvent.ACTION_MOVE:
				destinationPosition = new PointF(event.getX(), event.getY());
				player.moveTo(destinationPosition);
				break;
				
			case MotionEvent.ACTION_POINTER_2_DOWN:
				player.definirAngulo(new PointF(event.getX(), event.getY()));
				if(player.getMunicao() > 0){
					Tiro tiro = new Tiro(super.getContext(), player);
					listTiro.add(tiro);
					player.diminuirMunicao();
					Log.i("Municao", ""+player.getMunicao());
				}
				else{
					player.Recarregar();
				}
				
				break;
				
			default:
				break;
			}
		}
		
		return super.onTouchEvent(event);
	}
}
