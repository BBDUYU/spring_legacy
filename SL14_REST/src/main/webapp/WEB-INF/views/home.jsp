<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<!-- 단순문자열 -->
<h3><a href="/sample/getText">/sample/getText</a></h3>
<!-- 자바객체 -> JSON, XML 응답컨트롤러 메서드 -->
<h3><a href="/sample/getSample">/sample/getSample</a></h3>
<!-- 여러개의 자바객체 -> JSON, XML 응답컨트롤러 메서드 -->
<h3><a href="/sample/getSampleList">/sample/getSampleList</a></h3>
<hr />
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<h3>
	<a href="/time">/time</a>
</h3>
</body>
</html>
