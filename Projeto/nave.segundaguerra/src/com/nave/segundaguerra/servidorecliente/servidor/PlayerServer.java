package com.nave.segundaguerra.servidorecliente.servidor;

import java.util.Random;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Angulator;
import com.nave.segundaguerra.game.PontosTime;
import com.nave.segundaguerra.servidorecliente.cliente.DadosDoCliente;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

public class PlayerServer 
{
	protected int x;
	protected int y;
	protected Point destinationPosition = new Point();
	private int speed = 3;
	DadosDoCliente dadosCliente;

	public int dano;
	private int ammo = 20;
	private int life;
	TiroServer lastDagameTaken;
	PontosTime pontos;
	public Angulator anguloTiro;
	private Rect playerRect;
	
	// Grupo do Thyago
	protected String nome;
	public String time;

	public static final String TAG = "Jogador";

	public PlayerServer() 
	{
		this.life = 100;
		pontos = new PontosTime();
		this.playerRect = new Rect(0,0,0,0);
		this.playerRect = RectLibrary.getInstance().getRect("Soldado");
	}

	public void update() 
	{


		
		if (life <= 0) {
			if (lastDagameTaken.getOwner() == this) {
				lastDagameTaken.getOwner().pontos.ComunicacaoScore(time,
						"Perda");
			} else {
				lastDagameTaken.getOwner().pontos.ComunicacaoScore(time,
						"Ganho");
			}
		}

		checkMapa();

		this.x += destinationPosition.x/20;
		this.y += destinationPosition.y/20;
		
		this.setPosition(new Point(x, y));
		
	}

	public int getLife() {
		return life;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Point getPosition() {
		return new Point(playerRect.centerX(), playerRect.centerY());
	}
	public void setPosition(Point pos) {
		
		this.x = pos.x;
		this.y = pos.y;
		
		int tempX = this.x - this.playerRect.height()/2;
		int tempY = this.y - this.playerRect.width()/2;
		
		playerRect.set(tempX, tempY, x + this.playerRect.height()/2, y + this.playerRect.height()/2);
	}
	public void setDestino(Point destino){
		this.destinationPosition = destino;
	}

	public int getLargura() {
		return this.playerRect.width();
	}
	public int getAltura() {
		return this.playerRect.height();
	}

	public Angulator getAngulo() {
		return anguloTiro;
	}
	public void definirAngulo(PointF touch) {
		anguloTiro = new Angulator(new PointF(x, y), touch);
	}
	
	public int getMunicao() {
		return ammo;
	}
	public void diminuirMunicao() {
		this.ammo--;
	}
	public void Recarregar() {
		ammo = 20;
	}

	public void respawn() {
		Random random = new Random();
		Point novaPosicao = new Point();
		novaPosicao.x = random.nextInt(MapaServer.getLargura());
		novaPosicao.y = random.nextInt(MapaServer.getAltura());
		this.setPosition(novaPosicao);
	}
	
	public void collisionTiro(TiroServer tiro, int dano) 
	{
		lastDagameTaken = tiro;
		life -= dano;

		if (life <= 0)
		{
			respawn();
		}
	}

	private void checkMapa() {
		
		if (this.x < MapaServer.getX()) {
			this.setPosition(new Point(MapaServer.getX(), y));
		}

		if (this.y < MapaServer.getY()) {
			this.setPosition(new Point(x, MapaServer.getY()));
		}

		if (this.x > MapaServer.getLargura()) {
			this.setPosition(new Point(MapaServer.getLargura(), y));
		}

		if (this.y > MapaServer.getAltura()) {
			this.setPosition(new Point(x, MapaServer.getAltura()));
		}

	}
	
	/*public int getHeight(){
		return playerRect.height();
	}*/
	
	/*public int getWidth(){
		return playerRect.width();
	}*/
	
	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time = time;
	}
	
	public Rect getRect(){
		return playerRect;
	}
	
	public String toString() {
		return "Jogador [nome=" + nome + ", x=" + x + ", y=" + y + "]";
	}
	public String toStringCSV() {
		return nome + "," + x + "," + y + ";";
	}
}