package com.nave.segundaguerra.activitys;



import com.nave.segundaguerra.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends Activity {

	GerenciadorActivity gerente = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_menu);
		gerente = GerenciadorActivity.GetInstance();
		
	}
	
	public void Click_Batalha(View v){
		gerente.CenaConect();
	}
	
	public void Click_Creditos(View v){
		gerente.CenaCreditos();
	}
	
	public void Click_Dicas(View v){
		gerente.CenaDicas();
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
								finish();
							}

						}).setNegativeButton("Então tá, fico + um pouco", null)
				.show();
	}

}
