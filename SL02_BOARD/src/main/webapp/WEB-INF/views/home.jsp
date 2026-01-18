<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3>
  <a href='/board/list'>목록 보기</a> 
</h3>
<h3>
  <a href="/time">/time</a>
</h3>

<!--
1. pom_original.xml  
   pom.xml 수정.
2. jspPro>webapp>resources -> src>main>webapp>resources 복사
3. home.jsp 링크태그 추가
   <a href="/time">/time</a>        ->           time.jsp 응답.
4. src>main>webapp>WEB-INF>views 폴더
   ㄴ home.jsp         
   ㄴ time.jsp       
5. org.doit.ik.aop 패키지         스프링 AOP
5. org.doit.ik.config 패키지      스프링 설정 
5. org.doit.ik.controller 패키지  컨트롤러  [M]VC     
5. org.doit.ik.domain 패키지      DTO/VO  
5. org.doit.ik.exception 패키지   예외  
5. org.doit.ik.mapper 패키지      매퍼 + MyBatis(ORM)          / JPA           DAO
5. org.doit.ik.security 패키지    보안( 인증 + 권한 ) 
5. org.doit.ik.service 패키지     서비스
5. org.doit.ik.util 패키지        유틸리티

6. HomeController.java ->  TimeMybatisController.java 복사 + 수정
7. org.doit.ik.mapper.TimeMapper.java  인터페이스를 추가.
      String getTime();
7-2. src/main/resources  
     1) org 폴더 추가  
     2) doit 폴더 추가  
     3) ik 폴더 추가
     4) mapper 폴더 추가
         TimeMapper.xml  코딩.
8. src/main/resources 폴더
     1) log4j.xml  수정
	   <root>
		 <!-- <priority value="warn" />  
		 <priority value="info" />
		 <appender-ref ref="console" />
	  </root>
	  2) log4jdbc.log4j2.properties 파일 추가
	     log4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator

9. src/main/webapp
     ㄴ images 폴더 추가	

10. WEB-INF/spring 폴더
    ㄴ root-context.xml       모든 컨테이너에서 사용할 설정 파일
    ㄴ security-context.xml   보안 설정 파일
    ㄴ appServlet 폴더
       ㄴ servlet-context.xml 해당 컨테이너에서만 사용할 설정 파일
10-2. org.doit.ik.mapper 패키지
    mybatis-config.xml 추가    : 마이바티스 설정파일     
10-3. security-context.xml 수정   
10-4. servlet-context.xml 수정  

11. /WEB-INF
       ㄴtiles 폴더 추가
          ㄴ tiles.xml  추가 + 코딩 수정

12. web.xml -> web_original.xml 코딩 수정  


(주의) ojdbc6.jar 직접 설정....            
 -->

</body>
</html>







