package com.example.DigitalCampus.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DigitalCampus.R;
import com.example.DigitalCampus.activities.AltaEdicionExamenesActivity;
import com.example.DigitalCampus.activities.EdicionExamenesActivity;
import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Aulas;
import com.example.DigitalCampus.objects.Examenes;

public class ListExamenesAdapter extends ArrayAdapter<Examenes>{

	private int myLayout;
	private Context context2;
	private Activity a2;
	private List<Asignaturas> asig;
	private List<Aulas> aulas;

	public ListExamenesAdapter(Context context, Activity a,int resource, List<Examenes> objects,List<Asignaturas> asig,List<Aulas> Aulas) {
		super(context, resource, objects);
		this.myLayout = resource;
		this.context2=context;
		this.a2=a;
		this.asig = asig;
		this.aulas = Aulas;
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
					Toast toast = Toast.makeText(context2, Integer.toString(position),Toast.LENGTH_LONG);
					toast.show();
					
					Intent i = new Intent(context2,
							EdicionExamenesActivity.class);
					i.putExtra("position", position);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					context2.startActivity(i);
					
				}
			});
			
		}
		
		
		//2. Capturo els controls de la nova vista.
		TextView txtData = (TextView) row.findViewById(R.id.txtv_list_examenes_data);
		txtData.setTextColor(Color.BLACK);

		// txtTitle.setTextColor(color.holo_orange_light);

		TextView hora = (TextView) row.findViewById(R.id.textEscriureHora);
		TextView aula = (TextView) row.findViewById(R.id.textViewAula);
		TextView asignatura = (TextView) row.findViewById(R.id.txtv_list_examen_asig);
		TextView alumnos = (TextView) row.findViewById(R.id.numAlumnos);

		//TextView txtAsig = (TextView) row.findViewById(R.id.txtv_list_examen_asig);


		
		
		//3. Asssigno els nous valors als controls capturats.
		final Examenes p = getItem(position);
		txtData.setText(this.getItem(position).getData());
		hora.setText(this.getItem(position).getHora());
		aula.setText(Integer.toString(this.getItem(position).getAula()));
		alumnos.setText(Integer.toString(this.getItem(position).getNumAlumnes()));

		/*for(int i =0;i<llargada_aules;i++){
			if (aulas.get(i).getId()== this.getItem(position).getAula()) {
				aula.setText(aulas.get(i).getNombre());

			}
		}
		*/
		int llargada_asignaturas = asig.size();
		for(int j =0;j<llargada_asignaturas;j++){
			if (asig.get(j).getId()== this.getItem(position).getAsignatura()) {
				asignatura.setText(asig.get(j).getTitulo());

			}
		}
		//alumnos.setText(Integer.toString(this.getItem(position).getNumAlumnes()));

		//txtAsig.setText(this.getItem(position).getAsignatura());

		//txtComment.setText(this.getItem(position).getEdad());
		

		
		row.setTag(this.getItem(position).getData());
		
	
		
		
		
		//4. Retorno.
		return row;
	}
	
}
