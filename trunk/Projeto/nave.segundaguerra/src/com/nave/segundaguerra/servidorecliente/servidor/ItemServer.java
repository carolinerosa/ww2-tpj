package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class ItemServer 
{
	private Point posicaoItem;
	private Rect rectItem;
	
	public ItemServer(Point posicaoItem, String tag)
	{
		this.posicaoItem = posicaoItem;
		
		if(tag == "MunicaoArma")
		{
			rectItem = new Rect(RectLibrary.getInstance().getRect("Caixa"));
		}
	}
	public Point getPosition(){
		return this.posicaoItem;
	}
	
	// Colocar no Loop Server
	public boolean checarColisao(ConcurrentHashMap<String, PlayerServer> jogadores)
	{
		Iterator iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) 
		{
			String key = (String) iterator.next();
			PlayerServer jogador = jogadores.get(key);
			
				if(rectItem.intersect(jogador.getRect()))
				{
					Log.i("ColisaoCaixa", "Interceptou");
					jogador.Recarregar();
					Log.i("ColisaoCaixa", "Recarregou");
					return true;
				}
		}
		return false;
	}	
}
