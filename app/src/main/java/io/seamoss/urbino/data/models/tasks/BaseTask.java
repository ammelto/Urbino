package io.seamoss.urbino.data.models.tasks;

import io.seamoss.urbino.data.models.Task;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class BaseTask {
    private Task.Types type;
    private boolean hasBonus = false;
    private int multiplier = 1;

    public BaseTask(Task.Types type) {
        this.type = type;
    }

    public Task.Types getType(){
        return type;
    }

    public boolean isHasBonus() {
        return hasBonus;
    }

    public void setHasBonus(boolean hasBonus) {
        this.hasBonus = hasBonus;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
