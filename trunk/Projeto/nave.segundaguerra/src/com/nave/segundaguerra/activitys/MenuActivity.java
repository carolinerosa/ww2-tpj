package com.nave.segundaguerra.activitys;



import com.nave.segundaguerra.R;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends Activity {

	GerenciadorActivity gerente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.activity_menu);
		SoundManager.getInstance().playSound(R.raw.menu, "MenuSound",true, this);

		gerente = GerenciadorActivity.GetInstance();
		

//		Typeface tf = Typeface.createFromAsset(getAssets(), "aha.TTF");
//		
//		Button batalha = (Button)findViewById(R.id.Batalha);
//		batalha.setTypeface(tf);
//		
//		Button creditos = (Button)findViewById(R.id.Creditos);
//		creditos.setTypeface(tf);
//		
//		Button dicas = (Button)findViewById(R.id.Creditos);
//		dicas.setTypeface(tf);
	}
	
	public void Click_Batalha(View v)
	{

		gerente.CenaConect();
	}
	
	public void Click_Creditos(View v){
		gerente.CenaCreditos();
	}
	
	public void Click_Dicas(View v){
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
	
	public void onBackPressed() {
		
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("abandonando o barco")
				.setMessage(
						"Tem certeza que vai embora ? vou sentir sua falta ...")
				.setPositiveButton("Adeus",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								gerente.killMeSoftly();
								SoundManager.getInstance().StopAllSongs();
								finish();
							}

						}).setNegativeButton("Então tá, fico + um pouco", null)
				.show();
	}

}
