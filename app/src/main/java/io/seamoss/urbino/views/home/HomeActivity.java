package io.seamoss.urbino.views.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import javax.inject.Inject;

import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.nav.BaseNavActivity;
import io.seamoss.urbino.data.SharedPrefsManager;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.views.active_board.ActiveBoardActivity;
import io.seamoss.urbino.views.home.boards.BoardsFragment;
import io.seamoss.urbino.views.public_boards_list.PublicBoardsActivity;
import io.seamoss.urbino.views.home.board_info.BoardInfoActivity;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

public class HomeActivity extends BaseNavActivity implements HomeView{

    @Inject
    HomePresenter homePresenter;

    @Inject
    SharedPrefsManager sharedPrefsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");

        Urbino.instance().getAppGraph().inject(this);
        attachFragment(R.id.fragment_container, new BoardsFragment());

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.homePresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.homePresenter.detachView();
    }

    @Override
    public void gotoPublicBoardsActivity() {
        Intent intent = new Intent(this, PublicBoardsActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
    }

    @Override
    public void gotoBoardInfoActivity(Board board) {
        Intent intent = new Intent(this, BoardInfoActivity.class);
        intent.putExtras(getIntent());
        intent.putExtra("NAME", board.getName());
        intent.putExtra("URL", board.getUrl());
        intent.putExtra("SUBSCRIBED", true);
        startActivity(intent);
    }

    @Override
    public void launchCourseCodeDialog() {
        CourseCodeDialog courseCodeDialog = new CourseCodeDialog();

        courseCodeDialog.show(getFragmentManager(), null);
    }

    @Override
    public void launchActiveBoard(Board board) {
        Intent intent = new Intent(this, ActiveBoardActivity.class);
        intent.putExtras(getIntent());

        startActivity(intent);
    }
}
