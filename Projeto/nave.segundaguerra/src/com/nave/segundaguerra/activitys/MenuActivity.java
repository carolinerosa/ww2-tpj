package com.nave.segundaguerra.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

public class MenuActivity extends Activity implements ActivityManager{

	GerenciadorActivity gerente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		//Chama a classe ViewUtil para definir as configurações iniciais.
		ViewUtil.enableMainConfig(this);
		
		setContentView(R.layout.activity_menu);
		
		SoundManager.getInstance().playSound(R.raw.menu, "MenuSound",true, this);

		gerente = GerenciadorActivity.GetInstance();
	}
	
	
	
	public void Click_Batalha(View v)
	{
		gerente.CenaConect();
	}
	
	public void Click_Creditos(View v)
	{
		gerente.CenaCreditos();
	}
	
	public void Click_Dicas(View v)
	{
		gerente.CenaDicas();
	}
	
    
	protected void onPause() 
    {
		SoundManager.getInstance().PauseSong("MenuSound");
		super.onPause();
    }

    protected void onResume()
    {
		SoundManager.getInstance().StopAllSongs();
		SoundManager.getInstance().playSound(R.raw.menu, "MenuSound",true, this);
    	
		super.onResume();
    }

    protected void OnDestroy()
	{
		SoundManager.getInstance().StopAllSongs();
		super.onDestroy();
	}
	
	public void onBackPressed() 
	{
		ViewUtil.dialogAlertButton(this, "Sair", "Deseja Sair do Jogo?", "Sim", "Não");
	}
	
	public void backFunction()
	{
		gerente.killMeSoftly();
		SoundManager.getInstance().StopAllSongs();
		finish();
	}

}
