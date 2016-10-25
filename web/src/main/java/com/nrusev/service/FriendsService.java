package com.nrusev.service;

import java.util.List;

public interface FriendsService {
	
	List<String> readFriends();
	
	void saveFriend(String friend);
}
