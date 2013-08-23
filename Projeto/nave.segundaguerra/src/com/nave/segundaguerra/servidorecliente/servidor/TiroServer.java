package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Player;
import com.nave.segundaguerra.servidorecliente.util.DialogHelper;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

public class TiroServer 
{
	private Point posicaoTiro;
	private Point velocidade;
	private PlayerServer meuDono;
	private Rect balaRect;
	
	public TiroServer(PlayerServer player, Point touch)
	{
		if(player == null)
			Log.e("PlayerMissing", "Player Missing");
		
		this.posicaoTiro = player.getPosition();
		
		this.velocidade = new Point(touch.x/15, touch.y/15);
		this.meuDono = player;
		
		balaRect = RectLibrary.getInstance().getRect("Projetil");
		
	}
	
	void update()
	{
		posicaoTiro.x += this.velocidade.x; 
		posicaoTiro.y += this.velocidade.y;

		this.setPosition(posicaoTiro);
	}
	
	public boolean offsetScreen()
	{
		if(posicaoTiro.x > MapaServer.getLargura() || posicaoTiro.y > MapaServer.getAltura() || posicaoTiro.x < 0 || posicaoTiro.y < 0)
			return true;
		
		return false;
	}
	
	public boolean checarColisao(ConcurrentHashMap<String, JogadorServer> jogadores)
	{

		Iterator iterator = jogadores.keySet().iterator();
		
		while (iterator.hasNext()) 
		{
			String key = (String) iterator.next();
			JogadorServer jogador = jogadores.get(key);
			
			if(this.getOwner().getTime() != jogador.getTime())
			{
				if(balaRect.intersect(jogador.getRect()))
				{
					jogador.respawn();
					Log.i("COLISAO TIRO", "TOMOU   " + posicaoTiro);
					return true;
				}
			}
		}
		return false;

	}

	private boolean Intersect(JogadorServer player)
	{
		if(this.balaRect.intersect(player.getRect()))
			return true;
		
//		
//		if(this.balaRect.centerX() > player.getRect().left && this.balaRect.centerX() < player.getRect().right &&
//				this.balaRect.centerY() > player.getRect().top && this.balaRect.centerY() < player.getRect().bottom) {
//			return true;
//		}
		
		return false;
	}
	public Point getPosition()
	{
		return new Point(balaRect.centerX(), balaRect.centerY());
	}
	
	public PlayerServer getOwner()
	{
		return meuDono;
	}
	
	private void setPosition(Point pos) 
	{
		int tempX = pos.x - this.balaRect.height()/2;
		int tempY = pos.y - this.balaRect.width()/2;
		
		balaRect.set(tempX, tempY, pos.x + this.balaRect.height()/2, pos.y + this.balaRect.width()/2);
	}

	public Rect getRect() 
	{
		return balaRect;
	}
	
}
