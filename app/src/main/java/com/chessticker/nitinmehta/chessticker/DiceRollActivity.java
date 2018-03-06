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
    private Button rollDiceButton;
    private ImageView imageView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dice_roll_activity);
        rollDiceButton = (Button)findViewById(R.id.tapButton);
        imageView1 = (ImageView)findViewById(R.id.imageView1);

        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1 = randomDiceValue();

                int res1 = getResources().getIdentifier("dice_" + value1, "drawable", "com.chessticker.nitinmehta.chessticker");

                imageView1.setImageResource(res1);
            }
        });
    }

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }
}
