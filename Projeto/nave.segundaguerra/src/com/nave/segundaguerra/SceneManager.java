package com.nave.segundaguerra;

import android.app.Activity;

public class SceneManager extends Activity {

	private static SceneManager instance;
	


	public static SceneManager getInstance() {
		return instance;
	}

	public static void setInstance(SceneManager instance) {
		SceneManager.instance = instance;
	}
	
}