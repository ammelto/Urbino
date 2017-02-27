package io.seamoss.urbino.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alexander Melton on 2/27/2017.
 */

public class UrbinoProfile {
    @SerializedName("boards_list")
    private List<Board> boards;

    @SerializedName("background")
    private String backgroundUrl;

    public List<Board> getBoards() {
        return boards;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }
}
