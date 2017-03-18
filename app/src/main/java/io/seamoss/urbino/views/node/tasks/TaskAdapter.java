package io.seamoss.urbino.views.node.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseRecyclerAdapter;
import io.seamoss.urbino.data.models.Task;
import io.seamoss.urbino.data.models.tasks.Exercise;
import io.seamoss.urbino.data.models.tasks.Game;
import io.seamoss.urbino.data.models.tasks.Video;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class TaskAdapter extends BaseRecyclerAdapter<Task, TaskViewholder> {

    @Override
    protected int getLayoutRes() {
        switch (Task.Types.values()[getViewtype()]){
            case EXERCISES:
                return R.layout.card_exercise;
            case HTML5_GAME:
                return R.layout.card_game;
            case VIDEO:
                return R.layout.card_video;
            default:
                return R.layout.card_exercise;
        }
    }

    @Override
    protected TaskViewholder inflateViewHolder(View v) {
        switch (Task.Types.values()[getViewtype()]){
            case EXERCISES:
                return new ExerciseViewHolder(v);
            case HTML5_GAME:
                return new GameViewHolder(v);
            case VIDEO:
                return new VideoViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Timber.d("Ordinal " + get(position).getType().ordinal());
        return get(position).getType().ordinal();
    }

    @Override
    public void onBindViewHolder(TaskViewholder holder, int position) {
        switch (Task.Types.values()[getViewtype()]){
            case EXERCISES:
                holder.bind((Exercise) get(position));
                break;
            case HTML5_GAME:
                holder.bind((Game) get(position));
                break;
            case VIDEO:
                holder.bind((Video) get(position));
                break;
        }
    }
}
