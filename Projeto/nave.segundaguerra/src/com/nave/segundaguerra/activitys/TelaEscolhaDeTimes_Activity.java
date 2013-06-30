package com.nave.segundaguerra.activitys;


import com.nave.segundaguerra.R;
import com.nave.segundaguerra.activitys.views.TelaEscolhaDetTime;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class TelaEscolhaDeTimes_Activity extends Activity implements Runnable {

	private boolean running;
	GerenciadorActivity gerente = GerenciadorActivity.GetInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Na falta de um código otimizado de GerenciadorDeCenas,
		// ficará por enquanto este Thread.
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
				
				gerente.CenaMenu();
			}
		}
	}
}