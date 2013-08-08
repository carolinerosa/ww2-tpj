package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.activitys.views.ViewDeRede;

public class ViewPort {

	int x, y;
	int altura, largura;
	int alturaTela, larguraTela;
	JogadorCliente jogador;
	Point posPlayerDraw;

	private boolean SeguirPlayerX = false;
	private boolean SeguirPlayerY = false;
	private Paint paint;
	
	Point posMapa;
	int mapaVerifyContX = 0;
	int mapaVerifyContY = 0;
	MapaCliente map;
	
	public ViewPort(JogadorCliente playerCliente){
		this.jogador = playerCliente;
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		
		int oldX = MapaCliente.getInstance().getX();
		int oldY = MapaCliente.getInstance().getY();
		
		this.posMapa = new Point(oldX, oldY);
		map = MapaCliente.getInstance();
	}
	
	public void update(){
		
		this.largura = this.x + this.larguraTela;
		this.altura = this.y + this.alturaTela;
		
		checkMapa();
	}
	
	public boolean checkDraw(int X, int Y){
		if(X >= x){
			return true;
		}
		
		if(X <= altura){
			return true;
		}
		
		if(Y >= y){
			return true;
		}
		
		if(Y <= largura){
			return true;
		}
		
		return false;
	}
	
	public void drawInViewPort(JogadorCliente player, Canvas canvas){
		
		int oldX = player.getX();
		int oldY = player.getY();
		
		//Acompanha o Player
		if(SeguirPlayerX)
		{
			this.x = jogador.getX() - (larguraTela/2);
			
		}
		if(SeguirPlayerY)
		{
			this.y = jogador.getY() - (alturaTela/2);
		}
		
		if(player.nome == jogador.nome)
		{
			posPlayerDraw = new Point(oldX - x, oldY - y);
		}
		
		if(this.checkDraw(oldX - x, oldY - y))
		{
			player.setX((oldX - x));
			player.setY((oldY - y));
			
			
			player.draw(canvas);
			
		}
		
		player.setX(oldX);
		player.setY(oldY);
		
	}
	
	public void drawInViewPort(TiroCliente tiro, Canvas canvas){
		
		Point old = tiro.getPosition();
		Point next = new Point((old.x - x), (old.y - y));
		
		if(this.checkDraw(next.x, next.y))
		{
			tiro.setPosition(next);
			tiro.DrawTiro(canvas);
		}
		
		tiro.setPosition(old);

	}
	
	public void drawInViewPort(MapaCliente mapa, Canvas canvas)
	{
		int oldX = MapaCliente.getInstance().getX();
		int oldY = MapaCliente.getInstance().getY();
		
		MapaCliente.setX(oldX - x);
		MapaCliente.setY(oldY - y);
		
		MapaCliente.getInstance().Draw(canvas);
		
		MapaCliente.setX(oldX);
		MapaCliente.setY(oldY);
		
	}
	
	public Point getPosPlayerDraw(){
		return posPlayerDraw;
	}
	
	public void setTela(int Altura, int Largura){
		this.alturaTela = Altura;
		this.larguraTela = Largura;
	}
	
public void checkMapa(){
		
		
		int centroTelaX = larguraTela/2;
		int centroTelaY = alturaTela/2;
		
		
		if(this.x <= posMapa.x){
			SeguirPlayerX = false;
			if(mapaVerifyContX == 0){
				this.x = posMapa.x;
				mapaVerifyContX = 1;
			}
			if(posPlayerDraw.x > centroTelaX){
			SeguirPlayerX =true;
			}
		}else{
			mapaVerifyContX = 0;
		}
		
		if(this.y <= posMapa.y){
			SeguirPlayerY = false;
			if(mapaVerifyContY == 0){
				this.y = posMapa.y;
				mapaVerifyContY = 1;
			}
			if(posPlayerDraw.y > centroTelaY){
			SeguirPlayerY = true;
			}
		}else{
			mapaVerifyContY = 0;
		}
		
		if(this.altura >=  map.getAltura()){
			SeguirPlayerY = false;
			if(mapaVerifyContY == 0){
				this.y = map.getAltura() - alturaTela;
				mapaVerifyContY = 1;
			}
			if(posPlayerDraw.y < centroTelaY){
			SeguirPlayerY =true;
			}
		}else{
			mapaVerifyContY = 0;
		}
		
		if(this.largura >= map.getLargura()){
			SeguirPlayerX = false;
			if(mapaVerifyContX == 0){
				this.x = map.getLargura() - larguraTela;
				mapaVerifyContX = 1;
			}
			if(posPlayerDraw.x < centroTelaX){
			SeguirPlayerX =true;
			}
		}else{
			mapaVerifyContX = 0;
		}
		
	}
	
}
