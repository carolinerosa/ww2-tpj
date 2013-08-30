package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Point;

import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class MedicoCliente extends PlayerCliente{
	
	public MedicoCliente(String nome, Point position) {
		super(nome, position, "Medico");
		//Coloquei 'Projetil' pq não há assets do Médico. por: Nuno
		
	}
	
	@Override
	public void CarregarImagem(int larguraTela, int alturaTela){
		this.setImage(ImageLibrary.getInstance().getImage("Medico"));
	}
	
	@Override
	public void sendTiro(DadosDoCliente dadosCliente, Point toque){
		dadosCliente.getCliente().write(Protocolo.PROTOCOL_SHOOT + "," + nome + "," + toque.x + "," + toque.y);
	}
	@Override
	public void setPos(DadosDoCliente dadosCliente, Point pos){
		//dadosCliente.setPos(pos);
		dadosCliente.setX(pos.x);
		dadosCliente.setY(pos.y);
	}
}
