package io.seamoss.urbino.views.home.boards;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseFragment;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.views.public_boards_list.PublicBoardsActivity;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public class BoardsFragment extends BaseFragment implements BoardsView {

    @Inject
    BoardsPresenter boardsPresenter;

    @BindView(R.id.board_recycler)
    RecyclerView boardRecycler;

    @BindView(R.id.home_fab)
    FabSpeedDial fab;

    private BoardAdapter boardAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);
    }

    @Override
    public void fillBoardsList(List<Board> boardList) {
        boardAdapter.swapData(boardList);
    }

    @Override
    public void onResume() {
        super.onResume();
        boardsPresenter.attachView(this);
        boardsPresenter.buildView();
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        boardRecycler.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(boardRecycler.getContext(),
                linearLayoutManager.getOrientation());
        boardRecycler.addItemDecoration(dividerItemDecoration);

        boardAdapter = new BoardAdapter();
        boardRecycler.setAdapter(boardAdapter);

        addScrollListener(boardRecycler);
        fab.setMenuListener((new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_goto_public_boards:
                        switchToPublicBoardsFragment();
                        break;
                    case R.id.action_goto_class_code:
                        break;
                }
                return false;
            }
        }));

        return fragmentView;
    }

    private void switchToPublicBoardsFragment(){
        switchFragment(R.layout.fragment_public_boards_list, new PublicBoardsActivity(), true);
    }

    private void addScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                    int fab_bottomMargin = layoutParams.bottomMargin;
                    fab.animate().translationY(fab.getHeight() + fab_bottomMargin).setInterpolator(new LinearInterpolator()).start();
                } else if (dy < 0) {
                    fab.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
                }
            }
        });
    }
}
