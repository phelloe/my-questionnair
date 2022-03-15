package com.example.application.views.main;

import com.example.application.views.main.model.Question;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.binder.Binder;

public class RadioQuestionPage extends QuestionPage {
    RadioButtonGroup<String> answer = new RadioButtonGroup<>();

    public RadioQuestionPage(Question question) {
        super(question);
        binder = new Binder<>(Question.class);
        answer.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        answer.setItems(question.options);
        binder.bind(answer, Question::getAnswer, Question::setAnswer);
        add(answer);
    }
}
