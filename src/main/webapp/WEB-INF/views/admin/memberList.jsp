<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.top-title{
			margin: auto;
			border-bottom: #2BA542 solid 4px;
	}
	
	.outer{
		width:950px;
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
		<h1>관리자 페이지</h1>
	</div>
	<br>
	<h3>회원 목록</h3>
	
	<br>
	
	<table border="1" class="table">
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>회원등급</th>
			<th>회원 가입일</th>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="4">조회된 회원이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="m" items="${list}">
					<tr>
						<td>${m.memberId }</td>
						<td>${m.memberNick }</td>
						<td>${m.memberAuth }</td>
						<td>
							<fmt:parseDate value="${m.enrollDate}" var="eDate" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${eDate}" type="both"/>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<br><br><br><br><br>
</body>
</html>