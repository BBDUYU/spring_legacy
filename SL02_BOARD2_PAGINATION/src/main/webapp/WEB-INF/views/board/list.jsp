<%@ page trimDirectiveWhitespaces="true" %>  
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>2026. 1. 15. 오후 2:23:59</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">kEnik HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code">
    list.jsp
  </xmp>
  
  <table>
    <caption style="text-align: right;">
      <a href="/board/register">글쓰기</a>
    </caption>
    <thead>
	    <tr>
	      <th>#번호</th>
	      <th>제목</th>
	      <th>작성자</th>
	      <th>작성일</th>
	      <th>수정일</th>        
	    </tr>
    </thead>
    <tbody>
       <c:choose>
         <c:when test="${ empty list }">
           <tr>
             <td colspan="5">no board...</td>
           </tr>
         </c:when>
         <c:otherwise>
           <c:forEach items="${ list }" var="board">
             <tr>
               <td><c:out value="${ board.bno }" /></td>                 
               <%-- <td><a href="/board/get?bno=${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
               <td><a class="move" href="${ board.bno }"><c:out value="${ board.title }" /></a></td>
               <td><c:out value="${ board.writer }" /></td>
               <td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd" /></td>
               <td><fmt:formatDate value="${ board.updatedate }"  pattern="yyyy-MM-dd" /></td>
             </tr>
           </c:forEach>
         </c:otherwise>
       </c:choose>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="5">
          <div class="center">
          	<div class="pagination">
          	   <!-- [1] 2 3 4 5 6 7 8 9 10 > -->
          	   <!-- 1. 이전 prev -->
          	   <c:if test="${ pageMaker.prev }">&laquo;</c:if>
          	   <!-- 2. 1 2  ... 10 -->
          	   <c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1" var="num">
          	   <a href="${ num }" class="${ num eq pageMaker.criteria.pageNum ? 'active': '' }">${ num }</a>
          	   </c:forEach>
          	   <!-- 3. 다음 next -->
          	   <c:if test="${ pageMaker.next }">&raquo;</c:if>
          	</div>
          </div>
        </td>
      </tr>
    </tfoot>
  </table>  
  
  <form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="pageNum" value="${ pageMaker.criteria.pageNum }">
    <input type="hidden" name="amount" value="${ pageMaker.criteria.amount }">
    <!-- 검색관련 검색조건, 검색어 등등 -->
  </form>
  
</div>
<script>
  $(function (){
	  // [1]
	  var result = '<c:out value="${result}" />';
	  // alert( result + "번 등록되었습니다.");
	  checkModal(result);
	  
	  history.replaceState({}, null, null);
	  
	  function checkModal(result){
		  if( result === "" || history.state ) return;
		  if( parseInt(result) > 0 ){
			  alert( result + "번 등록되었습니다.");
			  return;
		  }
		  if( result === 'REMOVESUCCESS' ){
			  var deleteBno = "${bno}";
			  alert(`\${deleteBno}번 삭제되었습니다.`);
			  return;
		  }
	  } // function checkModal
	  	  
	  //  [2] 게시판 테이블의 제목 링크를 클릭할 때 처리.
	  const actionForm = $("#actionForm");
	  
	  $("a.move").on("click", function (){		  
		  event.preventDefault();
		  let bno = $(this).attr("href");		  
		  //    /board/get?bno=3
		  actionForm
		    .attr("action", "/board/get")
		    .append(`<input type="hidden" name="bno" value="\${bno}">`)
		    .submit();
		  
	  }); // $("a.move").on("click"
			  
	  //  [4] 페이징 블럭의 링크 클릭
	  $(".pagination a").on("click", function (){
		  event.preventDefault();
		  let pageNum = $(this).attr("href");
          //	    /board/list?pageNum=2&amount=3
		  actionForm
		    .attr("action", "/board/list")
		    .find(":hidden[name=pageNum]")
		        .val(pageNum)
		        .end()		       
		    .submit();
	  }); //  $(".pagination a").on("click",
	  
  }); // $(function (){
</script>  
</body>
</html>







