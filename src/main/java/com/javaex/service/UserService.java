package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//LOGIN GET USER
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser]");
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}
	
	//JOIN INSERT USER
	public int userInsert(UserVo userVo) {
		System.out.println("[UserService.login]");
		
		int count = userDao.userInsert(userVo);
		
		return count;
	}
	
	//MODIFY USER INFO(GET)
	public UserVo userInfo(int userNo) {
		System.out.println("[UserService.userInfo]");
		
		return userDao.userInfo(userNo);
	}
	
	//MODIFY USER
	public UserVo userModify(UserVo userVo) {
		System.out.println("[UserService.userModify]");
		
		userDao.userModify(userVo);
		
		return userDao.selectUser(userVo);
	}
	
	//CHECK OVERLAP
	public boolean checkId(String id) {
		System.out.println("[UserService.checkId]");
		boolean check;
		
		if (userDao.getId(id) == null) {
			//ID 사용 가능할시
			check = true;
		} else {
			//ID 사용 불가능할시
			check = false;
		}
		
		return check;
	}
	
	
	

}
