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
		
		if(linha.startsWith(Protocolo.PROTOCOL_DAMAGE)){
			modifyDamage(origem, linha);
		}
		
		if(linha.startsWith(Protocolo.PROTOCOL_RESPAWN)){
			respawn(origem, linha);
		}
		
		if(linha.startsWith(Protocolo.PROTOCOL_SCORE))
		{
			score(origem, linha);
		}
		
		if(linha.startsWith(Protocolo.PROTOCOL_ITEM))
		{
			addItem(origem, linha);
		}
		
		if(linha.startsWith(Protocolo.PROTOCOL_ASKITEM))
		{
			askItem(origem, linha);
		}
		
		
		if(tiroList.size() > 0)
		{
			atualizaTiros(origem);
		}
	}

	private void informaTodosUsuarios(Conexao origem,tipoDeInformativo informacaoRecebida) 
	{
		StringBuffer buffer = new StringBuffer();
		Iterator iterator = jogadores.keySet().iterator();
		String protocolType = "";
		
		switch(informacaoRecebida)
		{
			case MOVEUSUARIO:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_MOVE;
					//jogador.update();
					buffer.append(jogador.toStringCSV());
				}
				
			break;
			
			
			case DAMAGE:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_DAMAGE;
					//jogador.update();
					//buffer.append(jogador.toStringCSV());
					//Send to player your life
				}
				
			break;
			
			case SCORE:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_SCORE;
					//jogador.update();
					//buffer.append(jogador.toStringCSV());
					//Send to player your score
				}
				
			break;
			
			case ADDITEM:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_ITEM;
					//jogador.update();
					//buffer.append(jogador.toStringCSV());
					//Add Item to player
				}
				
			break;
			
			case ASKITEM:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_ASKITEM;
					//jogador.update();
					//buffer.append(jogador.toStringCSV());
					//Ask item for all players
				}
				
			break;
			
			case DIE:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_DEATH;
					//jogador.update();
					//buffer.append(jogador.toStringCSV());
					//Send to player die events
				}
				
			break;
			
			case RESPAWN:
				
				while (iterator.hasNext()) 
				{
					String key = (String) iterator.next();
					JogadorServer jogador = jogadores.get(key);
					protocolType = Protocolo.PROTOCOL_RESPAWN;
					//jogador.update();
					//buffer.append(jogador.toStringCSV());
					//Respawn Player in the scenario
				}
				
			break;
		}

		origem.write(protocolType + buffer.toString());
	}

	private void moveUsuario(Conexao origem, String linha) {
		String[] array = linha.split(",");
		int x = Integer.parseInt(array[1]);
		int y = Integer.parseInt(array[2]);

		JogadorServer jogador = jogadores.get(origem.getId());
		jogador.setX(x);
		jogador.setY(y);
		
		informaTodosUsuarios(origem,tipoDeInformativo.MOVEUSUARIO);
	}
	
	private void modifyDamage(Conexao origem, String linha) 
	{
		String[] array = linha.split(",");
		int damage = Integer.parseInt(array[1]);
		JogadorServer jogador = jogadores.get(origem.getId());
		int life = jogador.getLife();
		
		//jogador.setLife = life - damage;
		
		//if(life <= 0)
		//{
			//die();
		//}
		//else
		//{
			//informaTodosUsuarios(origem,tipoDeInformativo.DAMAGE);
		//}
	}

	private void score(Conexao origem, String linha) 
	{
		String[] array = linha.split(",");
		String damage = array[1];
		JogadorServer jogador = jogadores.get(origem.getId());

		//Chama a classe do grupo do Thyago.
	}
	
	private void addItem(Conexao origem, String linha) 
	{
		String[] array = linha.split(",");
		String item = array[1];
		JogadorServer jogador = jogadores.get(origem.getId());

		//Adciona o Item ao jogador
	}
	
	private void askItem(Conexao origem, String linha) 
	{
		String[] array = linha.split(",");
		String ite = array[1];
		JogadorServer jogador = jogadores.get(origem.getId());
		//informaTodosUsuarios(origem,tipoDeInformativo.ASKITEM);
		//Pede aos jogadores um item
	}

	private void die()
	{
		//send to players that player is dead; 
		//informaTodosUsuarios(origem,tipoDeInformativo.DIE);
	}
	
 	private void respawn(Conexao origem, String linha) 
	{
		String[] array = linha.split(",");
		int x = Integer.parseInt(array[1]);
		int y = Integer.parseInt(array[2]);

		JogadorServer jogador = jogadores.get(origem.getId());
		jogador.setX(x);
		jogador.setY(y);
		informaTodosUsuarios(origem,tipoDeInformativo.RESPAWN);
	}

	private void adicionaNovoUsuario(Conexao origem, String linha) {
		String[] array = linha.split(",");
		String nome = array[1];
		int x = Integer.parseInt(array[2]);
		int y = Integer.parseInt(array[3]);

		origem.setId(nome);
		JogadorServer jogador = new JogadorServer(nome, x, y);
		jogadores.put(nome, jogador);
		
		informaTodosUsuarios(origem,tipoDeInformativo.MOVEUSUARIO);
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
	
	public enum tipoDeInformativo
	{
		MOVEUSUARIO,
		DAMAGE,
		SCORE,
		ADDITEM,
		ASKITEM,
		DIE,
		RESPAWN
	}
}
