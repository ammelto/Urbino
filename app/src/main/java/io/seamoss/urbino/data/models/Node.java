package io.seamoss.urbino.data.models;

import android.util.ArrayMap;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class Node {
    public enum Challenges{
     EXP_PER_PERSON, MISS_NO_QUESTIONS, HELP_ONE_PERSON
    }
    private String name;
    private Boolean complete;
    private int challenge;
    private int multiplier;
    private String description;
    private int people;

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public int getChallenge() {
        return challenge;
    }

    public void setChallenge(int challenge) {
        this.challenge = challenge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
