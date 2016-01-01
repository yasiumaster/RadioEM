package com.example.radioem;

import java.io.IOException;

import android.app.Activity;
//import android.content.BroadcastReceiver; //for next v.
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter; //for next v.
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * 
 * @author Daniel Skoczny
 * @version beta
 *
 */

public class MainActivity extends Activity {

	private MediaPlayer mediaPlayer = new MediaPlayer();
	String url = "http://194.181.177.253:8000";
	// String url = "http://streaming.radionomy.com/Radio-Mozart"; //przykladowy
	// dzialajacy stream
	static boolean isPlaying = false;
	static boolean headSetPlugged = false;
	boolean isConnected;
	ConnectivityManager cm;
	Button actionButton;

	// for next v.
	/*
	 * private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
	 * 
	 * @Override public void onReceive(Context context, Intent intent) { boolean
	 * noConnectivity =
	 * intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
	 * } };
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// sprawdza status polaczenia sieciowego
		cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		actionButton = (Button) findViewById(R.id.actionButton);

		// headphones
		PlayerInterface playerInterface = new PlayerInterface(actionButton,
				mediaPlayer);
		final MusicIntentReceiver musicIntentReceiver = new MusicIntentReceiver(
				playerInterface, mediaPlayer);
		registerReceiver(musicIntentReceiver, new IntentFilter(
				Intent.ACTION_HEADSET_PLUG));

		actionButton.setText("Play");
		actionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (isPlaying) {
					stopStream();
				} else {
					if (isConnected()) {
						playStream();
					} else {
						Toast.makeText(MainActivity.this, "Brak po³¹czenia!",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		// for next v. - sprawdz zmiane statusu sieci
		// registerReceiver(new ConnectionChangedBroadcastReveiver(), new
		// IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

		/*
		 * IntentFilter phoneStateFilter = new
		 * IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
		 * registerReceiver(new PhoneStateReceiver(), phoneStateFilter);
		 */

	}

	private boolean isConnected() {
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}

	private void prepareStream() {

		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mediaPlayer.prepareAsync();
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
				actionButton.setEnabled(true);
				isPlaying = true;
			}
		});

	}

	private void stopStream() {
		mediaPlayer.stop();
		// mediaPlayer.release();
		isPlaying = false;
		actionButton.setText("Play");
	}

	private void playStream() {
		actionButton.setEnabled(false);
		prepareStream();
		actionButton.setText("Stop");

	}

}
