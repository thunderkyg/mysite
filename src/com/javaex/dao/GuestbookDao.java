package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {

	//Field
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "webdb";
		String pw = "webdb";
		
		private void getConnection() {
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		private void close() {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		
		//SelectAll
		public List<GuestbookVo> getList() {

			// DB에서 리스트 가져옴
			List<GuestbookVo> arrayList = new ArrayList<GuestbookVo>();

			getConnection();

			try {

				String query = "";
				query += " select guestbook_no, ";
				query += "		  name, ";
				query += "        password, ";
				query += "        content, ";
				query += "        reg_date ";
				query += " from guestbook ";
				query += " order by guestbook_no asc ";
				
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int guestbookNo = rs.getInt("guestbook_no");
					String Name = rs.getString("name");
					String Pw = rs.getString("password");
					String Content = rs.getString("content");
					String Date = rs.getString("reg_date");

					GuestbookVo guestbookVo = new GuestbookVo(guestbookNo, Name, Pw, Content, Date);

					arrayList.add(guestbookVo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
			return arrayList;
		}
		
		//Insert
		
		public int guestbookInsert(GuestbookVo guestbookVo) {
			int count = -1;

			getConnection();

			try {
				String query = "";
				query += " insert into guestbook ";
				query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, guestbookVo.getName());
				pstmt.setString(2, guestbookVo.getPassword());
				pstmt.setString(3, guestbookVo.getContent());

				count = pstmt.executeUpdate();

				// 결과처리
				System.out.println(count + "건이 등록되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
			return count;
		}
		
		//Delete
		
		public int guestbookDelete(GuestbookVo g) {
			int count = -1;

			getConnection();

			try {
				String query = "";
				query += " delete from guestbook ";
				query += " where guestbook_no = ? ";
				query += " and password =  ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, g.getGuestbook_no());
				pstmt.setString(2, g.getPassword());

				count = pstmt.executeUpdate();
				System.out.println(count + "건이 삭제되었습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
			return count;
		}
		
		//Get Password
//		public String getGuestbook (int guestbookNo) {
//			
//			String password = null;
//			getConnection();
//			
//			try {
//				//Query
//				String query = "";
//				query += " select password ";
//				query += " from guestbook ";
//				query += " where guestbook_no = ? ";
//
//				pstmt = conn.prepareStatement(query);
//				pstmt.setInt(1, guestbookNo);
//				rs = pstmt.executeQuery();
//				
//
//				while(rs.next()) {
//					password = rs.getString("password");
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			close();
//			return password;
//		}
	
}
