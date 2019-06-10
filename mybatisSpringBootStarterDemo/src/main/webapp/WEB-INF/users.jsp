<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table style="width:100%">
		<tr>
　　　　　　<td>id</td>
　　　　　　<td>name</td>
　　　　</tr>
	<c:if test="${!empty users}">
		<c:forEach items="${users}" var="row" varStatus="status">
			<tr>
				<td style="text-align: left; width: 10%;">${row.id }</td>
				<td style="text-align: left;">${row.name} &nbsp;</td>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</body>
</html>