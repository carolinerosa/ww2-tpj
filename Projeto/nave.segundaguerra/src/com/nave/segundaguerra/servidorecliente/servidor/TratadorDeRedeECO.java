package com.nave.segundaguerra.servidorecliente.servidor;

import com.nave.segundaguerra.R;
import com.nave.segundaguerra.servidorecliente.util.Conexao;
import com.nave.segundaguerra.servidorecliente.util.Const;
import com.nave.segundaguerra.servidorecliente.util.DepoisDeReceberDados;

import android.util.Log;

public class TratadorDeRedeECO implements DepoisDeReceberDados {

	public void execute(Conexao origem, String linha) {

		Log.i(Const.TAG, "<<" + linha);
		if (linha != null) {
			origem.write("eco : " + linha);
		}

	}

}
