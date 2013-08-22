package com.nave.segundaguerra.servidorecliente.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;

public class ImageLibrary 
{	
	private Resources resources;
	private static ImageLibrary _instance;
	private HashMap<String, Bitmap> library;


	private ImageLibrary() 
	{
		library = new HashMap();
	}
	
	public static ImageLibrary getInstance()
	{
		if(_instance == null)
			_instance = new ImageLibrary();
		
		return _instance;
	}

	public void AddResources(Resources res)
	{
		this.resources = res;
	}
	
	public void addImage(String key,int id)
	{
		Bitmap currentImage = BitmapFactory.decodeResource(this.resources, id);
		library.put(key, currentImage);
	}
	
	public void addImage(String key,String location, Activity activity)
	{			
		try 
		{
			InputStream is = activity.getAssets().open(location);
			Bitmap image = BitmapFactory.decodeStream(is);
			library.put(key, image);
		} 
		catch (IOException e) 
		{
			Log.e("ERROR-IMAGELOAD", "Erro ao carregar a imagem");
		}
	}

	public Bitmap getImage(String key)
	{
		return library.get(key);
	}
	
	public Point getImageSize(String key)
	{
		int x = library.get(key).getWidth();
		int y = library.get(key).getHeight();
		return new Point(x,y);
	}
	
}
