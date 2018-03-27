package com.example.DigitalCampus.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.CustomOnItemSelectedListener;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOCarreras;
import com.example.DigitalCampus.objects.Carreras;
import com.example.DigitalCampus.objects.Examenes;

public class EdicionExamenesActivity extends BaseActivity implements OnItemSelectedListener{


	private ArrayList<Carreras> items;
	private DAOCarreras database;
	private Spinner spinner;
	private Spinner spinnerAulas;
	private Spinner spinnerAsignaturas;
	private Button btnSubmit;
	private EditText editFecha;
	private EditText editHora;
	private int positionActu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		positionActu = extras.getInt("position");
		Examenes e= new Examenes();
				e = getMyApplication().itemsExamenes.get(positionActu);
		

		
		setContentView(R.layout.activity_alta_edicion_examenes);
		ArrayList<String> carreras = new ArrayList<String>();
		for(int i=0;i<getMyApplication().carreras.size();i++){
			carreras.add(getMyApplication().carreras.get(i).getNombre());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, carreras);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner = (Spinner) findViewById(R.id.spinnerCarreras);
		spinner.setAdapter(dataAdapter);
		spinner.setSelection(e.getCarrera());
		editHora = (EditText) findViewById(R.id.editHora);
		editFecha = (EditText) findViewById(R.id.editFecha);
		editHora.setText(e.getHora());
		editFecha.setText(e.getData());
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setSubtitle("Editar examen");

//spiner aulas
		
		ArrayList<String> aulas = new ArrayList<String>();
		for(int i=0;i<getMyApplication().aulas.size();i++){
			aulas.add(getMyApplication().aulas.get(i).getNombre());
		}
		
		
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, aulas);
			dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerAulas = (Spinner) findViewById(R.id.spinnerAulas);
		spinnerAulas.setAdapter(dataAdapter2);
		spinnerAulas.setSelection(e.getAula());
		
		
		
		//spinner asignaturas
		ArrayList<String> asignaturas= new ArrayList<String>();
		for(int i=0;i<getMyApplication().itemsAsignaturas.size();i++){
			asignaturas.add(getMyApplication().itemsAsignaturas.get(i).getTitulo());
		}
		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, asignaturas);
			dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerAsignaturas = (Spinner) findViewById(R.id.spinnerAsignaturas);
		spinnerAsignaturas.setAdapter(dataAdapter3);
		spinnerAsignaturas.setSelection(e.getAsignatura());

		
		
		database = new DAOCarreras(getApplicationContext());
		
		items = database.selectAll();
		
		
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
	}

	
	public void addListenerOnSpinnerItemSelection() {
		spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	  }
	 
	  // get the selected dropdown list value
	  public void addListenerOnButton() {
		
		
		btnSubmit = (Button) findViewById(R.id.buttonGuardarExamen);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						
						//Intent i = new Intent(
					
					int idCarrera=1;
					int llargada_carreras=getMyApplication().carreras.size();
					for(int i=0;i<llargada_carreras;i++){
						String carreraSpinner = String.valueOf(spinner.getSelectedItem());
						if (carreraSpinner.equals(getMyApplication().carreras.get(i).getNombre()) ){
							idCarrera = getMyApplication().carreras.get(i).getId();
						}
					}
					
					int idAsig=1;
					int llargada_asignaturas=getMyApplication().itemsAsignaturas.size();
					for(int i=0;i<llargada_asignaturas;i++){
						String SpinnerVaue = String.valueOf(spinnerAsignaturas.getSelectedItem());
						if (SpinnerVaue.equals(getMyApplication().itemsAsignaturas.get(i).getTitulo()) ){
							idAsig = getMyApplication().carreras.get(i).getId();
						}
					}
					
					int idAula=1;
					int llargada_aulas=getMyApplication().aulas.size();
					for(int i=0;i<llargada_aulas;i++){
						String SpinnerVaue = String.valueOf(spinnerAulas.getSelectedItem());
						if (SpinnerVaue.equals(getMyApplication().aulas.get(i).getNombre()) ){
							idAsig = getMyApplication().carreras.get(i).getId();
						}
					}
					
					getMyApplication().databaseExamenes.updateExamen(getMyApplication().itemsExamenes.get(positionActu).getId(),idAsig, idCarrera, idAula, editFecha.getText().toString(), editHora.getText().toString());
					Intent i = new Intent(getApplicationContext(),
							ListadoExamenesActivity.class);
					startActivity(i);
					}
				});
	  }
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_alumno, menu);
		return true;
	}

	
	
	private void associateControls() {

		
	}
	private void prepareSpinner(){
		
			
	}
	
	private void prepareActivity() {


		

		

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
