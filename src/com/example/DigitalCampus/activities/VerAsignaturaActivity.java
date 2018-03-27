package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;
import com.example.DigitalCampus.adapters.ListaAlumnosXAsigAdapter;
import com.example.DigitalCampus.adapters.NumberedStringsAdapter;
import com.example.DigitalCampus.objects.Alumnos;

public class VerAsignaturaActivity extends BaseActivity {

	private TextView nombre;
	private TextView descri;
	private int positionActu;
	private ListView myListView;
	private ListView myListViewAlumnos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		positionActu = extras.getInt("position");

		super.onCreate(savedInstanceState);
		getMyApplication().databaseAsig_alu = new DAOAsignatura_Alumnos(
				getApplicationContext());
		getMyApplication().databaseAsig = new DAOAsignatura(
				getApplicationContext());
		getMyApplication().databaseAlumnos = new DAOAlumno(
				getApplicationContext());

		getMyApplication().itemsAsignaturas_Alumnos = getMyApplication().databaseAsig_alu
				.selectAll();

		getMyApplication().itemsAlumnos = getMyApplication().databaseAlumnos
				.selectAll();
		getMyApplication().itemsAsignaturas = getMyApplication().databaseAsig
				.selectAll();

		ActionBar actionBar = getActionBar();
		setContentView(R.layout.activity_ver_asignatura);
		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setSubtitle("Visualizar asignatura");
		associateControls();
	}

	private void associateControls() {

		this.nombre = (TextView) findViewById(R.id.textViewNombre);
		this.descri = (TextView) findViewById(R.id.textViewDescri);
		nombre.setText(getMyApplication().itemsAsignaturas.get(positionActu)
				.getTitulo());
		descri.setText(getMyApplication().itemsAsignaturas.get(positionActu)
				.getDescripcion());
		myListView = (ListView) findViewById(R.id.listTemas);
		NumberedStringsAdapter adapter = new NumberedStringsAdapter(
				getApplicationContext(), this,
				android.R.layout.simple_list_item_1,
				getMyApplication().itemsAsignaturas.get(positionActu)
						.getTemas());
		myListView.setAdapter(adapter);
		myListViewAlumnos = (ListView) findViewById(R.id.listAlumnosDinsAsig);
		int llargada = getMyApplication().itemsAsignaturas_Alumnos.size();
		ArrayList<Alumnos> alumnos_a_mostrar = new ArrayList<Alumnos>();
		System.out.println(llargada);

		for (int i = 0; i < llargada; i++) {

			if (getMyApplication().itemsAsignaturas_Alumnos.get(i)
					.getIdAsignatura() == getMyApplication().itemsAsignaturas
					.get(positionActu).getId()) {
				System.out.println(llargada);
				int idAlumno = getMyApplication().itemsAsignaturas_Alumnos.get(
						i).getIdAlumno();
				int llargada_alumno = getMyApplication().itemsAlumnos.size();
				for (int j = 0; j < llargada_alumno; j++) {
					if (getMyApplication().itemsAlumnos.get(j).getId() == idAlumno)
						alumnos_a_mostrar.add(getMyApplication().itemsAlumnos
								.get(j));
					System.out.println("jroguhrgr");
				}
			}
		}

		ListaAlumnosXAsigAdapter adapter2 = new ListaAlumnosXAsigAdapter(
				getApplicationContext(), VerAsignaturaActivity.this,
				R.layout.alumno_asignatura, alumnos_a_mostrar,
				getMyApplication());
		myListViewAlumnos.setAdapter(adapter2);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_asignatura, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch ((item).getItemId()) {
		
		case android.R.id.home: 
            onBackPressed();
            break;
		
		case R.id.verAsignatura_delete:

			switch ((item).getItemId()) {
			
			
			case R.id.verAsignatura_delete:

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);

				// set title
				alertDialogBuilder.setTitle("Eliminar asignatura");

				// set dialog message
				alertDialogBuilder
						.setMessage("Estas seguro?")
						.setCancelable(false)
						.setPositiveButton("Si",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										getMyApplication().databaseAsig
												.deleteAsignatura(getMyApplication().itemsAsignaturas
														.get(positionActu)
														.getTitulo());
										getMyApplication().itemsAsignaturas
												.remove(positionActu);
										Intent i = new Intent(
												getApplicationContext(),
												GestionarAsignaturasActivity.class);
										startActivity(i);

									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
				break;

			}

		}

		return false;
	}

}
