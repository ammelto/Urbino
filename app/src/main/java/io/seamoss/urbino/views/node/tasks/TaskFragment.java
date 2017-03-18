package io.seamoss.urbino.views.node.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseFragment;
import io.seamoss.urbino.data.models.Question;
import io.seamoss.urbino.data.models.Task;
import io.seamoss.urbino.data.models.tasks.Exercise;
import io.seamoss.urbino.data.models.tasks.Game;
import io.seamoss.urbino.data.models.tasks.Video;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class TaskFragment extends Fragment {

    @BindView(R.id.node_task_recycler)
    RecyclerView taskRecycler;

    private TaskAdapter taskAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_node_task, container, false);
        ButterKnife.bind(this, fragmentView);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskRecycler.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(taskRecycler.getContext(),
                linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext() ,R.drawable.card_gutter_12dp));
        taskRecycler.addItemDecoration(dividerItemDecoration);

        taskAdapter = new TaskAdapter();
        taskRecycler.setAdapter(taskAdapter);

        List<Question> questions = new LinkedList<>();

        Question question = new Question();
        question.setQuestion("What is the meaning of life?");
        question.setAnswer("42");
        question.setChoices(Arrays.asList("77","65","108","42"));

        Question question2 = new Question();
        question.setQuestion("What is your favorite color?");
        question.setAnswer("red");
        question.setChoices(Arrays.asList("red","green","blue","cyan"));

        Question question3 = new Question();
        question.setQuestion("What is 25/5? ");
        question.setAnswer("5");
        question.setChoices(Arrays.asList("1","3","7","5"));

        questions.add(question);
        questions.add(question2);
        questions.add(question3);

        Task exercise = new Exercise(questions, -1, "Whole Numbers Problems", "Practice problems covering basic whole number division");
        exercise.setHasBonus(true);
        exercise.setMultiplier(2);

        Task video = new Video("nwTPFqWfosM");

        Task game = new Game("http://www.primarygames.com/mobile/game/fractionmatcher/phone.php");

        taskAdapter.add(exercise);
        taskAdapter.add(video);
        taskAdapter.add(game);


        return fragmentView;
    }
}
