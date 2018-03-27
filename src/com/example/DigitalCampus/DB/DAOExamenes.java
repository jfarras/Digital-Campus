package com.example.DigitalCampus.DB;

import java.util.ArrayList;
import java.util.List;

import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Examenes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DAOExamenes extends DBHelper {

	private Examenes a;

	public Examenes getA() {
		return a;
	}

	public void setA(Examenes a) {
		this.a = a;
	}

	public DAOExamenes(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Examenes> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<Examenes> llista_Exameness = new ArrayList<Examenes>();
		String sql = "select * FROM 'examenes'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Examenes a = LoadFromCursor(cursor);

			sql = "SELECT count(idAlumno) AS c FROM asignaturasAlumnos WHERE idAsignatura = " + a.getAsignatura() +" GROUP BY idAsignatura ";
			Cursor cursor2 = mydatabase.rawQuery(sql, new String[] {});
			while (cursor2.isAfterLast() == false) {
				
				//LoadSuma(cursor2, a);
				cursor2.moveToNext();
			}
			llista_Exameness.add(a);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		super.close();
		return llista_Exameness;

	}
/*
	public String LoadSuma(Cursor cursor,Examenes e) {
		System.out.println(cursor.getString(cursor.getColumnIndex("c")));
		return(cursor.getString(cursor.getColumnIndex("c")));

		
	}
	*/
	public Examenes LoadFromCursor(Cursor cursor) {
		
		a = new Examenes();
		
		a.setId(cursor.getInt(cursor.getColumnIndex("id")));
		a.setAsignatura(cursor.getInt(cursor.getColumnIndex("idAsignatura")));
		a.setCarrera(cursor.getInt(cursor.getColumnIndex("idCarrera")));
		a.setAula(cursor.getInt(cursor.getColumnIndex("idAula")));
		a.setData(cursor.getString(cursor.getColumnIndex("fecha")));
		a.setHora(cursor.getString(cursor.getColumnIndex("hora")));

		return a;
		
	}

	public void insertarExamen( int idAsig, int idCarrera, int idAula,String fecha,String hora) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("idAsignatura", idAsig);
			valores.put("idAula", idAula);
			valores.put("idCarrera",idCarrera);
			valores.put("fecha", fecha);
			valores.put("hora",hora);
			mydatabase.insert("examenes", null, valores);
		}
		mydatabase.close();
	}
	
	public void updateExamen( int id,int idAsig, int idCarrera, int idAula,String fecha,String hora) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("idAsignatura", idAsig);
			valores.put("idAula", idAula);
			valores.put("idCarrera",idCarrera);
			valores.put("fecha", fecha);
			valores.put("hora",hora);
			String[] args = new String[]{ Integer.toString(id)};

			mydatabase.update("examenes", valores, "id = ?", args);
		}
		mydatabase.close();
	}
	
	
	}


