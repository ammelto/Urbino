package io.seamoss.urbino.views.public_boards_list.PublicBoardsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseFragment;
import io.seamoss.urbino.base.BaseSupportFragment;
import io.seamoss.urbino.data.api.UrbinoApi;
import io.seamoss.urbino.data.models.Board;
import io.seamoss.urbino.data.models.User;
import io.seamoss.urbino.views.home.boards.BoardAdapter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/2/2017.
 */

public class PublicBoardsFragment extends Fragment {

    @BindView(R.id.public_board_recycler)
    RecyclerView publicBoardRecycler;

    @Inject
    User user;

    private BoardAdapter boardAdapter;
    private String subject;

    @Inject
    public UrbinoApi urbinoApi;

    private Subscription localSubjectSubscription;

    private void fetchData(String subject){

        localSubjectSubscription = urbinoApi.getPublicBoards(subject)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::buildBoardsList, e -> {
                    Timber.e(e, "Error loading pubic boards for " +subject);
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData(this.getArguments().getString("subject"));
    }

    @Override
    public void onPause() {
        localSubjectSubscription.unsubscribe();
        localSubjectSubscription = null;
        super.onPause();
    }

    public void buildBoardsList(List<Board> boards){
        boardAdapter.swapData(boards);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_public_boards_list, container, false);
        ButterKnife.bind(this, fragmentView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        publicBoardRecycler.setLayoutManager(linearLayoutManager);

        publicBoardRecycler.hasFixedSize();

        publicBoardRecycler.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(publicBoardRecycler.getContext(),
                linearLayoutManager.getOrientation());
        publicBoardRecycler.addItemDecoration(dividerItemDecoration);

        boardAdapter = new BoardAdapter(true);
        publicBoardRecycler.setAdapter(boardAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), publicBoardRecycler);

        return fragmentView;
    }

}
