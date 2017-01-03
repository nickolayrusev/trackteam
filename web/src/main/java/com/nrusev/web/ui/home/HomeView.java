package com.nrusev.web.ui.home;

import com.nrusev.domain.Game;
import com.nrusev.web.ui.mvp.MvpView;

import java.util.List;


public interface HomeView extends MvpView {
	void initLayout();

	void displayTodaysGames(List<Game> todaysGames);
	/**
	 * get buttons to register click handlers
	 * @return
	 */
//	List<Button> getMatchButtons();
	class GameClickedEvent {

		private Game game;

		public GameClickedEvent(Game data) {
            this.game = data;
		}

		public Game getGame() {
			return game;
		}
	}

}
