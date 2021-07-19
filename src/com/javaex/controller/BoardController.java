package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/bc")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Board Controller 확인
		System.out.println("[BoardController]");
		
		//Encoding
		request.setCharacterEncoding("UTF-8");
		
		//BoardDao
		BoardDao boardDao = new BoardDao();
		
		//Action
		String action = request.getParameter("action");
		
		//if문 시작
		if("list".equals(action)) {
			//리스트 불러오기
			List<BoardVo> boardList  = boardDao.getList();
			
			//Attribute로 데이터 넣기
			request.setAttribute("boardList", boardList);
			
			//Forward
			WebUtil.forward(request, response, "/WEB-INF/view/board/list.jsp");
			
		} else if("delete".equals(action)) {
			//Parameter
			int no = Integer.parseInt(request.getParameter("no"));
			
			//Delete
			boardDao.boardDelete(no);
			
			//Redirect
			WebUtil.redirect(request, response, "./bc?action=list");
			
		} else if("writeform".equals(action)) {
			//authUser Attribute 가져오기
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if(authUser != null) {
				//글쓰기 폼으로 포와딩
				WebUtil.forward(request, response, "/WEB-INF/view/board/writeForm.jsp");
			} else {
				WebUtil.forward(request, response, "/WEB-INF/view/user/loginForm.jsp");
			}
			
		} else if("write".equals(action)) {
			
			//authUser Attribute 가져오기
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");

			String title = request.getParameter("title");
			String content = request.getParameter("content"); 
			int user_no = authUser.getNo();
			
			BoardVo boardVo = new BoardVo(title, content, user_no);
			
			boardDao.boardInsert(boardVo);
			
			//Redirect
			WebUtil.redirect(request, response, "./bc?action=list");			
		} else if("read".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			//조회수 +1
			boardDao.addHit(no);
			
			BoardVo boardVo = boardDao.getBoardInfo(no);
			
			request.setAttribute("readBoardVo", boardVo);
			
			WebUtil.forward(request, response, "/WEB-INF/view/board/read.jsp");
			
		} else if("modifyform".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardVo boardVo = boardDao.getBoardInfo(no);
			
			request.setAttribute("modifyBoardVo", boardVo);
			
			WebUtil.forward(request, response, "/WEB-INF/view/board/modifyForm.jsp");
		} else if("modify".equals(action)) {
		
			//authUser Attribute 가져오기
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			//Param
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int board_no = Integer.parseInt(request.getParameter("no"));
			int userno = Integer.parseInt(request.getParameter("userno"));
			
			//보안
			if(authUser.getNo() == userno) {
				BoardVo boardVo = new BoardVo(board_no, title, content);
				
				boardDao.boardUpdate(boardVo);
				
				WebUtil.redirect(request, response, "./bc?action=list");	
			} else {
				WebUtil.redirect(request, response, "./bc?action=list");
			}
		} else if("search".equals(action)) {
			
			//Parameter
			String search = request.getParameter("search");
			
			//Search List 불러오기
			List<BoardVo> boardSearch  = boardDao.boardSearch(search);
			
			//Attribute로 데이터 넣기
			request.setAttribute("boardList", boardSearch);
			//Forward
			WebUtil.forward(request, response, "/WEB-INF/view/board/list.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
