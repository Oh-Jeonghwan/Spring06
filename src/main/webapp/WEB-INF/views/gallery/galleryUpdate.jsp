<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	.gAdd{
		padding: 50px;
		margin:auto;
	}
	
	.top-title{
		margin: auto;
		border-bottom: #2BA542 solid 4px;
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
		<h1>갤러리 수정</h1>
	</div>
	<br>
	<div class="outer">
		<div class="gAdd" align="center">
			<form action="update.do" method="post" enctype="multipart/form-data">
				<div class="content" align="left">
					<input type="hidden" name="gno" value="${gno}">
					<input type="text" name="galleryTitle" value="${gallery.galleryTitle}" 
							placeholder="제목을 입력해주세요." style="width:728px; height:35px;" required><br><br>
					<br>
					<textarea id="galleryExplain" name="galleryExplain" cols="70" rows="30" 
								style="resize:none; width:830px; height:500px;" required>${gallery.galleryExplain}</textarea>
				
					<br><br>
					
					<div id="file-area">
						<input type="file" name="upfile" multiple accept=".jpg , .png, .gif, .jfif" required><br><br>
					</div>
					<div align="right">
						<button type="submit" id="submitModifyBoardBtn">등록</button>
						<button type="button" onclick="location.href='list.do?currentpage=1'">취소</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br><br><br><br><br>
	<script type="text/javascript">
		var oEditors = [];
		$(function() { 
		
			// Editor Setting 
			nhn.husky.EZCreator.createInIFrame({ oAppRef : oEditors, // 전역변수 명과 동일해야 함. 
				elPlaceHolder : "galleryExplain", // 에디터가 그려질 textarea ID 값과 동일 해야 함. 
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

			oEditors.getById["galleryExplain"].exec("UPDATE_CONTENTS_FIELD", []);

			});

	
	</script>

</body>
</html>