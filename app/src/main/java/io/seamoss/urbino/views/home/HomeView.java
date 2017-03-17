package io.seamoss.urbino.views.home;

import io.seamoss.urbino.base.mvp.BaseView;
import io.seamoss.urbino.data.models.Board;

/**
 * Created by Alexander Melton on 2/13/2017.
 */

public interface HomeView extends BaseView {

    void gotoPublicBoardsActivity();

    void gotoBoardInfoActivity(Board board);

    void launchCourseCodeDialog();

    void launchActiveBoard(Board board);
}
