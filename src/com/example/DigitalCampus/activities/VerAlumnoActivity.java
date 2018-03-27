package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;
import com.example.DigitalCampus.DB.DAOCarreras;
import com.example.DigitalCampus.adapters.ListAsignaturasAlumnoAdapter;
import com.example.DigitalCampus.adapters.ListaAsignaturasAdapter;
import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Asignaturas_Alumnos;
import com.example.DigitalCampus.objects.Carreras;

public class VerAlumnoActivity extends BaseActivity {

	private ArrayList<Alumnos> items;

	private TextView nombre;
	private TextView carrera;
	private TextView date;
	private TextView sexo;
	private ListView list;
	private ImageView myImage;
	private ArrayList<Asignaturas_Alumnos> asignaturasAlumno;
	private ArrayList<Asignaturas> asignaturas;
	private ArrayList<Asignaturas> itemsAsignaturas;

	private Carreras carreraAlumno;

	private DAOAlumno dbAlumnos;
	private DAOCarreras dbCarreras;
	private DAOAsignatura_Alumnos dbAsignaturasAlumno;
	private DAOAsignatura dbAsignaturas;

	private int positionActu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		positionActu = extras.getInt("position");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_alumno);
		ActionBar actionBar = getActionBar();
		actionBar.setSubtitle("Ver alumno");
		actionBar.setDisplayHomeAsUpEnabled(true);

		dbCarreras = new DAOCarreras(getApplicationContext());
		dbAlumnos = new DAOAlumno(getApplicationContext());
		dbAsignaturasAlumno = new DAOAsignatura_Alumnos(getApplicationContext());
		dbAsignaturas = new DAOAsignatura(getApplicationContext());
		itemsAsignaturas = new ArrayList<Asignaturas>();

		this.items = dbAlumnos.selectAll();

		associateControls();

	}

	private void associateControls() {

		this.nombre = (TextView) findViewById(R.id.textViewNombreAlumno);
		nombre.setText(getMyApplication().verAlumno.getNombre());

		this.sexo = (TextView) findViewById(R.id.textViewSexo);
		sexo.setText(getMyApplication().verAlumno.getSexo());

		Carreras Carreras = dbCarreras
				.selectCarrera(getMyApplication().verAlumno.getCarrera());

		this.carrera = (TextView) findViewById(R.id.textViewCarreraVerAlumno);
		carrera.setText(Carreras.getNombre());

		this.date = (TextView) findViewById(R.id.textViewData);
		date.setText(getMyApplication().verAlumno.getFechaNacimiento());

		this.myImage = (ImageView) findViewById(R.id.imgView_verAlumno);
		if (getMyApplication().verAlumno.getFoto() != null) {
			myImage.setImageBitmap(BitmapFactory
					.decodeFile(getMyApplication().verAlumno.getFoto()));
		} else {
			myImage.setImageResource(R.drawable.ic_action_picture);
		}

		asignaturasAlumno = dbAsignaturasAlumno
				.selectAsignaturesId(getMyApplication().verAlumno.getId());

		for (int i = 0; i < asignaturasAlumno.size(); ++i) {
			asignaturas = dbAsignaturas.selectAsignaturaId(asignaturasAlumno
					.get(i).getIdAsignatura());
			if (!asignaturas.isEmpty())
				itemsAsignaturas.add(asignaturas.get(0));
		}

		list = (ListView) findViewById(R.id.list_asignaturasVerAlumno);

		final ListAsignaturasAlumnoAdapter adapter = new ListAsignaturasAlumnoAdapter(
				VerAlumnoActivity.this, itemsAsignaturas, getMyApplication());

		list.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_alumno, menu);
		return true;
	}

}
