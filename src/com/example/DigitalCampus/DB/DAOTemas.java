package com.example.DigitalCampus.DB;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.DigitalCampus.objects.Carreras;
import com.example.DigitalCampus.objects.Temas;

public class DAOTemas extends DBHelper{
	
	
	
	private Temas c;

	public Temas getTemas() {
		return c;
	}

	public void setTemas(Temas c) {
		this.c = c;
	}

	public DAOTemas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	
	public ArrayList<Temas> selectTema(String nom) {
		mydatabase = getReadableDatabase();
		ArrayList<Temas> llista_Temas = new ArrayList<Temas>();
		String sql = "select * FROM 'temas'";
		Cursor cursor = mydatabase.rawQuery("SELECT * FROM temas WHERE nom = '"+nom+"'", null); 
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Temas a = LoadFromCursor(cursor);
			llista_Temas.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista_Temas;
	}
	
	public ArrayList<Temas> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<Temas> llista_Temas = new ArrayList<Temas>();
		String sql = "select * FROM 'temas'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Temas a = LoadFromCursor(cursor);
			llista_Temas.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista_Temas;
	}

	public Temas LoadFromCursor(Cursor cursor) {
		c = new Temas();
		c.setId(cursor.getInt(cursor.getColumnIndex("id")));
		c.setNom(cursor.getString(cursor.getColumnIndex("nom")));
		return c;
	}

	public void insertarTemas(String nom) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("nom", nom);
			mydatabase.insert("temas", null, valores);
		}
		mydatabase.close();
	}
	
	public void deleteTemas(String nom) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
						
			int i = mydatabase.delete("temas", "nom=?", new String[]{nom});
		mydatabase.close();
	}
	}
	

}
