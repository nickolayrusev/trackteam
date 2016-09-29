package com.nrusev.web;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

/**
 * Created by nikolayrusev on 3/1/16.
 */
@SpringView
public class MyPoolsView extends VerticalLayout implements View {

    public MyPoolsView(){

    }

    @PostConstruct
    public void setUp(){
        GridLayout gridLayout = new GridLayout(3,3);
        IntStream.range(0,9).forEach(i->{
            Panel panel = new Panel();
            panel.setSizeFull();
            Label label = new Label("this is my label # "+i);
            panel.setContent(label);
            gridLayout.addComponent(panel);
        });
        addComponent(gridLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
