package com.example.DigitalCampus.DB;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.DigitalCampus.objects.AsignaturasTemas;

public class DAOAsignaturasTemas extends DBHelper{

	private AsignaturasTemas a;

	

	public DAOAsignaturasTemas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<AsignaturasTemas> selectAll() {
		mydatabase = getReadableDatabase();
		ArrayList<AsignaturasTemas> llista= new ArrayList<AsignaturasTemas>();
		String sql = "select * FROM 'asignaturasTemas'";
		Cursor cursor = mydatabase.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			AsignaturasTemas a = LoadFromCursor(cursor);
			llista.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		super.close();
		return llista;

	}
	
	
	public AsignaturasTemas LoadFromCursor(Cursor cursor) {
		a = new AsignaturasTemas();
		a.setId(cursor.getInt(cursor.getColumnIndex("id")));

		a.setIdTema(cursor.getInt(cursor.getColumnIndex("idTema")));
		a.setIdAsignatura((cursor.getInt(cursor.getColumnIndex("idAsignatura"))));
		return a;
	}

	public void insertarAsignaturasTemas(int idAsignatura, int idTema) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
			ContentValues valores = new ContentValues();
			valores.put("idAsignatura", idAsignatura);
			valores.put("idTema", idTema);
			int i = (int) mydatabase.insert("asignaturasTemas", null, valores);
		}
		mydatabase.close();
	}
	
	public void deleteAsignaturasTemas(String idTema) {
		mydatabase = getWritableDatabase();
		if (mydatabase != null) {
						
			int i = mydatabase.delete("asignaturasTemas", "idTema=?", new String[]{idTema});
		mydatabase.close();
	}
	}

}
