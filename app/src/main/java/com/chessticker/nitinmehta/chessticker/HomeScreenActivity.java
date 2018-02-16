package com.chessticker.nitinmehta.chessticker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;

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
        final AlertDialog dialog = mBuilder.create();

        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!setTime.getText().toString().isEmpty()) {
                    currentStartTime = Long.parseLong(setTime.getText().toString());
                    String finalTimeTobeShown = convertTime(currentStartTime);
                    textView1.setText(finalTimeTobeShown);
                    textView2.setText(finalTimeTobeShown);
                    Toast.makeText(HomeScreenActivity.this,
                            "Time Updated",
                            Toast.LENGTH_SHORT).show();
                    resetTimers();
                    dialog.cancel();
                } else {
                    Toast.makeText(HomeScreenActivity.this,
                            "Please enter Minutes",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBuilder.setView(mView);
        dialog.show();
    }

    private void resetTimers() {
        bottomTimer = new BottomCounterClass(currentStartTime * 60000,1000);
        topTimer = new TopCounterClass(currentStartTime * 60000,1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
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
                buttonEnterTime.setEnabled(false);
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                resetButton.setEnabled(true);
                topTimerLayout.setEnabled(false);
                bottomTimerLayout.setEnabled(true);
                bottomTimer.start();
                isTimeRunning = true;
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));

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
                buttonEnterTime.setEnabled(true);
                bottomTimer.cancel();
                topTimer.cancel();
                isTimeRunning = false;
                topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_white_background));
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
                buttonEnterTime.setEnabled(true);
                bottomTimer.cancel();
                topTimer.cancel();
                textView1.setText(initialTime);
                textView2.setText(initialTime);
                isTimeRunning = false;
                topTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_gray_background));
                bottomTimerLayout.setBackground(getResources().getDrawable(R.drawable.customborder_gray_background));
            }
        });

        //Bottom Layout
        bottomTimerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        //Top Layout
        topTimerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        buttonEnterTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTimeDialog();
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
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            textView1.setText(hms);
            millisLeft = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            Toast.makeText(HomeScreenActivity.this, "Game Over! Player 2 Wins", Toast.LENGTH_SHORT).show();
            textView1.setText(initialTime);
            textView2.setText(initialTime);
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
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textView2.setText(hms);
            millisLeft = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            Toast.makeText(HomeScreenActivity.this, "Game Over! Player 1 Wins", Toast.LENGTH_SHORT).show();
            textView1.setText(initialTime);
            textView2.setText(initialTime);
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
