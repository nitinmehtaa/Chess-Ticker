package com.chessticker.nitinmehta.chessticker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by NitinMehta on 05/03/2018.
 */

public class DiceRollActivity extends Activity {

    public static final Random RANDOM = new Random();
    private Button tapButton1, tapButton2, tapButton3, tapRollDice;
    private ImageView image1, image2, image3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_roll_activity);

        tapButton1 = (Button) findViewById(R.id.button1);
        tapButton2 = (Button) findViewById(R.id.button2);
        tapButton3 = (Button) findViewById(R.id.button3);
        image1 = (ImageView) findViewById(R.id.diceImage1);
        image2 = (ImageView) findViewById(R.id.diceImage2);
        image3 = (ImageView) findViewById(R.id.diceImage3);
        tapRollDice = (Button) findViewById(R.id.tapButton);

        image1.setVisibility(View.VISIBLE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);
        tapButton1.setBackground(getResources().getDrawable(R.drawable.custom_dice_button_border));
        tapButton1.setTextColor(getResources().getColor(R.color.purpleColor));

        tapRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1 = randomDiceValue();
                int value2 = randomDiceValue();
                int value3 = randomDiceValue();

                int re1 = getResources().getIdentifier("ic_dice_" + value1, "drawable", getPackageName());
                int re2 = getResources().getIdentifier("ic_dice_" + value2, "drawable", getPackageName());
                int re3 = getResources().getIdentifier("ic_dice_" + value3, "drawable", getPackageName());

                rotateImageAnimation(image1);
                image1.setImageResource(re1);
                rotateImageAnimation(image2);
                image2.setImageResource(re2);
                rotateImageAnimation(image3);
                image3.setImageResource(re3);
            }
        });

        tapButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.GONE);
                image3.setVisibility(View.GONE);

                tapButton1.setBackground(getResources().getDrawable(R.drawable.custom_dice_button_border));
                tapButton1.setTextColor(getResources().getColor(R.color.purpleColor));

                tapButton2.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                tapButton2.setTextColor(getResources().getColor(R.color.lightPurpleColor));

                tapButton3.setBackground(getResources().getDrawable(R.color.whiteColor));
                tapButton3.setTextColor(getResources().getColor(R.color.lightPurpleColor));
            }
        });

        tapButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.GONE);
                tapButton2.setBackground(getResources().getDrawable(R.drawable.custom_dice_button_border));
                tapButton2.setTextColor(getResources().getColor(R.color.purpleColor));

                tapButton1.setBackground(getResources().getDrawable(R.color.whiteColor));
                tapButton1.setTextColor(getResources().getColor(R.color.lightPurpleColor));

                tapButton3.setBackground(getResources().getDrawable(R.color.whiteColor));
                tapButton3.setTextColor(getResources().getColor(R.color.lightPurpleColor));
            }
        });

        tapButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);
                tapButton3.setBackground(getResources().getDrawable(R.drawable.custom_dice_button_border));
                tapButton3.setTextColor(getResources().getColor(R.color.purpleColor));

                tapButton1.setBackground(getResources().getDrawable(R.color.whiteColor));
                tapButton1.setTextColor(getResources().getColor(R.color.lightPurpleColor));

                tapButton2.setBackground(getResources().getDrawable(R.color.whiteColor));
                tapButton2.setTextColor(getResources().getColor(R.color.lightPurpleColor));
            }
        });
    }

    public void rotateImageAnimation(View view) {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);
        if(image1.isShown() && image2.isShown() && image3.getVisibility() == view.GONE) {
            image1.startAnimation(animation);
            image2.startAnimation(animation);
            image3.clearAnimation();
        } else if (image1.isShown() && image2.getVisibility() == view.GONE && image3.getVisibility() == view.GONE){
            image1.startAnimation(animation);
            image2.clearAnimation();
            image3.clearAnimation();
        } else {
            image1.startAnimation(animation);
            image2.startAnimation(animation);
            image3.startAnimation(animation);
        }
    }

    public static int randomDiceValue(){
        return RANDOM.nextInt(6) + 1;
    }
}
