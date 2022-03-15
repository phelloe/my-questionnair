package com.example.application.views.main;

import com.example.application.views.main.model.Question;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.data.binder.Binder;

public class CheckboxQuestionPage extends QuestionPage {
    CheckboxGroup<String> answer = new CheckboxGroup<>();

    public CheckboxQuestionPage(Question question) {
        super(question);
        binder = new Binder<>(Question.class);
        answer.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        answer.setItems(question.options);
        binder.bind(answer, Question::getAnswers, Question::setAnswers);
        add(answer);
    }
}
