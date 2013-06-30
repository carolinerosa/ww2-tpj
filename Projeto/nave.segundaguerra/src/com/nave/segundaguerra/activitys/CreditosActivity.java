package com.nave.segundaguerra.activitys;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.listactivity.ListaDeNomes_Adapter;
import com.nave.segundaguerra.listactivity.ListaDeNomes_Base;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;

public class CreditosActivity extends ListActivity {

	ArrayList<ListaDeNomes_Base> integrantes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		integrantes = new ArrayList<ListaDeNomes_Base>();
		
		setIntegrantes();
		
		setListAdapter(new ListaDeNomes_Adapter(this, integrantes));
		
	}

	private void setIntegrantes(){
		
		ListaDeNomes_Base ldb1 = new ListaDeNomes_Base();
		ldb1.setIntegrantes("Brian Athayde");
		ldb1.setFuncao("Programador");
		
		ListaDeNomes_Base ldb2 = new ListaDeNomes_Base();
		ldb2.setIntegrantes("Caroline Ribeiro");
		ldb2.setFuncao("Programador");
		
		ListaDeNomes_Base ldb3 = new ListaDeNomes_Base();
		ldb3.setIntegrantes("Davi Rodrigues");
		ldb3.setFuncao("Programador");
		
		ListaDeNomes_Base ldb4 = new ListaDeNomes_Base();
		ldb4.setIntegrantes("Edson Candido");
		ldb4.setFuncao("Programador");
		
		ListaDeNomes_Base ldb5 = new ListaDeNomes_Base();
		ldb5.setIntegrantes("Emanoel Faria");
		ldb5.setFuncao("Programador");
		
		ListaDeNomes_Base ldb6 = new ListaDeNomes_Base();
		ldb6.setIntegrantes("Filipe Zanella");
		ldb6.setFuncao("Programador");
		
		ListaDeNomes_Base ldb7 = new ListaDeNomes_Base();
		ldb7.setIntegrantes("Flávio Garrido");
		ldb7.setFuncao("Programador");
		
		ListaDeNomes_Base ldb8 = new ListaDeNomes_Base();
		ldb8.setIntegrantes("Gabriel Smith");
		ldb8.setFuncao("Programador");
		
		ListaDeNomes_Base ldb9 = new ListaDeNomes_Base();
		ldb9.setIntegrantes("Gustavo Silva");
		ldb9.setFuncao("Programador");
		
		ListaDeNomes_Base ldb10 = new ListaDeNomes_Base();
		ldb10.setIntegrantes("Henrique Elias");
		ldb10.setFuncao("Programador");
		
		ListaDeNomes_Base ldb11 = new ListaDeNomes_Base();
		ldb11.setIntegrantes("Hugo Renzi");
		ldb11.setFuncao("Programador");
		
		ListaDeNomes_Base ldb12 = new ListaDeNomes_Base();
		ldb12.setIntegrantes("Igor Silva");
		ldb12.setFuncao("Programador");
		
		ListaDeNomes_Base ldb13 = new ListaDeNomes_Base();
		ldb13.setIntegrantes("Juliano Cunha");
		ldb13.setFuncao("Programador");
		
		ListaDeNomes_Base ldb14 = new ListaDeNomes_Base();
		ldb14.setIntegrantes("Julien Breno");
		ldb14.setFuncao("Programador");
		
		ListaDeNomes_Base ldb15 = new ListaDeNomes_Base();
		ldb15.setIntegrantes("Julio Cesar");
		ldb15.setFuncao("Programador");
		
		ListaDeNomes_Base ldb16 = new ListaDeNomes_Base();
		ldb16.setIntegrantes("Kevin Vilar");
		ldb16.setFuncao("Programador");
		
		ListaDeNomes_Base ldb17 = new ListaDeNomes_Base();
		ldb17.setIntegrantes("Leonardo Menezes");
		ldb17.setFuncao("Programador");
		
		ListaDeNomes_Base ldb18 = new ListaDeNomes_Base();
		ldb18.setIntegrantes("Lucas Bastos");
		ldb18.setFuncao("Programador");
		
		ListaDeNomes_Base ldb19 = new ListaDeNomes_Base();
		ldb19.setIntegrantes("Lucas Matheus");
		ldb19.setFuncao("Programador");
		
		ListaDeNomes_Base ldb20 = new ListaDeNomes_Base();
		ldb20.setIntegrantes("Lucas Novo");
		ldb20.setFuncao("Programador");
		
		ListaDeNomes_Base ldb21 = new ListaDeNomes_Base();
		ldb21.setIntegrantes("Manoela Barreto");
		ldb21.setFuncao("Programador");
		
		ListaDeNomes_Base ldb22 = new ListaDeNomes_Base();
		ldb22.setIntegrantes("Marcio Lima");
		ldb22.setFuncao("Programador");
		
		ListaDeNomes_Base ldb23 = new ListaDeNomes_Base();
		ldb23.setIntegrantes("Nuno Duarte");
		ldb23.setFuncao("Programador");
		
		ListaDeNomes_Base ldb24 = new ListaDeNomes_Base();
		ldb24.setIntegrantes("Pablo Alexandre");
		ldb24.setFuncao("Programador");
		
		ListaDeNomes_Base ldb25 = new ListaDeNomes_Base();
		ldb25.setIntegrantes("Ricardo Brambila");
		ldb25.setFuncao("Programador");
		
		
		ListaDeNomes_Base ldb26 = new ListaDeNomes_Base();
		ldb26.setIntegrantes("Rudney Netto");
		ldb26.setFuncao("Programador");
		
		ListaDeNomes_Base ldb27 = new ListaDeNomes_Base();
		ldb27.setIntegrantes("Thâmara Chedid");
		ldb27.setFuncao("Programador");
		
		ListaDeNomes_Base ldb28 = new ListaDeNomes_Base();
		ldb28.setIntegrantes("Thauan Lopes");
		ldb28.setFuncao("Programador");
		
		ListaDeNomes_Base ldb29 = new ListaDeNomes_Base();
		ldb29.setIntegrantes("Thaynã Moreira");
		ldb29.setFuncao("Programador");
		
		ListaDeNomes_Base ldb30 = new ListaDeNomes_Base();
		ldb30.setIntegrantes("Thyago Taian");
		ldb30.setFuncao("Programador");
		
		ListaDeNomes_Base ldb31 = new ListaDeNomes_Base();
		ldb31.setIntegrantes("Victor Rangel");
		ldb31.setFuncao("Programador");
		
		ListaDeNomes_Base ldb32 = new ListaDeNomes_Base();
		ldb32.setIntegrantes("Vitor Martins");
		ldb32.setFuncao("Programador");
		
		ListaDeNomes_Base ldb33 = new ListaDeNomes_Base();
		ldb33.setIntegrantes("Vitória Fernanda");
		ldb33.setFuncao("Programador");
		
		
		
		integrantes.add(ldb1);
		integrantes.add(ldb2);
		integrantes.add(ldb3);
		integrantes.add(ldb4);
		integrantes.add(ldb5);
		integrantes.add(ldb6);
		integrantes.add(ldb7);
		integrantes.add(ldb8);
		integrantes.add(ldb9);
		integrantes.add(ldb10);
		integrantes.add(ldb11);
		integrantes.add(ldb12);
		integrantes.add(ldb13);
		integrantes.add(ldb14);
		integrantes.add(ldb15);
		integrantes.add(ldb16);
		integrantes.add(ldb17);
		integrantes.add(ldb18);
		integrantes.add(ldb19);
		integrantes.add(ldb20);
		integrantes.add(ldb21);
		integrantes.add(ldb22);
		integrantes.add(ldb23);
		integrantes.add(ldb24);
		integrantes.add(ldb25);
		integrantes.add(ldb26);
		integrantes.add(ldb27);
		integrantes.add(ldb28);
		integrantes.add(ldb29);
		integrantes.add(ldb30);
		integrantes.add(ldb31);
		integrantes.add(ldb32);
		integrantes.add(ldb33);

	}
}
