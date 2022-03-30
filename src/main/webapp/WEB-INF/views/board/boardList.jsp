<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 커스터마이징 하는 jstl 라이브러리의 코어 -->
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
    
    .list-area{
    	padding-top: 0; 
		padding-bottom: 45px;
        width:940px;
        margin: auto;
        border-bottom: solid black 1px;
    }
    
    .button{
		width:850px;
		margin:auto;
	}
	
	.pagination{
		width:850px;
		margin-left:75px;
	}
	
	.search{
		width:850px;
		margin:auto;
	}
	
	tbody>tr:hover{
		cursor: pointer;
        opacity: 0.7;
	}
	
	ul {
	    list-style: none;
	    padding-left: 0px;
	    display:inline-block;
 	}
 	
 	ul li{
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
		<h1>자유 게시판</h1>
	</div>
	<br>
	<div class="outer">
		<br>
		<div class="list-area">
			<table class="table">
				<thead>
					<tr>
						<th style="width:70px;">글번호</th>
						<th style="width:60px;">항목</th>
						<th style="width:500px;">글제목</th>
						<th style="width:100px;">작성자</th>
						<th style="width:250px;">작성일</th>
						<th style="width:70px;">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="5" style="text-align:center">조회된 결과가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="b" items="${list}">
								<tr>
									<td>${b.boardNo }</td>
									<td>${b.boardHead }</td>
									<td>${b.boardTitle }</td>
									<td>${b.boardWriter}</td>
									<td>
										<fmt:parseDate value="${b.createDate}" var="cDate" pattern="yyyy-MM-dd HH:mm:ss"/>
										<fmt:formatDate value="${cDate}" type="both"/>
									</td>
									<td>${b.boardRead }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<c:choose>
	  	<c:when test="${!empty keyword && !empty type}">
	  		<div class="pagination" align="center">
		        <ul>
		            <!--
				        이전 버튼이 클릭가능한 조건이면, a태그를 이용해 이전 버튼이 뜨도록 하고, href로 링크를 걸되
				        아까 만든 makeQuery 메서드를 이용해서 쿼리문자열을 만들어 줍니다.
				        ?page=어쩌고&perPageNum=어쩌고 이 부분을 생성해서 넣게 되는데 단 이전 버튼을 클릭하면
				        현재 페이지가 시작페이지의 -1 이 되도록 되어야 함으로 그 부분만 신경써 주면 됩니다.
		            -->
		            <c:if test="${pageMaker.prev}">
		                <li>
		                    <a href="union.do${pageMaker.makeQuery(pageMaker.startPage - 1)}&type=${type}&keyword=${keyword}" class="previous">&lt;</a>
		                </li>
		                
		            </c:if>
		 
		            <!--
		                [1][2][3]....[10] 이 부분을 삽입하는 부분이다. jstl 이용해for문을 돌면서 startPage ~ endPage까지
		                                    표시해주되, a태그 눌렀을 때 이동하는 page 부분에 index 지정하는 부분을 유의깁게 보길 바란다.
		            -->
		            <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="index">
		                <button type="button" onclick="location.href='union.do${pageMaker.makeQuery(index) }&type=${type}&keyword=${keyword}'" 
			            class="btn btn-default" ${(pageMaker.cri.page==index)?'disabled':''}>${index }</button>
		            </c:forEach>
		 
		            <c:if test="${pageMaker.next }">
	                <!--
	                                         이전버튼과 마찬가지로 다음버튼을 끝 페이지보다1개 큰게 현재 페이지가 되도록 makeQuery에 page를 넣어줍시다.
	                -->
		                <li>
		                    <a href="union.do${pageMaker.makeQuery(pageMaker.endPage + 1)}&type=${type}&keyword=${keyword}" class="next">&gt; </a>
		                </li>
		            </c:if>  
		        </ul>
	        </div>
		 	
	    </c:when>
	    <c:otherwise>
	    	<div class="pagination" align="center">
		        <ul>
		            <!--
				        이전 버튼이 클릭가능한 조건이면, a태그를 이용해 이전 버튼이 뜨도록 하고, href로 링크를 걸되
				        아까 만든 makeQuery 메서드를 이용해서 쿼리문자열을 만들어 줍니다.
				        ?page=어쩌고&perPageNum=어쩌고 이 부분을 생성해서 넣게 되는데 단 이전 버튼을 클릭하면
				        현재 페이지가 시작페이지의 -1 이 되도록 되어야 함으로 그 부분만 신경써 주면 됩니다.
		            -->
		            <c:if test="${pageMaker.prev}">
		                <li>
		                    <a href="union.do${pageMaker.makeQuery(pageMaker.startPage - 1)}" class="previous">&lt;</a>
		                </li>
		                
		            </c:if>
		 
		            <!--
		                [1][2][3]....[10] 이 부분을 삽입하는 부분이다. jstl 이용해for문을 돌면서 startPage ~ endPage까지
		                표시해주되, a태그 눌렀을 때 이동하는 page 부분에 index 지정하는 부분을 유의깁게 보길 바란다.
		            -->
		            <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="index">
			            <button type="button" onclick="location.href='union.do${pageMaker.makeQuery(index) }'" 
			            class="btn btn-default" ${(pageMaker.cri.page==index)?'disabled':''}>${index }</button>
		            </c:forEach>
		 
		            <c:if test="${pageMaker.next }">
	                <!--
	                    이전버튼과 마찬가지로 다음버튼을 끝 페이지보다1개 큰게 현재 페이지가 되도록 makeQuery에 page를 넣어줍시다.
	                -->
		                <li>
		                    <a href="union.do${pageMaker.makeQuery(pageMaker.endPage + 1)}" class="next">&gt; </a>
		                </li>
		            </c:if>  
		        </ul>
	        </div>
	    </c:otherwise>
    </c:choose>
    <br>
	<div class="button" align="right">
		<!-- 로그인한 사용자만 -->
		<c:if test="${loginUser != null}">
			<a href="write.do" class="btn btn-primary stn-sm">글작성</a>
		</c:if>
			&nbsp;<a href="../" class="btn btn-default stn-sm">메인으로</a>
		<br>
	</div>
	
	<script>
		$(function(){
			$(".table tr").click(function(){
				var bno = $(this).children().eq(0).text();
				
				if(bno!="글번호"){
					location.href="${pageContext.request.contextPath}/board/content.do?boardNo="+bno;
				}
			
				//Context Root == Context path == url 주소의 최상위(메인) 페이지 주소
				//기존 스크립틀릿 방식:request.getContextPaht()
				//EL 방식: pageContext.request.contextPath
				//jstl 방식: c:url 태그의 밸류 속성에 값을 지정해서 쓰면 된다.(vlaue="/")
				//=> 단, JSTL 같은 경우는 스크립트 태그 안에서 사용 불가
			});
		});
	</script>
	<br>
	<!-- 검색창 -->
	<div class="search" align="center">
		<form action="union.do" method="get">
			<select name="type">
				<option value="BOARD_TITLE">제목</option>
				<option value="BOARD_WRITER">작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어">
			<button class="btn-default" type="submit">검색</button>
		</form>
	</div>
<br><br><br><br><br>
</body>
</html>