package com.nrusev.web.ui.dashboard;

import com.google.common.eventbus.EventBus;
import com.nrusev.service.GameService;
import com.nrusev.service.TeamPoolService;
import com.nrusev.service.UserService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringView(name = "dashboard")
public class DashboardPresenter extends MvpPresenter<DashboardView> {

    private final TeamPoolService teamPoolService;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public DashboardPresenter(DashboardView view, EventBus eventBus, TeamPoolService teamPoolService, UserService userService, GameService gameService) {
        super(view, eventBus);
        this.teamPoolService = teamPoolService;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void enter(ViewChangeEvent viewChangeEvent) {
        userService.findByUserName("nrusev").ifPresent(u->{
            this.teamPoolService.findAllByUserName(u.getUserName());
            this.gameService.findTodaysGames().forEach(g->{
                
            });

        });
    }
}
