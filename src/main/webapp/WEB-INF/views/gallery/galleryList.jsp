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
	.top-title{
		margin: auto;
		border-bottom: #2BA542 solid 4px;
	}
	
    .outer{
		width:950px;
		margin:auto;
    }

    .list-area{
    	padding-top: 45px; 
		padding-bottom: 45px;
        width:760px;
        margin: auto;
        border-bottom: solid black 1px;
    }

    .thumbnail{
        width:auto;
        height:auto;
        display: inline-block;
        margin: 15px;
    }

    .thumbnail:hover{
        cursor: pointer;
        opacity: 0.7;
    }
    
	.button{
		width:850px;
		margin:auto;
	}
	
	button:hover{
        cursor: pointer;
        opacity: 0.7;
    }
	
	.pagination{
		width:850px;
		margin:auto;
		display: flex;
	}
	
	.img{
		width:auto;
		height:auto;
		max-width:200px;
		max-height:300px;
	}
</style>
</head>
<body  style="width:1000px; margin:auto;">
	<div id="header">
       <jsp:include page="../../../index.jsp"></jsp:include>
    </div>
	<br><br>
	<div class="top-title">
		<h1>사진 게시판</h1>
	</div>
	<br>
    <div class="outer">
        <br>
        <div class="list-area">
            <c:choose>
                <c:when test="${empty list }">
                    리스트가 비어있습니다.
                </c:when>
                <c:otherwise>
                    <c:forEach var="b" items="${list}">
                        <div class="thumbnail img-thumbnail" align="center">
                            <input type="hidden" value="${b.galleryNo }">
                            <img src="${ pageContext.request.contextPath }${b.path}" class="img">
                            <p>
                                No.${b.galleryNo } ${b.galleryTitle }. <br>
                           	        조회수:${b.count }
                            </p>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <br>
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
	                    <a href="list.do${pageMaker.makeQuery(pageMaker.startPage - 1)}" class="previous">&lt;</a>
	                </li>
	                
	            </c:if>
	 
	            <!--
	                [1][2][3]....[10] 이 부분을 삽입하는 부분이다. jstl 이용해for문을 돌면서 startPage ~ endPage까지
	                표시해주되, a태그 눌렀을 때 이동하는 page 부분에 index 지정하는 부분을 유의깁게 보길 바란다.
	            -->
	            <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="index">
	            	<button type="button" onclick="location.href='list.do${pageMaker.makeQuery(index) }'" 
			            class="btn btn-default" ${(pageMaker.cri.page==index)?'disabled':''}>${index }</button>
	            </c:forEach>
	 
	            <c:if test="${pageMaker.next }">
	                <!--
	                    이전버튼과 마찬가지로 다음버튼을 끝 페이지보다1개 큰게 현재 페이지가 되도록 makeQuery에 page를 넣어줍시다.
	                -->
	                <li>
	                    <a href="list.do${pageMaker.makeQuery(pageMaker.endPage + 1)}"class="next">&gt; </a>
	                </li>
	            </c:if>  
	        </ul>
        </div>
	    <div class="button" align="right">
	    	<c:if test="${loginUser != null}">
	    		<button onclick="location.href='add.do'" class="btn btn-primary stn-sm">글작성</button>	
	    	</c:if>
	    	<button onclick="location.href='../'" class="btn btn-default stn-sm">메인으로</button> 
	        <br><br>
	    </div>
    </div>
	<script>
	    $(function(){
	        $(".thumbnail").click(function(){
	            //클릭될 때마다 url 요청=> location.href
	            var bno = $(this).children().eq(0).val();
	            location.href = "content.do?bno="+bno;
	        });
	    });
	</script>
<br><br><br><br><br>
</body>
</html>