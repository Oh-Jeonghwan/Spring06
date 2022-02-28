<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		width:950px;
		margin:auto;
	}
	.gAdd{
		padding: 50px;
		margin:auto;
	}
	
	.top-title{
		margin: auto;
		border-bottom: #2BA542 solid 4px;
	}
	
	.button{
		width:650px;
		margin:auto;
	}
	
</style>
</head>
<body style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>갤러리 이미지 등록</h1>
	</div>
	<br>
	<div class="outer" align="center">
		<form action="${pageContext.request.contextPath}/board/boardUpdate.do" method="post">
			<input type="hidden" name="boardNo" value="${board.boardNo }">
			카테고리:
			<select name="boardHead">
				<option>정보</option>
				<option>공지</option>
				<option>유머</option>
			</select> &nbsp;&nbsp;
			제목: <input type="text" name="boardTitle" value="${board.boardTitle}"> <br><br>
			<textarea name="boardContent" rows="10" cols="70" style="resize:none;">${board.boardContent}</textarea>
			<br><br>
			<div class="button" align="right">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/board/union.do?page=1'">취소</button>
				<button type="submit">작성</button>
			</div>
		</form>
	</div>
	<br><br><br><br><br>
</body>
</html>