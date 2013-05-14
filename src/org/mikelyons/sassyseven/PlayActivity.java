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
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayActivity extends Activity {
	
	// View, container, and text
	RelativeLayout playBallContainer;
	RelativeLayout playRotateView;
	TextView playText;
	// Sensor manager
	private SensorManager mSensorManager;
	// Array of sounds
	private final int[] sounds = { R.raw.allihear1, R.raw.allihear2, R.raw.allihear3, R.raw.answerdat1, R.raw.answerdat2, R.raw.answerdat3, R.raw.areyoustupid1, R.raw.areyoustupid2, R.raw.biggirlpanties1, R.raw.franklymydear1, R.raw.franklymydear2, R.raw.franklymydear3, R.raw.franklymydear4, R.raw.girldonteven1, R.raw.girldonteven2, R.raw.girldonteven3, R.raw.girldonteven4, R.raw.givesadamn1, R.raw.givesadamn2, R.raw.hellno1, R.raw.hellno2, R.raw.hellno3, R.raw.hellno4, R.raw.ignoreyoulater1, R.raw.ignoreyoulater2, R.raw.ignoreyoulater3, R.raw.ignoreyoulater4, R.raw.liketoagree1, R.raw.liketoagree2, R.raw.mmgirl1, R.raw.nobodygottime1, R.raw.nobodygottime2, R.raw.nobodygottime3, R.raw.ohnoyoudidnt1, R.raw.ohnoyoudidnt2, R.raw.ohnoyoudidnt3, R.raw.ringonit1, R.raw.ringonit2, R.raw.sayingsomething1, R.raw.sayingsomething2, R.raw.sayingsomething3, R.raw.shutyomouth1, R.raw.whatno1, R.raw.whatno2, R.raw.whatno3, R.raw.whatwhatwhat1};
	// Array of phrases
	private final String[] phrases = {"All I Hear Is\nBuzzing\n\n", "All I Hear Is\nBuzzing\n\n", "All I Hear Is\nBuzzing\n\n", "I'm ain't gonna answer that"}; // TODO Write all of these phrases with formatting
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
			// Select phrase
			int selection = (int)(Math.random()*sounds.length);
			// Play Sounds
			mp = MediaPlayer.create(this, sounds[selection]);
			mp.start();
			// Update text
			playText.setText(phrases[0]);
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
