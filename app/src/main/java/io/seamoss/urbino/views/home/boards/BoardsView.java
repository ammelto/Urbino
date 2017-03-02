package io.seamoss.urbino.views.home.boards;

import java.util.List;

import io.seamoss.urbino.base.mvp.BaseView;
import io.seamoss.urbino.data.models.Board;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public interface BoardsView extends BaseView {

    void fillBoardsList(List<Board> boardList);
}
