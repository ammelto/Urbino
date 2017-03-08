package io.seamoss.urbino.views.public_boards_list;

import java.util.List;

import io.seamoss.urbino.base.BaseFragment;
import io.seamoss.urbino.base.mvp.BaseView;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.Subject;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public interface PublicBoardsView extends BaseView {
    void buildViewPager(List<Subject> subjects);

    void gotoBoardInfoActivity(Board board);
}
