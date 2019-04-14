package com.restfam.luisaguirre.rf;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.FaceDetector;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;

import java.io.IOException;

public class Radio extends AppCompatActivity {
    Button btnPlay;

    MediaPlayer mediaplayer;
    String streaming = "http://stream.zenolive.com/gf7eastn0xquv";
    Boolean prepared = false;
    Boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        btnPlay = (Button) findViewById(R.id.button2);
        btnPlay.setEnabled(true);
        btnPlay.setText("Cargando");

        mediaplayer = new MediaPlayer();
        mediaplayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        new PlayerTask().execute(streaming);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started){
                    started = false;
                    mediaplayer.pause();
                    btnPlay.setText("PLAY");
                }else{
                    started = true;
                    mediaplayer.start();
                    btnPlay.setText("PAUSE");
                }

            }
        });
    }

    class PlayerTask extends AsyncTask<String, Void, Boolean>{
        @Override
        protected Boolean doInBackground (String... strings){
           try {

               mediaplayer.setDataSource(strings[0]);
               mediaplayer.prepare();
               prepared = true;
           } catch (IOException e) {
               e.printStackTrace();
           }
           return prepared;
        }

        @Override
        protected void onPostExecute(Boolean  aBoolean){
            super.onPostExecute(aBoolean);
            btnPlay.setEnabled(true);
            btnPlay.setText("PLAY");
        }




    }




}
