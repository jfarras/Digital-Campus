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

public class NumberedStringsAdapter extends ArrayAdapter<String>{

	private int myLayout;
	private Context context2;
	private Activity a2;
	private List<String> items;


	public NumberedStringsAdapter(Context context, Activity a,int resource, List<String> objects) {
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
			
		}
		
		
		//2. Capturo els controls de la nova vista.
		TextView txtNom = (TextView) row.findViewById(android.R.id.text1);
		txtNom.setTextColor(Color.BLACK);

		// txtTitle.setTextColor(color.holo_orange_light);



		
		
		//3. Asssigno els nous valors als controls capturats.
		txtNom.setText(position+1 +". "+ this.getItem(position));
		
		
		row.setTag(this.getItem(position));
		
	
		
		
		
		//4. Retorno.
		return row;
	}
	
}
