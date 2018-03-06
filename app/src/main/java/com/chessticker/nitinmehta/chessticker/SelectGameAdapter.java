package com.chessticker.nitinmehta.chessticker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NitinMehta on 27/02/2018.
 */

public class SelectGameAdapter extends RecyclerView.Adapter<SelectGameAdapter.MyViewHolder> {
    private List<GameData> gameDataList;
    private Context mContext;

    public SelectGameAdapter(Context context, List<GameData> gameDataList){
        this.gameDataList = gameDataList;
        this.mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView gameTitle, gameSubtitle, startButton;
        public ImageView gameThumbnail;


        public MyViewHolder(View view){
            super(view);
            gameTitle = (TextView)view.findViewById(R.id.game_title);
            gameSubtitle = (TextView)view.findViewById(R.id.game_subtitle);
            gameThumbnail = (ImageView)view.findViewById(R.id.game_thumbnail);
            startButton = (TextView)view.findViewById(R.id.start_button);
        }
    }

    public SelectGameAdapter(List<GameData>gameDataList){
        this.gameDataList = gameDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_game_row, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        GameData data = gameDataList.get(position);
        holder.gameTitle.setText(data.getGameTitle());
        holder.gameSubtitle.setText(data.getGamesubTitle());
        holder.gameThumbnail.setImageResource(data.getThumbnail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    v.getContext().startActivity(new Intent(v.getContext(), ChessClockActivity.class));
                } else if (position == 1) {
                    v.getContext().startActivity(new Intent(v.getContext(), DiceRollActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != gameDataList ? gameDataList.size() : 0);
    }
}
