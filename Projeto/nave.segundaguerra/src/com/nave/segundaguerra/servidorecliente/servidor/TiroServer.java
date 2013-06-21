package com.nave.segundaguerra.servidorecliente.servidor;

import android.content.Context;
import android.graphics.PointF;

import com.nave.segundaguerra.game.Player;

public class TiroServer {

	private float posicaoInicialY;
	private PointF posicaoPersonagem;
	private PointF posicaoTiro;
	private PointF velocidade;
	private PlayerServer meuDono;
	
	public TiroServer(PlayerServer player, PointF touch){
		
		this.posicaoTiro = new PointF(player.getPosition().x + player.getLargura() / 2, player.getPosition().y + player.getAltura() / 2);
		player.definirAngulo(touch);
		this.velocidade = new PointF(touch.x/15, touch.y/15);
		this.meuDono = player;
		
		
	}
	
	void update(){
		posicaoTiro.x += this.velocidade.x; 
		posicaoTiro.y += this.velocidade.y;
		//Log.i("Tiro", "Estou no update !!!");
	}
	
	public boolean checarColisao(Player player){
		if(this.posicaoTiro.x >= player.getPosition().x && this.posicaoTiro.x < (player.getPosition().x + player.getImage().getWidth()) && 
		this.posicaoTiro.y >= player.getPosition().y && this.posicaoTiro.y < (player.getPosition().y + player.getImage().getHeight())){
			return true;
		}
		return false;
	}
	public PointF getPosition(){
		return posicaoTiro;
	}
	public PlayerServer getOwner(){
		return meuDono;
	}
	
}
