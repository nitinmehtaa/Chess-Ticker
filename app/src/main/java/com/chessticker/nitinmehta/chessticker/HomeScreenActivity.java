package com.chessticker.nitinmehta.chessticker;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.concurrent.TimeUnit;


public class HomeScreenActivity extends Activity {

    private TextView textView1, textView2;
    private LinearLayout topTimerLayout, bottomTimerLayout;
    private Button startButton, stopButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        textView1 = (TextView) findViewById(R.id.watch1);
        textView2 = (TextView) findViewById(R.id.watch2);
        startButton = (Button) findViewById(R.id.buttonStart);
        stopButton = (Button) findViewById(R.id.buttonStop);
        resetButton = (Button) findViewById(R.id.buttonResume);
        topTimerLayout = (LinearLayout) findViewById(R.id.topLayout);
        bottomTimerLayout = (LinearLayout) findViewById(R.id.bottomLayout);

        //Buttons state on start of Application
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
        topTimerLayout.setEnabled(false);
        bottomTimerLayout.setEnabled(false);

        //Display time on start of application
        final String initialTime = "00:12:00";
        textView1.setText(initialTime);
        textView2.setText(initialTime);
        final CounterClass bottomTimer = new CounterClass(720000,1000);
        final CounterClass topTimer = new CounterClass(720000,1000);

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                resetButton.setEnabled(true);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(true);
                bottomTimer.start();

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                stopButton.setEnabled(false);
                resetButton.setEnabled(true);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(false);
                bottomTimer.cancel();
                topTimer.cancel();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetButton.setEnabled(false);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(false);
                bottomTimer.cancel();
                topTimer.cancel();
                textView1.setText(initialTime);
                textView2.setText(initialTime);
            }
        });

        //Bottom Layout
        bottomTimerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bottomTimer.pause();
//                topTimer.start();

            }
        });

        //Top Layout
        topTimerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                topTimer.pause();
//                bottomTimer.resume();
            }
        });
    }

    public class CounterClass extends CountDownTimer {

        private long startPauseTime;
        private long pauseTime = 0L;

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textView1.setText(hms);
            textView2.setText(hms);
        }

        @Override
        public void onFinish() {

        }

        public void pause() {
//            startPauseTime = System.currentTimeMillis();
            

        }

        public void resume() {
//            pauseTime += System.currentTimeMillis() - startPauseTime;
        }
    }

}
