package com.example.DigitalCampus.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;

public class InsertarAsignatura1Activity extends BaseActivity {

	private Button continuar;
	private EditText title;
	private EditText descri;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setSubtitle("Nueva Asignatura 1/3");
		actionBar.setDisplayHomeAsUpEnabled(true);

		getMyApplication().temasAsignatura.clear();

		continuar = new Button(getApplicationContext());
		title = new EditText(getApplicationContext());
		descri = new EditText(getApplicationContext());

		setContentView(R.layout.insertar_asignatura1);

		// actionBar.setDisplayHomeAsUpEnabled(true);

		associateControls();
		prepareActivity();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buida, menu);

		associateControls();
		prepareActivity();

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch ((item).getItemId()) {
		case R.id.CrearAlumno:
			super.onBackPressed();

			break;
		}
		return false;
	}

	private void associateControls() {

		this.title = (EditText) findViewById(R.id.titulo);
		this.descri = (EditText) findViewById(R.id.descripcion);
		this.continuar = (Button) findViewById(R.id.continuar);
		this.continuar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String titul2 = title.getText().toString();
				String descri2 = descri.getText().toString();
				
				if (titul2.isEmpty() || descri2.isEmpty()){
					Toast.makeText(getApplicationContext(), "Dades incompletes!",
							Toast.LENGTH_SHORT).show();
				} else {

					Intent i = new Intent(getApplicationContext(),
							InsertarAsignatura2Activity.class);
					getMyApplication().newAsignatura.setTitulo(titul2);
					getMyApplication().newAsignatura.setDescripcion(descri2);
					i.putExtra("titol", titul2);
					i.putExtra("descri", descri2);
					startActivity(i);
				}

			}

		});
	}

	private void prepareActivity() {

	}

}
