package com.nave.segundaguerra.servidorecliente.servidor;

import android.graphics.Point;
import android.graphics.PointF;

public class JogadorServer extends PlayerServer {
	

	public JogadorServer(String nome, int x, int y) {
				
		this.nome = nome;
		this.setPosition(new Point(x, y));
		this.respawn();
	}

	public String toString() {
		return "Jogador [nome=" + nome + ", x=" + x + ", y=" + y + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getX() {
		return  x;
	}

	public void setX(int x) {
		this.destinationPosition.x = x;
	}

	public int getY() {
		return  y;
	}

	public void setY(int y) {
		this.destinationPosition.y = y;
	}

	public String toStringCSV() {
		return nome + "," + x + "," + y + ";";
	}
	
	

}
