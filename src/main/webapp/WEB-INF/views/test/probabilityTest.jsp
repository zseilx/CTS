<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>probability</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>타일이름</th>
			<th>타일별 방문</th>
			<th>전체 방문</th>
			<th>방문 확률</th>
		</tr>
		
		<c:forEach items="${ list }" var="vo">
		
			<tr>
				<td>${ vo.get("tile_name") }</td>
				<td>${ vo.get("tile_visit") }</td>
				<td>${ vo.get("total_visit") }</td>
				<td>${ vo.get("probability") }</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>
