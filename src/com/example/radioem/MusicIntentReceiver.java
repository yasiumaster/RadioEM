package com.example.radioem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MusicIntentReceiver extends BroadcastReceiver {

	private PlayerInterface playerInterface;
	MediaPlayer mediaPlayer;

	public MusicIntentReceiver(PlayerInterface playerInterface,
			MediaPlayer mediaPlayer) {

		this.playerInterface = playerInterface;
		this.mediaPlayer = mediaPlayer;
	}
	
	public void passPlayingStatus(boolean isPlaying) {
		//this.isPlaying = isPlaying;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
			int state = intent.getIntExtra("state", -1);
			switch (state) {
			case 0:
				if(MainActivity.headSetPlugged) {
				Toast.makeText(context, "Odlaczono sluchawki. ", Toast.LENGTH_LONG).show();
				MainActivity.headSetPlugged = false;
				if (MainActivity.isPlaying) {
					playerInterface.stopStream();
				}
				}
				break;
			case 1:
				MainActivity.headSetPlugged = true;
				Toast.makeText(context, "Podlaczono sluchawki. ",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	}

}
