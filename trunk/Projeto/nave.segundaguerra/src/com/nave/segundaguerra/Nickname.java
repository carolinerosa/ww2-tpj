package com.nave.segundaguerra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Nickname extends Activity {

	TextView nick;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nickname);
		nick = (TextView) findViewById(R.id.nick);

	}

	public Nickname() 
	{

	}

	public void conectado(View v) {
		if (setName().length() == 0) {
			Toast.makeText(this, "Insira um nickname", Toast.LENGTH_SHORT)
					.show();
		}
		if (setName().length() > 10) {
			Toast.makeText(this, "Insira um nickname menor", Toast.LENGTH_SHORT)
					.show();
		}

		if (setName().length() >= 1 && setName().length() <= 10) {
			// Trocar de cena
			Intent intent = new Intent(Nickname.this, TelaEscolhaDetTime.class);
			startActivity(intent);
		}
	}

	public String setName() {
		return nick.getText().toString();
	}
}
