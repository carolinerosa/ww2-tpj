package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Point;

import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class SoldadoCliente extends PlayerCliente{
	
	public SoldadoCliente(String nome, Point position) {
		super(nome, position, "Soldado");
		
	}
	
	@Override
	public void CarregarImagem(){
		this.setImage(ImageLibrary.getInstance().getImage("Soldado"));
	}
	
	@Override
	public void sendTiro(DadosDoCliente dadosCliente, Point toque){
		dadosCliente.getCliente().write(Protocolo.PROTOCOL_SHOOT + "," + nome + "," + toque.x + "," + toque.y);
	}
}
