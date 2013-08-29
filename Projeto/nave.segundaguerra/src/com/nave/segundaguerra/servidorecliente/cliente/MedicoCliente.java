package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Point;

import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class MedicoCliente extends PlayerCliente{
	
	public MedicoCliente(String nome, Point position) {
		super(nome, position, "Soldado");
		//Coloquei 'Projetil' pq não há assets do Médico. por: Nuno
		
	}
	
	@Override
	public void CarregarImagem(){
		this.setImage(ImageLibrary.getInstance().getImage("Projetil"));
	}
	
	@Override
	public void sendTiro(DadosDoCliente dadosCliente, Point toque){
		dadosCliente.getCliente().write(Protocolo.PROTOCOL_SHOOT + "," + nome + "," + toque.x + "," + toque.y);
	}
}
