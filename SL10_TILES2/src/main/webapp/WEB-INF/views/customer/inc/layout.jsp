<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
		<link href="${ pageContext.request.contextPath }/customer/notice.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<!-- header start -->
		<tiles:insertAttribute name="header"/>
		<!-- header end -->
		
		<!-- visual start -->
		<tiles:insertAttribute name="visual"/>
		<!-- visual end -->
		
		<div id="main">
			<div class="top-wrapper clear">
						
				<!-- content start -->
				<tiles:insertAttribute name="content"/>
				<!-- content end -->
				
				<!-- nav start -->
				<tiles:insertAttribute name="aside"/>
				<!-- nav end -->
				</div>
		</div>
		<!-- footer start -->
		<tiles:insertAttribute name="footer"/>
		<!-- footer end -->
		
		</body>
</html>
