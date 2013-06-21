package com.nave.segundaguerra.game;

import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;


public class PontosTime{

	private int points;
	int pointsAzul;
	int pointsVermelho;
	private Conexao conexao;
	
	public int Perda()
	{
		return -1;
	}
	
	public int Ganho()
	{
		return +1;	
	}
	
	public void ComunicacaoScore(String time, String condicao)
	{
		conexao.write(Protocolo.PROTOCOL_SCORE + "," + time + "," + condicao);
	}
}
