package com.nave.segundaguerra.activitys;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.activitys.views.ViewDeRede;
import com.nave.segundaguerra.servidorecliente.cliente.ControleDeUsuariosCliente;
import com.nave.segundaguerra.servidorecliente.cliente.SoundManager;
import com.nave.segundaguerra.servidorecliente.servidor.ControleDeUsuariosServidor;
import com.nave.segundaguerra.servidorecliente.servidor.GerenteDEConexao;
import com.nave.segundaguerra.servidorecliente.servidor.PlayerServer;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.Const;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.DialogHelper;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.RedeUtil;
import com.nave.segundaguerra.servidorecliente.util.ViewUtil;

public class ConectActivity extends Activity implements Killable
{

	public static final String TAG = "rede";
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
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.activity_conect);

		editIP = (EditText) findViewById(R.id.IP);
		editUsuario = (EditText) findViewById(R.id.Name);
		thisContext = this;
		gerenteCenas = GerenciadorActivity.GetInstance();
	}

	public void OnClickAzul(View sender)
	{
		GerenciadorActivity.GetInstance().getPlayer().setTime(Const.TIMEAZUL);
		escolheuTime = true;
	}

	public void OnClickVermelho(View sender)
	{
		GerenciadorActivity.GetInstance().getPlayer().setTime(Const.TIMEVERMELHO);
		escolheuTime = true;
	}

	public void Click_criarServidor(View sender)
	{

		try
		{
			ViewUtil.closeKeyboard(this);

			if (gerente != null)
			{
				gerente.killMeSoftly();
			}
			
			gerente = new GerenteDEConexao(PORTA_PADRAO);

			// gerente.iniciarServidor(new TratadorDeRedeECO());
			gerente.iniciarServidor(new ControleDeUsuariosServidor());

			DepoisDeReceberDados tratadorDeDadosDoCliente = new ControleDeUsuariosCliente();

			usuario = GerenciadorActivity.GetInstance().getPlayer().getNome();
			Socket s = null;
			s = new Socket("127.0.0.1", PORTA_PADRAO);
			conexao = new Conexao(s, usuario, tratadorDeDadosDoCliente);

			String serverIp = RedeUtil.getLocalIpAddress();

			if (serverIp == null)
			{

				DialogHelper.message(this, "Conecte-se a alguma rede");

			} else 
				if(!escolheuNome){
				DialogHelper.message(this, "Insira um nickname.");
			}else 
				if(!escolheuTime){
				DialogHelper.message(this, "Escolha o seu Time.");
			}else
			{
				SoundManager.getInstance().StopSong("MenuSound");

				DialogHelper
						.message(this, "endereco do servidor : " + serverIp);
				setTitle("servidor : " + serverIp);

				// garante que view possa recuperar a lista de usuarios atual e
				// enviar dados pela rede

				viewDoJogo = new ViewDeRede(this, conexao,
						(ControleDeUsuariosCliente) tratadorDeDadosDoCliente);
				setContentView(viewDoJogo);
			}

		} catch (UnknownHostException e)
		{
			DialogHelper.error(this, "Erro ao conectar com o servidor",
					ConectActivity.TAG, e);

		} catch (IOException e)
		{
			DialogHelper.error(this, "Erro ao comunicar com o servidor",
					ConectActivity.TAG, e);
		}
	}

	public void Click_salvarUsuario(View sender)
	{
		Log.i(TAG, "usuario salvo:" + usuario);

		if (setName().length() == 0)
		{
			DialogHelper.message(this, "Insira um nickname.");
		}

		if (setName().length() > 10)
		{
			DialogHelper.message(this, "Insira um nickname menor.");
		}

		if (setName().length() >= 1 && setName().length() <= 10)
		{
			ViewUtil.closeKeyboard(this);
			GerenciadorActivity.GetInstance().getPlayer()
					.setNome(editUsuario.getText().toString());
			Log.i(TAG, "usuario salvo:" + usuario);
			escolheuNome = true;

		}
	}

	public String setName()
	{
		return editUsuario.getText().toString();
	}

	public void Click_conectar(View sender)
	{

		if(!escolheuNome){
			DialogHelper.message(this, "Insira um nickname.");
		}
		
		if(!escolheuTime){
			DialogHelper.message(this, "Escolha o seu Time.");
		}
		
		String ip = editIP.getText().toString();

		if (ip.trim().length() == 0)
		{
			DialogHelper.message(this,
					"endereco do servidor nao pode ser vazio");

		} else
		{
			ViewUtil.closeKeyboard(this);

			try
			{
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

			} catch (UnknownHostException e)
			{
				DialogHelper.error(this, "Erro ao conectar com o servidor",
						ConectActivity.TAG, e);

			} catch (IOException e)
			{
				DialogHelper.error(this, "Erro ao comunicar com o servidor",
						ConectActivity.TAG, e);
			}
		}
	}

	/**
	 * @see http 
	 *      ://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm
	 *      -that-the-user-wishes-to-exit-an-android-activity
	 */
	public void onBackPressed()
	{
		Log.i(TAG, "--------- back pressed");

		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("abandonando o barco")
				.setMessage(
						"Tem certeza que vai embora ? vou sentir sua falta ...")
				.setPositiveButton("Adeus",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int which)
							{
								
								SoundManager.getInstance().playSound(R.raw.menu, "MenuSound",true, thisContext);

								killMeSoftly();

							}

						})
				.setNegativeButton("Então tá, fico + um pouco", null).show();
	}

	/**
	 * realiza limpeza dos threads em funcionamento
	 */
	public void killMeSoftly()
	{
		ElMatador.getInstance().killThenAll();
		finish();
	}
}
