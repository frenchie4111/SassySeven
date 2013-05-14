package org.mikelyons.sassyseven;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class PlayActivity extends Activity {
	
	RelativeLayout playBallContainer;
	
	RelativeLayout playRotateView;
	private SensorManager mSensorManager;
	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity

	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = mAccel * 0.9f + delta; // perform low-cut filter
			
			if (mAccel > 2)
			{
				Log.v(SENSOR_SERVICE, "Phone shook");
			}
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

	};


	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		playBallContainer = (RelativeLayout) findViewById(R.id.playBallViewContainer);
		
		playRotateView = (RelativeLayout) findViewById(R.id.playRotateContainer);
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	    mAccel = 0.00f;
	    mAccelCurrent = SensorManager.GRAVITY_EARTH;
	    mAccelLast = SensorManager.GRAVITY_EARTH;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		animateIn();
		super.onResume();
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	/**
	 * Calls the functions to animate in
	 */
	public void animateIn() {
		rollIn();
		// TODO Slide in Sassy
	}
	
	/**
	 * Rolls the ball in (Home Menu Animation)
	 */
	public void rollIn() {
		Animation translate = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, 0.8f, TranslateAnimation.RELATIVE_TO_SELF, 0,
				TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0);
		
		translate.setDuration(MainActivity.ANIMATION_DURATION);
		translate.setRepeatCount(0);
		//translate.setFillAfter(true);
		playBallContainer.setAnimation(translate);
		
	    // Create an animation instance
	    Animation an = new RotateAnimation(360.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

	    // Set the animation's parameters
	    an.setDuration(MainActivity.ANIMATION_DURATION);               // duration in ms
	    an.setRepeatCount(0);                // -1 = infinite repeated
	    an.setRepeatMode(Animation.REVERSE); // reverses each repeat
	    //an.setFillAfter(true);  
	    
	    playRotateView.setAnimation(an);
	}
	
}
