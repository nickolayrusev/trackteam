package com.nrusev.web.ui.user_pools;

import com.google.common.eventbus.EventBus;
import com.nrusev.service.TeamPoolService;
import com.nrusev.service.TeamService;
import com.nrusev.service.UserService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by Nikolay Rusev on 26.10.2016 г..
 */
@SpringView(name = "user-pools")
public class UserPoolsPresenter extends MvpPresenter<UserPoolsView> {

    private final UserService userService;

    private final TeamPoolService teamPoolService;
    private final TeamService teamService;

    @Autowired
    public UserPoolsPresenter(UserPoolsView view, EventBus eventBus, UserService userService, TeamPoolService teamPoolService, TeamService teamService) {
        super(view, eventBus);
        this.userService = userService;
        this.teamPoolService = teamPoolService;
        this.teamService = teamService;
    }

    @PostConstruct
    public void postConstruct() {
        initLayout();
        loadData();
    }

    private void initLayout() {
        getView().initLayout();
    }

    private void loadData(){
        //TODO: grab user from session
        this.userService.findByUserName("nrusev").ifPresent(user -> {
            getView().loadData(this.teamPoolService.findAllByUserName(user.getUserName()));
        });

        getView().loadDataForAutoComplete(this.teamService.findAllClubTeams());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
