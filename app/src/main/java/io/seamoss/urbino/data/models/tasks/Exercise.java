package io.seamoss.urbino.data.models.tasks;

import java.util.List;

import io.seamoss.urbino.data.models.Question;
import io.seamoss.urbino.data.models.Task;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class Exercise extends BaseTask implements Task {
    private List<Question> questions;
    private int timeLimit;
    private String name;
    private String description;

    public Exercise(List<Question> questions, int timeLimit, String name, String description) {
        super(Types.EXERCISES);
        this.questions = questions;
        this.timeLimit = timeLimit;
        this.name = name;
        this.description = description;
    }

    @Override
    public int computeGoldValue() {
        return questions.size() * 10 * getMultiplier();
    }

    @Override
    public int computeExperienceValue() {
        return questions.size() * 3 * getMultiplier();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
