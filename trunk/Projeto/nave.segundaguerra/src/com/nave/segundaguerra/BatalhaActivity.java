package com.nave.segundaguerra;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BatalhaActivity extends Activity {

	public static String TAG = "Teste";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new GameLoop(this));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_batalha, menu);
		return true;
	}

}
