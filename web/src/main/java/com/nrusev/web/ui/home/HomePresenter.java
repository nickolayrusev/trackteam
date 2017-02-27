package com.nrusev.web.ui.home;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.nrusev.domain.Game;
import com.nrusev.service.CountryService;
import com.nrusev.service.GameService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

import java.time.LocalDate;


@SuppressWarnings("serial")
@SpringView(name = "home")
public class HomePresenter extends MvpPresenter<HomeView> {

    private final GameService gameService;
    private Navigator navigator;

    @Autowired
    public HomePresenter(HomeView view, EventBus eventBus, GameService gameService) {
        super(view, eventBus);
        this.gameService = gameService;
    }

    @PostConstruct
    public void postConstruct() {
        initLayout();
        loadTodaysGames();
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("destroying ..." + this.getClass().getSimpleName());
    }

    @Override
    public void enter(ViewChangeEvent event) {
        super.enter(event);
        navigator = UI.getCurrent().getNavigator();
    }

    private void initLayout() {
        getView().initLayout();
    }

    private void loadTodaysGames() {
//        getView().displayTodaysGames(gameService.findGamesByDate(LocalDate.of(2011,11,11)));
        getView().displayTodaysGames(gameService.findTodaysGames());
    }


    @Subscribe
    public void handleTeamClicked(HomeView.TeamClickedEvent event){
        navigator.navigateTo("team" + "/" + event.getTeam().getId());
    }

    @Subscribe
    public void handleGameChange(HomeView.GameClickedEvent event) {
        System.out.println("navigating...." + event.getGame().getId());
        navigator.navigateTo("match" + "/" + event.getGame().getId());
    }

//    @Subscribe
//    public void handleOkClick(String ok) {
//        System.out.println("from parent component ..." + ok);
//    }
}
