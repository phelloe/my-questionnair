package com.example.application.views.main;

import com.example.application.views.main.model.Question;
import com.example.application.views.main.model.Questionnaire;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {
    static int index = 0;
    static QuestionPage currentPage;

    public MainView() {
        setMargin(true);
        try (FileReader fr = new FileReader("test.json")) {
            Gson gson = new GsonBuilder().create();
            Questionnaire questionnaire = gson.fromJson(fr, Questionnaire.class);
            List<QuestionPage> pages = new ArrayList<>();
            Div progressBarLabel = new Div();
            ProgressBar progressBar = new ProgressBar();
            progressBar.setMin(0);
            progressBar.setMax(questionnaire.questions.size() - 1);
            progressBar.setValue(index);
            progressBarLabel.setText("Question (" + (index + 1) + "/" + questionnaire.questions.size() + ")");
            for (Question question : questionnaire.questions) {
                QuestionPage questionPage = switch (question.type) {
                    case "radio" -> new RadioQuestionPage(question);
                    case "checkbox" -> new CheckboxQuestionPage(question);
                    default -> new TextAreaQuestionPage(question);
                };
                pages.add(questionPage);
            }
            Button next = new Button("Next");
            Button prev = new Button("Previous");
            Button submit = new Button("Submit");
            submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            next.setVisible(index < pages.size() - 1);
            submit.setVisible(index == pages.size() - 1);
            prev.setVisible(index > 0);
            HorizontalLayout navButtons = new HorizontalLayout();
            navButtons.add(prev, next, submit);
            ComponentEventListener<ClickEvent<Button>> listener = componentEvent -> {
                this.replace(currentPage, pages.get(index));
                currentPage = pages.get(index);
                next.setVisible(index < pages.size() - 1);
                submit.setVisible(index == pages.size() - 1);
                prev.setVisible(index > 0);
                progressBar.setValue(index);
                progressBarLabel.setText("Question (" + (index + 1) + "/" + questionnaire.questions.size() + ")");
            };
            next.addClickListener(buttonClickEvent -> index++);
            prev.addClickListener(buttonClickEvent -> index--);
            next.addClickListener(listener);
            prev.addClickListener(listener);
            submit.addClickListener(buttonClickEvent -> {
                pages.forEach(page -> {
                    try {
                        page.getBinder().writeBean(page.getQuestion());
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println(gson.toJson(questionnaire));
            });
            currentPage = pages.get(index);
            add(new H1(questionnaire.title), currentPage, navButtons, progressBarLabel, progressBar);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
