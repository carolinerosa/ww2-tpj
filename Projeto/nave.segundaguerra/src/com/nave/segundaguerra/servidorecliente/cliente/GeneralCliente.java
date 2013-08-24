package com.nave.segundaguerra.servidorecliente.cliente;

import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;



public class GeneralCliente extends PlayerCliente {
	//private String nome;
	 private static String minhaClasse = "Soldado";
	
	public GeneralCliente(String nome, Point position) {
		super(nome, position, minhaClasse);
		//Coloquei 'Soldado' para teste apenas. por: Nuno
	}
	
	@Override
	public void CarregarImagem(){
		this.setImage(ImageLibrary.getInstance().getImage("Soldado"));
	}
	
	@Override
	public void sendTiro(DadosDoCliente dadosCliente, Point toque){
		dadosCliente.getCliente().write(Protocolo.PROTOCOL_SHOOT + "," + nome + "," + toque.x + "," + toque.y);
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
	}*/
	
	
	

}
