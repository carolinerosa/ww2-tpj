package com.nave.segundaguerra.servidorecliente.servidor;

import com.nave.segundaguerra.activitys.GerenciadorActivity;

public class MapaServer {

	static private int x = 0;
	static private int y = 0;
	static private int Largura = GerenciadorActivity.GetInstance().getImageMapa().getWidth();
	static private int Altura = GerenciadorActivity.GetInstance().getImageMapa().getHeight();
	
	public static int getX(){
		return x;
	}
	
	public static int getY(){
		return y;
	}
	
	public static int getLargura(){
		return Largura;
	}
	
	public static int getAltura(){
		return Altura;				
	}
	
	public static String toStringCSV() {
		return ""+x+","+y+";";
	}
}
