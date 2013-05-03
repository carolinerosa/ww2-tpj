package com.nave.segundaguerra;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	TextView nick;
	TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nickname);
		nick = (TextView) findViewById(R.id.nick);
		text = (TextView) findViewById(R.id.textNick);
	}

	public void Conectado(View v)
	{
		// Trocar de cena
		// Teste de click comprovado com a mudança do texto da textview acima do campo de digitação.
		text.setText(nick.getText().toString());
	}

}
