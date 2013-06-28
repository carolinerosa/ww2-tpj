package com.nave.segundaguerra.listactivity;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaDeNomes_Adapter extends BaseAdapter {

	private Context context;
	private List<ListaDeNomes_Base> listaDeNomes;
	
	public ListaDeNomes_Adapter(Context context,List<ListaDeNomes_Base> listaDeNomes) {
		this.context = context;
		this.listaDeNomes = listaDeNomes;
	}

	public int getCount() {
		return listaDeNomes.size();
	}

	public Object getItem(int position) {
		return listaDeNomes.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ListaDeNomes_Base integrante = listaDeNomes.get(position);
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(com.nave.segundaguerra.R.layout.activity_creditos, null);

		TextView nome = (TextView) v.findViewById(com.nave.segundaguerra.R.id.creditos_nome);
		TextView funcao = (TextView) v.findViewById(com.nave.segundaguerra.R.id.creditos_funcao);

		nome.setText(integrante.getIntegrantes());
		funcao.setText(integrante.getFuncao());
		
		return v;
	}

}
