package com.example.application.views.main;

import com.example.application.views.main.model.Question;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;

public class TextAreaQuestionPage extends QuestionPage {
    TextArea answer = new TextArea();

    public TextAreaQuestionPage(Question question) {
        super(question);
        binder = new Binder<>(Question.class);
        binder.bind(answer, Question::getAnswer, Question::setAnswer);
        add(answer);
    }
}
