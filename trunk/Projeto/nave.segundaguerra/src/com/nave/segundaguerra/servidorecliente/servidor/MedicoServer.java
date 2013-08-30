package com.nave.segundaguerra.servidorecliente.servidor;

import android.graphics.Point;

public class MedicoServer extends PlayerServer {
	
	public MedicoServer(String nome, Point position) {
		this.nome = nome;
		this.setPosition(position);
		this.respawn();
	}
}
