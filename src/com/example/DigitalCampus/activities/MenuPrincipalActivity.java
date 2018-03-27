package com.example.DigitalCampus.activities;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;

@SuppressLint("NewApi")
public class MenuPrincipalActivity extends BaseActivity {

	private ImageButton salir;
	private ImageButton examenes;
	private ImageButton asignaturas;
	private ImageButton alumnos;
	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
		salir = new ImageButton(getApplicationContext());
		examenes = new ImageButton(getApplicationContext());
		asignaturas = new ImageButton(getApplicationContext());
		alumnos = new ImageButton(getApplicationContext());
		ActionBar actionBar = getActionBar();
		actionBar.setIcon(R.drawable.dc_logo);
		actionBar.show();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_main);
		getMyApplication().databaseAsig = new DAOAsignatura(getApplicationContext());
		
		getMyApplication().itemsAsignaturas = getMyApplication().databaseAsig.selectAll();
getMyApplication().databaseAlumnos = new DAOAlumno(getApplicationContext());
		
		getMyApplication().itemsAlumnos = getMyApplication().databaseAlumnos.selectAll();
		
getMyApplication().databaseAsig_alu = new DAOAsignatura_Alumnos(getApplicationContext());
		
		getMyApplication().itemsAsignaturas_Alumnos = getMyApplication().databaseAsig_alu.selectAll();
		
		com.example.DigitalCampus.DB.DBHelper db = new com.example.DigitalCampus.DB.DBHelper(getBaseContext());

		try {
			db.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			db.copyDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		associateControls();
		prepareActivity();

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

		switch(( item).getItemId()){
		case R.id.mainMenu_CrearAlumno:
			Intent ii = new Intent(getApplicationContext(),InsertarAlumnoActivity.class);
			ii.putExtra("Main", true);
        	startActivity(ii);
        	

		break;
	
		case R.id.mainMenu_CrearAsignatura:

			Intent ji = new Intent(getApplicationContext(),InsertarAsignatura1Activity.class);
	    	startActivity(ji);
	
		break;
		
		case R.id.mainMenu_NuevoExamen:

			Intent ki = new Intent(getApplicationContext(),AltaEdicionExamenesActivity.class);
	    	startActivity(ki);
	
		break;
		
		   case R.id.mainMenu_Salir:
	            finish();
	            return true;
	            
		   case android.R.id.home: 
				 finish();

				 break;
	}
	 
	
		return false;
	}

	private void associateControls() {
		
		this.salir =  (ImageButton)findViewById(R.id.imageSalir);
		this.salir.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
							
				Intent i = new Intent(getApplicationContext(),
					Login.class);
				i.putExtra("logout", true);
				startActivity(i);
				
			}
		});
		this.alumnos =  (ImageButton)findViewById(R.id.imageAlumnos);
		this.alumnos.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
							
				Intent i = new Intent(getApplicationContext(),
					AlumnosActivity.class);
				startActivity(i);
				
			}
		});
		this.examenes =  (ImageButton)findViewById(R.id.imageExamenes);
		this.examenes.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
							
				Intent i = new Intent(getApplicationContext(),
						ListadoExamenesActivity.class);
				startActivity(i);
				
			}
		});

		this.asignaturas =  (ImageButton)findViewById(R.id.imageAsignaturas);
		this.asignaturas.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
						
			Intent i = new Intent(getApplicationContext(),
					GestionarAsignaturasActivity.class);
			startActivity(i);
			
		}
	});

	}

	private void prepareActivity() {

	}
	
}
