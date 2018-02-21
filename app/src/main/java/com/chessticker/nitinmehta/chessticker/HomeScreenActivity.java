package com.chessticker.nitinmehta.chessticker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.TimeUnit;


public class HomeScreenActivity extends Activity {

    TopCounterClass topTimer;
    BottomCounterClass bottomTimer;

    private TextView textView1, textView2;
    private LinearLayout topTimerLayout, bottomTimerLayout;
    private Button startButton, stopButton, resetButton, buttonEnterTime;
    private boolean isTimeRunning = true;
    public final int INITIAL_TIME_IN_MINUTES = 6;
    public String initialTime;
    public long currentStartTime = 6;
    public long maxMinutesAllowed = 1440L;
    public long minMinutesAllowed = 1L;

    //Convert minutes into HH:MM:SS Format
    private String convertTime(long time) {
        String finalTime = "";
        long hour = (time%(24*60)) / 60;
        long minutes = (time%(24*60)) % 60;
        long seconds = time / (24*3600);

        finalTime = String.format("%02d:%02d:%02d",
                TimeUnit.HOURS.toHours(hour) ,
                TimeUnit.MINUTES.toMinutes(minutes),
                TimeUnit.SECONDS.toSeconds(seconds));
            return finalTime;
    }

    //Dialog to set Time
    private void showEditTimeDialog(){
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        final EditText setTime = (EditText) mView.findViewById(R.id.edit_text);
        Button setTimeButton = (Button) mView.findViewById(R.id.button_set_time);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To check empty string
                if (!setTime.getText().toString().isEmpty()) {

                    //To check minutes should be in between 1 and 1440
                     long number = Long.parseLong(setTime.getText().toString());
                    if (number > maxMinutesAllowed || number < minMinutesAllowed) {
                        Toast.makeText(HomeScreenActivity.this,
                                "Time should not be less than 1 minute or more than 24 Hours, Try again ",
                                Toast.LENGTH_LONG).show();
                    } else {
                        currentStartTime = Long.parseLong(setTime.getText().toString());
                        String finalTimeTobeShown = convertTime(currentStartTime);
                        textView1.setText(finalTimeTobeShown);
                        textView2.setText(finalTimeTobeShown);
                        Toast.makeText(HomeScreenActivity.this,
                                "Time Updated",
                                Toast.LENGTH_SHORT).show();
                        resetTimers();
                        dialog.cancel();
                    }

                } else {
                    Toast.makeText(HomeScreenActivity.this,
                            "Please enter Minutes",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    private void resetTimers() {
        try {
        bottomTimer = new BottomCounterClass(currentStartTime * 60000,1000);
        topTimer = new TopCounterClass(currentStartTime * 60000,1000);
        } catch (Exception e){
            Crashlytics.getInstance().core.logException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //To keep screen always ON
        findViews();

        //Buttons state on start of Application
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
        topTimerLayout.setEnabled(false);
        bottomTimerLayout.setEnabled(false);
        buttonEnterTime.setEnabled(true);

        //Display time on start of application
        initialTime = convertTime(INITIAL_TIME_IN_MINUTES);
        textView1.setText(initialTime);
        textView2.setText(initialTime);

        bottomTimer = new BottomCounterClass(360000,1000);
        topTimer = new TopCounterClass(360000,1000);

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                buttonEnterTime.setEnabled(false);
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                resetButton.setEnabled(true);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(true);
                bottomTimer.start();
                isTimeRunning = true;
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                } catch (Exception e){
                    Crashlytics.getInstance().core.logException(e);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                startButton.setEnabled(false);
                stopButton.setEnabled(false);
                resetButton.setEnabled(true);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(false);
                buttonEnterTime.setEnabled(false);
                bottomTimer.cancel();
                topTimer.cancel();
                isTimeRunning = false;
                topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                } catch(Exception e){
                    Crashlytics.getInstance().core.logException(e);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetButton.setEnabled(false);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(false);
                buttonEnterTime.setEnabled(true);
                bottomTimer.cancel();
                topTimer.cancel();
                textView1.setText(initialTime);
                textView2.setText(initialTime);
                isTimeRunning = false;
                topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_gray_background));
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_gray_background));
                } catch (Exception e){
                    Crashlytics.getInstance().core.logException(e);
                }
            }
        });

        //Bottom Layout
        bottomTimerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                bottomTimerLayout.setEnabled(false);
                topTimerLayout.setEnabled(true);
                bottomTimer.pause();
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_gray_background));
                if(isTimeRunning) {
                    topTimer.start();
                    topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                    isTimeRunning = false;
                } else {
                    topTimer.resume();
                    topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                }
                } catch (Exception e){
                    Crashlytics.getInstance().core.logException(e);
                }
            }
        });

        //Top Layout
        topTimerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(true);
                topTimer.pause();
                bottomTimer.resume();
                topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_gray_background));
                if(isTimeRunning) {
                    bottomTimer.start();
                    bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                    isTimeRunning = false;
                } else {
                    bottomTimer.resume();
                    bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                }
                } catch (Exception e){
                    Crashlytics.getInstance().core.logException(e);
                }
            }
        });

        //show dialog on click of edit time button
        buttonEnterTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                showEditTimeDialog();
                } catch (Exception e) {
                    Crashlytics.getInstance().core.logException(e);
                }
            }
        });
    }

    private void findViews(){
        textView1 = (TextView) findViewById(R.id.watch1);
        textView2 = (TextView) findViewById(R.id.watch2);
        startButton = (Button) findViewById(R.id.buttonStart);
        stopButton = (Button) findViewById(R.id.buttonStop);
        resetButton = (Button) findViewById(R.id.buttonResume);
        topTimerLayout = (LinearLayout) findViewById(R.id.topLayout);
        bottomTimerLayout = (LinearLayout) findViewById(R.id.bottomLayout);
        buttonEnterTime = (Button) findViewById(R.id.button_enter_time);
    }

    //CountDownTimer class for bottom layout
    public class BottomCounterClass extends CustomCountDownTimer {

        private long millisLeft = 0;
        public BottomCounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.millisLeft = millisInFuture;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            try {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                textView1.setText(hms);
                millisLeft = millisUntilFinished;
            } catch (Exception e){
                Crashlytics.getInstance().core.logException(e);
            }
        }

        @Override
        public void onFinish() {
            try {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
                Toast.makeText(HomeScreenActivity.this, "Game Over! Player 2 Wins", Toast.LENGTH_SHORT).show();
                textView1.setText(initialTime);
                textView2.setText(initialTime);
            } catch (Exception e){
                Crashlytics.getInstance().core.logException(e);
            }
        }

        @Override
        public long pause() {
            return super.pause();
        }

        @Override
        public long resume() {
            return super.resume();
        }
    }

    //CountDownTimer class for top layout
    public class TopCounterClass extends CustomCountDownTimer {
        private long millisLeft = 0;
        public TopCounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.millisLeft = millisInFuture;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            try {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                textView2.setText(hms);
                millisLeft = millisUntilFinished;
            } catch (Exception e){
                Crashlytics.getInstance().core.logException(e);
            }
        }

        @Override
        public void onFinish() {
            try {
            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            Toast.makeText(HomeScreenActivity.this, "Game Over! Player 1 Wins", Toast.LENGTH_SHORT).show();
            textView1.setText(initialTime);
            textView2.setText(initialTime);
            } catch (Exception e) {
                Crashlytics.getInstance().core.logException(e);
            }

        }

        @Override
        public long pause() {
            return super.pause();
        }

        @Override
        public long resume() {
            return super.resume();
        }
    }
}
