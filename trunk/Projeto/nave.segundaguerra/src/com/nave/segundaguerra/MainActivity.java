package com.nave.segundaguerra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}
	
	public void Click_Batalha(View v){
		Intent intent = new Intent(MainActivity.this,Nickname.class);
		startActivity(intent);
	}
	
	public void Click_Creditos(View v){
		Intent intent = new Intent(this,CreditosActivity.class);
		startActivity(intent);
	}
	
	public void Click_Dicas(View v){
		Intent intent = new Intent(this,DicasActivity.class);
		startActivity(intent);
	}

}
