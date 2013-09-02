package com.nave.segundaguerra.servidorecliente.cliente;

import com.nave.segundaguerra.game.CalculoImagem;
import com.nave.segundaguerra.servidorecliente.util.Const;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.Protocolo;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



public class GeneralCliente extends PlayerCliente{
	//private String nome;
	 private Bitmap meuBotao;
	 private PointF posBotao = new PointF(100,100);
	 private int larguraDaTela;
	 private int alturaDaTela;
	 
	public GeneralCliente(String nome, Point position) {
		super(nome, position, "General");
		//Coloquei 'Soldado' para teste apenas. por: Nuno
	}
	
	public Bitmap getBotao(){
		return this.meuBotao;
	}
	public PointF getPosBotao(){
		return this.posBotao;
	}
	@Override
	public void CarregarImagem(int larguraTela, int alturaTela){
		this.setImage(ImageLibrary.getInstance().getImage("Soldado"));
		this.larguraDaTela = larguraTela;
		this.alturaDaTela = alturaTela;
		this.meuBotao = ImageLibrary.getInstance().getImage("ZoomGeneral");
		this.meuBotao = CalculoImagem.getInstance().RedimensionarImagem(this.meuBotao, (8 * alturaTela) / 100, (8 * larguraTela) / 100);
		this.posBotao = new PointF(larguraTela / 1.1f, alturaTela / 7);
	}
	
	@Override
	public void sendTiro(DadosDoCliente dadosCliente, Point toque){
		dadosCliente.getCliente().write(Protocolo.PROTOCOL_ITEM + "," + "MunicaoArma" + "," + toque.x + "," + toque.y);
		Log.i(Const.TAG,"Entrou no general.");
	}
	
	@Override
	public void setPos(DadosDoCliente dadosCliente, Point pos){
		//dadosCliente.setPos(pos);
		if(!this.telaCheia){
		dadosCliente.setX(pos.x);
		dadosCliente.setY(pos.y);
		}
	}
	@Override
	public void draw(Canvas canvas){
		if(!this.verificarTelaCheia()){
			canvas.drawBitmap(meuBotao, posBotao.x, posBotao.y, paint);
		}
	/*if(!this.verificarTelaCheia()){
		if(time == Const.TIMEVERMELHO)
		{	
			this.paint.setColor(Color.RED);
		}else if(time == Const.TIMEAZUL){	
			this.paint.setColor(Color.BLUE);
		}else{
			this.paint.setColor(Color.BLACK);
		}
		
		this.playerSprite.Update(30);
		
		if(this.getImage() != null){
			
			this.playerSprite.Draw(canvas);
			
		}		
		canvas.drawText("<" + this.nome + ">", this.x - 30
				, this.y + 30, this.paint);
	}else{
		float posX = ((alturaDaTela * this.getPosition().x) / ImageLibrary.getInstance().getImage("Cenario").getHeight()) * 1.25f;
		float posY = ((larguraDaTela * this.getPosition().y) /  ImageLibrary.getInstance().getImage("Cenario").getWidth()) / 1.25f;
		
		canvas.drawCircle(posX,posY,5, this.paint);
		//canvas.drawCircle(jogador.getPosition().x, jogador.getPosition().y,10, paint);
	}*/
		//canvas.drawBitmap(meuBotao, posBotao.x, posBotao.y, paint);
	}
	
	@Override
	public void verificarZoom(float x, float y, int altura, int largura){
				if(x >= posBotao.x && x < ( posBotao.x + meuBotao.getWidth()) && 
				   y >= posBotao.y && y < ( posBotao.y + meuBotao.getHeight())){
					Log.i("ACERTOU !", "Acertei a imagem haha");
					Log.i("ACERTOU !", "x: " + largura + " y: " + altura);
					if(!this.telaCheia){
					MapaCliente.getInstance().setImage(altura, largura);
					//meuJogador.setPosition(new Point((this.getHeight() - ImageLibrary.getInstance().getImage("Cenario").getHeight()) + meuJogador.getPosition().x, (this.getWidth() - ImageLibrary.getInstance().getImage("Cenario").getWidth()) + meuJogador.getPosition().y));
					//meuJogador.setPos(dadosDoCliente,new Point((this.getHeight() * meuJogador.getPosition().y) / ImageLibrary.getInstance().getImage("Cenario").getHeight(), (this.getWidth() * meuJogador.getPosition().x) /  ImageLibrary.getInstance().getImage("Cenario").getWidth()));
					this.telaCheia = true;
					}
					else{
						MapaCliente.getInstance().setImage(ImageLibrary.getInstance().getImage("Cenario").getHeight(),ImageLibrary.getInstance().getImage("Cenario").getWidth());
						//meuJogador.setPosition(new Point((ImageLibrary.getInstance().getImage("Cenario").getHeight() - this.getHeight()) + meuJogador.getPosition().x, (ImageLibrary.getInstance().getImage("Cenario").getWidth() - this.getWidth()) + meuJogador.getPosition().y));
						//meuJogador.setPos(dadosDoCliente,new Point((ImageLibrary.getInstance().getImage("Cenario").getHeight() * meuJogador.getPosition().y) / this.getHeight(), (ImageLibrary.getInstance().getImage("Cenario").getWidth() * meuJogador.getPosition().x) / this.getWidth()));
						this.telaCheia = false;
					}
				}
	}
	/*public String toString() {
		return "Jogador [nome=" + nome + ", x=" + x + ", y=" + y + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.setPosition(new Point(x, this.y));
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.setPosition(new Point(this.x, y));
	}

	public String toStringCSV() {
		return nome + "," + x + "," + y + ";";
	}*/
	
	
	

}
