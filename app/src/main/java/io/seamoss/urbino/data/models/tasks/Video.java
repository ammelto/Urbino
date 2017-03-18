package io.seamoss.urbino.data.models.tasks;

import io.seamoss.urbino.data.models.Task;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class Video extends BaseTask implements Task {

    private String url;

    public Video(String url) {
        super(Types.VIDEO);
        this.url = url;
    }

    @Override
    public int computeGoldValue() {
        return 100;
    }

    @Override
    public int computeExperienceValue() {
        return 50;
    }

    public String getUrl() {
        return url;
    }
}
