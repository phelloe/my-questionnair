package com.example.application.views.main.model;

import java.util.*;

public class Question {
    public String title;
    public String text;
    public String type;
    public List<String> options = new ArrayList<>();
    private Set<String> answer = new HashSet<>(List.of(""));

    public String getAnswer() {
        return answer.toArray(new String[0])[0];
    }

    public Set<String> getAnswers() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.clear();
        this.answer.add(answer);
    }

    public void setAnswers(Set<String> answer) {
        this.answer = answer;
    }
}
