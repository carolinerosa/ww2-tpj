package com.nave.segundaguerra.servidorecliente.servidor;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.nave.segundaguerra.game.Angulator;
import com.nave.segundaguerra.game.Player;
import com.nave.segundaguerra.game.Pontos;
import com.nave.segundaguerra.game.Tiro;
import com.nave.segundaguerra.servidorecliente.cliente.DadosDoCliente;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;


public class PlayerServer 
{
	protected int x;
	protected int y;
	protected Point destinationPosition = new Point();
	private int speed = 3;
	DadosDoCliente dadosCliente;
	
	public int dano;
	private int ammo = 20;
	private int life;
	TiroServer lastDagameTaken;
	Pontos pontos;
	public Angulator anguloTiro;
	private int altura = 3;
	private int largura = 3;
	private String time = "Vermelho";
	
	// Grupo do Thyago 
	protected String nome;
	
	public static final String TAG = "Jogador";

	public PlayerServer()
	{
		this.life = 100;
		pontos = new Pontos();
		
	}
	
	public void update()
	{
		
		//if(destinationPosition.x != x || destinationPosition.y != y)
		
			//checa se est� abaixo do ponto destino no eixo x
			if(x < destinationPosition.x)
			{
				x += speed;
			}
			
			//checa se est� abaixo do ponto destino no eixo x
			if(x > destinationPosition.x)
			{
				x -= speed;
			}
			
			//checa se est� abaixo do ponto destino no eixo y
			if(y < destinationPosition.y)
			{
				y += speed;
			}
					
			//checa se est� abaixo do ponto destino no eixo y
			if(y > destinationPosition.y)
			{
				y -= speed;
			}
		
			if(life <= 0)
			{
				if(lastDagameTaken.getOwner() == this)
				{
					lastDagameTaken.getOwner().pontos.Perda();
				}
				else
				{
					lastDagameTaken.getOwner().pontos.Ganho();
				}
			}
			
			
			checkMapa();
	}
	

	public int getLife()
	{
		return life;
	}
	
	public void definirAngulo(PointF touch){
		anguloTiro = new Angulator(new PointF(x, y), touch);
	}
	
	public int getMunicao(){
		return ammo;
	}
	
	public void setPosition(Point pos)
	{
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public Point getPosition()
	{
		return new Point(x, y);
	}
	
	public int getLargura()
	{
		return largura;
	}
	
	public int getAltura()
	{
		return altura;
	}
	
	

	public Angulator getAngulo(){
		return anguloTiro;
	}
	
	public void diminuirMunicao(){
		this.ammo --;
	}
	
	public void Recarregar(){
		ammo = 20;
	}
	
	private void respawn(int largura, int altura, PlayerServer playerServer, String tag) {
		Random random = new Random();
		Point novaPosicao = new Point();
		
		if(tag == "Azul")
		{
		 novaPosicao.x = random.nextInt(largura/3);
		 novaPosicao.y = random.nextInt(altura/3);
		 playerServer.setPosition(novaPosicao);
		}
		
		if(tag == "Vermelho")
		{
		 novaPosicao.x = random.nextInt(largura/3) +  ((2*largura)/3) ;
		 novaPosicao.y = random.nextInt(altura/3) + ((2*altura)/3);
		 playerServer.setPosition(novaPosicao);
		}
	}
	
	public void collisionTiro(TiroServer tiro, int dano, int largura, int altura, String tagTime)
	{
		lastDagameTaken = tiro;
		
		if(tagTime != this.time)
		{
			life -= dano;
			
			if(life <= 0)
				respawn(largura - this.largura, altura - this.altura, this, this.time);
		}
	}
	
	
	private void checkMapa(){
		if(this.x <= MapaServer.getX()){
			this.x = MapaServer.getX();
		}
		
		if(this.y <= MapaServer.getY()){
			this.y = MapaServer.getY();
		}
		
		if(this.x >= MapaServer.getLargura()){
			this.y = MapaServer.getLargura();
		}
		
		if(this.y >= MapaServer.getAltura()){
			this.y = MapaServer.getAltura();
		}
			
			
	}
	
}