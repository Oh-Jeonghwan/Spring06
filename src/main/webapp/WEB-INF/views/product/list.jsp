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
</style>
</head>
<body style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>상품목록</h1>
	</div>
	<br>
	<!-- 필터바 --><!-- ${pageContext.request.contextPath} -->
	<a href="list.do?col=PRICE&order=DESC">높은가격순</a> | 
	<a href="list.do?col=PRICE&order=ASC">낮은가격순</a> |
	<a href="list.do?col=PRODUCT_NAME&order=ASC">상품명순</a> |
	<a href="list.do?col=ENROLL_DATE&order=DESC">최신등록순</a> 
	<br><br>
	<table border=1 class="table">
		<thead>
			<tr>
				<th>품번</th>
				<th>품명</th>
				<th>가격</th>
				<th>분류</th>
				<th>등록일</th>
				<th>관리메뉴</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="6">등록된 상품이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="p" items="${list }">
						<tr>
							<td>${p.productNo }</td>
							<td>${p.productName }</td>	
							<td>${p.price }</td>
							<td>${p.type }</td>
							<td>
								<fmt:parseDate value="${p.enrollDate }" var="eDate" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${ eDate}" type="both"/>	
							</td>
							<td>
						
								<!-- 기존방식: Request parameter 방식(요청 파리미터 방식)
								=> 쿼리스트링을 이용해서 키-밸류 세트로 넘기는 기존 방식(정석)
								 주소창:/product/detail.do?pno=x-->
								
							<%--
								*path variable 방식(주소 변수 방식)
								=>파라미터(밸류)를 URL 경로에 포함시키는 방식(예. 티스토리 블로그 주소체계)
								주소창: /product/detail/x
							 --%>	
								<a href="detail/${p.productNo }">상세</a> | 
								<a href="">수정</a> | 
								<a href="delete/${p.productNo}">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<br>
	<button type="button" onclick="location.href='../product/'" class="btn btn-primary">PRODUCT HOME</button>
	<br><br><br><br><br>
</body>
</html>