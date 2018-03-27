package com.example.DigitalCampus.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.DigitalCampus.objects.Asignaturas;

public class DAOAsignatura extends DBHelper {

	private Asignaturas a;

	public Asignaturas getA() {
		return a;
	}

	public void setA(Asignaturas a) {
		this.a = a;
	}

	public DAOAsignatura(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Asignaturas> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<Asignaturas> llista_Asignaturass = new ArrayList<Asignaturas>();
		String sql = "select * FROM 'asignaturas'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Asignaturas a = LoadFromCursor(cursor);
			ArrayList<String> temas = new ArrayList<String>();
			sql = "SELECT t.nom FROM asignaturas AS a,asignaturasTemas AS rel,temas AS t WHERE rel.idTema = t.id AND  rel.idAsignatura = "  + a.getId() +" AND rel.idAsignatura = a.id";
			Cursor cursor2 = mydatabase.rawQuery(sql, new String[] {});
			cursor2.moveToFirst();
			while (cursor2.isAfterLast() == false) {
				
				String tema = LoadTemas(cursor2,a);
				temas.add(tema);
				cursor2.moveToNext();
			}
			a.setTemas(temas);
			llista_Asignaturass.add(a);
			
			cursor.moveToNext();
		}
		
		cursor.close();
		super.close();
		
		return llista_Asignaturass;

	}
	
	public ArrayList<Asignaturas> selectAsignaturaId(int idAsignatura) {
		mydatabase = getReadableDatabase();
		
		ArrayList<Asignaturas> llista_Asignaturass = new ArrayList<Asignaturas>();
		String sql = "select * FROM 'asignaturas'";
		Cursor cursor = mydatabase.rawQuery("SELECT * FROM asignaturas WHERE id = '"+idAsignatura+"'", null); 
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Asignaturas a = LoadFromCursor(cursor);
			ArrayList<String> temas = new ArrayList<String>();
			sql = "SELECT t.nom FROM asignaturas AS a,asignaturasTemas AS rel,temas AS t WHERE rel.idTema = t.id AND  rel.idAsignatura = "  + a.getId() +" AND rel.idAsignatura = a.id";
			Cursor cursor2 = mydatabase.rawQuery(sql, new String[] {});
			cursor2.moveToFirst();
			while (cursor2.isAfterLast() == false) {
				
				String tema = LoadTemas(cursor2,a);
				temas.add(tema);
				cursor2.moveToNext();
			}
			a.setTemas(temas);
			llista_Asignaturass.add(a);
			
			cursor.moveToNext();
		}
		
		cursor.close();
		super.close();
		
		return llista_Asignaturass;

	}
	
	public ArrayList<Asignaturas> selectAsignatura(String titulo) {
		mydatabase = getReadableDatabase();
		
		ArrayList<Asignaturas> llista_Asignaturass = new ArrayList<Asignaturas>();
		String sql = "select * FROM 'asignaturas'";
		Cursor cursor = mydatabase.rawQuery("SELECT * FROM asignaturas WHERE titulo = '"+titulo+"'", null); 
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			Asignaturas a = LoadFromCursor(cursor);
			ArrayList<String> temas = new ArrayList<String>();
			sql = "SELECT t.nom FROM asignaturas AS a,asignaturasTemas AS rel,temas AS t WHERE rel.idTema = t.id AND  rel.idAsignatura = "  + a.getId() +" AND rel.idAsignatura = a.id";
			Cursor cursor2 = mydatabase.rawQuery(sql, new String[] {});
			cursor2.moveToFirst();
			while (cursor2.isAfterLast() == false) {
				
				String tema = LoadTemas(cursor2,a);
				temas.add(tema);
				cursor2.moveToNext();
			}
			a.setTemas(temas);
			llista_Asignaturass.add(a);
			
			cursor.moveToNext();
		}
		
		cursor.close();
		super.close();
		
		return llista_Asignaturass;

	}
	
	public String LoadTemas(Cursor cursor,Asignaturas a) {
		return(cursor.getString(cursor.getColumnIndex("nom")));

		
	}

	public Asignaturas LoadFromCursor(Cursor cursor) {
		a = new Asignaturas();
		a.setId(cursor.getInt(cursor.getColumnIndex("id")));

		a.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
		a.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
		return a;
	}

	public void insertarAsignatura(String titol, String descripcio, String foto) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("titulo", titol);
			valores.put("descripcion", descripcio);
			valores.put("foto", foto);
			mydatabase.insert("asignaturas", null, valores);
		}
		mydatabase.close();
	}
	
	public void deleteAsignatura(String titol) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
						
			int i = mydatabase.delete("asignaturas", "titulo=?", new String[]{titol});
		mydatabase.close();
	}
	}

}
