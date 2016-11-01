package com.nrusev.web.ui.match;

import com.nrusev.domain.Game;
import com.nrusev.web.ui.mvp.MvpView;

import java.util.List;

/**
 * Created by Nikolay Rusev on 27.10.2016 г..
 */
public interface MatchView extends MvpView {
    void initLayout();
    void loadInitialData(Game game);
    void displayPreviousMeetings(List<Game> previousGames);


}
