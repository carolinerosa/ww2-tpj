package com.nave.segundaguerra.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import com.nave.segundaguerra.servidorecliente.cliente.GeneralCliente;
import com.nave.segundaguerra.servidorecliente.cliente.MedicoCliente;
import com.nave.segundaguerra.servidorecliente.cliente.PlayerCliente;
import com.nave.segundaguerra.servidorecliente.cliente.SoldadoCliente;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

public class GerenciadorActivity extends Activity implements Killable,ActivityManager {

	public static GerenciadorActivity Instance;
	
	public static final String TAG = "GerenteCenas";
	GerenciadorClasse gerenciadorClasse = GerenciadorClasse.nenhum;
	Object meuPlayer;
	
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
		
		this.gerenciadorClasse = GerenciadorClasse.medico;
		meuPlayer = selecionarPlayer(this.gerenciadorClasse);
	}
	
	public PlayerCliente selecionarPlayer(GerenciadorClasse gerenciador){
		
		switch (gerenciador) {
		
		case general:
		GeneralCliente general = new GeneralCliente("", new Point(100, 50));
		general.CarregarImagem();
		return general;
		
		case soldado:
		SoldadoCliente soldado = new SoldadoCliente("", new Point(100, 50));
		soldado.CarregarImagem();
		return soldado;
			
		case medico:
		MedicoCliente medico = new MedicoCliente("", new Point(100, 50));
		medico.CarregarImagem();
		return medico;
			
		}
		return null;
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

	
	public PlayerCliente getPlayer()
	{
		return (PlayerCliente)this.meuPlayer;
	}
	public GerenciadorClasse getGerenciador(){
		return this.gerenciadorClasse;
	}
	public void setGerenciador(GerenciadorClasse gerenciador){
		this.gerenciadorClasse = gerenciador;
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
