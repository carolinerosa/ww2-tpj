package com.nave.segundaguerra.servidorecliente.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.RedeUtil;

import android.util.Log;


public class GerenteDEConexao implements Runnable, Killable {

	private static final String TAG = "gerente de conexao";
	private Thread ouvinte;

	public GerenteDEConexao(int porta) {
		this.porta = porta;
		conexoes = new ArrayList<Conexao>();
		ElMatador.getInstance().add(this);
	}

	private int porta;
	private DepoisDeReceberDados depoisDeReceberDadosHandler;

	public DepoisDeReceberDados getDepoisDeReceberDadosHandler() {
		return depoisDeReceberDadosHandler;
	}

	public void setDepoisDeReceberDadosHandler(
			DepoisDeReceberDados depoisDeReceberDadosHandler) {
		this.depoisDeReceberDadosHandler = depoisDeReceberDadosHandler;
	}

	public int getPorta() {
		return porta;
	}

	private boolean ativo = true;
	private ServerSocket servidor;
	private ArrayList<Conexao> conexoes;

	public ArrayList<Conexao> getConexoes() {
		return conexoes;
	}

	public void iniciarServidor(DepoisDeReceberDados depoisDeReceberDadosHandler) {

		if (depoisDeReceberDadosHandler == null) {
			throw new RuntimeException(
					"depoisDeReceberDadosHandler precisa ser informado");
		}

		try {

			this.depoisDeReceberDadosHandler = depoisDeReceberDadosHandler;

			servidor = new ServerSocket(porta);
			Log.i(TAG, "--- endereco do servidor : " + RedeUtil.getLocalIpAddress());

			ouvinte = new Thread(this);
			ouvinte.start();

		} catch (IOException e) {
			ativo = false;
			Log.e(TAG, "erro ao iniciar servidor", e);
		}
	}

	public void run() {
		while (ativo) {

			try {
				Socket conexao = servidor.accept();
				Log.i(TAG, "!! nova conexao : "
						+ conexao.getInetAddress().getHostAddress().toString());

				conexoes.add(new Conexao(conexao, "conexao-servidor:"
						+ conexao.getRemoteSocketAddress(),
						depoisDeReceberDadosHandler));

			} catch (IOException e) {
				Log.e(TAG, "erro ao aguardar nova conexao", e);
				ativo = false;
			}
		}
	}

	public void killMeSoftly() {
		if (servidor != null) {
			try {
				servidor.close();
			} catch (IOException e) {
				Log.e(TAG, "erro ao fechar conexao", e);
			}
		}

		// interrompe conexoes dos clientes
		ArrayList<Conexao> conexoes = getConexoes();
		for (Conexao conexao : conexoes) {
			conexao.killMeSoftly();
		}

		ativo = false;
		try {
			Thread.sleep(200);
			ouvinte.interrupt();
		} catch (InterruptedException e) {
			Log.e(TAG, "erro interrompendo conexao", e);
		}

		Log.i(TAG, "adeus() -- conexao encerrada.");
	}
}
