package io.seamoss.urbino.views.home;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.nav.BaseNavActivity;
import io.seamoss.urbino.views.home.boards.BoardsFragment;

/**
 * Created by Alexander Melton on 2/11/2017.
 */

public class HomeActivity extends BaseNavActivity implements HomeView{

    @Inject HomePresenter homePresenter;

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
}
