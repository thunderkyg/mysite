package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[UserController]");

		// Encoding
		request.setCharacterEncoding("UTF-8");

		// Action
		String action = request.getParameter("action");

		if ("joinForm".equals(action)) {
			System.out.println("[UserController.joinForm]");

			// Forward
			WebUtil.forward(request, response, "/WEB-INF/view/user/joinForm.jsp");
		} else if ("join".equals(action)) {
			System.out.println("[UserController.join]");

			// ====================회원가입====================

			// 파라미터 꺼내기
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

			// 로그인 폼 포워드
			WebUtil.forward(request, response, "/WEB-INF/view/user/loginForm.jsp");

		} else if ("login".equals(action)) {
			
			//Parameter
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			//Dao
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(id, pw);
			
			if(userVo != null) {
				System.out.println("로그인 성공");
				//성공일때 (아이디, 비번 일치했을때) 세션에 저장 --> 성공시
				HttpSession session = request.getSession();
				session.setAttribute("authUser", userVo); //jsp에 데이터전달할대 비교 request.setAttribute();
				
				//Redirect
				WebUtil.redirect(request, response, "./main");
			} else { 
				//실패시
				System.out.println("로그인 실패");
				
				//Redirect
				WebUtil.redirect(request, response, "./user?action=loginForm&result=fail");

			}
			
			
		} else if ("logout".equals(action)) {
			
			//'authUser' Session Remove
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			//Redirect
			WebUtil.redirect(request, response, "./main");
		} else if ("modifyForm".equals(action)) {
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if(authUser != null) {
				UserDao userDao = new UserDao();
				UserVo userVo = userDao.getUserInfo(authUser.getNo());
				request.setAttribute("userVo", userVo);
				
				//Forward
				WebUtil.forward(request, response, "/WEB-INF/view/user/modifyForm.jsp");
			} else {
				//Redirect
				WebUtil.redirect(request, response, "./user?action=loginForm");
			}
		} else if ("update".equals(action)) {
			
			//Parameter 불러오기
			UserDao userDao = new UserDao();
			int no = Integer.parseInt(request.getParameter("no")) ;
			String id = request.getParameter("id");
			String password = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender= request.getParameter("gender");
			
			//Update하기
			UserVo userVo = new UserVo(no, password, name, gender);
			userDao.userUpdate(userVo);
			
			//Session 덮어쓰기
			UserVo userVoSession = userDao.getUser(id, password);
			HttpSession session = request.getSession();
			session.setAttribute("authUser", userVoSession);
			
			//Redirect
			WebUtil.redirect(request, response, "./main");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
