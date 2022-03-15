package com.example.application.views.main;

import com.example.application.views.main.model.Question;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;

public abstract class QuestionPage extends VerticalLayout {
    protected Binder<Question> binder;
    protected Question question;

    public QuestionPage(Question question) {
        this.setPadding(false);
        this.question = question;
        add(new H2(question.title), new Span(question.text));
    }

    public Binder<Question> getBinder() {
        return binder;
    }
    public Question getQuestion() {
        return this.question;
    }
}
