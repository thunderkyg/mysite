package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Encoding
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 action 값 받아오기
		String action = request.getParameter("action");
		System.out.println(action);
		
		//Action If-문
		if ("addlist".equals(action)) {
			//리스트 불러오기
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getList();
			
			//Attribute로 데이터 넣기
			request.setAttribute("guestList", guestList);
			
			//Request Dispatcher --> forward할 주소 입력하기
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addlist.jsp");
//			rd.forward(request, response);
			
			WebUtil.forward(request, response, "/WEB-INF/view/guestbook/addlist.jsp");
			
			// Forward 할때 attribute(guestList[List])가 있음. 
		} else if ("add".equals(action)) {
			
			//Encoding
			request.setCharacterEncoding("UTF-8");
			
			//Parameter 가져오기
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			
			//DB에 저장하기
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookInsert(guestbookVo);
			
			//Redirect
//			response.sendRedirect("./gbc?action=addlist");
			WebUtil.redirect(request, response, "./gbc?action=addlist");
		} else if ("dform".equals(action)) {
			
			//Guestbook_No 받아오기
			int no = Integer.parseInt(request.getParameter("no"));
			
			//Set Attribute
			request.setAttribute("no", no);
			
			//Request Dispatcher
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
//			rd.forward(request, response);
			WebUtil.forward(request, response, "/WEB-INF/view/guestbook/deleteForm.jsp");
			
		} else if ("delete".equals(action)) {
			
			//Parameter 가져오기
			String password = request.getParameter("pass");
			int guestbookNo = Integer.parseInt(request.getParameter("no"))  ;
			
			GuestbookDao guestbookDao = new GuestbookDao();
			GuestbookVo deletepw = new GuestbookVo (guestbookNo, password);
			//Delete
			guestbookDao.guestbookDelete(deletepw);

			//Redirect
//			response.sendRedirect("./gbc?action=addlist");
			WebUtil.redirect(request, response, "./gbc?action=addlist");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
