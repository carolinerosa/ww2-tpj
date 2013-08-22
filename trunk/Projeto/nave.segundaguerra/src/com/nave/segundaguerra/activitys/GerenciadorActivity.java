package com.nave.segundaguerra.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.nave.segundaguerra.servidorecliente.cliente.JogadorCliente;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

public class GerenciadorActivity extends Activity implements Killable,ActivityManager {

	public static GerenciadorActivity Instance;
	
	public static final String TAG = "GerenteCenas";
	
	private JogadorCliente meuPlayer;
	
	public static GerenciadorActivity GetInstance()
	{
		return Instance;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		ViewUtil.enableMainConfig(this);

		Instance = this;
		
		CenaMenu();
		
		ImageLibrary _library = ImageLibrary.getInstance();
		RectLibrary _rectLibrary = RectLibrary.getInstance();
		
	
		_library.addImage("Soldado","soldado.png",this);
		_library.addImage("Projetil","projetil.png",this);
		_library.addImage("Cenario","Cenario1.jpg",this);
		
		_rectLibrary.addRectFromBitmap("Soldado", "Soldado", 10);
		_rectLibrary.addRectFromBitmap("Projetil", "Projetil", 5);

		meuPlayer = new JogadorCliente("", 100, 50);
	}
	
	
	public void CenaMenu()
	{		
		Intent intent = new Intent(GerenciadorActivity.this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void CenaConect()
	{
		Intent intent = new Intent(GerenciadorActivity.this, ConectActivity.class);
		startActivity(intent);
	}
	
	public void CenaCreditos()
	{
		Intent intent = new Intent(GerenciadorActivity.this,CreditosActivity.class);
		startActivity(intent);
	}
	
	public void CenaDicas()
	{
		Intent intent = new Intent(GerenciadorActivity.this,DicasActivity.class);
		startActivity(intent);
	}

	
	public JogadorCliente getPlayer()
	{
		return meuPlayer;
	}

	public void onBackPressed() 
	{
		ViewUtil.dialogAlertButton(this, "Sair", "Deseja Sair do Jogo?", "Sim", "Não");
	}
	
	public void backFunction()
	{
		killMeSoftly();
	}

	public void killMeSoftly() 
	{
		ElMatador.getInstance().killThenAll();
		finish();
	}


}
