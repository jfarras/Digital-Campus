package com.example.DigitalCampus;

import android.app.Activity;

public class BaseActivity extends Activity{

	
	public MyApplication getMyApplication(){
		return (MyApplication)getApplication();
	}
	
}

