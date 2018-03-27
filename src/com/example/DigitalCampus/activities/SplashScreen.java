package com.example.DigitalCampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;

//Activity for Splash screen
public class SplashScreen extends BaseActivity {

	private boolean mIsBackButtonPressed;
	private static final int SPLASH_DURATION = 2000; // 6 seconds
	private Handler myhandler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		myhandler = new Handler();

		// run a thread to start the home screen
		myhandler.postDelayed(new Runnable() {
			@Override
			public void run() {

				finish();

				if (!mIsBackButtonPressed) {
					// start the home activity
					Intent intent = new Intent(SplashScreen.this, Login.class);
					SplashScreen.this.startActivity(intent);
				}

			}

		}, SPLASH_DURATION);
	}

	// handle back button press
	@Override
	public void onBackPressed() {
		mIsBackButtonPressed = true;
		super.onBackPressed();
	}

}
