package io.seamoss.urbino.data.models;

import io.seamoss.urbino.data.models.tasks.BaseTask;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public interface Task {
    enum Types{
        HTML5_GAME, VIDEO, EXERCISES
    }

    int type = -1;

    Task.Types getType();

    int computeGoldValue();

    int computeExperienceValue();

    boolean isHasBonus();

    void setHasBonus(boolean hasBonus);

    int getMultiplier();

    void setMultiplier(int multiplier);
}
