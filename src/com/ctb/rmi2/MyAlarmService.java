package com.ctb.rmi2;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyAlarmService extends Service {
	private NotificationManager nManager;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
		
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	
		nManager= (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(this.getApplicationContext(),Home.class);
		Notification notification = new Notification(R.drawable.ic_launcher,"Upcoming Reminder",System.currentTimeMillis());
		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		PendingIntent pendingIntentNotification =PendingIntent.getActivity(this.getApplicationContext(), 0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
		notification.flags |=Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(this.getApplicationContext(),"R mi !!!","Reminder!!!" , pendingIntentNotification);
		nManager.notify(0,notification);
	
	}
	
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
