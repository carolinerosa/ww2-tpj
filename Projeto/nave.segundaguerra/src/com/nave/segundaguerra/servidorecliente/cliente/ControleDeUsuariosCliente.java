package com.nave.segundaguerra.servidorecliente.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.servidorecliente.servidor.PlayerServer;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class ControleDeUsuariosCliente implements DepoisDeReceberDados {

	private ConcurrentHashMap<String, PlayerCliente> jogadores;
	private List<TiroCliente> tiroList = new CopyOnWriteArrayList();

	public ConcurrentHashMap<String, PlayerCliente> getJogadores() {
		return jogadores;
	}
	public List<TiroCliente> getListTiros(){
		return this.tiroList;
	}
	
	public void setListTiros(List<TiroCliente> list)
	{
		this.tiroList = list;
	}

	public ControleDeUsuariosCliente() {
		jogadores = new ConcurrentHashMap<String, PlayerCliente>();
		
	}


	public void execute(Conexao origem, String linha) {
		
		Log.e("ReciboCliente", linha);
		
		
		if (linha.startsWith(Protocolo.PROTOCOL_MOVE)) {
			
			String[] lista = linha.split(":");
			moveUsuario(origem, lista[1]);
		}
		
		if (linha.startsWith(Protocolo.PROTOCOL_SHOOT)) {
			
			String[] lista = linha.split(":");
			moveTiros(origem, lista[1]);
		}
	}
	
	// recebe do servidor no formato : nome,x,y;nome,x,y
	public void moveUsuario(Conexao origem, String linha) {
		String[] lista = linha.split(";");
		for (String um : lista) {
			String[] separado = um.split(",");
			String nome = separado[0];
			int x = Integer.parseInt(separado[1]);
			int y = Integer.parseInt(separado[2]);
			String classe = separado[3];
			String time = separado[4];
			
			PlayerCliente jogador = jogadores.get(nome);
			if (jogador == null) {
				//jogador = new SoldadoCliente(nome, new Point(x, y));
				selecionarPlayerCliente(classe, nome, new Point(x, y));
				jogador.setTime(time);
				jogadores.put(nome, jogador);
			} else {
				//jogador.setX(x);
				//jogador.setY(y);
				jogador.setPosition(new Point(x,y));
			}
		}
	}
	private PlayerCliente selecionarPlayerCliente(String classe, String nome, Point position){
		
		if (classe == "General") {
		GeneralCliente general = new GeneralCliente(nome, position);
		return general;
		}
		else if(classe == "Soldado"){
		SoldadoCliente soldado = new SoldadoCliente(nome, position);
		return soldado;
		}
		else{
		MedicoCliente medico = new MedicoCliente(nome, position);
		return medico;
		}
	}
	public void moveTiros(Conexao origem, String linha){
		
		tiroList.clear();
		
		String[] lista = linha.split(";");
		int i = 0;
		for (String um : lista) {
			String[] separado = um.split(",");
			int x = Integer.parseInt(separado[0]);
			
			int y = Integer.parseInt(separado[1]);
			
			
			tiroList.add(i, new TiroCliente(new Point(x, y)));
			i++;
		}
	}
	
	
	public void iniciarJogo(){
		
		PlayerCliente meuJogador = GerenciadorActivity.GetInstance().getPlayer();
		PlayerCliente jogador = jogadores.get(meuJogador.getNome());
		if (jogador == null) {
			jogadores.put(meuJogador.getNome(), meuJogador);
		}
	}
}

