package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	//Field
	@Autowired
	private SqlSession sqlSession;
	
	
	//Field
	//SELECT USER
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser]");
		System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
		
	}
	
	//INSERT USER (JOIN)
	public int userInsert(UserVo userVo) {
		System.out.println("[UserDao.userInsert]");
		
		int count = sqlSession.insert("user.insertUser", userVo);
		
		return count;
	}
	
	//ModifyForm User Info (Get Info)
	public UserVo userInfo(int userNo) {
		System.out.println("[UserDao.userInfo]");
		
		UserVo userVo = sqlSession.selectOne("user.userInfo", userNo);
		
		return userVo;
	}
	
	//Modify User
	public int userModify(UserVo userVo) {
		System.out.println("[UserDao.userModify]");
		
		return sqlSession.update("user.userModify", userVo);
		
		
	}
	
	

}
