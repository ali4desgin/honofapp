package com.alhonof.app;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CountAcitvity extends AppCompatActivity {
    private int counter = 0;
    private int total = 30; // the total number

    TextView textView5;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_acitvity);
        textView5 = findViewById(R.id.textView5);
        mp  = MediaPlayer.create(this, R.raw.mysound);
        makeCounter();
    }



    public  void  makeCounter(){


        new Thread(new Runnable() {

            public void run() {
                while (counter < total) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    textView5.post(new Runnable() {

                        public void run() {


                            textView5.setText("" + counter);
                            if(counter==total-1){
                                mp.start();
                                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        mp.start();
                                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer) {
                                                mp.start();
                                            }
                                        });
                                    }
                                });
                            }


                        }

                    });





                    counter++;
                }

            }



        }).start();;


    }
}
