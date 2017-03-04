package io.seamoss.urbino.views.home.boards;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseRecyclerAdapter;
import io.seamoss.urbino.data.models.Board;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class BoardAdapter extends BaseRecyclerAdapter<Board, BoardsViewHolder> {

    private final Boolean isPublic;

    public BoardAdapter(Boolean isPublic){
        this.isPublic = isPublic;
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
    }
}
