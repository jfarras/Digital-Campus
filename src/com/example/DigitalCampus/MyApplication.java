package com.example.DigitalCampus;

import java.util.ArrayList;

import android.app.Application;

import com.example.DigitalCampus.DB.DAOAlumno;
import com.example.DigitalCampus.DB.DAOAsignatura;
import com.example.DigitalCampus.DB.DAOAsignatura_Alumnos;
import com.example.DigitalCampus.DB.DAOExamenes;
import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Asignaturas_Alumnos;
import com.example.DigitalCampus.objects.Aulas;
import com.example.DigitalCampus.objects.Carreras;
import com.example.DigitalCampus.objects.Examenes;
import com.example.DigitalCampus.objects.Temas;

public class MyApplication extends Application {
	
	public ArrayList<Asignaturas> itemsAsignaturas;
	public ArrayList<Alumnos> itemsAlumnos;
	public ArrayList<Asignaturas_Alumnos> itemsAsignaturas_Alumnos;

	public DAOAsignatura databaseAsig;
	public DAOAlumno databaseAlumnos;
	public ArrayList<Aulas> aulas;
	public ArrayList<Carreras> carreras;
	public ArrayList<Examenes> itemsExamenes;
	public DAOExamenes databaseExamenes;
	public DAOAsignatura_Alumnos databaseAsig_alu;
	
	public Asignaturas newAsignatura = new Asignaturas();
	public ArrayList<Alumnos> alumnosAsignatura;
	public ArrayList<Temas> temasAsignatura;
	
	public Alumnos verAlumno;

	public MyApplication(){
		aulas = new ArrayList<Aulas>();
		carreras = new ArrayList<Carreras>();
		alumnosAsignatura = new ArrayList<Alumnos>();
		temasAsignatura = new ArrayList<Temas>();
		verAlumno = new Alumnos();
		
		prepare();
	}
	public void prepare(){
		
			Carreras a = new Carreras(1,"Aeronáutica");
			carreras.add(a);
			Carreras b = new Carreras(2,"Bio Tecnlogia");
			carreras.add(b);
			Carreras c = new Carreras(3,"Derecho");
			carreras.add(c);
			Carreras d = new Carreras(4,"Multimedia");
			carreras.add(d);
			Carreras e = new Carreras(5,"TIC");
			carreras.add(e);
			Carreras f = new Carreras(6,"Electrónica");
			carreras.add(f);
			
			Aulas a1 = new Aulas(1,"Aula 1");
			Aulas a2 = new Aulas(2,"Aula 2");
			Aulas a3 = new Aulas(3,"Aula 3");
			Aulas a4 = new Aulas(4,"Aula 4");
			Aulas a5 = new Aulas(4,"Aula 5");
			aulas.add(a1);
			aulas.add(a2);
			aulas.add(a3);
			aulas.add(a4);
			aulas.add(a5);

		}
		
		
		
	
	
}
