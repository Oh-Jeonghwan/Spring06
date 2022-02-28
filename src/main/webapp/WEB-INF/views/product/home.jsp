<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
    h3{
    	display:inline-block;
    }
</style>
</head>
<body style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>상품관리 메인 페이지</h1>
	</div>
	<br>
	<div class="outer" align="center">
		<h3><a href="add.do" class="btn btn-primary stn-sm">상품등록</a></h3>&nbsp;&nbsp;
		<h3><a href="list.do" class="btn btn-primary stn-sm">상품목록</a></h3>&nbsp;&nbsp;
		<h3><a href="../" class="btn btn-default stn-sm">메인으로</a></h3>
	</div>
	
</body>
</html>