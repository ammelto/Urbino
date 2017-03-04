package io.seamoss.urbino.views.home.boards;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.seamoss.urbino.R;
import io.seamoss.urbino.data.models.Board;

/**
 * Created by Alexander Melton on 2/28/2017.
 */

public class BoardsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.board_icon)
    CircleImageView circleImageView;

    @BindView(R.id.board_name)
    TextView boardName;

    @BindView(R.id.board_description)
    TextView boardDescription;

    @BindView(R.id.board_info)
    ImageView infoIcon;

    public BoardsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(Board board, boolean isPublic){
        boardName.setText(board.getName());
        boardDescription.setText(board.getDescription());

        if(board.getImage() != null){
            Picasso.with(itemView.getContext())
                    .load(board.getImage())
                    .into(circleImageView);
        }

        if(isPublic){
            infoIcon.setVisibility(View.INVISIBLE);
        }
    }
}
