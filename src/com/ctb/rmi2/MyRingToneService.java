package com.ctb.rmi2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyRingToneService extends Service {

	public	MediaPlayer media_song;
	public	WindowManager wm; 
	public Uri alert;
	public Ringtone rt;
	Button btnStop,btnSnooze;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	      if (alarmUri == null) {
	          alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
	      }
	       rt = RingtoneManager.getRingtone(getApplication(), alarmUri);
	      rt.play();
		
		
		
		wm =(WindowManager)getSystemService(WINDOW_SERVICE);
		LayoutInflater inflater =(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
	            WindowManager.LayoutParams.WRAP_CONTENT,
	            WindowManager.LayoutParams.WRAP_CONTENT,
	            WindowManager.LayoutParams.TYPE_PHONE,
	            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
	          
	            PixelFormat.TRANSLUCENT);
		  params.gravity = Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL;
		  params.x = 0;
	      params.y = 100;
	    final LinearLayout chatheadView = (LinearLayout) inflater.inflate(R.layout.ringtone_layout, null);
		 
	     
	     btnStop = (Button)chatheadView.findViewById(R.id.btnRTStop);
	    btnSnooze =(Button)chatheadView.findViewById(R.id.btnRTSnooze);
	     wm.addView(chatheadView, params);
		

	     btnSnooze.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rt.stop();
				wm.removeView(chatheadView);
				PendingIntent pendingIntent;
				Intent myIntent = new Intent(getApplicationContext() ,MyReciever.class);
				pendingIntent =PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, 0);
				
				long currentTimeMillis = System.currentTimeMillis();
				long nextUpdateTimeMillis = currentTimeMillis + 1 * DateUtils.MINUTE_IN_MILLIS;
				Time nextUpdateTime = new Time();
				nextUpdateTime.set(nextUpdateTimeMillis);
				AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			    alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
		
			
			}
		});
	     

	     btnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	media_song.stop();
				rt.stop();
				
				wm.removeView(chatheadView);
				
			}
		});
		
		

		
		
		
		return super.onStartCommand(intent, flags, startId);

	}

}
