package com.nave.segundaguerra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Armas extends Activity {

	int pente;
	int dano;
	float intervaloRecarga;
	float intervaloDisparo;
	int pentePistola;
	int danoPistola;
	float intervaloRecargaPistola;
	float intervaloDisparoPistola;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.choose_function);
	}

	public void espingarda(View v) {

		// Pente: 10 balas Dano: 70 de HP
		// Intervalo(disparo): 1 segundo(s) Intervalo(recarga): 1,3 segundos(s)

		pente = 10;
		dano = 70;
		intervaloRecarga = 1.3f;
		intervaloDisparo = 1;

		pentePistola = 15;
		danoPistola = 25;
		intervaloDisparoPistola = 0.5f;
		intervaloRecargaPistola = 1;

	}

	public void metralhadora(View v) {

		// Pente: 100 balas Dano: 17 de HP
		// Intervalo(disparo): 0,2 segundo(s) Intervalo(recarga): 1,3 segundo(s)

		pente = 100;
		dano = 17;
		intervaloDisparo = 0.2f;
		intervaloRecarga = 1.3f;

		pentePistola = 15;
		danoPistola = 25;
		intervaloDisparoPistola = 0.5f;
		intervaloRecargaPistola = 1;
	}

	public void medico(View v) {

		pente = 0;
		dano = 0;
		intervaloDisparo = 0;
		intervaloRecarga = 0;

		pentePistola = 15;
		danoPistola = 25;
		intervaloDisparoPistola = 0.5f;
		intervaloRecargaPistola = 1;

	}

	public void dinamite(View v) {

		pente = 0;
		dano = 0;
		intervaloDisparo = 0;
		intervaloRecarga = 0;

		pentePistola = 15;
		danoPistola = 25;
		intervaloDisparoPistola = 0.5f;
		intervaloRecargaPistola = 1;

	}

	public void minaTerrestre(View v) {

		pente = 0;
		dano = 0;
		intervaloDisparo = 0;
		intervaloRecarga = 0;

		pentePistola = 15;
		danoPistola = 25;
		intervaloDisparoPistola = 0.5f;
		intervaloRecargaPistola = 1;

	}
	

	/*
	 * Pente: 15 balas Dano: 25 de HP Intervalo(disparo): 0,5 segundo(s)
	 * Intervalo(recarga): 1 segundo(s)
	 */

}
