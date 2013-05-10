package com.nave.segundaguerra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Menu_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void Click_Batalha(){
		Intent intent = new Intent(Menu_Activity.this,BatalhaActivity.class);
		startActivity(intent);
	}
	
	public void Click_Creditos(){
		Intent intent = new Intent(Menu_Activity.this,CreditosActivity.class);
		startActivity(intent);
	}
	
	public void Click_Dicas(){
		Intent intent = new Intent(Menu_Activity.this,DicasActivity.class);
		startActivity(intent);
	}
	
}
