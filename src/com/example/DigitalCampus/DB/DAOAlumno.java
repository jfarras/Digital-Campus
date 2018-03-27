package com.example.DigitalCampus.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.DigitalCampus.objects.Alumnos;
import com.example.DigitalCampus.objects.Alumnos;

public class DAOAlumno extends DBHelper {

	private Alumnos a;

	public Alumnos getA() {
		return a;
	}

	public void setA(Alumnos a) {
		this.a = a;
	}

	public DAOAlumno(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Alumnos> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<Alumnos> llista_Alumnos = new ArrayList<Alumnos>();
		String sql = "select * FROM 'alumnos'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Alumnos a = LoadFromCursor(cursor);
			llista_Alumnos.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista_Alumnos;

	}

	public Alumnos LoadFromCursor(Cursor cursor) {
		a = new Alumnos();
		a.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
		a.setId(cursor.getInt(cursor.getColumnIndex("id")));

		a.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
		a.setFechaNacimiento(cursor.getString(cursor.getColumnIndex("fechanacimiento")));
		a.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
		a.setCarrera(cursor.getInt(cursor.getColumnIndex("idCarrera")));
		
		return a;
	}

	public void insertarAlumno(String nombre, String sexo, String fechanacimiento, String foto, int idCarrera) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("nombre", nombre);
			valores.put("sexo", sexo);
			valores.put("fechanacimiento", fechanacimiento);
			valores.put("foto", foto);
			valores.put("idCarrera", idCarrera);
			
			 mydatabase.insert("alumnos", null, valores);
		}
		mydatabase.close();
	}
	
	public void deleteAsignatura(String titol) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
						
			mydatabase.delete("asigaturas", "titulo = ?", new String[]{titol});
		mydatabase.close();
	}
	}

}
