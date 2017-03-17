package io.seamoss.urbino.views.active_board;

import io.seamoss.urbino.base.mvp.BaseView;
import io.seamoss.urbino.data.models.Node;

/**
 * Created by Alexander Melton on 3/5/2017.
 */

public interface ActiveBoardView extends BaseView{
    void moveCamera(float px, float py);

    void endLoading();

    void selectNode(Node node);

    void openNode(Node node);
}
