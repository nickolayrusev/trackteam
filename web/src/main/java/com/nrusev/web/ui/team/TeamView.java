package com.nrusev.web.ui.team;

import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.web.ui.mvp.MvpView;
import com.vaadin.ui.Button;

import java.util.List;

/**
 * Created by Nikolay Rusev on 1.11.2016 г..
 */
public interface TeamView extends MvpView {
    void initLayout();
    void loadData(Team team);
    void loadPreviousGames(List<Game> games);
    void loadFormOfLastGames(List<String> formOfLastGames);

    class GameClickedEvent {
        private Game game;

        public GameClickedEvent(Game data) {
            this.game = data;
        }

        public Game getGame() {
            return game;
        }
    }

    class TeamClickedEvent{
        private Team team;

        public TeamClickedEvent(Team team) {
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }
    }
}
