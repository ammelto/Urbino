package io.seamoss.urbino.views.home.boards;

import javax.inject.Inject;

import io.seamoss.urbino.base.mvp.BasePresenter;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.User;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public class BoardsPresenter extends BasePresenter<BoardsView> {

    private User user;

    @Inject
    public BoardsPresenter(User user) {
        this.user = user;
    }

    public void buildView(){
        getView().fillBoardsList(user.getBoards());
    }

    @Override
    public void attachView(BoardsView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


}
