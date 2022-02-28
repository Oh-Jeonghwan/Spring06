<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		border-bottom: solid black 1px;
		width:950px;
		margin:auto;
	}
	
    .top-title{
		margin: auto;
		border-bottom: #2BA542 solid 4px;
	}
	
	.picture{
		height:auto;
		width: auto;
		max-width: 500px;
    	max-height: 500px;
	}
	
	.info{
		margin:10px;
	}
	
	.title{
		font-size: x-large;
        margin-left: 10px;
	}
	
	.date{margin-left: 10px;}
	
	.explain{
		font-size: large;
		margin-left:20px;
		width:850px;
		margin:auto;
	}
	
	.thumbnail{
		margin-left:20px;
		width:850px;
		margin:auto;
	}
	
	.button{
		width:850px;
		margin:auto;
	}
	
	.list-area{
		min-height: 400px;
	}
</style>
</head>
<body style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>갤러리 상세보기</h1>
	</div>
	<br>
	<div class="outer">
		<div class="list-area">
	        <c:choose>
	            <c:when test="${empty list }">
	                게시판이 비어있습니다.
	            </c:when>
	            <c:otherwise>
	            	<div class="info">
	            		<div class="title"><b>${list[0].galleryTitle }</b></div><br>
	            		<div class="writer">${list[0].galleryWriter }</div><br>
	            		<div class="date">
	            			<fmt:parseDate value="${list[0].enrollDate}" var="gDate" pattern="yyyy-MM-dd HH:mm:ss"/>
	            			<fmt:formatDate value="${gDate }" type="both"/>
	            		</div>
	            	</div>
	            	<hr>
	            	<br>
	            	<div class="explain">
						${list[0].galleryExplain}
	    			</div>
	    			<br><br>
	                <c:forEach var="b" items="${list}">
	                    <div class="thumbnail">
	                        <img class="picture" src="${ pageContext.request.contextPath }${b.path}">
	                    </div>
	                    <br>
	                </c:forEach>
	            </c:otherwise>
	        </c:choose>
	    </div>
    </div>
    <br>
    <!-- 댓글 영역 -->
    <div id="reply-area">
        <table border="1" align="center" class="table table-stripped">
            <thead>
				<!--로그인이 되어있을 경우: 댓글작성 가능-->
				<!--로그인이 안 되어 있을 경우: 댓글작성 불가능-->
				<tr>
					<th>댓글 작성</th>
					<td>
						<textarea id="replyContent" cols="50" rows="3" style="resize:none;" ${(loginUser == null)?'readonly':'' } placeholder="로그인 후 작성 가능"></textarea>
					</td>
					<td><button ${(loginUser != null)?"onclick=insertReply()":""} >댓글등록</button></td>
				</tr>
            </thead>
            <tbody>
                    
            </tbody>
        </table>
        <br><br>
    </div>
    <div class="button" align="right">
    	<button type="button" onclick="location.href='list.do?page=1'">목록가기</button>
    	<c:if test="${list[0].galleryWriter==loginUser.memberId}">
	    	<button type="button" onclick="location.href='update.do?gno=${list[0].galleryNo}'">수정하기</button>
	    	<button type="button" onclick="location.href='delete.do?gno=${list[0].galleryNo}'">삭제하기</button>
    	</c:if>
    </div>
<br><br><br><br><br>
<script>
        function insertReply(){
        	$.ajax({
        		url: "rinsert.do",
        		data: {
        			content: $("#replyContent").val(),
        			gno: ${list[0].galleryNo}
        		},
        		type: "post",
        		success: function(result){
        			console.log(result);
        			$("#replyContent").val("");
        			if(result>0){
        				alert("댓글이 등록되었습니다.");
        			}
        		},
        		error:function(){
        			console.log("댓글 삽입용 ajax 실패");
        		}
        	});
        	
        	$(function(){
    			$.ajax({
    				url: "rlist.do",
    				type:"post",
    				data: {gno: ${list[0].galleryNo}},
    				success:function(list){
    					//댓글 개수만큼 반복=> 누적(문자열)
    					var result = "";
    					console.log(list);
    					for(var i in list){//for in 문
    						result += "<tr>"
    										+"<td>"+list[i].reflyWriter + "</td>"
    										+"<td>"+list[i].replyContent + "</td>"
    										+"<td>"+list[i].createDate + "</td>"
    								+ "</tr>";
    					}
    					//tbody를 골라 innerHtml로 넣어주기
    					$("#reply-area tbody").html(result);
    				},
    				error: function(){
    					console.log("댓글리스트용 ajax 실패");
    							}	
    			});
        	});
        }
        
      //댓글은 화면이 ㄹ ㅗ딩되었을 때 곧바로 뿌려주어야함 => window.onload => $(function)
		$(function(){
			$.ajax({
				url: "rlist.do",
				type:"post",
				data: {gno: ${list[0].galleryNo}},
				success:function(list){
					//댓글 개수만큼 반복=> 누적(문자열)
					var result = "";
					for(var i in list){//for in 문
						result += "<tr>"
										+"<td>"+list[i].reflyWriter + "</td>"
										+"<td>"+list[i].replyContent + "</td>"
										+"<td>"+list[i].createDate + "</td>"
								+ "</tr>";
					}
					//tbody를 골라 innerHtml로 넣어주기
					$("#reply-area tbody").html(result);
				},
				error: function(){
					console.log("댓글리스트용 ajax 실패");
							}	
			});
    	});
        </script>
</body>
</html>