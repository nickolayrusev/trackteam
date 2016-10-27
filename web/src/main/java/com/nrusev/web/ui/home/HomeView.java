package com.nrusev.web.ui.home;

import com.nrusev.domain.Country;
import com.nrusev.web.ui.mvp.MvpView;
import com.vaadin.ui.Button;

import java.io.Serializable;
import java.util.List;


public interface HomeView extends MvpView {
	void initLayout();

	void displayCountries(List<Country> countries);

	void displayTodaysGames();
	/**
	 * get buttons to register click handlers
	 * @return
	 */
	List<Button> getCountriesButtons();
}
