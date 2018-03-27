package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.adapters.ListaAlumnosAdapter;
import com.example.DigitalCampus.adapters.ListaAsignaturasAdapter;
import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Asignaturas;

@SuppressLint("NewApi")
public class AlumnosActivity extends BaseActivity {
	private ListView myListView;
	ListaAlumnosAdapter adapter;
	
	public void onResume(){
		   super.onResume();

		adapter.notifyDataSetChanged();
		getMyApplication().itemsAlumnos = getMyApplication().databaseAlumnos.selectAll();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Gestión alumnos");

		associateControls();
		prepareActivity();
		
		ListView myListView = (ListView) findViewById(R.id.list_alumnos);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_alumnos);
		getMyApplication().databaseAlumnos = new DAOAlumno(getApplicationContext());
		
		getMyApplication().itemsAlumnos = getMyApplication().databaseAlumnos.selectAll();

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Gestión alumnos");

		associateControls();
		prepareActivity();
		
		ListView myListView = (ListView) findViewById(R.id.list_alumnos);
		
		adapter = new ListaAlumnosAdapter(getApplicationContext(),AlumnosActivity.this, R.layout.alumno, getMyApplication().itemsAlumnos, getMyApplication());

			myListView.setAdapter(adapter);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_alumnos_men, menu);

		associateControls();
		prepareActivity();

		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {

		
		switch (item.getItemId()) {

		case android.R.id.home: 
            onBackPressed();
            break;
		}
		return false;
	}
	

	private void associateControls() {

		
	}

	private void prepareActivity() {

	}
}
