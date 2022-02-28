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
		border-bottom: solid black 1px;
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
		<h1>상품추가</h1>
	</div>
	<br>
	<div class="outer">
		<form action="add.do" method="post">
			상품명 : <input type="text" name="productName"><br><br>
			가격 : <input type="text" name="price"><br><br>
			상품유형: <input type="text" name="type"><br><br>
			<button type="submit">등록</button>
		</form>
		<br>
	</div>
</body>
</html>