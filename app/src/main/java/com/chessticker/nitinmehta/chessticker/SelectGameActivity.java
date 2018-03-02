package com.chessticker.nitinmehta.chessticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NitinMehta on 27/02/2018.
 */

public class SelectGameActivity extends AppCompatActivity implements ClickListener{
    private List<GameData> gameDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SelectGameAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.select_game_recycler_view);
        mAdapter = new SelectGameAdapter(gameDataList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        prepareGameData();
    }

    private void prepareGameData() {
        int[] thumbImages = new int[] {
                R.drawable.round_button_reset,
                R.drawable.round_button_start,
                R.drawable.round_button_stop,
                R.drawable.round_button_reset
        };

        GameData data = new GameData("Chess Clock", "A chess clock in android to help you track time on your moves", thumbImages[0]);
        gameDataList.add(data);

        data = new GameData("Dice Roll", "Roll dice with a single tap on screen to play any game", thumbImages[1]);
        gameDataList.add(data);

        data = new GameData("Direction", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever ", thumbImages[2]);
        gameDataList.add(data);

        data = new GameData("Toss Coin", "Toss coin with a single tap on screen", thumbImages[3]);
        gameDataList.add(data);

        data = new GameData("Dumb Charads", "Get random movie names for playing dumb charads", thumbImages[1]);
        gameDataList.add(data);

        data = new GameData("Tambola Housie", "Get unique numbers from 1 to 100 for playing classic Tambola Housie", thumbImages[2]);
        gameDataList.add(data);

        data = new GameData("FRGTHY", "asdajsdjkadefrgthyjukihsdj", thumbImages[3]);
        gameDataList.add(data);

        data = new GameData("Dice Roll", "A virtual Dice", thumbImages[1]);
        gameDataList.add(data);

        data = new GameData("ABCD", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambl", thumbImages[2]);
        gameDataList.add(data);

        data = new GameData("FRGTHY", "asdajsdjkadefrgthyjukihsdj", thumbImages[3]);
        gameDataList.add(data);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(GameData gameDataItem) {

    }
}
