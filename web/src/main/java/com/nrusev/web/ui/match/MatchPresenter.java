package com.nrusev.web.ui.match;

import com.google.common.eventbus.EventBus;
import com.nrusev.service.GameService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
@SpringView(name="match")
public class MatchPresenter extends MvpPresenter<MatchView>{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final GameService gameService;

    @PostConstruct
    public void postContruct() {
        getView().initLayout();
        getView().loadInitialData();
    }

    @Autowired
    public MatchPresenter(MatchView view, EventBus eventBus, GameService gameService) {
        super(view, eventBus);
        this.gameService = gameService;
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {
        System.out.println(viewChangeEvent.getParameters());
    }
}
