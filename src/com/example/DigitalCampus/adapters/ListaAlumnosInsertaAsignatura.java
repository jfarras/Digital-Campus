package com.example.DigitalCampus.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DigitalCampus.MyApplication;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.activities.InsertarAsignatura2Activity;
import com.example.DigitalCampus.objects.Alumnos;

public class ListaAlumnosInsertaAsignatura extends ArrayAdapter<String> {

	/*
	 * Atributes
	 */
	private List<Alumnos> items;
	private Activity context;
	private MyApplication myApp;

	/*
	 * Constructor
	 */
	public ListaAlumnosInsertaAsignatura(Activity context,
			List<Alumnos> arrayList, MyApplication myApp) {
		super(context, R.layout.insertar_asignatura2_alumo);
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
		View rowView = inflater.inflate(R.layout.insertar_asignatura2_alumo,
				null, true);

		TextView txtNom = (TextView) rowView
				.findViewById(R.id.textView_insertaAsignatura2Alumno_Nombre);
		txtNom.setText(items.get(position).getNombre());
		// txtTitle.setTextColor(color.holo_orange_light);

		Bitmap bmp = BitmapFactory.decodeFile(items.get(position).getFoto());
		ImageView imagenAlumno = (ImageView) rowView
				.findViewById(R.id.imageview_insertaAsignatura2Alumno);
		imagenAlumno.setImageBitmap(bmp);

		CheckBox alumnoInAsignatura = (CheckBox) rowView
				.findViewById(R.id.checkBox_alumno_insertaAsignatura);

		alumnoInAsignatura.setOnClickListener(new OnClickListener() {

		      @Override
		      public void onClick(View v) {
		                //is chkIos checked?
		        if (((CheckBox) v).isChecked()) {
		        	myApp.alumnosAsignatura.add(items.get(position));
		        }
		        else{
		        	myApp.alumnosAsignatura.remove(position);
		        }

		      }
		    });

		return rowView;
	}

}
