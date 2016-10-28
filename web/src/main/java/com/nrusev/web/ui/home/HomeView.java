package com.nrusev.web.ui.home;

import com.nrusev.domain.Country;
import com.nrusev.domain.Game;
import com.nrusev.web.ui.mvp.MvpView;
import com.vaadin.ui.Button;

import java.util.List;


public interface HomeView extends MvpView {
	void initLayout();

	void displayTodaysGames(List<Game> todaysGames);
	/**
	 * get buttons to register click handlers
	 * @return
	 */
	List<Button> getMatchButtons();
}
