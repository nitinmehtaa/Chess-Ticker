package com.chessticker.nitinmehta.chessticker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by NitinMehta on 05/03/2018.
 */

public class DiceRollActivity extends Activity {

    public static final Random RANDOM = new Random();
    private Button tapButton1, tapButton2, tapButton3;
    private ImageView image1, image2, image3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_roll_activity);
        tapButton1 = (Button)findViewById(R.id.button1);
        tapButton2 = (Button)findViewById(R.id.button2);
        tapButton3 = (Button)findViewById(R.id.button3);

//        rollDiceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value1 = randomDiceValue();
//
//                int res1 = getResources().getIdentifier("ic_dice_" + value1, "drawable", "com.chessticker.nitinmehta.chessticker");
//
//                imageView1.setImageResource(res1);
//            }
//        });
    }

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }
}
