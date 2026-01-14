<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- 			
	복사 후 org.eclipse.wst.common.component -> 프로젝트 이름 바꾸기
			<name>SL01_MVC</name> -> 프로젝트 이름 바꾸기
			
	/scott/dept 요청
	ScottController.java
		ㄴ 부서정보를 처리하는 컨트롤러 메서드 선언
	/WEB-INF/views/scott/dept.jsp 	M[V]C
	
	1) org.doit.ik.domain.DeptDTO.java
	2) org.doit.ik.mapper.scott.DeptMapper.java 인터페이스
	3) src/main/resources 폴더
	   org.doit.ik.mapper
		ㄴ scott 폴더
			ㄴ DeptMapper.xml
	4) 	BoardDAO 인터페이스 / BoardDAOImpl 클래스 X -> ORM
		rootcontext.xml DB연동 -> bean 태그
		new BoardDAOImpl()
		DeptMapper.java 인터페이스 + DeptMapper.xml
		BoardDAO 인터페이스
			
	
 -->
<P>  The time on the server is ${serverTime}. </P>
<h3>
	<a href="/scott/dept">/scott/dept</a>
</h3>
<h3>
	<a href="/time">/time</a>
</h3>
</body>
</html>
