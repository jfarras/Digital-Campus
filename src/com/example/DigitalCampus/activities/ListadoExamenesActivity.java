package com.example.DigitalCampus.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.DigitalCampus.BaseActivity;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOExamenes;
import com.example.DigitalCampus.adapters.ListExamenesAdapter;

public class ListadoExamenesActivity extends BaseActivity {

private ListView myListView;
	

	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_examenes);
		

		ActionBar actionBar = getActionBar();
		//actionBar.setDisplayHomeAsUpEnabled(true);
		associateControls();
		prepareActivity();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setSubtitle("Listado exámenes");

getMyApplication().databaseExamenes = new DAOExamenes(getApplicationContext());
		
getMyApplication().itemsExamenes = getMyApplication().databaseExamenes.selectAll();

for(int i =0;i<getMyApplication().itemsExamenes.size();i++){
	int count=0;
	for(int j=0;j<getMyApplication().itemsAsignaturas_Alumnos.size();j++){
		if(getMyApplication().itemsExamenes.get(i).getAsignatura() == getMyApplication().itemsAsignaturas_Alumnos.get(j).getIdAsignatura()) count++;
		getMyApplication().itemsExamenes.get(i).setNumAlumnes(count);
	}
	
}
		ListView myListView = (ListView) findViewById(R.id.list_examenes);
	
		ListExamenesAdapter adapter = new ListExamenesAdapter(getApplicationContext(),ListadoExamenesActivity.this, R.layout.examen, getMyApplication().itemsExamenes,getMyApplication().itemsAsignaturas,getMyApplication().aulas);

			myListView.setAdapter(adapter);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.lista_examenes_menu, menu);
	
		associateControls();
		prepareActivity();

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		
		switch (item.getItemId()) {
		 case android.R.id.home: 
	            onBackPressed();
	            break;

		 case R.id.lista_examenes_add:
				Intent i = new Intent(getApplicationContext(),
						AltaEdicionExamenesActivity.class);
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
