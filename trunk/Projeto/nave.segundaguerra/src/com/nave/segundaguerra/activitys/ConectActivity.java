package com.nave.segundaguerra.activitys;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.activitys.views.ViewDeRede;
import com.nave.segundaguerra.servidorecliente.cliente.ControleDeUsuariosCliente;
import com.nave.segundaguerra.servidorecliente.cliente.GeneralCliente;
import com.nave.segundaguerra.servidorecliente.cliente.MedicoCliente;
import com.nave.segundaguerra.servidorecliente.cliente.SoldadoCliente;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;
import com.nave.segundaguerra.servidorecliente.servidor.ControleDeUsuariosServidor;
import com.nave.segundaguerra.servidorecliente.servidor.GerenteDEConexao;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.Const;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.DialogHelper;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.RedeUtil;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

public class ConectActivity extends Activity implements Killable {

	public static final String TAG = "REDE";
	private static final int PORTA_PADRAO = 2121;

	private GerenteDEConexao gerente;

	private EditText editUsuario;
	private EditText editIP;

	private String usuario;

	private ViewDeRede viewDoJogo;

	private Conexao conexao;

	private Context thisContext;

	private boolean escolheuNome, escolheuTime;

	GerenciadorActivity gerenteCenas = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewUtil.enableMainConfig(this);

		setContentView(R.layout.activity_conect);

		editIP = (EditText) findViewById(R.id.IP);
		editUsuario = (EditText) findViewById(R.id.Name);
		TextView text = (TextView) findViewById(R.id.Classe);
		gerenteCenas = GerenciadorActivity.GetInstance();
		
		String classeName = "";
		
		switch(gerenteCenas.gerenciadorClasse)
		{
			case general:
			classeName = "Classe - General";
			break;
		
			case soldado:
			classeName = "Classe - Soldado";
			break;
			
			case medico:
			classeName = "Classe - Médico";
			break;
		}
		
		text.setText(classeName);
		
		thisContext = this;

	}

	public void OnClickAzul(View sender) {
		GerenciadorActivity.GetInstance().getPlayer().setTime(Const.TIMEAZUL);
		escolheuTime = true;
	}

	public void OnClickVermelho(View sender) {
		GerenciadorActivity.GetInstance().getPlayer()
				.setTime(Const.TIMEVERMELHO);
		escolheuTime = true;
	}

	public void Click_criarServidor(View sender) 
	{
		ViewUtil.closeKeyboard(this);
		
		String serverIp = RedeUtil.getLocalIpAddress();

		if (serverIp == null) {
			DialogHelper.message(this, "Conecte-se a alguma rede");
		}

		else if (!checkNickname(getName().length())) {
			DialogHelper.message(this,
					"Insira um nickname entre 1 e 10 caracteres.");
		}

		else if (!escolheuTime) {
			DialogHelper.message(this, "Escolha o seu Time.");
		} 
		else 
		{
			if (gerente != null) {
				gerente.killMeSoftly();
			}

			gerente = new GerenteDEConexao(PORTA_PADRAO);
	
			gerente.iniciarServidor(new ControleDeUsuariosServidor());
	
			final DepoisDeReceberDados tratadorDeDadosDoCliente = new ControleDeUsuariosCliente();
	
			usuario = GerenciadorActivity.GetInstance().getPlayer().getNome();

			Thread socketThread = new Thread(new Runnable() {
	
				public void run() {
					Socket s;
					try {
						s = new Socket("127.0.0.1", PORTA_PADRAO);
						conexao = new Conexao(s, usuario, tratadorDeDadosDoCliente);
	
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		
			socketThread.start();
			
			SoundManager.getInstance().StopSong("MenuSound");
			DialogHelper.message(this, "Endereço Server: " + serverIp);
	
			viewDoJogo = new ViewDeRede(this, conexao,(ControleDeUsuariosCliente) tratadorDeDadosDoCliente);
			setContentView(viewDoJogo);
		}

	}

	public String getName() {
		return editUsuario.getText().toString();
	}

	private boolean checkNickname(int nicknameLenght) {
		if (nicknameLenght < 1 || nicknameLenght > 10) {
			return false;
		}

		ViewUtil.closeKeyboard(this);
		GerenciadorActivity.GetInstance().getPlayer()
				.setNome(editUsuario.getText().toString());

		return true;
	}

	public void Click_conectar(View sender) {

		if (!escolheuNome) {
			DialogHelper.message(this,
					"Insira um nickname entre 1 e 10 caracteres.");
		}

		if (!escolheuTime) {
			DialogHelper.message(this, "Escolha o seu Time.");
		}

		String ip = editIP.getText().toString();

		if (ip.trim().length() == 0) {
			DialogHelper.message(this,
					"endereco do servidor nao pode ser vazio");

		} else {
			ViewUtil.closeKeyboard(this);

			try {
				SoundManager.getInstance().StopSong("MenuSound");

				DepoisDeReceberDados tratadorDeDadosDoCliente = new ControleDeUsuariosCliente();

				usuario = GerenciadorActivity.GetInstance().getPlayer()
						.getNome();
				Socket s = new Socket(ip, PORTA_PADRAO);
				conexao = new Conexao(s, usuario, tratadorDeDadosDoCliente);

				// garante que view possa recuperar a lista de usuarios atual e
				// enviar dados pela rede
				viewDoJogo = new ViewDeRede(this, conexao,
						(ControleDeUsuariosCliente) tratadorDeDadosDoCliente);
				setContentView(viewDoJogo);

			} catch (UnknownHostException e) {
				DialogHelper.error(this, "Erro ao conectar com o servidor",
						ConectActivity.TAG, e);

			} catch (IOException e) {
				DialogHelper.error(this, "Erro ao comunicar com o servidor",
						ConectActivity.TAG, e);
			}
		}
	}

	public void onBackPressed() 
	{
		killMeSoftly();
	}

	public void killMeSoftly() {
		ElMatador.getInstance().killThenAll();
		finish();
	}
}
