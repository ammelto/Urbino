package io.seamoss.urbino.views.node.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.seamoss.urbino.data.models.Task;
import io.seamoss.urbino.data.models.tasks.BaseTask;
import io.seamoss.urbino.data.models.tasks.Exercise;
import io.seamoss.urbino.data.models.tasks.Game;
import io.seamoss.urbino.data.models.tasks.Video;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public abstract class TaskViewholder extends RecyclerView.ViewHolder {
    public TaskViewholder(View itemView) {
        super(itemView);
    }

    public void bind(Video video){

    }

    public void bind(Exercise exercise){

    }
    public void bind(Game game){

    }

}
