package com.example.DigitalCampus.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;

public class Login extends BaseActivity {
	private boolean save;
	private Button btn_login;
	Context context = this;
	private EditText mail;
	private EditText password;
	private CheckBox check;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			setContentView(R.layout.login_form);

			associateControls();
			prepareActivity();
		} else {
			SharedPreferences shared = getSharedPreferences("shared",
					MODE_PRIVATE);

			if (shared.contains("auto")) {
				Intent i = new Intent(getApplicationContext(),
						MenuPrincipalActivity.class);
				startActivity(i);
			} else {
				setContentView(R.layout.login_form);

				associateControls();
				prepareActivity();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		associateControls();
		prepareActivity();

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		/*
		 * case R.id.accept_add_car:
		 * 
		 * addCar();
		 * 
		 * return true;
		 */
		}

		return false;
	}

	private void associateControls() {

		this.password = (EditText) findViewById(R.id.password);
		this.mail = (EditText) findViewById(R.id.mail);
		this.check = (CheckBox) findViewById(R.id.checkBox1);
		this.check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				save = true;

			}
		});
		this.btn_login = (Button) findViewById(R.id.btnLogin);
		this.btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mail2 = mail.getText().toString();
				String password2 = password.getText().toString();
				System.out.println(mail2);
				Log.d("mail2", mail2);
				if (mail2.equals("administrador@salleurl.edu")
						&& password2.equals("123qwe")) {
					if (save)
						saveInformation();

					Intent i = new Intent(getApplicationContext(),
							MenuPrincipalActivity.class);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(),
							"Dadas incorrectas", Toast.LENGTH_LONG).show();

				}
			}
		});

	}

	private void prepareActivity() {

	}

	public void saveInformation() {
		SharedPreferences shared = getSharedPreferences("shared", MODE_PRIVATE);
		SharedPreferences.Editor editor = shared.edit();
		editor.putBoolean("auto", true);

		editor.commit();
	}

}
