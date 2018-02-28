package com.chessticker.nitinmehta.chessticker;

import android.widget.ImageView;

/**
 * Created by NitinMehta on 27/02/2018.
 */

public class GameData {
    private String gameTitle, gamesubTitle;
    private int thumbnail;

    public GameData(String gameTitle, String gamesubTitle, int thumbnail){
        this.gameTitle = gameTitle;
        this.gamesubTitle = gamesubTitle;
        this.thumbnail = thumbnail;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGamesubTitle() {
        return gamesubTitle;
    }

    public void setGamesubTitle(String gamesubTitle) {
        this.gamesubTitle = gamesubTitle;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
