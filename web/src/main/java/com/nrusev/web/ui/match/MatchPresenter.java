package com.nrusev.web.ui.match;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Game;
import com.nrusev.domain.Log;
import com.nrusev.domain.Team;
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

    private Game game;

    @PostConstruct
    public void postContruct() {
        getView().initLayout();
    }

    @Autowired
    public MatchPresenter(MatchView view, EventBus eventBus, GameService gameService) {
        super(view, eventBus);
        this.gameService = gameService;
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {
        LOG.debug("params are ", viewChangeEvent.getParameters());
        game = gameService.findById(Long.valueOf(viewChangeEvent.getParameters()));
        getView().loadInitialData(game);
        getView().displayPreviousMeetings(gameService.findAllHeadToHead(getHomeTeam().getTitle(),getVisitorTeam().getTitle()));
    }

    private Team getHomeTeam(){
        return game.getHomeTeam();
    }

    private Team getVisitorTeam(){
        return game.getVisitorTeam();
    }
}
