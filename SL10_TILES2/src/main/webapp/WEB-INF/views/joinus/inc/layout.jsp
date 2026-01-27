<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>index</title>
      <link href="${ pageContext.request.contextPath }/joinus/join.css" type="text/css" rel="stylesheet" />
      <link href="${ pageContext.request.contextPath }/joinus/login.css" type="text/css" rel="stylesheet" />
      
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
   </head>
   <body>
   
      <!-- header 영역 시작 -->
      <tiles:insertAttribute name="header"/>
      <!-- header 영역 끝 -->
      
      <!-- visual 영역 시작 -->
      <tiles:insertAttribute name="visual"/>
      <!-- visual 영역 끝 -->
      <div id="main">
         <div class="top-wrapper clear">
         <!-- content 영역 시작 -->
         <tiles:insertAttribute name="content"/>
         <!-- content 영역 끝 -->
         
         <!-- navi 영역 시작 -->
         <tiles:insertAttribute name="aside"/>
         <!-- navi 영역 끝 -->
         </div>
      </div>
      <!-- footer 영역 시작 -->
      <tiles:insertAttribute name="footer"/>
      <!-- footer 영역 끝 -->
      
      </body>
</html>
