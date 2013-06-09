package com.nave.segundaguerra.game;


public class Pontos{

	private int points;
	
	public int Pontos(){
		
		return points;		
	}
	
	public void Perda()
	{
		points-=1;
	}
	
	public void Ganho()
	{
		points+=1;
	}

}
