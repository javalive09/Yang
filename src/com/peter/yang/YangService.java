package com.peter.yang;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class YangService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "YangService onStartCommand()", Toast.LENGTH_SHORT).show();
		return START_NOT_STICKY;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "YangService onCreate()", Toast.LENGTH_SHORT).show();
		mHandler.sendEmptyMessageDelayed(0, 5000);
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "YangService onDestroy()", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(getApplicationContext(), YangService.class);
		startService(intent);
	}
	
	Handler mHandler = new Handler(Looper.getMainLooper()) {
		public void handleMessage(Message msg) {
			boolean serviceRunning = serviceRunning(YangService.this, "com.peter.yin");
			if(!serviceRunning) {
				Toast.makeText(YangService.this, "yin not Running", Toast.LENGTH_SHORT).show();
				startService(new Intent("com.peter.yin"));
				sendEmptyMessageDelayed(0, 5000);
			}
		};
	};
	
	private boolean serviceRunning(Context context, String packagename) {
		if(packagename == null || "".equals(packagename)) {
			return false;
		}
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (packagename.equals(service.service.getPackageName())) {
				
				return true;
			}
		}
		return false;
	}
}
