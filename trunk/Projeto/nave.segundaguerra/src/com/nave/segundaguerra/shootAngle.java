package com.nave.segundaguerra;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class shootAngle extends View {

	float cx, cy, cw, ch;
	float posX, posY;
	
	private static float speed;
	
	public shootAngle(Context context) {
		super(context);
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Log.i(MainActivity.TAG, "down baby down !! ");
			cx = event.getRawX();
			cy = event.getRawY();
		}

		if (event.getAction() == MotionEvent.ACTION_POINTER_2_DOWN) {
			cw = event.getRawX();
			ch = event.getRawY();

			posX = (cw - cx) * getSpeed();
			posY = ((ch - cy) / posX) * getSpeed();
		}
		return super.onTouchEvent(event);
	}

	public static float getSpeed() {
		return speed;
	}
}