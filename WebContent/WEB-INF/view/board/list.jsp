<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="./assets/css/board.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/view/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">

			<c:import url="/WEB-INF/view/includes/aside.jsp"></c:import>

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="./bc" method="post">
							<div class="form-group text-right">
								<input type="hidden" name="action" value="search"> 
								<input type="text" name="search" value="">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<!-- 반복문 -->
								<c:forEach items="${boardList}" var="boardVo" varStatus="status">
									<tr>
										<td>${boardVo.board_no}</td>
										<td class="text-left"><a href="./bc?action=read&no=${boardVo.board_no}">${boardVo.title}</a></td>
										<td>${boardVo.name}</td>
										<td>${boardVo.hit}</td>
										<td>${boardVo.reg_date }</td>
										<td><c:if test="${authUser.no == boardVo.no}">
												<a href="./bc?action=delete&no=${boardVo.board_no}">[삭제]</a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>


							<div class="clear"></div>
						</div>
						<c:if test="${authUser != null }">
							<a id="btn_write" href="./bc?action=writeform">글쓰기</a>
						</c:if>

					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->


		<c:import url="/WEB-INF/view/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
