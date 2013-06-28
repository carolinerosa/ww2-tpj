package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.PointF;
import android.util.Log;

import com.nave.segundaguerra.game.Tiro;
import com.nave.segundaguerra.servidorecliente.cliente.TiroCliente;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.Const;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class ControleDeUsuariosServidor implements DepoisDeReceberDados {

	// notar que este tipo de hashmap eh sincronizado
	// suportando acessos de multiplos threads
	private ConcurrentHashMap<String, JogadorServer> jogadores;
	private List<TiroServer> tiroList = new CopyOnWriteArrayList();
	
	private LoopServer loop;
	
	
	public ConcurrentHashMap<String, JogadorServer> getJogadoresList(){
		return this.jogadores;
	}
	
	public List<TiroServer> getTirosList(){
		return this.tiroList;
	}
	
	public ControleDeUsuariosServidor() {
		jogadores = new ConcurrentHashMap<String, JogadorServer>();
		loop = new LoopServer(this);
	}
	
	
	
	// comandos possiveis dos clientes
	// ID,nome do usuario,x,y
	// MOVE,x,y

	public void execute(Conexao origem, String linha) {

		Log.i(Const.TAG, "<<" + linha);

		if (linha.startsWith(Protocolo.PROTOCOL_ID)) {
			adicionaNovoUsuario(origem, linha);
		}
		
		if (linha.startsWith(Protocolo.PROTOCOL_MOVE)) {
			moveUsuario(origem, linha);
		}
		
		if(linha.startsWith(Protocolo.PROTOCOL_SHOOT)){
			adicionaTiro(origem, linha);
		}
		
		informaTodosUsuarios(origem);
		
		if(tiroList.size() > 0)
		{
			atualizaTiros(origem);
		}
	}

	private void informaTodosUsuarios(Conexao origem) {

		StringBuffer buffer = new StringBuffer();
		Iterator iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			JogadorServer jogador = jogadores.get(key);
			//jogador.update();
			buffer.append(jogador.toStringCSV());
		}

		origem.write(Protocolo.PROTOCOL_MOVE + buffer.toString());
	}

	private void moveUsuario(Conexao origem, String linha) {
		String[] array = linha.split(",");
		int x = Integer.parseInt(array[1]);
		int y = Integer.parseInt(array[2]);

		JogadorServer jogador = jogadores.get(origem.getId());
		jogador.setX(x);
		jogador.setY(y);
	}

	private void adicionaNovoUsuario(Conexao origem, String linha) {
		String[] array = linha.split(",");
		String nome = array[1];
		int x = Integer.parseInt(array[2]);
		int y = Integer.parseInt(array[3]);

		origem.setId(nome);
		JogadorServer jogador = new JogadorServer(nome, x, y);
		jogadores.put(nome, jogador);
	}
	
	private void adicionaTiro(Conexao origem, String linha){
		String[] array = linha.split(",");
		String nome = array[1];
		int x = Integer.parseInt(array[2]);
		int y = Integer.parseInt(array[3]);
		
		
		tiroList.add(new TiroServer(jogadores.get(nome), new PointF(x, y)));
		
		atualizaTiros(origem);
		
	}
	
	private void atualizaTiros(Conexao origem) {

		StringBuffer buffer = new StringBuffer();
		
		for(TiroServer t : tiroList){
			
			int x = (int) t.getPosition().x;
			int y = (int) t.getPosition().y;
			String tiroToString = x + "," + y + ";";
			
			buffer.append(tiroToString);
		}

		origem.write(Protocolo.PROTOCOL_SHOOT + buffer.toString());
	}

}