package com.com.com.firebase;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("fdao")
public class FirebaseDaoImpl implements FirebaseDao{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void createUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlSession.insert("fmapper.insert", map);
	}

	@Override
	public Map<String, Object> selectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("fmapper.selectUser", map);
	}
	
}
