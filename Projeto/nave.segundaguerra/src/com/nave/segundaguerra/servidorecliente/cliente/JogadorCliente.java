package com.nave.segundaguerra.servidorecliente.cliente;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;



public class JogadorCliente extends PlayerCliente {
	//private String nome;

	public JogadorCliente(String nome, int x, int y) {
		super(nome, new Point(x, y));
		
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
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.setPosition(new Point(x, this.y));
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.setPosition(new Point(this.x, y));
	}

	public String toStringCSV() {
		return nome + "," + x + "," + y + ";";
	}
	
	
	

}
