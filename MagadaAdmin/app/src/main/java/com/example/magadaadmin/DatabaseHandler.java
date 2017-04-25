package com.example.magadaadmin;

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
	private static final String DATABASE_NAME = "MAGADA_ADMIN_DB";

	// Contacts table name
	private static final String TABLE_USER = "MAGADA_ADMIN";
	private static final String TABLE_USERSTATS = "MAGADA_USRSTATS";

	// Contacts Table Columns names
	//private static final String KEY_SESSIONID="key";
	private static final String KEY_ID = "ID";
	private static final String KEY_PWD = "PWD";
	private static final String KEY_NAME = "NAME";
	private static final String KEY_MOBILE = "MOBILE";

	private static final String KEY_ID1 = "ID";
	private static final String KEY_MOBILE1 = "MOBILE";
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
				+ KEY_PWD + " TEXT," +  KEY_MOBILE + " TEXT" +")";
		db.execSQL(CREATE_CONTACTS_TABLE);
		
		String CREATE_PWD_TABLE = "CREATE TABLE " + TABLE_USERSTATS + "("
				+ KEY_ID1 + " TEXT," +KEY_MOBILE1 + " TEXT,"+ KEY_P1 + " NUMERIC,"
				+ KEY_P2 + " NUMERIC," + KEY_P3 + " NUMERIC," +KEY_P4 + " NUMERIC," +KEY_P5 + " NUMERIC," +KEY_P6 + " NUMERIC," +KEY_P7 + " NUMERIC," +KEY_P8 + " NUMERIC," +KEY_P9 + " NUMERIC,"+KEY_P10 + " TEXT" +",PRIMARY KEY(ID,MOBILE,DATE)"+ ")";		
				db.execSQL(CREATE_PWD_TABLE);
		
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
	
	public String dbusercreate(MagadAdmin magadadmin) {
		SQLiteDatabase db = this.getWritableDatabase();
		String a="SUCCESS";
		ContentValues values = new ContentValues();
		
		values.put(KEY_ID, magadadmin.get_id()); 
		values.put(KEY_PWD, magadadmin.get_pwd()); 
		values.put(KEY_NAME, magadadmin.get_name());
		values.put(KEY_MOBILE, magadadmin.get_mobile());
		
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
