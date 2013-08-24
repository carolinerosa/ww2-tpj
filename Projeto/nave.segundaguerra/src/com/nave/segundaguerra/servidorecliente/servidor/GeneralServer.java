package com.nave.segundaguerra.servidorecliente.servidor;

import android.graphics.Point;
import android.graphics.PointF;

public class GeneralServer extends PlayerServer {
	

	public GeneralServer(String nome, Point position) {
				
		this.nome = nome;
		this.setPosition(position);
		this.respawn();
	}

	/*public String toString() {
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
	}*/
	
	

}
