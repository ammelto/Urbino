package io.seamoss.urbino.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Melton on 3/17/2017.
 */

public class Question {
    private String question;
    private String answer;
    private List<String> choices;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
