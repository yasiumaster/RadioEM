package com.example.radioem;

import android.media.MediaPlayer;
import android.widget.Button;

public class PlayerInterface {

	Button bt;
	MediaPlayer mediaPlayer;
	
	public PlayerInterface(Button bt, MediaPlayer mp) {
		this.bt = bt;
		this.mediaPlayer = mp;
	}
	
	public void stopStream() {
		mediaPlayer.stop();
		MainActivity.isPlaying = false;
		bt.setText("Play");
	}
}
