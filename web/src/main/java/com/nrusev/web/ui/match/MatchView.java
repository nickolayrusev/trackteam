package com.nrusev.web.ui.match;

import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
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
    void clear();

    class TeamClickedEvent{
        private Team team;

        public TeamClickedEvent(Team team) {
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }
    }

    class GameClickedEvent{
        private Game game;

        public GameClickedEvent(Game game) {
            this.game = game;
        }

        public Game getGame() {
            return game;
        }
    }
}
