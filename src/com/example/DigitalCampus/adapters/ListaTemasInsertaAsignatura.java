package com.example.DigitalCampus.adapters;

import java.util.ArrayList;
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
import com.example.DigitalCampus.objects.Temas;

public class ListaTemasInsertaAsignatura extends ArrayAdapter<String> {

	/*
	 * Atributes
	 */
	private List<Temas> items;
	private Activity context;
	private MyApplication myApp;
	private Temas temaAux;

	/*
	 * Constructor
	 */
	public ListaTemasInsertaAsignatura(Activity context,
			List<Temas> arrayList, MyApplication myApp) {
		super(context, R.layout.insertar_asignatura3_temas);
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
		View rowView = inflater.inflate(R.layout.insertar_asignatura3_temas,
				null, true);

		TextView txtIndex = (TextView) rowView
				.findViewById(R.id.textView_insertaAsignatura3_index);
		txtIndex.setText(position + 1 + ".");
		
		TextView txtNom = (TextView) rowView
				.findViewById(R.id.textView_insertaAsignatura3_Tema);
		txtNom.setText(items.get(position).getNom());
		
		// txtTitle.setTextColor(color.holo_orange_light);
		
		Button buttonUp= (Button)rowView.findViewById(R.id.button_insertaAsignatura3_Tema_up);
		buttonUp.setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View arg0) {
	        	
	        	 if (position < 1){
						Toast.makeText(context, "El tema no es pot pujar!", Toast.LENGTH_SHORT)
						.show();
	        	 }else{
	        		 temaAux = myApp.temasAsignatura.get(position);
	        		 myApp.temasAsignatura.set(position, myApp.temasAsignatura.get(position - 1));
	        		 myApp.temasAsignatura.set(position - 1, temaAux);
	        		 
						ListaTemasInsertaAsignatura.this
						.notifyDataSetChanged();
	        	 }
	        	 
	         }
	        });
		
		Button buttonDown= (Button)rowView.findViewById(R.id.button_insertaAsignatura3_Tema_down);
		buttonDown.setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View arg0) {
	        	
	        	 if (position >= myApp.temasAsignatura.size() - 1){
						Toast.makeText(context, "El tema no es pot baixar!", Toast.LENGTH_SHORT)
						.show();
	        	 }else{
	        		 temaAux = myApp.temasAsignatura.get(position);
	        		 myApp.temasAsignatura.set(position, myApp.temasAsignatura.get(position + 1));
	        		 myApp.temasAsignatura.set(position + 1, temaAux);
	        		 
						ListaTemasInsertaAsignatura.this
						.notifyDataSetChanged();
	        	 }
	        	 
	         }
	        });
		
		Button buttonDelete= (Button)rowView.findViewById(R.id.button_insertaAsignatura3_Tema_delete);
		buttonDelete.setOnClickListener(new OnClickListener() {
	         @Override
	         public void onClick(View arg0) {
	        	
	        	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	        			 context);
	      
	     			// set title
	     			alertDialogBuilder.setTitle("Eliminar tema");
	      
	     			// set dialog message
	     			alertDialogBuilder
	     				.setMessage("Estas seguro?")
	     				.setCancelable(false)
	     				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	     					public void onClick(DialogInterface dialog,int id) {
	     						
	     						//items.remove(position);
	     						myApp.temasAsignatura.remove(position);
	     						
	     						ListaTemasInsertaAsignatura.this
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
					alertDialog.show();
	         }
	        });
		return rowView;
	}
}
