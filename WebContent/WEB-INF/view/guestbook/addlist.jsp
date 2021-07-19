<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page import="com.javaex.vo.UserVo"%>
<%@ page import="com.javaex.dao.GuestbookDao"%>
<%@ page import="com.javaex.vo.GuestbookVo"%>
<%@ page import="java.util.List"%>


<%
//Authentication Login
UserVo authUser = (UserVo) session.getAttribute("authUser");

//Get Attribute = Controller에서 리스트
List<GuestbookVo> guestbookList = (List<GuestbookVo>) request.getAttribute("guestList");
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="./assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="./assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/view/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="./gbc" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="pass"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">

					</form>

<%-- 					<%
					for (int i = 0; i < guestbookList.size(); i++) {
					%> --%>
					
					<c:forEach items="${guestList}" var="guestbookList" varStatus="status">
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
							<tr>
								<td>
									<%-- <%=guestbookList.get(i).getGuestbook_no() %> --%>${guestbookList.guestbook_no}</td>
								<td>
									<%-- <%=guestbookList.get(i).getName() %> --%>${guestbookList.name}</td>
								<td>
									<%-- <%=guestbookList.get(i).getReg_date() %> --%>${guestbookList.reg_date }</td>
								<td><a href="./gbc?action=dform&no=<%-- <%=guestbookList.get(i).getGuestbook_no() %> --%>${guestbookList.guestbook_no}">[삭제]</a></td>
							</tr>
							<tr>
							<td colspan="4"><%-- <%=guestbookList.get(i).getContent()%> --%>${guestbookList.content}</td>
						</tr>
					</table>
					<br>
					</c:forEach>
<%-- 					<%
					}
					%--%>
				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/view/includes/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->

</body>

</html>