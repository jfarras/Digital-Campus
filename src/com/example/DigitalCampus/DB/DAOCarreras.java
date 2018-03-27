package com.example.DigitalCampus.DB;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.DigitalCampus.objects.Asignaturas;
import com.example.DigitalCampus.objects.Carreras;

public class DAOCarreras extends DBHelper{

	
	private Carreras c;

	public Carreras getCarreras() {
		return c;
	}

	public void setA(Carreras c) {
		this.c = c;
	}

	public DAOCarreras(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public Carreras selectCarrera( int id) {
		mydatabase = getReadableDatabase();
		ArrayList<Carreras> llista_Carreras = new ArrayList<Carreras>();
		Cursor cursor = mydatabase.rawQuery("SELECT * FROM carreras WHERE id = '"+id+"'", null); 
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Carreras a = LoadFromCursor(cursor);
			llista_Carreras.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista_Carreras.get(0);
	}

	public ArrayList<Carreras> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<Carreras> llista_Carreras = new ArrayList<Carreras>();
		String sql = "select * FROM 'carreras'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Carreras a = LoadFromCursor(cursor);
			llista_Carreras.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista_Carreras;
	}

	public Carreras LoadFromCursor(Cursor cursor) {
	
		c = new Carreras();
		c.setId(cursor.getInt(cursor.getColumnIndex("id")));
		c.setNombre(cursor.getString(cursor.getColumnIndex("nom")));
		return c;
		
	}

	public void insertarCarreras(String nom) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("nom", nom);
			mydatabase.insert("carreras", null, valores);
		}
		mydatabase.close();
	}
	
	public void deleteCarreras(String nom) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
						
			int i = mydatabase.delete("carreras", "nom=?", new String[]{nom});
		mydatabase.close();
	}
	}
	
	
}
