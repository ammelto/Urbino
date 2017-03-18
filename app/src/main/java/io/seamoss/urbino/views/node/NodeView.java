package io.seamoss.urbino.views.node;

import io.seamoss.urbino.base.mvp.BaseView;
import io.seamoss.urbino.data.models.tasks.Game;
import io.seamoss.urbino.data.models.tasks.Video;

/**
 * Created by Alexander Melton on 3/16/2017.
 */

public interface NodeView extends BaseView {
    void openVideo(Video video);

    void launchGame(Game game);
}
