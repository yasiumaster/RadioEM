package com.example.radioem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
	    if (extras != null) {
	      String state = extras.getString(TelephonyManager.EXTRA_STATE);
	      Log.w("MY_DEBUG_TAG", state);
	      if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
	    	  //mute here mediaPlayer.setVolume(0,0);
	        String phoneNumber = extras
	            .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
	        Log.w("MY_DEBUG_TAG", phoneNumber);
	      }
	    }
	  }
}
