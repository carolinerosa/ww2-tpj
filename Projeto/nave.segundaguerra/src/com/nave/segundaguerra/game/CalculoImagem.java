package com.nave.segundaguerra.game;

import android.graphics.Bitmap;
import android.graphics.Matrix;
// Criei a classe CalculoImagem e o metodo de RedimensionarImagens e 
//deixei acessível à todo o projeto por singleton. Por: Nuno
public class CalculoImagem {
	private static CalculoImagem instancia;


	private CalculoImagem() {
	}
	
	public static CalculoImagem getInstance()
	{
		if(instancia == null){
			instancia = new CalculoImagem();
		}
		
		return instancia;
	}
	public Bitmap RedimensionarImagem(Bitmap bm, float novaAltura, float novaLargura)
	{
	    int largura = bm.getWidth();
	    int altura = bm.getHeight();
	    float escalaW = novaLargura / largura;
	    float escalaH = novaAltura / altura;
	    
	    Matrix matrix = new Matrix();
	    matrix.postScale(escalaW, escalaH);
	    
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, largura, altura, matrix, false);
	    return resizedBitmap;
	}
}
