package com.nrusev.web.ui.home;

import com.nrusev.domain.Game;
import com.nrusev.domain.Team;
import com.nrusev.web.ui.mvp.MvpView;

import java.util.List;


public interface HomeView extends MvpView {
	void initLayout();

	void displayTodaysGames(List<Game> todaysGames);

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
