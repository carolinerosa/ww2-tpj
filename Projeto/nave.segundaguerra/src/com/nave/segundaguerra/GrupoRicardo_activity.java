package com.nave.segundaguerra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GrupoRicardo_activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void Click_Batalha(){
		Intent intent = new Intent(GrupoRicardo_activity.this,BatalhaActivity.class);
		startActivity(intent);
	}
	
	public void Click_Creditos(){
		Intent intent = new Intent(GrupoRicardo_activity.this,CreditosActivity.class);
		startActivity(intent);
	}
	
	public void Click_Dicas(){
		Intent intent = new Intent(GrupoRicardo_activity.this,DicasActivity.class);
		startActivity(intent);
	}
	
}
