package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOCarreras;
import com.example.DigitalCampus.adapters.ExpandedListViewCarrerasAdapter;
import com.example.DigitalCampus.objects.Carreras;

public class InsertarAlumnoActivity extends BaseActivity {

	private ImageButton carga;
	private RadioGroup radioSexGroup;
	private RadioButton radioSexButton;
	private Button btn_AddImatgeAlumne;
	private Button btn_creaAlumne;
	private static int RESULT_LOAD_IMAGE = 1;
	Context context = this;
	private Spinner spiner;

	private ArrayList<Carreras> items;
	ExpandableListView list;
	private DAOCarreras databaseCarreras;
	private DAOAlumno dbAlumnos;

	private String nombre;
	private String sexo;
	private String fechanacimiento;
	private String foto;
	private int idCarrera;
	private String picturePath;
	private boolean main;

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		main = extras.getBoolean("main");
		ActionBar actionBar = getActionBar();
		actionBar.setSubtitle("Nuevo Alumno");
		actionBar.setDisplayHomeAsUpEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertar_alumno);

		databaseCarreras = new DAOCarreras(getApplicationContext());
		dbAlumnos = new DAOAlumno(getApplicationContext());
		ArrayList<String> carreras = new ArrayList<String>();
		for(int i=0;i<getMyApplication().carreras.size();i++){
			carreras.add(getMyApplication().carreras.get(i).getNombre());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, carreras);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spiner = new Spinner(getApplicationContext());
		spiner = (Spinner) findViewById(R.id.spinnerCarreras2);
		spiner.setAdapter(dataAdapter);
		// items = database.selectAll();

		associateControls();
		// prepareActivity();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picturePath = cursor.getString(columnIndex);
			cursor.close();

			ImageView imageView = (ImageView) findViewById(R.id.imgView_nouAlumne);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buida, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:

			onBackPressed();

			return true;

		}
		return false;
	}

	private void associateControls() {

		this.btn_AddImatgeAlumne = (Button) findViewById(R.id.buttonLoad);
		this.btn_AddImatgeAlumne.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});

		this.btn_creaAlumne = (Button) findViewById(R.id.buttonCrea);
		this.btn_creaAlumne.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				EditText etNomUser = (EditText) findViewById(R.id.editNombre);
				String nomUser = etNomUser.getText().toString();

				EditText etDades = (EditText) findViewById(R.id.editFecha);
				String dades = etDades.getText().toString();

				nombre = nomUser;
				fechanacimiento = dades;
				foto = picturePath;
				idCarrera = spiner.getSelectedItemPosition();
				if (nombre.length() == 0 || fechanacimiento.length() == 0) {
					Toast.makeText(context, "Dades incompletes",
							Toast.LENGTH_SHORT).show();
				} else {

					dbAlumnos.insertarAlumno(nombre, sexo, fechanacimiento,
							foto, idCarrera);

					Toast.makeText(context, "Alumne afegit correctament.",
							Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getApplicationContext(),
							AlumnosActivity.class);
						startActivity(i);
				}

			}
		});

		// list = (ExpandableListView) findViewById(R.id.expandableListView1);

	}

	private void prepareActivity() {

		final ExpandedListViewCarrerasAdapter adapter = new ExpandedListViewCarrerasAdapter(
				InsertarAlumnoActivity.this, items, getMyApplication());

		list.setAdapter(adapter);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {

			}
		});

	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		RadioButton radiomale = (RadioButton) findViewById(R.id.radioMale);
		RadioButton radiofemale = (RadioButton) findViewById(R.id.radioFemale);
		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.radioMale:
			if (checked)
				radiomale.setSelected(true);
			radiofemale.setSelected(false);
			sexo = "Male";
			break;
		case R.id.radioFemale:
			if (checked)
				radiomale.setSelected(false);
			radiofemale.setSelected(true);
			sexo = "Female";
			break;
		}
	}
}