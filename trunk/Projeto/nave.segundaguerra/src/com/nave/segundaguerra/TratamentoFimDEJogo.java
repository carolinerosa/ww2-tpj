package com.nave.segundaguerra;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class TratamentoFimDEJogo extends View{

	TextView nomeA, nomeB;
	TextView matouEMorreuA, matouEMorreuB;
	
	public TratamentoFimDEJogo(Context context, ArrayList<DadosJogador> timeA, 
								ArrayList<DadosJogador> timeB) {
		super(context);
		
		//Procura  as strings 
		nomeA = (TextView) findViewById(R.id.fimdejogo_texto_TimeA);
		nomeB = (TextView) findViewById(R.id.fimdejogo_texto_TimeB);
		
		matouEMorreuA = (TextView) findViewById(R.id.fimdejogo_texto_TimeA_MatouEMorreu);
		matouEMorreuB = (TextView) findViewById(R.id.fimdejogo_texto_TimeB_MatouEMorreu);
	
		nomeA.setText(timeA.get(0).getTime());
		nomeB.setText(timeB.get(0).getTime());
		
		contarMatouEMorreu(timeA, matouEMorreuA);
		contarMatouEMorreu(timeB, matouEMorreuB);
	}
	
	/**
	 * Classe que tratará os dados do Time para serem mostrados em FimDeJogoActivity
	 * @param time ArrayList de <code>DadosJogador</code> que será usado para coletar a variável matou e morreu.
	 * @param matouEMorreu TextView que aparece em FimDeJogoActivity
	 */
	private void contarMatouEMorreu(ArrayList<DadosJogador> time, TextView matouEMorreu)
	{
		int matou = 0, morreu = 0;
		
		//Soma de matou e morreu individualmente.
		for (int i = 0; i < time.size(); i++) {
			matou += time.get(i).getMatou();
			morreu += time.get(i).getMorreu();
		}
		
		//Concatenar o texto e alterar na Activity.
		String s =String.valueOf(matou) + " / " + String.valueOf(morreu);
		matouEMorreu.setText(s);
	}
}
