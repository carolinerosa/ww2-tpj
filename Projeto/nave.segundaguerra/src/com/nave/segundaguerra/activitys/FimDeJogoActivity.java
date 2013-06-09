package com.nave.segundaguerra.activitys;


import java.util.ArrayList;

import com.nave.segundaguerra.activitys.views.TratamentoFimDEJogo;
import com.nave.segundaguerra.game.DadosJogador;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FimDeJogoActivity extends Activity {

	private ArrayList<DadosJogador> dadosTimeA;
	private ArrayList<DadosJogador> dadosTimeB;
	GerenciadorActivity gerente = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//Código temporário por falta de GERENCIADOR\\;
		dadosTimeA = new ArrayList<DadosJogador>();
		dadosTimeB = new ArrayList<DadosJogador>();
		
		contrucaoTemporariaDeTime();
		
		setContentView(new TratamentoFimDEJogo(this,dadosTimeA, dadosTimeB));
		gerente = GerenciadorActivity.GetInstance();
	}
	
	private void contrucaoTemporariaDeTime()
	{
		DadosJogador dj1 = new DadosJogador();
		dj1.setTime("Time A");
		dj1.setMatou(20);
		dj1.setMorreu(10);
		
		DadosJogador dj2 = new DadosJogador();
		dj2.setTime("Time A");
		dj2.setMatou(5);
		dj2.setMorreu(13);
		
		DadosJogador dj3 = new DadosJogador();
		dj3.setTime("Time A");
		dj3.setMatou(4);
		dj3.setMorreu(3);
		
		DadosJogador dj4 = new DadosJogador();
		dj4.setTime("Time B");
		dj4.setMatou(4);
		dj4.setMorreu(3);
		
		DadosJogador dj5 = new DadosJogador();
		dj5.setTime("Time B");
		dj5.setMatou(4);
		dj5.setMorreu(3);
		
		DadosJogador dj6 = new DadosJogador();
		dj6.setTime("Time B");
		dj6.setMatou(4);
		dj6.setMorreu(3);
		
		dadosTimeA.add(dj1);
		dadosTimeA.add(dj2);
		dadosTimeA.add(dj3);
		dadosTimeB.add(dj4);
		dadosTimeB.add(dj5);
		dadosTimeB.add(dj6);
	}
}