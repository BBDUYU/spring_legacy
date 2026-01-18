<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>2026. 1. 16. 오전 9:31:44</title>
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
    get.jsp
  </xmp>
  
  <form action="/board/register" method="post">
	  <table>  
	    <tbody>
	      <tr>
	        <th>글번호</th>
	        <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ boardVO.bno }"></td>        
	      </tr> 
	      <tr>
	        <th>제목</th>
	        <td><input type="text" name="title" class="full"  readonly="readonly"  value='<c:out value="${ boardVO.title }"></c:out>'></td>        
	      </tr> 
	      <tr>
	        <th>내용</th>
	        <td><textarea  name="content" class="full" readonly="readonly"><c:out value="${ boardVO.content }"></c:out></textarea></td>        
	      </tr> 
	      <tr>
	        <th>작성자</th>
	        <td><input type="text" name="writer" class="short" readonly="readonly" value="${ boardVO.writer }"></td>        
	      </tr>  
	    </tbody> 
	    <tfoot>
	      <tr>
	        <td colspan="2">
	          <button type="button"  data-oper="modify" class="edit">Modify</button>
	          <button type="button"  data-oper="remove" class="delete">Delete</button>
	          <button type="button" data-oper="list"  class="list">List</button>
	        </td>
	      </tr>
	    </tfoot>
	  </table>
	  <!-- get.jsp -->
	  <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	  <input type="hidden" name="pageNum" value="${ criteria.pageNum }">
      <input type="hidden" name="amount" value="${ criteria.amount }">
      
    </form>
  
</div>
<script>
 // get.jsp
 $(function (){
	 
	 const formObj = $('form');
	 
	 $("tfoot button").on("click", function (){
		 
		// data-oper="modify"
		let operation = $(this).data("oper");
		if (operation == "modify") {  // 수정 요청
			formObj
			  .attr({
				  "method": "get", 
				  "action": "/board/modify"
			  }) 
			  .submit();
			
		} else if (operation == "remove") { // 삭제 요청
			
			if( confirm("정말 삭제할거냐?") ){
				formObj
				  .attr({
					  "method": "get", 
					  "action": "/board/remove"
				  }) 
				  .submit();
			} // if 
			
			
		} else if (operation == "list") {  // 목록 요청
			
			const pageNumClone = $(":hidden[name='pageNum']").clone();
			const pageAmountClone = $(":hidden[name='amount']").clone();
			  
			
			formObj
			  .attr({
				  "method": "get", 
				  "action": "/board/list"
			  }) 
			  .empty()   // 자식 요소 제거
			  .append(pageNumClone)
			  .append(pageAmountClone)
			  // pageNum, amount
			  .submit();
		}
		 
	 }); // $("tfoot button").on("click"
			 
			 
	 // 수정이 성공하면 result == SUCCESS 처리
	 var result = '<c:out value="${result}" />'; 
	 checkModal(result);
	  
	  history.replaceState({}, null, null);
	  
	  function checkModal(result){
		  if( result === "" || history.state ) return;
		  if( result == 'SUCCESS' ){
			  alert(  `${boardVO.bno} 번이 수정되었습니다.`  );
			  return;
		  }
	  } // function checkModal
	 
 }); //  $(function (){

</script>  
</body>
</html>







