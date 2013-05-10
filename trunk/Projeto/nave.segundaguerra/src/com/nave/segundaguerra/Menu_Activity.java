package com.example.segundaguerramenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Menu_Activity extends Activity {

@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void Click_Batalha(){
		Intent intent = new Intent(GrupoRicardo_activity.this,Nickname.class);
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
