package io.seamoss.urbino.data.models;

import android.net.Uri;

import java.util.List;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public class User {
    private String firstName;
    private String lastName;
    private String UUID;
    private Uri imageUrl;
    private String displayName;
    private List<Board> boards;
    private String backgroundNav;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public String getBackgroundNav() {
        return backgroundNav;
    }

    public void setBackgroundNav(String backgroundNav) {
        this.backgroundNav = backgroundNav;
    }
}
