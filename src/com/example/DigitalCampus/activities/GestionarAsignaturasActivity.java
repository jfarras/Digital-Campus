package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;
import com.example.DigitalCampus.adapters.ListaAsignaturasAdapter;
import com.example.DigitalCampus.objects.Asignaturas;

@SuppressLint("NewApi")
public class GestionarAsignaturasActivity extends BaseActivity {
	private ListView myListView;

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_asignaturas);

		ActionBar actionBar = getActionBar();
		actionBar.setSubtitle("Gestión asignaturas");

		actionBar.setDisplayHomeAsUpEnabled(true);
		associateControls();
		prepareActivity();
		getMyApplication().databaseAsig = new DAOAsignatura(
				getApplicationContext());

		getMyApplication().itemsAsignaturas = getMyApplication().databaseAsig
				.selectAll();

		ListView myListView = (ListView) findViewById(R.id.list_asignaturas);

		ListaAsignaturasAdapter adapter = new ListaAsignaturasAdapter(
				getApplicationContext(), GestionarAsignaturasActivity.this,
				R.layout.asignatura, getMyApplication().itemsAsignaturas);

		myListView.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.lista_asignaturas_men, menu);

		associateControls();
		prepareActivity();

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.gestAsign_CrearAsignatura:
			Intent i = new Intent(getApplicationContext(),
					InsertarAsignatura1Activity.class);
			startActivity(i);
			break;
		}

		return false;
	}

	private void associateControls() {

	}

	private void prepareActivity() {

	}

}
