package com.ctb.rmi2;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TableRow;



@SuppressLint("NewApi")
public class AddDialog  extends DialogFragment{
	
	TableRow trRem , trBirthday,trToDo;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
View v = inflater.inflate(R.layout.add_dialog_fragment,container,false);
getDialog().setTitle("What you want to add?");
trRem= (TableRow) v.findViewById(R.id.trReminder);
trBirthday= (TableRow) v.findViewById(R.id.trBirthday);
trToDo= (TableRow) v.findViewById(R.id.trTODO);

trRem.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		dismiss();
		FragmentManager fm = getFragmentManager();
		//ReminderDetailsDialog rdd = new ReminderDetailsDialog();
	//	rdd.show(fm, "Reminder Details");
	}
});

trBirthday.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		dismiss();
		
	FragmentManager fm = getFragmentManager();
		BirthdayDetailsDialog bdd = new BirthdayDetailsDialog();
		bdd.show(fm,"Add Birthday");
		
	
	}
});

trToDo.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		dismiss();
		FragmentManager fm = getFragmentManager();
	//	ToDoDetailsDialog tdd = new ToDoDetailsDialog();
		
		//tdd.show(fm, "Create To Do List");
	
	}
});

return v;
		
		
	}
	  
	public void closeFragment()
	{
		getActivity().getFragmentManager().beginTransaction().remove(this).commit();
	}
}
