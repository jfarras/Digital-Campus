package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.MyApplication;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;
import com.example.DigitalCampus.adapters.ListaAlumnosInsertaAsignatura;
import com.example.DigitalCampus.objects.Alumnos;

public class InsertarAsignatura2Activity extends BaseActivity {

	private Button continuar;
	private Button atras;
	Context context = this;
	ListView list;

	private DAOAlumno dbAlumnos;

	private ArrayList<Alumnos> itemsAlumnos;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setSubtitle("Nueva Asignatura 2/3");
		actionBar.setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.insertar_asignatura2);

		dbAlumnos = new DAOAlumno(getApplicationContext());
		this.itemsAlumnos = dbAlumnos.selectAll();

		// actionBar.setDisplayHomeAsUpEnabled(true);

		associateControls();
		prepareActivity();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch ((item).getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return false;
	}

	private void associateControls() {

		list = (ListView) findViewById(R.id.listView_insertaAsignaturas2);

		this.atras = (Button) findViewById(R.id.insertAsignatura2_Continuar);
		this.atras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						InsertarAsignatura1Activity.class);
				startActivity(i);
			}

		});

		this.continuar = (Button) findViewById(R.id.insertAsignatura2_Continuar);
		this.continuar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getMyApplication().alumnosAsignatura.isEmpty()){
					Toast.makeText(getApplicationContext(), "Afegeix almenys un alume",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent i = new Intent(getApplicationContext(),
							InsertarAsignatura3Activity.class);
					// i.putExtra("titol", titul2);
					// i.putExtra("descri", descri2);

					startActivity(i);
				}
			}
		});
	}

	private void prepareActivity() {

		getMyApplication().alumnosAsignatura.clear();

		final ListaAlumnosInsertaAsignatura adapter = new ListaAlumnosInsertaAsignatura(
				InsertarAsignatura2Activity.this, itemsAlumnos,
				getMyApplication());

		list.setAdapter(adapter);
	}

}
