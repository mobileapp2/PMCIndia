package com.imuons.pmcindia.DataModel;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelData {
    @SerializedName("level_id")
    @Expose
    private Integer levelId;
    @SerializedName("level_name")
    @Expose
    private String levelName;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @NonNull
    @Override
    public String toString() {
        //return super.toString();
        return levelName;
    }
}

