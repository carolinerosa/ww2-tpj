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
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class ControleDeUsuariosCliente implements DepoisDeReceberDados {

	private ConcurrentHashMap<String, JogadorCliente> jogadores;
	private List<TiroCliente> tiroList = new CopyOnWriteArrayList();

	public ConcurrentHashMap<String, JogadorCliente> getJogadores() {
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
		jogadores = new ConcurrentHashMap<String, JogadorCliente>();
		
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

			JogadorCliente jogador = jogadores.get(nome);
			if (jogador == null) {
				jogador = new JogadorCliente(nome, x, y);
				jogadores.put(nome, jogador);
			} else {
				jogador.setX(x);
				jogador.setY(y);
			}
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
		
		JogadorCliente meuJogador = GerenciadorActivity.GetInstance().getPlayer();
		JogadorCliente jogador = jogadores.get(meuJogador.getNome());
		if (jogador == null) {
			jogadores.put(meuJogador.getNome(), meuJogador);
		}
	}
}

