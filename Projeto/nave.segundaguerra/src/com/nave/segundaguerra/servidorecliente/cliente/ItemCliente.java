package com.nave.segundaguerra.servidorecliente.cliente;

import java.io.InputStream;

import com.nave.segundaguerra.activitys.GerenciadorActivity;
import com.nave.segundaguerra.game.Player;
import com.nave.segundaguerra.game.SpriteBala;
import com.nave.segundaguerra.game.SpriteCaixa;
import com.nave.segundaguerra.servidorecliente.util.Const;
import com.nave.segundaguerra.servidorecliente.util.ImageLibrary;
import com.nave.segundaguerra.servidorecliente.util.RectLibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class ItemCliente {

	private Bitmap caixa;
	private Point posicaoItem;
	private SpriteCaixa caixaSprite;
	private Paint paint;
	private Rect rect;
	
	public ItemCliente(Point pos)
	{
		this.posicaoItem = pos;
		this.caixa = ImageLibrary.getInstance().getImage("Caixa");
		caixaSprite = new SpriteCaixa();
		caixaSprite.setPosition(pos);
		
		caixaSprite.setPosition(new Point((int)posicaoItem.x, (int)posicaoItem.y));
	//	rect = RectLibrary.getInstance().getRect("Caixa");
		
	//	rect.set(caixa.getWidth()/2, caixa.getHeight()/2, caixa.getWidth(), caixa.getHeight());
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
	}
	
	public void DrawItem(Canvas canvas){
		if(caixa != null){
	//		canvas.drawBitmap(caixa,null,rect,paint);
	//		super.Draw(canvas, rect);
			Log.i(Const.TAG, "Caixa criada.");
			caixaSprite.setPosition(new Point((int)posicaoItem.x, (int)posicaoItem.y));
			caixaSprite.Draw(canvas);
		}else {
			canvas.drawCircle(posicaoItem.x, posicaoItem.y, 5, paint);
		}
	}
	
	public Point getPosition(){
		return posicaoItem;
	}
	
	public void setPosition(Point pos){
		this.posicaoItem = pos;
	//	balaSprite.setPosition(pos);
		
		
	}
	
}
