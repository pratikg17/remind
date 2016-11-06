package com.ctb.rmi2;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	

		 Thread tr = new Thread()
	        {
	        	public void run()
	        	{
	        		try {
	    				sleep(3000);
	    				Intent iLogin = new Intent(getApplicationContext(),Home.class);
	    				startActivity(iLogin);
	    				finish();
	    				
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	        		
	        	}
	        
	        };
	        tr.start();

	
	
	
	}

	

}
