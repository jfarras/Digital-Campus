package com.example.DigitalCampus.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Asignaturas_Alumnos;

public class DAOAsignatura_Alumnos extends DBHelper {

	private Asignaturas_Alumnos a;

	public DAOAsignatura_Alumnos(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	public ArrayList<Asignaturas_Alumnos> selectAsignaturesId( int idAlumno) {
		mydatabase = getReadableDatabase();
		ArrayList<Asignaturas_Alumnos> llista = new ArrayList<Asignaturas_Alumnos>();
		Cursor cursor = mydatabase.rawQuery("SELECT * FROM asignaturasAlumnos WHERE idAlumno = '"+idAlumno+"'", null); 
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Asignaturas_Alumnos a = LoadFromCursor(cursor);
			llista.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista;

	}
	
	public ArrayList<Asignaturas_Alumnos> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<Asignaturas_Alumnos> llista = new ArrayList<Asignaturas_Alumnos>();
		String sql = "select * FROM 'asignaturasAlumnos'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Asignaturas_Alumnos a = LoadFromCursor(cursor);
			llista.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista;

	}

	public Asignaturas_Alumnos LoadFromCursor(Cursor cursor) {
		a = new Asignaturas_Alumnos();
		a.setId(cursor.getInt(cursor.getColumnIndex("id")));

		a.setIdAlumno(cursor.getInt(cursor.getColumnIndex("idAlumno")));
		a.setIdAsignatura((cursor.getInt(cursor.getColumnIndex("idAsignatura"))));
		return a;
	}

	public void insertarAsignatura_Alumno_(int idAsignatura, int idAlumno) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("idAsignatura", idAsignatura);
			valores.put("idAlumno", idAlumno);
			int i = (int) mydatabase
					.insert("asignaturasAlumnos", null, valores);
		}
		mydatabase.close();
	}

	public void deleteAsignatura(String titol) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {

			int i = mydatabase.delete("asignaturas", "titulo=?",
					new String[] { titol });
			mydatabase.close();
		}
	}

}
