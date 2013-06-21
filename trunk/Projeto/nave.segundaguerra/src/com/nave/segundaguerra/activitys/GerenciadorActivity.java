package com.nave.segundaguerra.activitys;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Struct;

import com.nave.segundaguerra.servidorecliente.cliente.ControleDeUsuariosCliente;
import com.nave.segundaguerra.servidorecliente.cliente.JogadorCliente;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.Killable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GerenciadorActivity extends Activity implements Killable {

	private static GerenciadorActivity Instance;
	public static final String TAG = "GerenteCenas";
	private Bitmap imagemPlayer;
	private Bitmap imagemBala;
	private Bitmap imagemMapa;
	private static JogadorCliente meuPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Sem Activity e em modo Fullcreen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		GerenciadorActivity Inst = this;
		Instance = Inst;
		//setContentView(R.layout.activity_main);
		
		CenaMenu();
		
		try {
			InputStream is = this.getAssets().open("player.png");
			imagemPlayer = BitmapFactory.decodeStream(is);
			//imagemPlayer = Bitmap.createBitmap(b, 0, 0, 10, 10);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
		
		try {
			InputStream is = this.getAssets().open("projetil.png");
			imagemBala = BitmapFactory.decodeStream(is);
			//imagemPlayer = Bitmap.createBitmap(b, 0, 0, 10, 10);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
		
		try {
			InputStream is = this.getAssets().open("Cenario1.jpg");
			imagemMapa = BitmapFactory.decodeStream(is);
			//imagemPlayer = Bitmap.createBitmap(b, 0, 0, 10, 10);
		} catch (IOException e) {
			Log.e("hu3", "Erro carregando imagem");
		}
		
		meuPlayer = new JogadorCliente("MyPlayer", 100, 50);
	}
	
	
	
	public static GerenciadorActivity GetInstance(){
		return Instance;
	}
	
	
	public Bitmap getImagePlayer(){
		return imagemPlayer;
	}
	
	public Bitmap getImageBala(){
		return imagemBala;
	}
	
	public Bitmap getImageMapa(){
		return imagemMapa;
	}
	
	public void CenaMenu(){
		Intent intent = new Intent(GerenciadorActivity.this, MenuActivity.class);
		startActivity(intent);
	}
	
	public void CenaConect(){
		Intent intent = new Intent(GerenciadorActivity.this, ConectActivity.class);
		startActivity(intent);
	}
	
	public void CenaCreditos(){
		Intent intent = new Intent(GerenciadorActivity.this,CreditosActivity.class);
		startActivity(intent);
	}
	
	public void CenaDicas(){
		Intent intent = new Intent(GerenciadorActivity.this,DicasActivity.class);
		startActivity(intent);
	}
	
	public void CenaJogo(){
		Intent intent = new Intent(GerenciadorActivity.this, BatalhaActivity.class);
		startActivity(intent);
	}
	
	public void CenaJogoOnline(View view){
		setContentView(view);
	}
	
	public void CenaFinalJogo(){
		Intent intent = new Intent(GerenciadorActivity.this, FimDeJogoActivity.class);
		startActivity(intent);
	}
	
	public void CenaEscolhaTime(){
		Intent intent = new Intent(GerenciadorActivity.this, TelaEscolhaDeTimes_Activity.class);
		startActivity(intent);
	}
	
	
	public JogadorCliente getPlayer(){
		return meuPlayer;
	}

	public void onBackPressed() {
		Log.i(TAG,"--------- back pressed");

		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("abandonando o barco")
				.setMessage(
						"Tem certeza que vai embora ? vou sentir sua falta ...")
				.setPositiveButton("Adeus",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								killMeSoftly();
							}

						}).setNegativeButton("Então tá, fico + um pouco", null)
				.show();
	}

	/**
	 * realiza limpeza dos threads em funcionamento
	 */
	public void killMeSoftly() {
		ElMatador.getInstance().killThenAll();
		finish();
	}


}
