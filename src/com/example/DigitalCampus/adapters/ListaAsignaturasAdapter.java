package com.example.DigitalCampus.adapters;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DigitalCampus.R;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.activities.GestionarAsignaturasActivity;
import com.example.DigitalCampus.activities.InsertarAsignatura2Activity;
import com.example.DigitalCampus.activities.VerAsignaturaActivity;
import com.example.DigitalCampus.objects.Asignaturas;

public class ListaAsignaturasAdapter extends ArrayAdapter<Asignaturas>{

	private int myLayout;
	private Context context2;
	private Activity a2;
	private List<Asignaturas> items;


	public ListaAsignaturasAdapter(Context context, Activity a,int resource, List<Asignaturas> objects) {
		super(context, resource, objects);
		this.myLayout = resource;
		this.context2=context;
		this.a2=a;
		this.items = objects;
	}
	
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		

		//1. Creem la llista
		if (row == null){
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(myLayout,parent,false);
			row.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					
					Intent i = new Intent(context2,
							VerAsignaturaActivity.class);
					i.putExtra("position", position);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					context2.startActivity(i);
					
				}
			});
		}
		
		
		//2. Capturo els controls de la nova vista.
		TextView txtNom = (TextView) row.findViewById(R.id.txtv_list_asignaturas_titol);
		// txtTitle.setTextColor(color.holo_orange_light);

		TextView txtComment = (TextView) row.findViewById(R.id.txtv_list_asignaturas_descripcio);


		
		
		//3. Asssigno els nous valors als controls capturats.
		final Asignaturas p = getItem(position);
		txtNom.setText(this.getItem(position).getTitulo());
		txtNom.setTextColor(Color.BLACK);
		txtComment.setText(this.getItem(position).getDescripcion());
		Button buttonDelete= (Button)row.findViewById(R.id.deleteButton);
		buttonDelete.setTag(position);
		
		
	     buttonDelete.setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View arg0) {
	        	
	        	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	        			 a2);
	      
	     			// set title
	     			alertDialogBuilder.setTitle("Eliminar asignatura");
	      
	     			// set dialog message
	     			alertDialogBuilder
	     				.setMessage("Estas seguro?")
	     				.setCancelable(false)
	     				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	     					public void onClick(DialogInterface dialog,int id) {
	     						
	     						DAOAsignatura database = new DAOAsignatura(getContext());
	     						database.deleteAsignatura(p.getTitulo());
	     						
	     						items.remove(position);
     						
	     						ListaAsignaturasAdapter.this
								.notifyDataSetChanged();
	     						
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

		
		row.setTag(p.getId()+" "+this.getItem(position).getTitulo());
		
	
		
		
		
		//4. Retorno.
		return row;
	}
	
}
