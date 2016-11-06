package com.ctb.rmi2;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BirthdayDbAdapter {

	public static final String KEY_ROWID = "_id";
	 public static final String KEY_FNAME = "fname";
	 public static final String KEY_LNAME = "lname";
	 public static final String KEY_BIRTHDATE = "birthdate";
	 
	 private static final String TAG = "BirthdayDbAdapter";
	 private DatabaseHelper mDbHelper;
	 private SQLiteDatabase mDb;
	 private static final String DATABASE_NAME = "RMI2.DB";
	 private static final String SQLITE_TABLE = "BIRTHDAY";
	 private static final int DATABASE_VERSION = 1;

	 private final Context mCtx;
	 
	 private static final String DATABASE_CREATE =
			  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
			  KEY_ROWID + " integer PRIMARY KEY autoincrement," +
			  KEY_FNAME + "," +
			  KEY_LNAME + "," +
			  KEY_BIRTHDATE + "," +
			  " UNIQUE (" + KEY_ROWID +"));";

	 private static class DatabaseHelper extends SQLiteOpenHelper {

		  DatabaseHelper(Context context) {
		   super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Log.w(TAG, DATABASE_CREATE);
			   db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				     + newVersion + ", which will destroy all old data");
				   db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
				   onCreate(db);
		}
	 }
	 public BirthdayDbAdapter(Context ctx) {
		  this.mCtx = ctx;
		 }
	 
	 
	 public BirthdayDbAdapter open() throws SQLException {
		  mDbHelper = new DatabaseHelper(mCtx);
		  mDb = mDbHelper.getWritableDatabase();
		  return this;
		 }
	 public void close() {
		  if (mDbHelper != null) {
		   mDbHelper.close();
		  }
	 }
	 
	 
	 public long createBirthday( String fname, 
			   String lname, String bdate) {

			  ContentValues initialValues = new ContentValues();
			  initialValues.put(KEY_FNAME, fname);
			  initialValues.put(KEY_LNAME, lname);
			  initialValues.put(KEY_BIRTHDATE, bdate);

			  return mDb.insert(SQLITE_TABLE, null, initialValues);
			 }
	 
	 
	 public boolean deleteAllBirthdays() {

		  int doneDelete = 0;
		  doneDelete = mDb.delete(SQLITE_TABLE, null , null);
		  Log.w(TAG, Integer.toString(doneDelete));
		  return doneDelete > 0;

		 }
	 public Cursor fetchBirthdayByName(String inputText) throws SQLException {
		  Log.w(TAG, inputText);
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
		     KEY_FNAME, KEY_LNAME, KEY_BIRTHDATE}, 
		     null, null, null, null, null);

		  }
		  else {
		   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
		     KEY_FNAME, KEY_LNAME, KEY_BIRTHDATE}, 
		     KEY_FNAME + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;

		 }
	 

	 public Cursor fetchAllBirthdays() {

	  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
	    KEY_FNAME, KEY_LNAME, KEY_BIRTHDATE}, 
	    null, null, null, null, null);

	  if (mCursor != null) {
	   mCursor.moveToFirst();
	  }
	  return mCursor;
	 }
	 
	 public void insertSomeBirthdays()
	 {
		 createBirthday("PRATIK", "GAWAND", "17/08/1994");
	 }
	 
	 public void delete(long id) {
	        mDb.delete(BirthdayDbAdapter.SQLITE_TABLE, BirthdayDbAdapter.KEY_ROWID + "=" + id, null);
	    }
	 
}
