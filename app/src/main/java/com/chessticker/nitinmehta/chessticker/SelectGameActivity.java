package com.chessticker.nitinmehta.chessticker;

import android.content.Context;
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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NitinMehta on 27/02/2018.
 */

public class SelectGameActivity extends AppCompatActivity{
    private List<GameData> gameDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SelectGameAdapter mAdapter;
    private ImageView imageView;

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

        ArrayList<Integer> thumbImages = new ArrayList<Integer>();
        thumbImages.add(R.drawable.ic_chess);
        thumbImages.add(R.drawable.ic_dice);

        GameData data = new GameData("Chess Clock", "A chess clock in android to help you track time on your moves", thumbImages.get(0));
        gameDataList.add(data);

        data = new GameData("Dice Roll", "Roll dice with a single tap on screen to play any game", thumbImages.get(1));
        gameDataList.add(data);

        mAdapter.notifyDataSetChanged();
    }
}
