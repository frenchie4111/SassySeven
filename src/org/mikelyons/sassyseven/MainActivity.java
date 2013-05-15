package org.mikelyons.sassyseven;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Button;

public class MainActivity extends Activity {

	public static int ANIMATION_DURATION = 1000; // Time for menu animation. In ms
	
	// Container view
	RelativeLayout homeContainer;
	
	// Ball stuff
	RelativeLayout ballView;
	
	ImageView ballBackground;
	ImageView ballSeven;
	ImageView ballGlare;
	ImageView playSassyBanner;
	
	// Menu Stuff
	RelativeLayout homeMenuView;
	
	Button homeMenuPlay;
	Button homeMenuHelp;
	Button homeMenuAboutUs;
	
	private boolean animating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Container stuff
		homeContainer = (RelativeLayout) findViewById(R.id.homeContainer);
		
		// Header stuff
		playSassyBanner = (ImageView) findViewById(R.id.sassyBanner);
		
		// Ball stuff
		ballView = (RelativeLayout) findViewById(R.id.homeBallView); // The ball that rolls in
		
		ballBackground = (ImageView) findViewById(R.id.homeBallBackground); // The pink part of the ball
		ballSeven = (ImageView) findViewById(R.id.homeBallSeven); // The seven in the front of the ball
		ballGlare = (ImageView) findViewById(R.id.homeBallGlare); // The glare of the ball
		
		// Menu stuff
		homeMenuView = (RelativeLayout) findViewById(R.id.homeMenuView); // The view that contains the 3 menu buttons
		
		homeMenuPlay = (Button) findViewById(R.id.homeMenuPlayButton); // Play button
		homeMenuHelp = (Button) findViewById(R.id.homeMenuHelpButton); // Help button
		homeMenuAboutUs = (Button) findViewById(R.id.homeMenuAboutUsButton); // About us button
		
		addListeners();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		animateIn();
		addListeners();
	}
	
	/**
	 * Calls the functions to animate in
	 */
	public void animateIn() {
		rollIn();
		fadeInMenu();
		slideInSassy();
	}
	
	/**
	 * Rolls the ball in (Home Menu Animation)
	 */
	public void rollIn() {
		ballSeven.clearAnimation();
		
		Log.v("MainActivity", "Rolling the ball in");
		Animation translate = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, 0.8f, TranslateAnimation.RELATIVE_TO_SELF, 0,
				TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0);
		
		translate.setDuration(ANIMATION_DURATION);
		translate.setRepeatCount(0);
		//translate.setFillAfter(true);
		ballView.setAnimation(translate);
		
	    // Create an animation instance
	    Animation an = new RotateAnimation(360.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

	    // Set the animation's parameters
	    an.setDuration(ANIMATION_DURATION);               // duration in ms
	    an.setRepeatCount(0);                // -1 = infinite repeated
	    an.setRepeatMode(Animation.REVERSE); // reverses each repeat
	    //an.setFillAfter(true);  
	    
	    ballSeven.setAnimation(an);
	}
	
	/**
	 * Fades the menu view in (Home Menu Animation)
	 */
	public void fadeInMenu() {
		homeMenuView.clearAnimation();		
		
		Animation an = new AlphaAnimation( 0.0f, 1.0f );
		an.setDuration(ANIMATION_DURATION);
		//an.setFillAfter(true);
		
		homeMenuView.setAnimation(an);
	}
	
	/**
	 * Animates menu out
	 * 
	 * Runnable r - Callback for after the code runs
	 */
	public void animateOut(Runnable r) {
		homeContainer.invalidate(); // Tell it things are changing soon
		rollOut(r);
		fadeOutMenu();
		slideOutSassy();
	}
	
	/**
	 * Slides the top menu text in
	 */
	public void slideInSassy() {
		playSassyBanner.clearAnimation();
		
		Animation translate = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, -0.8f, TranslateAnimation.RELATIVE_TO_SELF, 0,
				TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0);
		
		translate.setDuration(MainActivity.ANIMATION_DURATION);
		translate.setRepeatCount(0);
		translate.setFillAfter(true);
		playSassyBanner.setAnimation(translate);
	}
	
	/**
	 * Slides the top menu text out
	 */
	public void slideOutSassy() {
		playSassyBanner.clearAnimation();
		
		Animation translate = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, -1.2f,
				TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0);
		
		translate.setDuration(MainActivity.ANIMATION_DURATION);
		translate.setRepeatCount(0);
		translate.setFillAfter(true);
		playSassyBanner.setAnimation(translate);
	}
	
	/**
	 * Rolls ball out (Transformers)
	 */
	public void rollOut(final Runnable r) {
		ballView.clearAnimation();
		
		Log.v("Menu","Rolling out");
		Animation translate = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.8f,
				TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0);
		
		translate.setDuration(ANIMATION_DURATION);
		translate.setRepeatCount(0);
		translate.setFillAfter(true);
		
		ballView.invalidate(); // You have to call invalidate for the animation to run
		ballView.setAnimation(translate);
		
	    // Create an animation instance
	    Animation an = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

	    // Set the animation's parameters
	    an.setDuration(ANIMATION_DURATION);               	// duration in ms
	    an.setRepeatCount(0);                	// -1 = infinite repeated
	    an.setRepeatMode(Animation.REVERSE);	// reverses each repeat
	    an.setFillAfter(true);  
	    
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
	    
	    ballSeven.invalidate();
	    ballSeven.setAnimation(an);
	}
	
	/**
	 * Fades menu out
	 */
	public void fadeOutMenu() {
		Animation an = new AlphaAnimation( 1.0f, 0.0f );
		an.setDuration(ANIMATION_DURATION);
		an.setFillAfter(true);
		
		homeMenuView.setAnimation(an);
		homeMenuView.invalidate();
	}
	
	
	public void addListeners() {
		// Play button
		homeMenuPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("Menu","Play clicked");
				if( !animating ) {
					animating = true;
					animateOut(new Runnable() {
						@Override
						public void run() {
							Intent i = new Intent(MainActivity.this, PlayActivity.class);
							i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							startActivity(i);
							animating = false;
						}
					}); // This animates it out and the runnable starts the PlayActity when the animation finishes 
				}
			}
		});
		
		// Help button
		homeMenuHelp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		// About Us Button
		homeMenuAboutUs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
