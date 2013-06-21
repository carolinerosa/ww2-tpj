package com.nave.segundaguerra.saveandload;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

public class SaveGame extends Activity implements IPrefix {

	private static SaveGame instance;
	private final String fileName = "WWW2.txt";
	private StringBuffer sb;

	private SaveGame() {
	}

	public static SaveGame getInstance() {
		if (instance == null)
			instance = new SaveGame();

		return instance;
	}

	public void saveGame(Context context, String[]nicknames) {
		try {
			// A variável sb deve ser iniciada aqui. Assim, o arquivo txt antigo
			// será limpo e reescrito.
			sb = new StringBuffer();

			FileOutputStream fos = context.openFileOutput(getFileName(), MODE_PRIVATE);
			appendTextName(nicknames);

			fos.write(sb.toString().getBytes());
			fos.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}
	
	private void appendTextName(String[] nicknames){
		
		//Salva o valor do dinheiro em uma linha.
		sb.append(PREFIX_NAME);
		
		for (String s : nicknames)
		{
			sb.append(String.valueOf(nicknames));
			sb.append(",");
		}

		sb.append("\r\n");
	}
}
