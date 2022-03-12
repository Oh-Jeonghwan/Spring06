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
		border: solid black 1px;
		width:950px;
		margin:auto;
	}
	
	.bAdd{
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
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/nse_files/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>일반 게시판 수정</h1>
	</div>
	<br>
	<div class="outer" align="center">
		<div class="bAdd" align="center">
			<form action="${pageContext.request.contextPath}/board/boardUpdate.do" method="post">
				<div align="left">
					<input type="hidden" name="boardNo" value="${board.boardNo }">
					카테고리:
					<select name="boardHead">
						<option>정보</option>
						<option>공지</option>
						<option>유머</option>
					</select> &nbsp;&nbsp;
					제목: <input type="text" name="boardTitle" style="width:600px; height:35px" 
							value="${board.boardTitle}"> <br><br>
					<textarea name="boardContent" id="boardContent" style="resize:none; width:830px; height:500px;">${board.boardContent}</textarea>
					</div>
				<br><br>
				<div class="button" align="right">
					<button type="submit" id="submitModifyBoardBtn">수정</button>
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/board/union.do?page=1'">취소</button>
				</div>
			</form>
		</div>
	</div>
	<br><br><br><br><br>
	<script type="text/javascript">
	 	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
		$(function() { 
		
			// Editor Setting 
			nhn.husky.EZCreator.createInIFrame({ oAppRef : oEditors, // 전역변수 명과 동일해야 함. 
				elPlaceHolder : "boardContent", // 에디터가 그려질 textarea ID 값과 동일 해야 함. 
				sSkinURI : "${pageContext.request.contextPath}/resources/nse_files/SmartEditor2Skin.html", // Editor HTML 
				fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X 
				htParams : { 
					// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
					bUseToolbar : true, 
					// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
					bUseVerticalResizer : true, 
					// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
					bUseModeChanger : true, } });
		});
		
		$("#submitModifyBoardBtn").click(function() {

			oEditors.getById["boardContent"].exec("UPDATE_CONTENTS_FIELD", []);

			});

	</script>
</body>
</html>