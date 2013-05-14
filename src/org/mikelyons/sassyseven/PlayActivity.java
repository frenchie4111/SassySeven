/**
 * PlayActivity
 * @author Nic Manoogian
 * @author Mike Lyons
 */
package org.mikelyons.sassyseven;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayActivity extends Activity {
	
	// View, container, and text
	RelativeLayout playBallContainer;
	RelativeLayout playRotateView;
	TextView playText;
	// Model
	private SassySevenModel model;
	// Sensor manager
	private SensorManager mSensorManager;
	// Media Player
	private static MediaPlayer mp;
	// acceleration apart from gravity
	private float mAccel;
	// current acceleration including gravity
	private float mAccelCurrent;
	// last acceleration including gravity
	private float mAccelLast;
	// Shake threshold
	private static final int SHAKE_THRESHOLD = 2;

	// Specialized shake sensor listener
	private final SensorEventListener mSensorListener = new SensorEventListener() {

		/**
		 * Runs playUpdate when shake is above threshold
		 */
		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = mAccel * 0.9f + delta; // perform low-cut filter
			
			// If acceleration is
			if (mAccel > SHAKE_THRESHOLD)
			{
				Log.v(SENSOR_SERVICE, "Phone shook");
				playUpdate();
			}
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// NO OP
		}

	};
	
	
	/**
	 * Chooses and plays a random sound
	 * Updates the play text
	 */
	public void playUpdate() {
		if (!mp.isPlaying()) {
			// Get the phrase
			String phrase = model.getRandomPhrase();
			// Play Sounds
			mp = MediaPlayer.create(this, model.getSound(phrase));
			mp.start();
			// Update text
			playText.setText(phrase);
		}
	}


	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		// Set container, view, and text
		playBallContainer = (RelativeLayout) findViewById(R.id.playBallViewContainer);
		playRotateView = (RelativeLayout) findViewById(R.id.playRotateContainer);
		playText = (TextView) findViewById(R.id.playTextField);
		
		// Set sensors and services
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	    
	    // Set acceleration variables
	    mAccel = 0.00f;
	    mAccelCurrent = SensorManager.GRAVITY_EARTH;
	    mAccelLast = SensorManager.GRAVITY_EARTH;
	    
	    // Initialize media player
	    mp = new MediaPlayer();
	    
	    // Initialize model
	    model = new SassySevenModel();
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
		fadeIn();
	}
	
	/**
	 * Fades the ball in (Home Menu Animation)
	 */
	public void fadeIn() {
		Animation an = new AlphaAnimation( 0,1 );
		an.setDuration(MainActivity.ANIMATION_DURATION);
		an.setFillAfter(true);
		
		playBallContainer.setAnimation(an);
	}
	
	/**
	 * Fades the ball out
	 * @param r Called after animation has finished
	 */
	public void fadeOut(final Runnable r)
	{
		Log.v("Ball", "Fading out");
		Animation an = new AlphaAnimation( 1,0 );
		an.setDuration(MainActivity.ANIMATION_DURATION);
		an.setFillAfter(true);
		
		playBallContainer.setAnimation(an);
//		Log.v("Ball", "Finished fade");

		an.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				r.run();
			}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationStart(Animation animation) {}
	    });
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Log.v("Button", "Back Pressed");
			fadeOut(new Runnable(){

				@Override
				public void run() {
					Log.v("Activity", "Trying to close");
					finish();
				}
				
			});
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
