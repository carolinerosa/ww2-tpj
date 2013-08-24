package com.nave.segundaguerra.servidorecliente.cliente;

import android.graphics.Point;
import android.util.Log;

import com.nave.segundaguerra.activitys.ConectActivity;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.ElMatador;
import com.nave.segundaguerra.servidorecliente.util.Killable;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;

public class DadosDoCliente implements Runnable, Killable {

	private Conexao cliente;
	private int updateTime;

	private int x;
	private int y;
	private String nome;
	private boolean ativo = true;

	public DadosDoCliente(Conexao cliente, int updateTime) 
	{
		this.cliente = cliente;
		this.nome = cliente.getId();
		this.updateTime = updateTime;
		ElMatador.getInstance().add(this);
	}

	public void run() 
	{
		while (ativo) 
		{
			try 
			{
				Thread.sleep(updateTime);
			} 
			
			catch (InterruptedException e) 
			{
				Log.e(ConectActivity.TAG, "interrupcao do run()");
			}

			cliente.write(Protocolo.PROTOCOL_MOVE + "," + x + "," + y);
		}

	}
	
	public String getNome()
	{
		return nome;
	}
	
	
	public int getX()
	{
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void killMeSoftly() 
	{
		ativo = false;
	}
	
	public void sendTiro(Point toque)
	{
		cliente.write(Protocolo.PROTOCOL_SHOOT + "," + nome + "," + toque.x + "," + toque.y);
	}
	public Conexao getCliente(){
		return this.cliente;
	}

}
