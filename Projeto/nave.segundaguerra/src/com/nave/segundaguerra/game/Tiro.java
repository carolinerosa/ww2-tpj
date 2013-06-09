package com.nave.segundaguerra.game;

import java.io.IOException;
import java.io.InputStream;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class Tiro {
	private Bitmap tiro;
	//private String meuDono;
	private float posicaoInicialY;
	private PointF posicaoPersonagem;
	private PointF posicaoTiro;
	private PointF velocidade;
	private Player meuDono;
	
	public Tiro(Context context, Player player){
		this.posicaoTiro = new PointF(player.getPosition().x + player.getImage().getWidth() / 2, player.getPosition().y + player.getImage().getHeight() / 2);
		this.velocidade = player.getAngulo().getSpeed();
		this.meuDono = player;
		
		try {
			InputStream is = context.getAssets().open("projetil.png");
			tiro = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
	}
	public void DrawTiro(Canvas canvas){
		canvas.drawBitmap(tiro, posicaoTiro.x, posicaoTiro.y, new Paint());

		//Log.i("Tiro", "Estou no draww !!!");
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
	public Player getOwner(){
		return meuDono;
	}
	/*public void Resize(int tamanhoTela, int porcentagem){
		int larguraTiro = tiro.getWidth();
		int alturaTiro = tiro.getHeight();
		float novaLarguraTiro;
		float novaAlturaTiro;
		
		novaLarguraTiro = porcentagem * tamanhoTela / 100;
		
		novaAlturaTiro = (alturaTiro * novaLarguraTiro) / larguraTiro;
		novoTiro = Bitmap.createBitmap(tiro,0, 0,(int) novaLarguraTiro, (int) novaAlturaTiro);
		//Bitmap.createBitmap(source, x, y, width, height);
		}*/
}
