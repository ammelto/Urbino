package io.seamoss.urbino.views.node;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.views.home.boards.BoardsFragment;

/**
 * Created by Alexander Melton on 3/16/2017.
 */

public class NodeActivity extends BaseActivity implements NodeView {


    @Inject
    NodePresenter nodePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");

        Urbino.instance().getAppGraph().inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.nodePresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.nodePresenter.detachView();
    }
}
