<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<div class="outer">
		<div class="gAdd" align="center">
			<form action="add.do" method="post" enctype="multipart/form-data">
				<div class="content" align="left">
					<input type="text" name="galleryTitle" placeholder="제목을 입력해주세요." 
							style="width:728px; height:35px;" required><br><br>
					<br>
					<textarea name="galleryExplain" cols="70" rows="30" placeholder="내용을 입력해주세요."
								style="resize:none; width:830px; height:500px;" required></textarea>
				
					<br><br>
					
					<div id="file-area">
						<input type="file" name="upfile" multiple accept=".jpg , .png, .gif, .jfif" required><br><br>
					</div>
					<div align="right">
						<button type="button" onclick="location.href='list.do?currentpage=1'">취소</button>
						<button type="submit">등록</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<br><br><br><br><br>
</body>
</html>