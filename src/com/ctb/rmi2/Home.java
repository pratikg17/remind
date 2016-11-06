package com.ctb.rmi2;




import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends Activity {

	
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	iv = (ImageView)findViewById(R.id.ivHome);
	iv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
			Intent i = new Intent(Home.this,BirthdayList.class);
			startActivity(i);
		}
	});
	
	
	}

	public void showAddDialog()
	{
		FragmentManager fm = getFragmentManager();
		AddDialog  ad = new AddDialog();
		ad.show(fm, "What you want to add?");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu item) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, item);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem menu) {
		// TODO Auto-generated method stub
		switch (menu.getItemId()) {
		case R.id.action_add:
			Toast.makeText(this, "Add selected", Toast.LENGTH_SHORT)
            .show();
			showAddDialog();
		
			break;

		default:
			break;
		}
		
		
		return true;
	}

}
