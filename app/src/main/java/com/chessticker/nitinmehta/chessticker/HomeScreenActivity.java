package com.chessticker.nitinmehta.chessticker;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreenActivity extends Activity {

    private TextView textView1, textView2;
    Handler handler;
    long Seconds, Minutes, MilliSeconds, Hour;
    long MillisecondTime1, StartTime1, TimeBuff1, UpdateTime1 = 0;
    long MillisecondTime2, StartTime2, TimeBuff2, UpdateTime2 = 0;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        handler = new Handler();

        textView1 = (TextView)findViewById(R.id.watch1);
        textView2 = (TextView)findViewById(R.id.watch2);
        startButton = (Button)findViewById(R.id.buttonStart);

        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                StartTime2 = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);

            }
        });

        //blue color
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //below two lines will pause blue time clock
                TimeBuff1 += MillisecondTime1;
                handler.removeCallbacks(runnable);


                //pause blue time and start time in pink clock
                StartTime1 = SystemClock.uptimeMillis();
                handler.postDelayed(runnableNew,0);
            }
        });

        //pink color
        textView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TimeBuff2 += MillisecondTime2;
                handler.removeCallbacks(runnableNew);

                StartTime2 = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
            }
        });
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime2 = SystemClock.uptimeMillis() - StartTime2;
            UpdateTime2 = TimeBuff2 + MillisecondTime2;
            Seconds = (UpdateTime2/1000);
            Minutes = Seconds/60;
            Hour = Minutes/60;
            MilliSeconds = (UpdateTime2 % 1000);

            textView1.setText("" + Hour + ":"
                    + String.format("%2d", Minutes) + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
        }
    };

    public Runnable runnableNew = new Runnable() {
        @Override
        public void run() {
            MillisecondTime1 = SystemClock.uptimeMillis() - StartTime1;
            UpdateTime1 = TimeBuff1 + MillisecondTime1;
            Seconds = (UpdateTime1/1000);
            Minutes = Seconds/60;
            MilliSeconds = (UpdateTime1 % 1000);

            textView2.setText("" + Hour + ":"
                    + String.format("%2d", Minutes) + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
        }
    };

}
