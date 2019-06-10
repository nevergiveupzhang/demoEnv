<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Date date=new Date();
		Cookie cookie=new Cookie("name",date.getTime()+"");
		response.addCookie(cookie);
		/* request.getSession().setAttribute("name", "zhangsan"); */
	%>
	<a href="test">go</a>
</body>
</html>