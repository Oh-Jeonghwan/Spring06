<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
		<h1>상품 상세조회</h1>
	</div>
	<br>
	<div class="outer">
		<h5>${product.productNo}</h5>
		<h3>${product.productName}</h3>
		<h5>${product.price}</h5>
		<h5>${product.type}</h5>
		<h5>${product.enrollDate}</h5>
	</div>	
	<button onclick="location.href='${pageContext.request.contextPath}/product/list.do'">목록</button>
</body>
</html>