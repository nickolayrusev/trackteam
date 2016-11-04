package com.nrusev.web.ui.team;

import com.google.common.eventbus.EventBus;
import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.service.GameService;
import com.nrusev.service.TeamService;
import com.nrusev.web.ui.mvp.MvpPresenter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 1.11.2016 г..
 */
@SpringView(name = "team")
public class TeamPresenter extends MvpPresenter<TeamView> {

    private Team team;

    private List<Game> previousGames;

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
        previousGames = gameService.findGamesByTeam(team.getId());

        getView().loadData(team);
        getView().loadFormOfLastGames(getFormOfLastGames(previousGames,team));
        getView().loadPreviousGames(previousGames);
        getView().getPreviosGamesButtons().forEach(button->{
            button.addClickListener(l->{
                Game game =(Game)button.getData();
                System.out.println(" Game is clicked ..." + game);
                UI.getCurrent().getNavigator().navigateTo("match" + "/" + game.getId());
            });
        });
    }

    public Team getTeam() {
        return team;
    }

    private List<String> getFormOfLastGames(List<Game> games, Team team){
        return games.stream().skip(0).limit(5).map(g -> {
            Long winner = g.getWinner();
            if(winner==null)
                return "n/a";
            if(new Long(0L).equals(winner))
                return "D";
            if (team.getId().equals(g.getVisitorTeam().getId()) && new Long(2L).equals(winner))
                return "W";
            else
                return "L";
        }).collect(toList());
    }

}
