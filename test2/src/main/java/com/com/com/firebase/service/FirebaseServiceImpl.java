package com.com.com.firebase.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.com.com.firebase.FirebaseDao;

@Service("fservice")
public class FirebaseServiceImpl implements FirebaseService {
	@Resource(name = "fdao")
	FirebaseDao firebaseDao;
	
	@Override
	public void createUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		firebaseDao.createUser(map);
	}
	
	@Override
	public Map<String, Object> getUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return firebaseDao.selectUser(map);
	}
	
	@Override
	public boolean idCheck(Map<String, Object> map) {
		boolean idcheck = true;
		Map <String, Object> dbValue = firebaseDao.selectUser(map);
		String paramId = map.get("userid").toString();
		String dbId = dbValue == null ? "@OK" : dbValue.get("userid").toString();
		if(paramId.equals(dbId)) {
			idcheck = false;
		}
		return idcheck;
	}
}
