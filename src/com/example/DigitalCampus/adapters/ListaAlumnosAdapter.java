package com.example.DigitalCampus.adapters;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DigitalCampus.MyApplication;
import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.activities.AlumnosActivity;
import com.example.DigitalCampus.activities.VerAlumnoActivity;
import com.example.DigitalCampus.activities.VerAsignaturaActivity;
import com.example.DigitalCampus.objects.Alumnos;

public class ListaAlumnosAdapter extends ArrayAdapter<Alumnos>{

	private int myLayout;
	private Context context2;
	private Activity a2;
	private MyApplication myApp;
	private List<Alumnos> items;
	


	public ListaAlumnosAdapter(Context context, Activity a,int resource, List<Alumnos> objects, MyApplication myApp) {
		super(context, resource, objects);
		this.myLayout = resource;
		this.context2=context;
		this.a2=a;
		this.myApp = myApp; 
		this.items = objects;
	}
	
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		final int myPosition = position;
		
		//1. Creem la llista
		if (row == null){
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(myLayout,parent,false);
			row.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Intent i = new Intent(context2,
							VerAlumnoActivity.class);
					
					myApp.verAlumno = items.get(position);
					
					i.putExtra("position", myPosition);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					context2.startActivity(i);
					
				}
			});
		}
		
		ImageView imageView = (ImageView) row.findViewById(R.id.imageView_listAlumnos);
		if (items.get(position).getFoto() != null)
			imageView.setImageBitmap(BitmapFactory.decodeFile(items.get(position).getFoto()));
		
		//2. Capturo els controls de la nova vista.
		TextView txtNom = (TextView) row.findViewById(R.id.txtv_list_Alumnos_nombre);
		txtNom.setTextColor(Color.BLACK);

		// txtTitle.setTextColor(color.holo_orange_light);

		TextView txtComment = (TextView) row.findViewById(R.id.txtv_list_Alumnos_edad);
		TextView txtCarrera = (TextView) row.findViewById(R.id.txtv_list_Alumnos_carrera);


		
		
		//3. Asssigno els nous valors als controls capturats.
		final Alumnos p = getItem(position);
		txtNom.setText(this.getItem(position).getNombre());
		//txtComment.setText(this.getItem(position).getEdad());
		Button buttonDelete= (Button)row.findViewById(R.id.deleteButton);
		buttonDelete.setTag(position);
	     buttonDelete.setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View arg0) {
	        	
	        	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	        			 a2);
	      
	     			// set title
	     			alertDialogBuilder.setTitle("Eliminar alumno");
	      
	     			// set dialog message
	     			alertDialogBuilder
	     				.setMessage("Estas seguro?")
	     				.setCancelable(false)
	     				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	     					public void onClick(DialogInterface dialog,int id) {
	     						DAOAsignatura database = new DAOAsignatura(getContext());
	     						database.deleteAsignatura(p.getNombre());
	     						
	     					}
	     				  })
	     				.setNegativeButton("No",new DialogInterface.OnClickListener() {
	     					public void onClick(DialogInterface dialog,int id) {
	     						// if this button is clicked, just close
	     						// the dialog box and do nothing
	     						dialog.cancel();
	     					}
	     				});
	     			AlertDialog alertDialog = alertDialogBuilder.create();
	     			 
					// show it
					alertDialog.show();
	         }
	        });

		
		row.setTag(this.getItem(position).getNombre());
		
	
		
		
		
		//4. Retorno.
		return row;
	}
	
}
