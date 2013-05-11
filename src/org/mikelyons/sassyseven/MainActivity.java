package org.mikelyons.sassyseven;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	RelativeLayout ballView;
	
	ImageView ballBackground;
	ImageView ballSeven;
	ImageView ballGlare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ballView = (RelativeLayout) findViewById(R.id.homeBallView); 
		
		ballBackground = (ImageView) findViewById(R.id.homeBallBackground);
		ballSeven = (ImageView) findViewById(R.id.homeBallSeven);
		ballGlare = (ImageView) findViewById(R.id.homeBallGlare);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		rollIn();
		fadeInMenu();
	}
	
	public void rollIn() {
		Animation translate = new TranslateAnimation( TranslateAnimation.RELATIVE_TO_SELF, 0.8f, TranslateAnimation.RELATIVE_TO_SELF, 0,
				TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0);
		
		translate.setDuration(1000);
		translate.setRepeatCount(0);
		translate.setFillAfter(true);
		ballView.setAnimation(translate);
		
	    // Create an animation instance
	    Animation an = new RotateAnimation(360.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

	    // Set the animation's parameters
	    an.setDuration(1000);               // duration in ms
	    an.setRepeatCount(0);                // -1 = infinite repeated
	    an.setRepeatMode(Animation.REVERSE); // reverses each repeat
	    an.setFillAfter(true);  
	    
	    ballSeven.setAnimation(an);
	}
	
	public void fadeInMenu() {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
