package io.seamoss.urbino.views.home.boards;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseFragment;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public class BoardsFragment extends BaseFragment implements BoardsView {

    @Inject
    BoardsPresenter boardsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Urbino.instance().getAppGraph().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        boardsPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        boardsPresenter.detachView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_boards, container, false);
        setUnbinder(ButterKnife.bind(this, fragmentView));
        return fragmentView;
    }
}
