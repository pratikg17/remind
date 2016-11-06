package com.ctb.rmi2;

import java.util.Calendar;



import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    public int d,m,y;
public 	String fname,lname,bday;
public String stringOfDate;

	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		  final Calendar c = Calendar.getInstance();
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH);
	        int day = c.get(Calendar.DAY_OF_MONTH);

	        return new DatePickerDialog(getActivity(), this, year, month, day);
		
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// TODO Auto-generated method stub
		stringOfDate = day + ":" + month + ":" + year;
		 d=day;
		 m=month;
		 y=year;
		 resendData();

	}
	
	public  void getFirstName( String firstname)
	{
		fname=firstname;
	}
	public  void getLastName( String lastname)
	{
		lname=lastname;
	}
	public  void getDate( String date)
	{
		bday=date;
	}
	
	public void resendData()
	{
		 FragmentManager fm= getFragmentManager();
	     BirthdayDetailsDialog bdd = new BirthdayDetailsDialog(); 
		 bdd.setFirstName(fname);
		 bdd.setDate(stringOfDate);
		 bdd.setLastName(lname);
	    bdd.setDay(d);
	    bdd.setMonth(m);
	    bdd.setYear(y);
		 bdd.show(fm, "Add Birthday");
	     
	}
	

}
