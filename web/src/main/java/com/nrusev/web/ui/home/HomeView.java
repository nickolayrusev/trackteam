package com.nrusev.web.ui.home;

import com.nrusev.domain.Country;
import com.nrusev.web.ui.mvp.MvpView;

import java.io.Serializable;
import java.util.List;


public interface HomeView extends MvpView {
	
	
	void initLayout();

	void displayFriends(List<String> readData);

	void displayCountries(List<Country> countries);
	
	
	
	public interface EditFriendListener extends Serializable {
		public void editFriend(String friend);
	}

	void addEditFriendListener(EditFriendListener editFriendListener);

	void removeEditFriendListener(EditFriendListener editFriendListener);

}
