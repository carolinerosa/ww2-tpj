package com.nave.segundaguerra.saveandload;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.Resources;

public class LoadGame implements IPrefix {

	private static LoadGame instance;

	private LoadGame() {
	}

	public static LoadGame getInstance() {
		if (instance == null)
			instance = new LoadGame();

		return instance;
	}

	public void loadGame(Context context, Resources resources) {
		String line;

		try {
			FileInputStream fis = context.openFileInput(SaveGame.getInstance().getFileName());
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			while ((line = reader.readLine()) != null) {
				
				 if (line.startsWith(PREFIX_NAME))
					loadNames(line, resources);
			}

			// Terminar de ler os arquivos
			reader.close();
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadNames(String line, Resources resources) {
		// Ignorar o início da linha.
		String mainContentLine = line.substring(PREFIX_NAME.length());
		String[] names = mainContentLine.split(",");
		
		
	}
}
