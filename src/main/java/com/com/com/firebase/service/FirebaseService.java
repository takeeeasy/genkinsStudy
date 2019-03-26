package com.com.com.firebase.service;

import java.util.Map;

public interface FirebaseService {

	void createUser(Map<String, Object> map);
	 
	Map<String, Object> getUser(Map<String, Object> map);

	boolean idCheck(Map<String, Object> map);

}
