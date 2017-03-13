package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.nrusev.domain.TeamPool;
import com.nrusev.service.TeamPoolService;
import com.nrusev.service.TeamService;
import com.nrusev.web.ui.components.PoolComponent;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

/**
 * Created by Nikolay Rusev on 10.3.2017 г..
 */
@SpringView(name = "pool")
public class PoolViewPresenter extends MvpPresenter<PoolViewImpl> {
    private TeamPool pool;
    private final TeamPoolService teamPoolService;
    private final TeamService teamService;

    public PoolViewPresenter(PoolViewImpl view, EventBus eventBus, TeamPoolService teamPoolService, TeamService teamService) {
        super(view, eventBus);
        this.teamPoolService = teamPoolService;
        this.teamService = teamService;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        pool = teamPoolService.findById(Long.valueOf(viewChangeEvent.getParameters()));
        System.out.println("entering pool view " + pool);
        getView().init(pool,teamService.findAllClubTeams());
    }

    @Subscribe
    public void handleTeamSelected(PoolComponent.AddTeamEvent event){
        System.out.println("team selected " + event.getTeam());
        getView().addTeam(event.getTeam());
    }
}
