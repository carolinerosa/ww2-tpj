package com.nave.segundaguerra.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;


public class ClasseActivity extends Activity 
{
	GerenciadorActivity gerente = null;
	private Context thisContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		thisContext = this;
		
		ViewUtil.enableMainConfig(this);
		setContentView(R.layout.activity_classe);
		gerente = GerenciadorActivity.GetInstance();
	}
	
	public void player_Soldado(View v)
	{
		gerente.gerenciadorClasse = GerenciadorClasse.soldado;
		gerente.meuPlayer = gerente.selecionarPlayer(gerente.gerenciadorClasse);
		gerente.CenaConect();
	}
	public void player_Medico(View v)
	{
		gerente.gerenciadorClasse = GerenciadorClasse.medico;
		gerente.meuPlayer = gerente.selecionarPlayer(gerente.gerenciadorClasse);
		gerente.CenaConect();
	}
	public void player_General(View v)
	{
		gerente.gerenciadorClasse = GerenciadorClasse.general;
		gerente.meuPlayer = gerente.selecionarPlayer(gerente.gerenciadorClasse);
		gerente.CenaConect();
	}
	
	public void onBackPressed() 
	{
		SoundManager.getInstance().playSound(R.raw.menu, "MenuSound", true,thisContext);
		super.onBackPressed();
	}

}
