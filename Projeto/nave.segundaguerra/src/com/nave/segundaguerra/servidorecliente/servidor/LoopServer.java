package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.nave.segundaguerra.game.Tiro;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

import android.util.Log;

public class LoopServer implements Runnable, Killable
{
	private static final int INTERVAL = 30;
	public static final String TAG = "Segunda Guerra";
	private boolean running = true;
	
	private ControleDeUsuariosServidor servidor;
	private ConcurrentHashMap<String, PlayerServer> jogadores;
	private List<TiroServer> tiroList = new CopyOnWriteArrayList();
	
	
	public LoopServer(ControleDeUsuariosServidor servidor)
	{
		this.servidor = servidor;
		start();
	}
	
	public void start()
	{
		running = true;
		
		this.jogadores = servidor.getJogadoresList();
		this.tiroList = servidor.getTirosList();
		
		Thread loopServer = new Thread(this);
		loopServer.start();
	}

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
		}
	}

	private void update()
	{
		for(TiroServer t : tiroList)
		{
			t.update();
			
			if(t.checarColisao(jogadores) || t.offsetScreen())
			{
				tiroList.remove(t);
				
				Log.i("Tiro Server", "Tiro Removido");
				
				servidor.setListTiros(tiroList);
			}
	    }
		
		Iterator iterator = jogadores.keySet().iterator();
		
		while (iterator.hasNext())
		{
			String key = (String) iterator.next();
			PlayerServer jogador = jogadores.get(key);
			jogador.update();
		}
		
		this.jogadores = servidor.getJogadoresList();
		this.tiroList = servidor.getTirosList();
				
	}

	public void killMeSoftly() {
		running = false;
		
	}
	
	
}
