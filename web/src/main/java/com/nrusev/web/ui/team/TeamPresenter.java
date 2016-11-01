package com.nrusev.web.ui.team;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Team;
import com.nrusev.service.GameService;
import com.nrusev.service.TeamService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Nikolay Rusev on 1.11.2016 г..
 */
@SpringView(name = "team")
public class TeamPresenter extends MvpPresenter<TeamView> {

    private Team team;

    private final TeamService teamService;

    private final GameService gameService;

    @Autowired
    public TeamPresenter(TeamView view, EventBus eventBus, TeamService teamService, GameService gameService) {
        super(view, eventBus);
        this.teamService = teamService;
        this.gameService = gameService;
    }

    @PostConstruct
    public void postConstruct() {
        initLayout();
        attachEventHandlers();
    }

    private void attachEventHandlers() {

    }

    private void initLayout() {
        getView().initLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        team = teamService.findById(Long.valueOf(viewChangeEvent.getParameters()));
        getView().loadData(team);
        getView().loadPreviousGames(gameService.findGamesByTeam(team.getId()));
    }

    public Team getTeam() {
        return team;
    }

}
