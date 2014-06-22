package com.peter.yang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class RestartReceiver extends BroadcastReceiver {

	private static final String PACKAGE_NAME = "com.peter.yin";
	private static final String ACTION = PACKAGE_NAME;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Uri url = intent.getData();
		String str = url.toString();
		String[] infos = str.split(":");
		for(String info : infos) {
			if(info.equals(PACKAGE_NAME)) {
				Toast.makeText(context, "YangService RestartReceiver", Toast.LENGTH_SHORT).show();
				context.startService(new Intent(ACTION));
			}
		}
	}

}
