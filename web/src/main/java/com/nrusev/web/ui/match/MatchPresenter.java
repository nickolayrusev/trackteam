package com.nrusev.web.ui.match;

import com.google.common.eventbus.EventBus;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@SpringView(name="match")
public class MatchPresenter extends MvpPresenter<MatchView>{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private VerticalLayout layout;


    @PostConstruct
    public void postContruct() {
        getView().initLayout();
        getView().loadInitialData();

    }

    public MatchPresenter(MatchView view, EventBus eventBus) {
        super(view, eventBus);
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {

    }
}
