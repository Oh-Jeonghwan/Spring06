<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		min-height: 400px;
		margin-left:20px;
		width:850px;
		margin:auto;
	}
	
	.button{
		width:850px;
		margin:auto;
	}
	
	table{border: 1px solid white;}
	
    table input, table textarea{
        width: 100%;
        box-sizing: border-box;
    }
    
    .like{
    	margin:auto;
    }
    
    .likeShape{
    	width: 200px;
    	height: 70px;
    	border:1px solid black;
    }
    
    #btnLike{
    	padding-right: 20px; 
    	background-color:transparent; 
    	border:none;
    }
    
</style>
</head>
<body  style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>자유 게시판 상세보기</h1>
	</div>
	<br>
	<div class="outer">
		<div class="list-area">
			<div class="info">
				<div class="title"><b>${board.boardTitle}</b></div>
				<br>
				<div class="writer">${board.boardWriter}</div>
				<br>
				<div class="date">${board.createDate}</div>
			</div>
			<hr>
			<br>
			<div class="explain">
				${board.boardContent}
			</div>
			
			<div class="like" align="center">
			<!-- 
				<button type="button" id="btnLike">
					<img src="${ isLiked == true ? '${pageContext.request.contextPath }/resources/like.png' : '${pageContext.request.contextPath }/resources/emptylike.png' }" 
						id="like_img" height="50px" width="50px">
				</button>
				<span id="like_count">${like_count }</span>  
			 -->
			 	<div class="likeShape" align="right">
			 		<button type="button" id="btnLike"> 
			 			<c:choose>
			 				<c:when test="${like.likeCheck==0}">
			 					<span id="likeCount">${like.likeCount}</span>
			 					<img src="${pageContext.request.contextPath }/resources/emptylike.png" id="like_img" height="50px" width="50px">
			 				</c:when>
			 				<c:otherwise>
			 					<span id="likeCount">${like.likeCount}</span>
			 					<img src="${pageContext.request.contextPath }/resources/like.png" id="like_img" height="50px" width="50px">
			 				</c:otherwise>
			 			</c:choose>
					</button>
				</div>
			</div>	
			<br>
	    </div>
    </div>
  	
    <br>
    <!-- 댓글 영역 -->
    <div id="reply-area">
        <table border="1" align="center" class="table table-stripped">
            <thead>
				<tr>
					<th style="vertical-align:middle">댓글 작성</th>
					<td>
						<textarea id="replyContent" cols="50" rows="3" style="resize:none;" ${(loginUser == null)?'readonly':'' } placeholder="로그인 후 작성 가능"></textarea>
					</td>
						<!--로그인이 되어있을 경우: 댓글작성 가능-->
						<!--로그인이 안 되어 있을 경우: 댓글작성 불가능-->
					<td style="vertical-align:middle"><button ${(loginUser != null)?"onclick=insertReply()":""} >댓글등록</button></td>
				</tr>
            </thead>
            <tbody>
                    
            </tbody>
        </table>
    </div>
    <br><br>
    <div class="button" align="right">
    	<button type="button" onclick="location.href='${pageContext.request.contextPath}/board/union.do?page=1'">목록가기</button>
	    <c:if test="${board.boardWriter==loginUser.memberId}">	
	    	<!-- 서브밋이 아닌 기본 버튼을 통한 유알엘 이동은 get 방식으로 넘어감 서블릿에서 get매핑으로 -->
	    	<button type="button" onclick="location.href='boardUpdate.do?bno=${board.boardNo}'">수정하기</button>
	    	<button type="button" onclick="location.href='delete.do?bno=${board.boardNo}'">삭제하기</button>
    	</c:if>
    </div>
<br><br><br><br><br>
	<script>
        function insertReply(){
        	$.ajax({
        		url: "rinsert.do",
        		data: {
        			content: $("#replyContent").val(),
        			bno: ${board.boardNo}
        		},
        		type: "post",
        		success: function(result){
        			content: $("#replyContent").val("");
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
    				data: {bno: ${board.boardNo}},
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
        }
        
     	$(function(){
     		//댓글은 화면이 ㄹ ㅗ딩되었을 때 곧바로 뿌려주어야함 => window.onload => $(function)
			$.ajax({
				url: "rlist.do",
				type:"post",
				data: {bno: ${board.boardNo}},
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
		
     
		$("#btnLike").click(function(){
			//좋아요 클릭 시 하트 채워지고 비워지는 ajax
			$.ajax({
				url:"likeUpdate.do",
				type:"post",
				data:{
					boardNo: ${board.boardNo},
					memId: "${loginUser.memberId}"
				},
				success: function(data){
					var result = "";
					if(data.msg==100){
						alert("로그인을 해주세요");
					}
					else{ //좋아요 값이 1일 때 0일 때 나눠줘야 함
						if(data.likeInsert==1 && data.likeUpdate==0){//likeinsert가 1 / likeupdate0일 때 (좋아요 버튼이 채워진다.)
							result = "<span id='likeCount'>"+data.likeCount+"</span>"
		 						    +"<img src='${pageContext.request.contextPath }/resources/like.png' id='like_img' height='50px' width='50px'>"
							alert("좋아요 감사");
						}
						else{//likeinsert가 0 / likeupdate가 1일 때 (좋아요 버튼이 비워진다.)
							result="<span id='likeCount'>"+data.likeCount+"</span>"
	 							  +"<img src='${pageContext.request.contextPath }/resources/emptylike.png' id='like_img' height='50px' width='50px'>"
							alert("내가 싫어요?");
						}
						$("#btnLike").html(result);
					}
				} ,
				error: function(){
					console.log("실패");
				}
			});
		});
	
    </script>
</body>
</html>