package io.seamoss.urbino.views.public_boards_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseFragment;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class PublicBoardsActivity extends BaseFragment implements PublicBoardsView {

    @Inject
    PublicBoardsPresenter publicBoardsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        publicBoardsPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        publicBoardsPresenter.detachView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_public_boards_list, container, false);
        setUnbinder(ButterKnife.bind(this, fragmentView));

        return fragmentView;
    }
}
