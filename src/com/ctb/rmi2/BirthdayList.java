package com.ctb.rmi2;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class BirthdayList extends Activity {

	private BirthdayDbAdapter dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_birthday_list);
		dbHelper= new BirthdayDbAdapter(this);
		dbHelper.open();
	
	//	dbHelper.deleteAllBirthdays();
		
		//dbHelper.insertSomeBirthdays();

		
	displayListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.birthday_list, menu);
		return true;
	}

	public void displayListView()
	{
		Cursor cursor =dbHelper.fetchAllBirthdays();
		startManagingCursor(cursor);
		String[] columns =new String[]{
				BirthdayDbAdapter.KEY_FNAME,
				BirthdayDbAdapter.KEY_LNAME,
				BirthdayDbAdapter.KEY_BIRTHDATE,
				BirthdayDbAdapter.KEY_ROWID
			
				
		};
		
		
		int[] to = new int[]{
			R.id.fname,
			R.id.lname,
			R.id.birthdate,
			R.id.Bid
		};
		
		dataAdapter =new SimpleCursorAdapter(this,
				R.layout.birthday_info,
				cursor,
				columns,
				to,
				0);
		
		
		ListView listView = (ListView)findViewById(R.id.listViewBirthday);
		listView.setAdapter(dataAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view, 
				     int position, long id) {
				// TODO Auto-generated method stub
				
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				String fname=cursor.getString(cursor.getColumnIndexOrThrow("fname"));
				 Toast.makeText(getApplicationContext(),
					     fname, Toast.LENGTH_SHORT).show();

			}
		});
		
		
	}
	
	
}
