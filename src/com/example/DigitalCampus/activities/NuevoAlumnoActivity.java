package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOCarreras;
import com.example.DigitalCampus.adapters.ExpandedListViewCarrerasAdapter;
import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Carreras;
import com.example.DigitalCampus.objects.Temas;

public class NuevoAlumnoActivity extends BaseActivity {

	private ArrayList<Carreras> items;
	private Spinner spiner;
	private DAOCarreras database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_alumno);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertar_alumno);

		database = new DAOCarreras(getApplicationContext());

		items = database.selectAll();
		ArrayList<String> carreras = new ArrayList<String>();
		for(int i=0;i<getMyApplication().carreras.size();i++){
			carreras.add(getMyApplication().carreras.get(i).getNombre());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, carreras);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spiner = (Spinner) findViewById(R.id.spinnerCarreras);
		spiner.setAdapter(dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_alumno, menu);
		return true;
	}

	private void associateControls() {


	}

	private void prepareActivity() {

		

	}

}
