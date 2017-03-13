package com.nrusev.web.ui.dashboard;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.nrusev.domain.Game;
import com.nrusev.domain.TeamPool;
import com.nrusev.service.GameService;
import com.nrusev.service.TeamPoolService;
import com.nrusev.service.UserService;
import com.nrusev.web.ui.components.PoolComponent;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringView(name = "dashboard")
public class DashboardPresenter extends MvpPresenter<DashboardView> {

    private final TeamPoolService teamPoolService;

    @Autowired
    public DashboardPresenter(DashboardView view, EventBus eventBus, TeamPoolService teamPoolService) {
        super(view, eventBus);
        this.teamPoolService = teamPoolService;
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {
        Map<Game, Collection<TeamPool>> userGames = teamPoolService.findUserGames();
        getView().initData(userGames);
    }

    @Subscribe
    public void handlePoolClicked(PoolComponent.EditTeamPoolEvent editTeamPoolEvent){
        UI.getCurrent().getNavigator().navigateTo("pool" + "/" + editTeamPoolEvent.getTeamPool().getId());
    }
}
