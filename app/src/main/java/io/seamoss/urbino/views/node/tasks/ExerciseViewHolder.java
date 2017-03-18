package io.seamoss.urbino.views.node.tasks;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.data.models.tasks.Exercise;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class ExerciseViewHolder extends TaskViewholder {

    private Exercise exercise;

    @BindView(R.id.exercise_title)
    TextView title;

    @BindView(R.id.exercise_description)
    TextView description;

    @BindView(R.id.exercise_questions_text)
    TextView questions;

    @BindView(R.id.exercise_timelimit_text)
    TextView timelimit;

    @BindView(R.id.exercise_mentor)
    Button mentor;

    @BindView(R.id.exercise_start)
    Button start;

    @BindView(R.id.exercise_bonus)
    FrameLayout bonus;

    @BindView(R.id.exercise_bonus_amount)
    TextView multiplier;

    public ExerciseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Exercise exercise) {
        this.exercise = exercise;
        title.setText(exercise.getName());
        description.setText(exercise.getDescription());
        questions.setText(String.format(itemView.getResources().getString(R.string.card_exercise_questions), exercise.getQuestions().size()));
        if(exercise.getTimeLimit() == -1) timelimit.setText(itemView.getResources().getString(R.string.card_exercise_timeLimit));
        else timelimit.setText(String.format(itemView.getResources().getString(R.string.card_exercise_timeLimit_active), exercise.getTimeLimit()));
        if(exercise.isHasBonus()) multiplier.setText(String.format(itemView.getResources().getString(R.string.task_bonus), exercise.getMultiplier()));
        else bonus.setVisibility(View.INVISIBLE);
    }
}
