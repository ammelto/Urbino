package io.seamoss.urbino.views.node.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.data.models.tasks.Game;
import io.seamoss.urbino.views.node.NodeView;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class GameViewHolder extends TaskViewholder {

    @BindView(R.id.game_frame)
    FrameLayout frameLayout;

    @BindView(R.id.game_bonus)
    FrameLayout bonus;

    @BindView(R.id.game_bonus_amount)
    TextView multiplier;

    private Game game;

    public GameViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        frameLayout.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        ((NodeView) itemView.getContext()).launchGame(game);
    }

    @Override
    public void bind(Game game) {
        this.game = game;
        if(game.isHasBonus()) multiplier.setText(String.format(itemView.getResources().getString(R.string.task_bonus), game.getMultiplier()));
        else bonus.setVisibility(View.INVISIBLE);
    }
}
