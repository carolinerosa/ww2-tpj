package com.nave.segundaguerra;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class TelaEscolhaDeTimes_Activity extends Activity implements Runnable {

	private boolean running;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Na falta de um c�digo otimizado de GerenciadorDeCenas,
		// ficar� por enquanto este Thread.
		running = true;
		Thread thread = new Thread(this);
		thread.start();

		setContentView(new TelaEscolhaDetTime(this));
	}

	public void run() {
		while (running) {
			if (TelaEscolhaDetTime.getClicou()) {
				TelaEscolhaDetTime.setClicou(false);
				running = false;

				Intent intent = new Intent(TelaEscolhaDeTimes_Activity.this,
						BatalhaActivity.class);
				startActivity(intent);
			}
		}
	}
}