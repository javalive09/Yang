package com.peter.yang;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class YangService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "YangService onCreate()", Toast.LENGTH_LONG).show();
		handler.sendEmptyMessageDelayed(0, 1);
	}
	
	Handler handler = new Handler(Looper.getMainLooper()) {
		public void handleMessage(android.os.Message msg) {
			startService(new Intent("com.peter.yin"));
			
			sendEmptyMessageDelayed(0, 1);
		};
	};
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "YangService onDestroy()", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(), YangService.class);
		startService(intent);
	}
}
