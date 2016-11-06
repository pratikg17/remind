package com.ctb.rmi2;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		 Log.i("Notification", "Received");
		Intent service1= new Intent(context,MyAlarmService.class);
		context.startService(service1);
		
		Intent service2 = new Intent(context,MyRingToneService.class);
		context.startService(service2);
	}

}
