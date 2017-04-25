package com.example.magada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "MAGADA_DB";

	// Contacts table name
	private static final String TABLE_USER = "MAGADA_USER";
	private static final String TABLE_USERSTATS = "MAGADA_STATS";

	// Contacts Table Columns names
	//private static final String KEY_SESSIONID="key";
	private static final String KEY_ID = "ID";
	private static final String KEY_PWD = "PWD";
	private static final String KEY_NAME = "NAME";
	private static final String KEY_HECHLDG = "HECHLDG";
	private static final String KEY_MOBILE = "MOBILE";
	private static final String KEY_SPECIES = "SPECIES";
	private static final String KEY_DIST = "DIST";
	private static final String KEY_TALUK = "TALUK";
	private static final String KEY_VILLAGE = "VILLAGE";

	private static final String KEY_ID1 = "ID";
	private static final String KEY_P1 = "PAR1";
	private static final String KEY_P2 = "PAR2";
	private static final String KEY_P3 = "PAR3";
	private static final String KEY_P4 = "PAR4";
	private static final String KEY_P5 = "PAR5";
	private static final String KEY_P6 = "PAR6";
	private static final String KEY_P7 = "PI";
	private static final String KEY_P8 = "SDPA";
	private static final String KEY_P9 = "SDPH";
	private static final String KEY_P10 = "DATE";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("inside db", "Success");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
				 + KEY_ID +" TEXT PRIMARY KEY,"+KEY_NAME + " TEXT,"
				+ KEY_PWD + " TEXT," + KEY_HECHLDG + " NUMERIC," + KEY_MOBILE + " TEXT," + KEY_SPECIES + " TEXT,"+KEY_DIST + " TEXT,"+KEY_TALUK+ " TEXT,"+KEY_VILLAGE+ " TEXT"+")";
		db.execSQL(CREATE_CONTACTS_TABLE);
		
		String CREATE_PWD_TABLE = "CREATE TABLE " + TABLE_USERSTATS + "("
				+ KEY_ID1 + " TEXT," + KEY_P1 + " NUMERIC,"
				+ KEY_P2 + " NUMERIC," + KEY_P3 + " NUMERIC," +KEY_P4 + " NUMERIC," +KEY_P5 + " NUMERIC," +KEY_P6 + " NUMERIC," +KEY_P7 + " NUMERIC," +KEY_P8 + " NUMERIC," +KEY_P9 + " NUMERIC,"+KEY_P10 + " TEXT" +",PRIMARY KEY(ID,DATE)"+ ")";		db.execSQL(CREATE_PWD_TABLE);
		
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER );
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERSTATS );
		
		// Creates tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */	
	
	public String dbusercreate(MagadaUser magadauser) {
		SQLiteDatabase db = this.getWritableDatabase();
		String a="SUCCESS";
		ContentValues values = new ContentValues();
		
		values.put(KEY_ID, magadauser.get_id()); 
		values.put(KEY_PWD, magadauser.get_pwd()); 
		values.put(KEY_NAME, magadauser.get_name());
		values.put(KEY_HECHLDG, magadauser.get_hechldg());
		values.put(KEY_MOBILE, magadauser.get_mobile());
		values.put(KEY_SPECIES, magadauser.get_species());
		values.put(KEY_DIST, magadauser.get_dist());
		values.put(KEY_TALUK, magadauser.get_taluk());
		values.put(KEY_VILLAGE, magadauser.get_village());
		
		// Inserting Row
		try{
		db.insertOrThrow(TABLE_USER, null, values);
		}
		catch(SQLException e)
		{
		   	System.out.println("Error in dbusercreate");
			a="FAILURE";
		}
		db.close(); // Closing database connection
		return a;
	}

	public String dbuserstatscreate(MagadaStats magadastats) {
		SQLiteDatabase db = this.getWritableDatabase();
		String a="SUCCESS";
		ContentValues values = new ContentValues();
		
		values.put(KEY_ID1, magadastats.get_Id()); 
		values.put(KEY_P1, magadastats.get_Par1()); 
		values.put(KEY_P2, magadastats.get_Par2());
		values.put(KEY_P3, magadastats.get_Par3());
		values.put(KEY_P4, magadastats.get_Par4());
		values.put(KEY_P5, magadastats.get_Par5());
		values.put(KEY_P6, magadastats.get_Par6());
		values.put(KEY_P7, magadastats.get_PI());
		values.put(KEY_P8, magadastats.get_Sdpa());
		values.put(KEY_P9, magadastats.get_Sdph());
		values.put(KEY_P10, magadastats.get_Dt());
		System.out.println(magadastats.get_Dt()+' '+magadastats.get_Id()+' '+magadastats.get_PI()+' '+magadastats.get_Sdpa()+' '+magadastats.get_Sdph());
		// Inserting Row
		try
		{
			db.insertOrThrow(TABLE_USERSTATS, null, values);
		}
		catch(SQLException e)
		{
		   	System.out.println("Error in dbusercreate");
			a="FAILURE";
		}
		db.close(); // Closing database connection
		return a;
	}

	public String dbpasswordvalidate(String s1, String s2) {
		String P1;
		String P2;
		Integer I1;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID,
				KEY_PWD }, KEY_ID + "=?",
				new String[] { String.valueOf(s1)}, null, null, null,null);
		if (cursor != null)
			cursor.moveToFirst();
		
		I1=cursor.getCount();
		
		if(I1 != 1)
		{	
			return "User Not Found";
		}
		else
		{
			P1=cursor.getString(0);
			P2=cursor.getString(1);
		    if(P1.equals(s1) && P2.equals(s2))
		    {
		    	return "Success";
		    }
		    else
		    {
		    	return "Failure";
		    }
		}
		
	}
	
	public Integer dbUserCheck()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String countQuery = "SELECT  * FROM " + TABLE_USER;
		Cursor cursor = db.rawQuery(countQuery, null);
		Integer I=cursor.getCount();
		cursor.close();
		db.close();
		return I;
	}
	
	public void deleteUser(String Usr) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USER, KEY_ID +" = ?",
				new String[] { String.valueOf(Usr)});
		db.close(); 
	}
	
	

}
