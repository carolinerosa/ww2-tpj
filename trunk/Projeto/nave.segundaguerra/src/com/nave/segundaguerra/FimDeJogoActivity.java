package com.nave.segundaguerra;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FimDeJogoActivity extends ListActivity {

	private ArrayList<DadosJogador> dadosJogadores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Algum grupo irá atualizar a variável "dadosJogadores" com a lista de
		// DadosJogador do jogo. Por enquanto, para ser testado será criado aqui
		// alguns dados aleatórios para serem ilustrados
		
		dadosJogadores = new ArrayList<DadosJogador>();
		
		DadosJogador dj1 = new DadosJogador();
		dj1.setNome("<-- Máurício -->");
		dj1.setMatou(30);
		dj1.setMorreu(10);
		
		DadosJogador dj2 = new DadosJogador();
		dj2.setNome("<-- Lula -->");
		dj2.setMatou(1);
		dj2.setMorreu(0);
		
		DadosJogador dj3 = new DadosJogador();
		dj3.setNome("<-- LALALA -->");
		dj3.setMatou(2);
		dj3.setMorreu(8);
		
		DadosJogador dj4 = new DadosJogador();
		dj4.setNome("<-- Dirceu -->");
		dj4.setMatou(8);
		dj4.setMorreu(15);
		
		dadosJogadores.add(dj1);
		dadosJogadores.add(dj2);
		dadosJogadores.add(dj3);
		dadosJogadores.add(dj4);
		
		setListAdapter(new FimDeJogo_Adapter(this, dadosJogadores));
	}
}
