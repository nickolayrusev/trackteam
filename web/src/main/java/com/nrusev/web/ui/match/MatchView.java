package com.nrusev.web.ui.match;

import com.nrusev.domain.Game;
import com.nrusev.web.ui.mvp.MvpView;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
public interface MatchView extends MvpView {
    void initLayout();
    void loadInitialData(Game game);
    void displayPreviousMeetings(List<Game> previousGames);

    //Attach event handlers
    List<Button> getPreviousGamesButtons();
    Button getHomeTeamButton();
    Button getVisitorTeamButton();
}
