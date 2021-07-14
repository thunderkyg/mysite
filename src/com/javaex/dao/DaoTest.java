package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {

		UserVo userVo = new UserVo("shujiga", "123", "rladudrl", "male");
		UserDao userDao = new UserDao();

		userDao.userInsert(userVo);
		System.out.println("건등록");
	}

}