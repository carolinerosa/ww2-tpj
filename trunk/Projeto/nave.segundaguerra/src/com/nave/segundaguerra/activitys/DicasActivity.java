package com.nave.segundaguerra.activitys;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.game.Dicas_Base;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

public class DicasActivity extends Activity {

	ArrayList<Dicas_Base> dicas = new ArrayList<Dicas_Base>();
	
	int contador = 0;
	
	String[] todasDicas = 
	{
			//"Na linha de tiro? Se esconda em uma trincheira pr�xima!",
			//"Sacos de areia s�o excelentes para bloquear ataques!",
			"Cuidado ao detonar a mina. Um aliado pode estar l�...",
			"Sem muni��o? Seu tenente precisa saber! Informe-o!",
			//"A �gua � uma �tima aliada contra tiros e explos�es.",
			"Aten��o! Inimigos tamb�m podem acessar suas caixas.",
			"Recebeu muito dano? Procure a equipe m�dica!",
			//"Artilharia e detona��o trabalham bem em conjunto.",
			"Equipe m�dica: n�o aja sozinha! Ajude seu time!",
			"Ao plantar uma mina, voc� fica muito vulner�vel. Cuidado!"
	};
	
	int[] todasImagens = 
	{
			R.drawable.mina_terrestre, R.drawable.pente,
			R.drawable.caixa, R.drawable.bandeira_branca,
			R.drawable.maleta_medica, R.drawable.mina_terrestre
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		ViewUtil.enableMainConfig(this);
		
		criarDicas();
		
		setContentView(R.layout.activity_dicas);
	}

	private void criarDicas() 
	{

		for (int i = 0; i < todasDicas.length; i++) 
		{
			Dicas_Base tempDica = new Dicas_Base();
			
			tempDica.setImage(todasImagens[i]);
			
			tempDica.setText(todasDicas[i]);

			dicas.add(tempDica);
		}
	}
	
	public void trocaDica_Avancar(View v) 
	{
		if (contador < todasDicas.length - 1)
			contador++;
		
		TextView tv = (TextView)findViewById(R.id.DICAS_texto);
		ImageView iv = (ImageView) findViewById(R.id.DICAS_imagem);
		
		tv.setText(dicas.get(contador).getText());
		iv.setImageResource(dicas.get(contador).getImage());
	}

	public void trocaDica_Retroceder(View v) 
	{
		if (contador != 0)
			contador--;
		
		TextView tv = (TextView)findViewById(R.id.DICAS_texto);
		ImageView iv = (ImageView) findViewById(R.id.DICAS_imagem);
		
		tv.setText(dicas.get(contador).getText());
		iv.setImageResource(dicas.get(contador).getImage());
	}
}
