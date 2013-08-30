package com.nave.segundaguerra.servidorecliente.servidor;

import android.graphics.Point;

public class SoldadoServer extends PlayerServer{
	
	public SoldadoServer(String nome, Point position) {
		this.nome = nome;
		this.setPosition(position);
		this.respawn();
	}
}
