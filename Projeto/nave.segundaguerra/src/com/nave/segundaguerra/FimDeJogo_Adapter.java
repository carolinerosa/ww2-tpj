package com.nave.segundaguerra;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FimDeJogo_Adapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<DadosJogador> dadosJogador;
	
	public FimDeJogo_Adapter(Context context, ArrayList<DadosJogador> dj) {
		this.context = context;
		this.dadosJogador = dj;
	}
	
	public int getCount() {
		return dadosJogador.size();
	}

	public Object getItem(int arg0) {
		return dadosJogador.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		DadosJogador dados = dadosJogador.get(arg0);
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.activity_fim_de_jogo, null);
		
		TextView nome = (TextView) v.findViewById(R.id.FIMDEJOGO_Nome);
		TextView matou = (TextView) v.findViewById(R.id.FIMDEJOGO_Matou);
		TextView morreu = (TextView) v.findViewById(R.id.FIMDEJOGO_Morreu);
		
		nome.setText(dados.getNome());
		matou.setText(dados.getMatou());
		morreu.setText(dados.getMorreu());
		
		return v;
	}

}
