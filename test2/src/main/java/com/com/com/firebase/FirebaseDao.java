package com.com.com.firebase;

import java.util.Map;

public interface FirebaseDao {

	void createUser(Map<String, Object> map);

	Map<String, Object> selectUser(Map<String, Object> map);

}
