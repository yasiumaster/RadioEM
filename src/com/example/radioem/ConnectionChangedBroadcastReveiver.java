package com.example.radioem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectionChangedBroadcastReveiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		NetworkInfo nwInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
		
		Toast.makeText(context, "Utracono polaczenie! " + nwInfo.getState(), Toast.LENGTH_LONG).show();
	}

}
