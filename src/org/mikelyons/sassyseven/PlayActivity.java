/**
 * PlayActivity
 * @author Nic Manoogian
 * @author Mike Lyons
 */
package org.mikelyons.sassyseven;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayActivity extends Activity {
	
	public static int ANIMATION_DURATION = 500;
	
	// View, center, container, text, triangle
	RelativeLayout playBallContainer;
	RelativeLayout playCenterView;
	RelativeLayout playRotateView;
	TextView playText;
	// Image Views
	ImageView holyBallWithTrim;
	ImageView playTriangle;
	ImageView bubble;
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
	
	float bubble_tx = -1;
	float bubble_ty = -1;
	boolean animating = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		// Set container, view, and text
		playBallContainer = (RelativeLayout) findViewById(R.id.playBallViewContainer);
		playCenterView = (RelativeLayout) findViewById(R.id.centerView);
 		playRotateView = (RelativeLayout) findViewById(R.id.playRotateContainer);
		playText = (TextView) findViewById(R.id.playTextField);
		
		//Image Views
		holyBallWithTrim = (ImageView) findViewById(R.id.holyballwithtrim);
		playTriangle = (ImageView) findViewById(R.id.playTriangle);
		bubble = (ImageView) findViewById(R.id.playBubble);
		
		// Load Images
		//holyBallWithTrim.setImageBitmap(decodeFile( R.drawable.holyballwithrim, holyBallWithTrim.getWidth(), holyBallWithTrim.getHeight() ));
		//bubble.setImageBitmap( decodeFile( R.drawable.bubble1, bubble.getWidth(), bubble.getHeight() ) );
		
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
	    
	    playTriangle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playUpdate();
			}
	    });
	}
	
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
			
			bubbleUpdate(x,y);
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// NO OP
		}

	};
	
	//decodes image and scales it to reduce memory consumption
	private Bitmap decodeFile(int res, int height, int width){
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.outHeight = 500;
		options.outWidth = 500;
		options.inSampleSize = calculateInSampleSize(options, height, width );
		return BitmapFactory.decodeResource(getResources(), res, options);
	}
	
	public static int calculateInSampleSize(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}
	
	public void bubbleUpdate( float x, float y ) {
		// Calculate the position of the bubble based on the acceleration of the phone
		bubble_tx = (int)((x/10)*(playCenterView.getWidth()/2) + playCenterView.getWidth()/2);
		bubble_ty = (int)playCenterView.getHeight() - (int)((y/10)*(playCenterView.getHeight()/2) + playCenterView.getHeight()/2);
//		if( !animating ) {
//			animating = true;
//			Animation move = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_PARENT, bubble_tx,
//					TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_PARENT, bubble_ty );
			
//			Log.v("Starting at: ", "x: " + bubble.getX());
//			Log.v("Going to: ", "x: " + bubble_tx );
//			
//			final float current_tx = bubble_tx;
//			final float current_ty = bubble_ty;
			
//			move.setDuration(100);
			
//			move.setAnimationListener(new AnimationListener() {
//				@Override
//				public void onAnimationStart(Animation animation) {
//					// TODO Auto-generated method stub
//				}
//				@Override
//				public void onAnimationRepeat(Animation animation) {
//					// TODO Auto-generated method stub
//				}
//				@Override
//				public void onAnimationEnd(Animation animation) {
					int tryx = (int)bubble.getX();
					int tryy = (int)bubble.getY();
					int JUMPX = Math.abs(tryx - (int)bubble_tx)/20;
					int JUMPY = Math.abs(tryy - (int)bubble_ty)/20;
//					Log.v(JUMPX+"", JUMPY+"");
					
					if (tryx - JUMPX > bubble_tx)
					{
						tryx-=JUMPX;
					}
					else if (tryx + JUMPX < bubble_tx)
					{
						tryx+=JUMPX;
					}
					if (tryy - JUMPY> bubble_ty)
					{
						tryy-=JUMPY;
					}
					else if (tryy + JUMPY< bubble_ty)
					{
						tryy+=JUMPY;
					}

					setBubbleMargins(tryx, tryy);
//					setBubbleMargins( (int) current_tx, (int) current_ty );
//					animating = false;
//				}
//			});
			
			//move.setFillAfter(true);
			
//			bubble.clearAnimation();
//			bubble.setAnimation(move);
//		}
	}
	
	public void setBubbleMargins( int x, int y ) {
		RelativeLayout.LayoutParams lp = (bubble.getLayoutParams() == null)? 
						new RelativeLayout.LayoutParams(bubble.getWidth(), bubble.getHeight()): 
						(RelativeLayout.LayoutParams)bubble.getLayoutParams();
		
		lp.setMargins(x, y, 0, 0);
		
		bubble.setLayoutParams(lp);
		playCenterView.invalidate();
	}
	
	/**
	 * Chooses and plays a random sound
	 * Updates the play text
	 */
	public void playUpdate() {
		if (!mp.isPlaying()) {
			// Get the phrase
			final String phrase = model.getRandomPhrase();
			// Play Sounds
			mp = MediaPlayer.create(this, model.getSound(phrase));
			mp.start();
			
			// Fade out/in triangle
			Animation fadeOut = new AlphaAnimation(1, 0);
			fadeOut.setDuration(PlayActivity.ANIMATION_DURATION);
			
			fadeOut.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					// Update text
					playText.setText(phrase);
					
					// Fade back in
					Animation fadeIn = new AlphaAnimation(0,1);
					fadeIn.setDuration(PlayActivity.ANIMATION_DURATION);
					
					playRotateView.clearAnimation();
					playRotateView.setAnimation(fadeIn);
				}
				@Override
				public void onAnimationRepeat(Animation animation) {}
				@Override
				public void onAnimationStart(Animation animation) {}
			});
			playRotateView.clearAnimation();
			playRotateView.setAnimation(fadeOut);
		}
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
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
		playBallContainer.clearAnimation();
		
		Animation an = new AlphaAnimation( 0,1 );
		an.setDuration(PlayActivity.ANIMATION_DURATION);
//		an.setFillAfter(true);
		
		playBallContainer.setAnimation(an);
	}
	
	/**
	 * Fades the ball out
	 * @param r Called after animation has finished
	 */
	public void fadeOut(final Runnable r)
	{
		playBallContainer.clearAnimation();
		
		Log.v("Ball", "Fading out");
		Animation an = new AlphaAnimation( 1,0 );
		an.setDuration(PlayActivity.ANIMATION_DURATION);
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
					finish(); // Ends activity
					overridePendingTransition(0, 0); // Stops closing animation
				}
				
			});
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
