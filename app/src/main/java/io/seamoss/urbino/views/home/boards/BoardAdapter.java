package io.seamoss.urbino.views.home.boards;

import android.view.View;

import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseRecyclerAdapter;
import io.seamoss.urbino.data.models.Board;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class BoardAdapter extends BaseRecyclerAdapter<Board, BoardsViewHolder> {

    private final Boolean isPublic;

    private final PublishSubject<Board> onImageClickSubject;
    private final PublishSubject<Board> onBoardSelectSubject;

    public BoardAdapter(Boolean isPublic){
        this.isPublic = isPublic;
        onImageClickSubject = PublishSubject.create();
        onBoardSelectSubject = PublishSubject.create();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_board;
    }

    @Override
    protected BoardsViewHolder inflateViewHolder(View v) {
        return new BoardsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BoardsViewHolder holder, int position) {
        holder.bind(get(position), isPublic);
        holder.getInfoIcon().setOnClickListener((view) -> onImageClickSubject.onNext(get(position)));
        holder.getRelativeLayout().setOnClickListener((view) -> onBoardSelectSubject.onNext(get(position)));
    }

    public boolean isPublic(){
        return isPublic;
    }

    public Observable<Board> getBoardSelected(){
        return onBoardSelectSubject.asObservable();
    }

    public Observable<Board> getInfoSelected(){
        return onImageClickSubject.asObservable();
    }
}
