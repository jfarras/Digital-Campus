package com.example.DigitalCampus.adapters;


import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DigitalCampus.MyApplication;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.activities.InsertarAsignatura2Activity;
import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Temas;

public class ListAsignaturasAlumnoAdapter extends ArrayAdapter<String> {

	/*
	 * Atributes
	 */
	private List<Asignaturas> items;
	private Activity context;
	private MyApplication myApp;
	private Temas temaAux;

	/*
	 * Constructor
	 */
	public ListAsignaturasAlumnoAdapter(Activity context,
			List<Asignaturas> arrayList, MyApplication myApp) {
		super(context, R.layout.visualizar_alumno_temaslist);
		this.context = context;
		this.items = arrayList;
		this.myApp = myApp;
	}

	/*
	 * Return item nums.
	 */
	public int getCount() {

		return this.items.size();

	}

	/*
	 * Return element by ID
	 */
	public String getItem(int index) {

		return items.get(index).toString();

	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.visualizar_alumno_temaslist,
				null, true);

		TextView txtIndex = (TextView) rowView
				.findViewById(R.id.textView_visualizarAlumnoTema_index);
		txtIndex.setText(position + 1 + ".");
		
		TextView txtNom = (TextView) rowView
				.findViewById(R.id.textView_visualizarAlumnoTema_Tema);
		txtNom.setText(items.get(position).getTitulo());
		
		return rowView;
	}
}