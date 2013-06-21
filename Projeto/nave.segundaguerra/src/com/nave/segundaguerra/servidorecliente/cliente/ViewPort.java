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
	
	public ViewPort(JogadorCliente playerCliente){
		this.jogador = playerCliente;
		
	}
	
	public void update(){
		
		
		this.largura = this.x + this.larguraTela;
		this.altura = this.y + this.alturaTela;
		
	}
	
	public boolean checkDraw(int objetoX, int objetoY){
		if(objetoX >= x){
			return true;
		}
		
		if(objetoX <= altura){
			return true;
		}
		
		if(objetoY >= y){
			return true;
		}
		
		if(objetoY <= largura){
			return true;
		}
		
		return false;
	}
	
	public void drawInViewPort(JogadorCliente player, Canvas canvas){
		
		int oldX = player.getX();
		int oldY = player.getY();
		
		
		this.x = jogador.getX() - (larguraTela/2);
		this.y = jogador.getY() - (alturaTela/2);
		
		player.setX((oldX - x));
		player.setY((oldY - y));
		
		posPlayerDraw = new Point(oldX - x, oldY - y);
		player.draw(canvas);
		
		player.setX(oldX);
		player.setY(oldY);
		
	}
	
	public void drawInViewPort(TiroCliente tiro, Canvas canvas){
		
		PointF old = tiro.getPosition();
		PointF next = new PointF((old.x - x), (old.y - y));
		
		tiro.setPosition(next);
		tiro.DrawTiro(canvas);
		
		tiro.setPosition(old);

	}
	
	public void drawInViewPort(MapaCliente mapa, Canvas canvas)
	{
		int oldX = mapa.getX();
		int oldY = mapa.getY();
		
		mapa.setX(oldX - x);
		mapa.setY(oldY - y);
		
		mapa.Draw(canvas);
		
		mapa.setX(oldX);
		mapa.setY(oldY);
		
	}
	
	public Point getPosPlayerDraw(){
		return posPlayerDraw;
	}
	
	public void setTela(int Altura, int Largura){
		this.alturaTela = Altura;
		this.larguraTela = Largura;
	}
	
}
