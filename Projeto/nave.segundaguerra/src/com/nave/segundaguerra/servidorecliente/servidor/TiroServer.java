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

public class TiroServer {

	private Point posicaoTiro;
	private Point velocidade;
	private PlayerServer meuDono;
	private Rect balaRect;
	
	public TiroServer(PlayerServer player, Point touch){
		
		this.posicaoTiro = player.getPosition();
		//player.definirAngulo(touch);
		this.velocidade = new Point(touch.x/15, touch.y/15);
		this.meuDono = player;
		
		balaRect = new Rect(GerenciadorActivity.GetInstance().getBalaRect());
		
	}
	
	void update(){
		posicaoTiro.x += this.velocidade.x; 
		posicaoTiro.y += this.velocidade.y;
		
		this.setPosition(posicaoTiro);
		//Log.i("Tiro", "Estou no update !!!");
	}
	
	public boolean checarColisao(ConcurrentHashMap<String, JogadorServer> jogadores){

		Iterator iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			JogadorServer jogador = jogadores.get(key);
			//Log.i("COLISAO TIRO", jogador.nome + jogador.toStringCSV());
			if(this.getOwner().getTime() != jogador.getTime())
				//Log.i("COLISAO TIRO", "Esse eh contra  " + jogador.nome);
				if(balaRect.intersect(jogador.getRect())){
					//jogador.Dano(this, 100);
					jogador.respawn();
					Log.i("COLISAO TIRO", "TOMOU   " + posicaoTiro);
					return true;
				}
		}
		return false;

	}

	private boolean Intersect(JogadorServer player){
		if(this.balaRect.centerX() > player.getRect().left && this.balaRect.centerX() < player.getRect().right &&
				this.balaRect.centerY() > player.getRect().top && this.balaRect.centerY() < player.getRect().bottom) {
			return true;
		}
		return false;
	}
	public Point getPosition(){
		return new Point(balaRect.centerX(), balaRect.centerY());
	}
	public PlayerServer getOwner(){
		return meuDono;
	}
	
	private void setPosition(Point pos) {
		int tempX = pos.x - this.balaRect.height()/2;
		int tempY = pos.y - this.balaRect.width()/2;
		
		balaRect.set(tempX, tempY, pos.x + this.balaRect.height()/2, pos.y + this.balaRect.width()/2);
	}

	public Rect getRect() {
		// TODO Auto-generated method stub
		return balaRect;
	}
	
}
