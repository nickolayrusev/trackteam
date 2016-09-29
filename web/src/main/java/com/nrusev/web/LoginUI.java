package com.nrusev.web;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

/**
 * Created by nikolayrusev on 3/2/16.
 */
@Theme("valo")
@SpringUI(path = "/login")
public class LoginUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        FormLayout formLayout = new FormLayout();

        formLayout.addComponent(new TextField("username"));
        formLayout.addComponent(new PasswordField("password"));

        formLayout.setMargin(true);
        formLayout.setSpacing(true);

        HorizontalLayout verticalLayout = new HorizontalLayout();
        verticalLayout.addComponent(new Button("login"));
        verticalLayout.addComponent(new Button("register"));
        verticalLayout.setSpacing(true);

        formLayout.addComponent(verticalLayout);
        setContent(formLayout);
    }
}
