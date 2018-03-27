package com.example.DigitalCampus.DB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public final String DATABASE_RUTA;
	protected SQLiteDatabase mydatabase;

	private Context mContext;

	private static final String DATABASE_NAME = "digitalCampus.db";

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
		this.DATABASE_RUTA = mContext.getDatabasePath(DATABASE_NAME).getPath();
	}

	public void createDataBase() throws IOException {
		// File pathFile = mContext.getDatabasePath(DATABASE_NAME);
		boolean dbExist = checkDataBase(DATABASE_RUTA);
		if (!dbExist) {
			this.getReadableDatabase();
			copyDataBase();
		}
	}

	private boolean checkDataBase(String path) {
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {
			// Database doesn't exist
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null;
	}

	public void copyDataBase() throws IOException {
		synchronized (this) {
			// Open your local db as the input stream
			mydatabase = getReadableDatabase();
			InputStream myInput = this.mContext.getAssets().open(DATABASE_NAME);
			// Path to the just created empty db
			String outFileName = this.DATABASE_RUTA;
			// + DB_NAME;
			// Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
		}
	}
}
