package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
//		UserVo userVo = new UserVo("shujiga", "123", "rladudrl", "male");
//
//		userDao.userInsert(userVo);
//		System.out.println("건등록");
		
//		UserVo userVo = userDao.getUser("thunderkyg", "1234");
//		System.out.println(userVo);
		UserVo userVo = userDao.getUserInfo(1);
		System.out.println(userVo);
		
				
	}

}