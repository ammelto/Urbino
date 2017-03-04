package io.seamoss.urbino.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander Melton on 3/2/2017.
 */

public class Subject {
    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
