package com.nave.segundaguerra.game;


/**
 * 
 * @author RicardoBrambila
 * Classe que será chamada mostrando o frag de cada jogador.
 */
public class DadosJogador {
	
	private String time;
	private String nome;
	private int matou;
	private int morreu;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMatou() {
		return matou;
	}
	public void setMatou(int matou) {
		this.matou = matou;
	}
	public int getMorreu() {
		return morreu;
	}
	public void setMorreu(int morreu) {
		this.morreu = morreu;
	}
}