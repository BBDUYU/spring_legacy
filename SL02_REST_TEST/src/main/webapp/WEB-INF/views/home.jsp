<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form>
	<label for="empno">아이디 : </label>
	<input type="text" id="empno" name="empno" autofocus="autofocus" value="7369">
	<button type="button" id="btnCheck">중복체크</button>
	<button type="button" id="btnCheck2">중복체크2</button>
	<br>
	<span id="idCheckResult"></span>
</form>
<hr>
<P>  The time on the server is ${serverTime}. </P>
<h3>
	<a href="/time">/time</a>
</h3>

<script>
  $(function() {
	  //2
	     $("#btnCheck2").on("click", function() {
      /* alert("버튼클릭") */
      var empno=$("#empno").val();
      if(!empno){
    	  alert("아이디를 입력하세요");
    	  return;
      }
      $.ajax({
    	  url:`${pageContext.request.contextPath}/empnoCheck/\${empno}`,
    	  type:"GET",
    	  success:function(response){
    		  if(response==="available"){
    			  $("#idCheckResult")
    			  	.text("사용가능한 아이디입니다")
    			  	.css("color","green");
    		  }else{
    			  $("#idCheckResult").text("이미 사용중인 아이디입니다").css("color","red");
    		  }
    	  },error:function(){
    		  alert("XXX");
    	  }
      });
   }); // $("#btnCheck").on("click",
	  //1
   $("#btnCheck").on("click", function() {
      /* alert("버튼클릭") */
      var empno=$("#empno").val();
      if(!empno){
    	  alert("아이디를 입력하세요");
    	  return;
      }
      $.ajax({
    	  url:"${pageContext.request.contextPath}/emp/empno",
    	  type:"GET",
    	  data:{empno:empno},
    	  success:function(response){
    		  if(response==="available"){
    			  $("#idCheckResult")
    			  	.text("사용가능한 아이디입니다")
    			  	.css("color","green");
    		  }else{
    			  $("#idCheckResult").text("이미 사용중인 아이디입니다").css("color","red");
    		  }
    	  },error:function(){
    		  alert("XXX");
    	  }
      });
   }); // $("#btnCheck").on("click",
  }); // $(function() {
</script>
</body>
</html>
