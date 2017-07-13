<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>visit_countTest</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>나이</th>
			<th>타일이름</th>
			<th>메이저</th>
			<th>마이너</th>
			<th>방문횟수(1인 1방문 기준)</th>
		</tr>
		
		<c:forEach items="${ list }" var="vo">
		
			<tr>
				<td>${ vo.get("age") }</td>
				<td>${ vo.get("tile_name") }</td>
				<td>${ vo.get("major") }</td>
				<td>${ vo.get("minor") }</td>
				<td>${ vo.get("visit_count") }</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>
