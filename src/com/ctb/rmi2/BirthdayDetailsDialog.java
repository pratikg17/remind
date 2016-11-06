package com.ctb.rmi2;




import java.util.Calendar;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BirthdayDetailsDialog extends DialogFragment {

	
	private BirthdayDbAdapter dbHelper;
	
	
	PendingIntent pendingIntent;
	int d , m, y;
	 String dateB,fnameB,lnameB ;	
	 String date,fname,lname,id ;
	
	Button btnBSave,btnBCancel,btnBSelectDate;
	EditText etBfname,etBlname;
	 public TextView tvBdate,tvBid;

	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View v = inflater.inflate(R.layout.birthday_details_dialog_fragment,container, false);
		
		 
			dbHelper= new BirthdayDbAdapter(getActivity().getApplicationContext());
			dbHelper.open();
		
		 
		 
		 
		 btnBSelectDate =(Button) v.findViewById(R.id.btnBdatepicker);
			btnBSave =(Button) v.findViewById(R.id.btnBSave);
			btnBCancel =(Button) v.findViewById(R.id.btnBcancel);
			tvBdate =(TextView ) v.findViewById(R.id.tvBtvDate);
			etBfname =(EditText) v.findViewById(R.id.etBDfname);
			etBlname =(EditText) v.findViewById(R.id.etBDlname);
			tvBid=(TextView)v.findViewById(R.id.tvBtvId);
			btnBCancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				getDialog().cancel();	
				}
			});
			btnBSelectDate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				getDialog().cancel();
				callDatePicker();
				}
			});
			
			
			
			
			
			btnBSave.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					fnameB = etBfname.getText().toString();
					lnameB = etBlname.getText().toString();
					dateB = tvBdate.getText().toString();
				
					
					try {
						dbHelper.createBirthday(fnameB,lnameB,dateB);
					
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						Log.w("Exception " ,e);
						Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
					}
					
					setAlarm();	
					
					
				}
			});
		
			
			((TextView)tvBdate).setText(date);
			((EditText)etBfname).setText(fname);
			((EditText)etBlname).setText(lname);
			((TextView)tvBid).setText(id);
			getDialog().setTitle("Add Birthday");
			
			return v;
	}
		
		public void setDay(int day)
		{
			d=day;
		}
		public void setYear(int year)
		{
			y=year;
		}public void setMonth(int month)
		{
			m=month;
		}
		
		public void setDate(String s)
		{
			date=s;
		
		}
		public  void setFirstName( String firstname)
		{
			fname=firstname;
		}
		public  void setLastName( String lastname)
		{
			lname=lastname;
		}
		public  void setId( String id)
		{
			id=id;
		}
		
		public  void callDatePicker()
		{
			FragmentManager fm = getFragmentManager();
			DatePickerFragment dpf = new DatePickerFragment();
			dpf.getFirstName(etBfname.getText().toString());
			dpf.getLastName(etBlname.getText().toString());
			dpf.getDate(tvBdate.getText().toString());
			dpf.show(fm, "Date Picker");

		}
		public void setAlarm()
		{
			String dm = d+":"+m+":"+y;
			Toast.makeText(getActivity(), dm, Toast.LENGTH_SHORT).show();
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, m);
			cal.set(Calendar.YEAR, y);
			cal.set(Calendar.DAY_OF_MONTH, d);
			
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			
			Intent myIntent = new Intent(getActivity().getApplicationContext() ,MyReciever.class);
			pendingIntent =PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, myIntent, 0);
			
			AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
		    alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
		 //   Toast.makeText(getActivity(), "Alarm Sett", Toast.LENGTH_SHORT).show();
		}
	
	
}
