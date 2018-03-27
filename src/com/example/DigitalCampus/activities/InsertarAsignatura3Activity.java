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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;
import com.example.DigitalCampus.DB.DAOAsignaturasTemas;
import com.example.DigitalCampus.DB.DAOTemas;
import com.example.DigitalCampus.adapters.ListaAlumnosInsertaAsignatura;
import com.example.DigitalCampus.adapters.ListaAsignaturasAdapter;
import com.example.DigitalCampus.adapters.ListaTemasInsertaAsignatura;
import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Aulas;
import com.example.DigitalCampus.objects.Temas;

public class InsertarAsignatura3Activity extends BaseActivity {

	private Button continuar;
	private Button atras;
	private Button addTema;
	private ArrayList<Asignaturas> asignaturas;
	private ArrayList<Temas> temas;

	Context context = this;
	ListView list;

	private DAOTemas dbTemas;
	private DAOAsignatura dbAsignaturas;
	private DAOAsignaturasTemas dbAsignaturasTemas;
	private DAOAsignatura_Alumnos dbAsignaturaAlumnos;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setSubtitle("Nueva Asignatura 3/3");
		actionBar.setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.insertar_asignatura3);

		dbTemas = new DAOTemas(getApplicationContext());
		dbAsignaturas = new DAOAsignatura(getApplicationContext());
		dbAsignaturasTemas = new DAOAsignaturasTemas(getApplicationContext());
		dbAsignaturaAlumnos = new DAOAsignatura_Alumnos(getApplicationContext());

		// this.itemsTemas = dbTemas.selectAll();

		// actionBar.setDisplayHomeAsUpEnabled(true);

		associateControls();
		prepareActivity();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_asignatura3, menu);

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

		list = (ListView) findViewById(R.id.listView_insertaAsignatura3_temas);

		this.atras = (Button) findViewById(R.id.btn_addAsignatura3_Atras);
		this.atras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						InsertarAsignatura2Activity.class);
				startActivity(i);
			}

		});

		this.continuar = (Button) findViewById(R.id.btn_addAsignatura3_Continuar);
		this.continuar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (getMyApplication().temasAsignatura.isEmpty()) {
					Toast.makeText(context, "Afegeix com a minim un tema",
							Toast.LENGTH_SHORT).show();
				} else {

					String tituloAs = getMyApplication().newAsignatura
							.getTitulo();
					String descripcionAs = getMyApplication().newAsignatura
							.getDescripcion();
					dbAsignaturas.insertarAsignatura(tituloAs, descripcionAs,
							null);

					asignaturas = dbAsignaturas.selectAsignatura(tituloAs);

					int idAsignatura = asignaturas.get(0).getId();

					for (int i = 0; i < getMyApplication().temasAsignatura
							.size(); ++i) {
						dbTemas.insertarTemas(getMyApplication().temasAsignatura
								.get(i).getNom().toString());
						temas = dbTemas
								.selectTema(getMyApplication().temasAsignatura
										.get(i).getNom().toString());
						dbAsignaturasTemas.insertarAsignaturasTemas(
								idAsignatura, temas.get(0).getId());

					}
					for (int i = 0; i < getMyApplication().alumnosAsignatura
							.size(); ++i) {
						dbAsignaturaAlumnos.insertarAsignatura_Alumno_(
								idAsignatura,
								getMyApplication().alumnosAsignatura.get(i)
										.getId());
					}

					Toast.makeText(context,
							"Asignatura afegida correctament!.",
							Toast.LENGTH_SHORT).show();

					Intent i = new Intent(getApplicationContext(),
							MenuPrincipalActivity.class);
					startActivity(i);

				}
			}
		});

		this.addTema = (Button) findViewById(R.id.btn_addAsignatura3_nuevoTema);
		this.addTema.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText nouTema = (EditText) findViewById(R.id.editTextaddAsignatura3_Tema);
				String nuevoTema = nouTema.getText().toString();
				
				if (nuevoTema.isEmpty()){
					Toast.makeText(getApplicationContext(), "Afegeix un nom",
							Toast.LENGTH_SHORT).show();
				} else {
					Temas newTema = new Temas(1, nuevoTema);
					getMyApplication().temasAsignatura.add(newTema);

					nouTema.setText("");

					final ListaTemasInsertaAsignatura adapter = new ListaTemasInsertaAsignatura(
							InsertarAsignatura3Activity.this,
							getMyApplication().temasAsignatura, getMyApplication());

					list.setAdapter(adapter);
				}
			}
		});
	}

	private void prepareActivity() {

		final ListaTemasInsertaAsignatura adapter = new ListaTemasInsertaAsignatura(
				InsertarAsignatura3Activity.this,
				getMyApplication().temasAsignatura, getMyApplication());

		list.setAdapter(adapter);
	}

}
