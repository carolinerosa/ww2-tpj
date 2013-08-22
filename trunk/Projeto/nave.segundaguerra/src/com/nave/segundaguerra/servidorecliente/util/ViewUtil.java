package com.nave.segundaguerra.servidorecliente.util;

import com.nave.segundaguerra.activitys.ActivityManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class ViewUtil 
{
	private static ActivityManager _manager = null;
	
	public static void closeKeyboard(Activity activity) {

		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(activity.getWindow().getCurrentFocus()
				.getWindowToken(), 0);

	}
	
	public static void dialogAlertButton(Activity activity,String Title,String Message,String positiveButton,String negativeButton)
	{
		_manager = (ActivityManager) activity;
		
		new AlertDialog.Builder(activity)
		
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(Title)
		.setMessage(Message)
		
		.setPositiveButton(positiveButton,new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog,	int which) 
			{
				onBack();
			}
			
		})
		
		.setNegativeButton(negativeButton, null)
		.show();
	}
	
	private static void onBack()
	{
		_manager.backFunction();
	}
	
	public static void enableMainConfig(Activity activity)
	{
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	public static void enableElement(mainPermissions permissions,Activity activity)
	{
		switch (permissions) 
		{
			case FULLSCREEN:
				activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
				break;
				
			case LANDSCAPE:
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				break;
				
			case SCREENON:
				activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				break;
				
			case NOTITLE:
				activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
				break;
				
			default:
				break;
		}
	}
	
	public enum mainPermissions
	{
		FULLSCREEN,
		LANDSCAPE,
		SCREENON,
		NOTITLE
	}
}


