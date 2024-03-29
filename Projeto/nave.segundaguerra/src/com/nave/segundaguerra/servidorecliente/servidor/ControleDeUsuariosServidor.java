package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Point;
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
	
	private ConcurrentHashMap<String, PlayerServer> jogadores;
	private List<TiroServer> tiroList = new CopyOnWriteArrayList();
	private List<ItemServer> itemList = new CopyOnWriteArrayList();
	
	private LoopServer loop;
	
	
	public ConcurrentHashMap<String, PlayerServer> getJogadoresList(){
		return this.jogadores;
	}
	
	public List<TiroServer> getTirosList()
	{
		return this.tiroList;
	}
	
	public List<ItemServer> getItemList(){
		return this.itemList;
	}
	
	public void setListTiros(List<TiroServer> list)
	{
		this.tiroList = list;
	}
	
	public ControleDeUsuariosServidor() {
		jogadores = new ConcurrentHashMap<String, PlayerServer>();
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
		
		if(linha.startsWith(Protocolo.PROTOCOL_ITEM))
		{
			adicionaItem(origem, linha);
			Log.i(Const.TAG, "Entrou no protocolo do item.");
		}
		
		informaTodosUsuarios(origem);
		
		if(tiroList.size() > 0)
		{
			atualizaTiros(origem);
		}
	}

	private void informaTodosUsuarios(Conexao origem) 
	{
		StringBuffer buffer = new StringBuffer();
		Iterator iterator = jogadores.keySet().iterator();
		
		while (iterator.hasNext()) 
		{
			String key = (String) iterator.next();
			PlayerServer jogador = jogadores.get(key);
			buffer.append(jogador.toStringCSV());
		}
		origem.write(Protocolo.PROTOCOL_MOVE + buffer.toString());
	}

	private void moveUsuario(Conexao origem, String linha)
	{
		String[] array = linha.split(",");
		int x = Integer.parseInt(array[1]);
		int y = Integer.parseInt(array[2]);

		PlayerServer jogador = jogadores.get(origem.getId());
		//jogador.setX(x);
		//jogador.setY(y);
		jogador.setDestino(new Point(x,y));
	}

	private void adicionaNovoUsuario(Conexao origem, String linha)
	{
		String[] array = linha.split(",");
		String nome = array[1];
		int x = Integer.parseInt(array[2]);
		int y = Integer.parseInt(array[3]);
		String time = array[4];
		String classe = array[5];
		
		origem.setId(nome);
		//PlayerServer jogador = new GeneralServer(nome, new Point(x, y));
		PlayerServer jogador = selecionarPlayerServer(classe, nome, new Point(x, y));
		jogador.setTime(time);
		jogador.setMinhaClasse(classe);
		jogadores.put(nome, jogador);
		
	}
	
	private PlayerServer selecionarPlayerServer(String classe, String nome, Point position){
		
		if (classe == "General") {
		GeneralServer general = new GeneralServer(nome, position);
		return general;
		}
		else if(classe == "Soldado"){
		SoldadoServer soldado = new SoldadoServer(nome, position);
		return soldado;
		}
		else{
		MedicoServer medico = new MedicoServer(nome, position,this);
		return medico;
		}
	}
	
	private void adicionaTiro(Conexao origem, String linha)
	{
		String[] array = linha.split(",");
		String nome = array[1];
		int x = Integer.parseInt(array[2]);
		int y = Integer.parseInt(array[3]);
		
		//if(jogadores.get(nome).getMunicao() > 0)
		//{
			tiroList.add(new TiroServer(jogadores.get(nome), new Point(x, y)));
		//}
		
		atualizaTiros(origem);
	}
	
	private void atualizaTiros(Conexao origem)
	{
		StringBuffer buffer = new StringBuffer();
		
		for(TiroServer t : tiroList)
		{
			int x = (int) t.getPosition().x;
			int y = (int) t.getPosition().y;
			String tiroToString = x + "," + y + ";";
			
			buffer.append(tiroToString);
		}

		origem.write(Protocolo.PROTOCOL_SHOOT + buffer.toString());
	}
	
	private void adicionaItem(Conexao origem, String linha)
	{
		String[] array = linha.split(",");
		String nome = array[1];
		int x = Integer.parseInt(array[2]);
		int y = Integer.parseInt(array[3]);

		itemList.add(new ItemServer(new Point(x, y),nome));
		//	itemList.add(new ItemServer(jogadores.get(nome), new Point(x, y)));
		//	itemList.add(new ItemServer(jogadores.get(nome), new Point(x, y)));
			
		atualizaItens(origem);
		
	}
	
	private void atualizaItens(Conexao origem) {

		StringBuffer buffer = new StringBuffer();
		
		for(ItemServer t : itemList){
			
			int x = (int) t.getPosition().x;
			int y = (int) t.getPosition().y;
			String itemToString = x + "," + y + ";";
			
			buffer.append(itemToString);
		}

		origem.write(Protocolo.PROTOCOL_ITEM + buffer.toString());
	}
	
	public void removerTiro(TiroServer tiro)
	{
		
	}
	
	public void configuracaoPrimarias(Conexao origem)
	{
		origem.write(Protocolo.PROTOCOL_MAPA + MapaServer.toStringCSV());		
	}

}
