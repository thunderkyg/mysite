package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("[UserController]");
		
		//Encoding
		request.setCharacterEncoding("UTF-8");
		
		//Action
		String action = request.getParameter("action");
		
		if("joinForm".equals(action)) {
			System.out.println("[UserController.joinForm]");

			//Forward
			WebUtil.forward(request, response, "/WEB-INF/view/user/joinForm.jsp");
		} else if("join".equals(action)) {
			System.out.println("[UserController.join]");
			
			//====================회원가입====================
			
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(id, pw, name, gender);
			System.out.println(userVo);
			
			//
			UserDao userDao = new UserDao();
			int count = userDao.userInsert(userVo);
			
			WebUtil.forward(request, response, "/WEB-INF/view/user/joinOk.jsp");
			
		} else if ("loginForm".equals(action)) {
			System.out.println("[UserController.loginForm]");
			
			//로그인 폼 포워드
			WebUtil.forward(request, response, "/WEB-INF/view/user/loginForm.jsp");
			
			
			
		} else if ("login".equals(action)) {
			
		}
		
		
	}

 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
