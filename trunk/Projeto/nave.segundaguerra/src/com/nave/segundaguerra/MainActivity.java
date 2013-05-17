package com.nave.segundaguerra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_menu);
	}
	
	public void Click_Batalha(View v){
		Intent intent = new Intent(MainActivity.this,Nickname.class);
		startActivity(intent);
	}
	
	public void Click_Creditos(View v){
		Intent intent = new Intent(MainActivity.this,CreditosActivity.class);
		startActivity(intent);
	}
	
	public void Click_Dicas(View v){
		Intent intent = new Intent(MainActivity.this,DicasActivity.class);
		startActivity(intent);
	}

}
