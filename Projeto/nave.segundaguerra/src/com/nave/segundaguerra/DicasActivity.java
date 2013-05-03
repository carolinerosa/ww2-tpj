package com.nave.segundaguerra;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DicasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dicas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dicas, menu);
		return true;
	}

}
