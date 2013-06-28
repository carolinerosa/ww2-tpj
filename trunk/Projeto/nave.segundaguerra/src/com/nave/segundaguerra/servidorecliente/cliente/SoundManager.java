package com.nave.segundaguerra.servidorecliente.cliente;

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class SoundManager {

	private String TAG = "Sound Manager";
	
	//private AudioManager audioManager;
	private HashMap<String, MediaPlayer> songs;
	private static SoundManager instance;
	
	private SoundManager() {
		this.songs = new HashMap<String, MediaPlayer>();
	}
	
	public static SoundManager getInstance()
	{
		if(instance == null)
		{
			instance = new SoundManager();
		}
		
		return instance;
	}
	
	public void playSound(final int source, String name, Context context)
	{
		final MediaPlayer mp = MediaPlayer.create(context, source);
		
		try{
			//mp.setLooping(true);
			mp.start();
			this.songs.put(name, mp);
		}catch(Exception e)
		{
			mp.stop();
			Log.i(TAG, "Erro no som"); 
		}
	}
	
	public void StopSong(String name)
	{
		try{
		this.songs.get(name).stop();
		this.songs.remove(name);
		}catch(Exception e)
		{
			
		}
	}
	
	public void StopAllSongs()
	{

	}

}