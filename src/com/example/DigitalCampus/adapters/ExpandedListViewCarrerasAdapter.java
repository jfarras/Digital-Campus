package com.example.DigitalCampus.adapters;


import java.util.List;

import org.w3c.dom.Comment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DigitalCampus.MyApplication;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.objects.Carreras;
import com.example.DigitalCampus.objects.Temas;
import com.example.DigitalCampus.R.layout;

public class ExpandedListViewCarrerasAdapter extends ArrayAdapter<String>{

	/*
	 * Atributes
	 */
	private List<Carreras> items;
	private Activity context;
	private MyApplication myApp;

	/*
	 * Constructor
	 */
	public ExpandedListViewCarrerasAdapter(Activity context, List<Carreras> arrayList, MyApplication myApp) {
		super(context, R.layout.insertar_alumno_carrera);
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
		View rowView = inflater.inflate(R.layout.insertar_alumno_carrera, null, true);

		TextView txtNom = (TextView) rowView.findViewById(R.id.insertarAlumnoCarrera);
		txtNom.setText(items.get(position).getNombre());

		/*
		rowView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (myApp.getDeleteUser()) {

					AlertDialog.Builder dialogo1 = new AlertDialog.Builder(
							context);
					dialogo1.setTitle("Importante");
					dialogo1.setMessage("Realmente desea eliminar el usuario "+ items.get(position).getNom() + "?");
					dialogo1.setCancelable(false);
					dialogo1.setPositiveButton("Eliminar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialogo1,
										int id) {

									myApp.delUser(position);
									Toast.makeText(context,
											"El usuario ha sido eliminado",
											Toast.LENGTH_SHORT).show();
									CustomListAdapter.this
											.notifyDataSetChanged();
								}
							});
					dialogo1.setNegativeButton("Cancelar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialogo1,
										int id) {

									Toast.makeText(context, "Opcion cancelada",
											Toast.LENGTH_SHORT).show();

								}
							});
					dialogo1.show();
				}
			}
		});

		*/
		return rowView;
	}
	
}
