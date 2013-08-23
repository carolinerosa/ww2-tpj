package com.nave.segundaguerra.servidorecliente.servidor;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;

public class MapaServer {

	static private int x = 0;
	static private int y = 0;
	static private int Largura = ImageLibrary.getInstance().getImage("Cenario").getWidth();
	static private int Altura = ImageLibrary.getInstance().getImage("Cenario").getHeight();
	
	public static int getX()
	{
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
