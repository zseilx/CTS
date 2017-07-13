<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>

    <script src="resources/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		$("document").ready(function() {
			setDate(document.forms[0]);
		});
		var setYear = function(FORM){
			
		};
		var setMonth = function(FORM) {
			
		};
		
		var setDay = function(FORM, year, month) {
			days = new Date(new Date(year, month, 1) - 86400000).getDate();
			FORM.day.length = 0;
			for (i = 0, j; i < days; i++) {
				j = (i < 9) ? '0' + (i + 1) : i + 1;
				FORM.day.options[i] = new Option(j, j);
			}
		};
		
		function setDate(FORM, year, month, day) {
	
			var current, year, month, day, days, i, j;
			current = new Date();
			year = (year) ? year : current.getFullYear();
			console.log("year: " + year);
			for (i = 0, j = year - 10; j <= year; i++, j++)
				FORM.year.options[i] = new Option(j, j);
			month = (month) ? month : current.getMonth() + 1;
			console.log("month: " + month)
			for (i = 0; i < 12; i++) {
				j = (i < 9) ? '0' + (i + 1) : i + 1;
				FORM.month.options[i] = new Option(j, j);
			}
			day = (day) ? day : current.getDate();
			console.log("day: " + day);
			days = new Date(new Date(year, month, 1) - 86400000).getDate();
			console.log("days: " + days);
			FORM.day.length = 0;
			for (i = 0, j; i < days; i++) {
				j = (i < 9) ? '0' + (i + 1) : i + 1;
				FORM.day.options[i] = new Option(j, j);
			}
			FORM.year.value = year;
			FORM.month.options[month - 1].selected = true;
			FORM.day.options[day - 1].selected = true;
	
		}
		
	</script>
</head>
<body>
	<select id="selYear" name="bsnsYear">

		<option value="">전체</option>
		<c:set var="now" value="<%=new java.util.Date()%>" />
		<fmt:formatDate value="${now}" pattern="yyyy" var="yearStart" />
		<c:forEach begin="0" end="10" var="result" step="1">
			<option value="<c:out value="${yearStart - result}" />"
				<c:if test="${(yearStart - result) == searchVO.bsnsYear}"> selected="selected"</c:if>>
				<c:out value="${yearStart - result}" />
			</option>
		</c:forEach>

	</select>
	
	<FORM name="example">
		<SELECT name="year"
			onChange="setDate(this.form, this.value, this.form.month.value)">
		</SELECT>년 <SELECT name="month"
			onChange="setDate(this.form, this.form.year.value, this.value)">
		</SELECT>월 <SELECT name="day"></SELECT>일
	</FORM>


	<table class="table table-bordered">
		<tr>
			<th>타일이름</th>
			<th>메이저</th>
			<th>마이너</th>
			<th>평균머문시간</th>
			<th>방문횟수</th>
		</tr>

		<c:forEach items="${ list }" var="vo">

			<tr>
				<td>${ vo.get("tile_name") }</td>
				<td>${ vo.get("major") }</td>
				<td>${ vo.get("minor") }</td>
				<td>${ vo.get("avg") }</td>
				<td>${ vo.get("cnt") }</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>
