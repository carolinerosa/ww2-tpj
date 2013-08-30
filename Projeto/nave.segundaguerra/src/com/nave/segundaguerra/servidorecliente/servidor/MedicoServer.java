package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Point;
import android.util.Log;

public class MedicoServer extends PlayerServer {

	ControleDeUsuariosServidor getUsuario;
	
	public MedicoServer(String nome, Point position,ControleDeUsuariosServidor getUsuario) {
		
		this.getUsuario = getUsuario;
		this.nome = nome;
		this.setPosition(position);
		this.respawn();
	}
	
	@Override
	public void update() {
		
		SpecialAbility(this);
		
		super.update();
	}
	public void SpecialAbility(PlayerServer meuPlayer)
	{
		
		ConcurrentHashMap<String, PlayerServer> jogadores = getUsuario.getJogadoresList();

		Iterator iterator = jogadores.keySet().iterator();
		while (iterator.hasNext()) {
			
			String key = (String) iterator.next();
			
			PlayerServer jogador = jogadores.get(key);
			if(meuPlayer.getNome()!=jogador.getNome()&&jogador.getTime() == meuPlayer.getTime())
			{

			Point minhaPosicao = new Point(meuPlayer.getPosition());
			
			Point outroPlayerPosicao = new Point(jogador.getPosition());
			

			Point difPosicoes = new Point(minhaPosicao.x
					- outroPlayerPosicao.x, minhaPosicao.y
					- outroPlayerPosicao.y);
			
			double distanciaDoMedico = Math
					.sqrt((difPosicoes.x * difPosicoes.x)
							+ (difPosicoes.y * difPosicoes.y));
			if(distanciaDoMedico < 5)
			{
				
				
			}
			
			Log.e("Medico", "Eu to on :" + minhaPosicao);
			}else{
				
				Log.e("Medico", "sou igual");
			}
		}
		
		
	}
}
