package io.seamoss.urbino.data.models.tasks;

import io.seamoss.urbino.data.models.Task;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class Game extends BaseTask implements Task {

    private String url;

    public Game(String url) {
        super(Types.HTML5_GAME);
        this.url = url;
    }

    @Override
    public int computeExperienceValue() {
        return 120;
    }

    @Override
    public int computeGoldValue() {
        return 40;
    }

    public String getUrl() {
        return url;
    }
}
