package com.nave.segundaguerra.servidorecliente.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class RectLibrary 
{	
	private static RectLibrary _instance;
	private HashMap<String, Rect> library;


	private RectLibrary() 
	{
		library = new HashMap();
	}
	
	public static RectLibrary getInstance()
	{
		if(_instance == null)
			_instance = new RectLibrary();
		
		return _instance;
	}
	
	public void addRect(String key,Rect rect)
	{			
		library.put(key, rect);
	}

	public void addRectFromBitmap(String key,String imageKey,int offset)
	{			
		Bitmap currentBitmap = ImageLibrary.getInstance().getImage(imageKey);
		Rect currentRect = new Rect(-currentBitmap.getHeight()/offset,-currentBitmap.getWidth()/offset,currentBitmap.getHeight()/offset,currentBitmap.getWidth()/offset);
		library.put(key, currentRect);
	}
	
	public Rect getRect(String key)
	{
		return library.get(key);
	}
	
}
