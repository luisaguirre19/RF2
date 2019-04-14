package com.restfam.luisaguirre.rf;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    Button btnPlay;
    View vista;
    MediaPlayer mediaplayer;
    String streaming = "http://stream.zenolive.com/gf7eastn0xquv";
    Boolean prepared = false;
    Boolean started = false;


    public BlankFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_blank, container, false);
        btnPlay =  (Button) vista.findViewById(R.id.btnprueba);

        btnPlay.setEnabled(true);
        btnPlay.setText("Cargando");

        mediaplayer = new MediaPlayer();
        mediaplayer.setWakeMode(vista.getContext(), PowerManager.PARTIAL_WAKE_LOCK);
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

        return vista;
    }
    class PlayerTask extends AsyncTask<String, Void, Boolean> {
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
