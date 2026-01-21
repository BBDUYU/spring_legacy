<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>2026. 1. 21. 오전 10:15:30</title>
<link rel="shortcut icon" type="image/x-icon" href="/resources/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<script src="/resources/js/dept.js"></script>
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
    dept.jsp
  </xmp>
    <form action="/scott/emp" method="post">
  <table id="tbl-dept">
    <caption></caption>
    <thead>
      <tr>
        <th></th>
        <th>DeptNo</th>
        <th>DName</th>
        <th>Loc</th>
        <th>Edit</th>
      </tr> 
    </thead>
    <tbody>
      <c:forEach items="${ list }" var="dto">
        <tr>
          <td><input type="checkbox" name="deptno" value="${ dto.deptno }" data-deptno="${ dto.deptno }"></td>          
          <td>${ dto.deptno }</td>
          <td>${ dto.dname }<span class="badge right red">${ dto.numberOfEmps }</span></td>
          <td>${ dto.loc }</td>
          <td align="center">
            <span class="material-symbols-outlined delete" 
           data-deptno="${ dto.deptno }">close</span>
          </td>
        </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="5">
          <button id="search" class="search"  type="button">search</button> 
         <button id="add" type="button" class="add">부서추가</button> 
        </td>
      </tr>
    </tfoot>
  </table>
  </form>
</div>
<script>
</script>  
</body>
</html>