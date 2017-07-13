<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function(){
		var user = "${user_id}";
		var bhf = "${bhf_code}";
});
</script>
<div class="row">
	<div class="col-lg-12">
	<form class="navbar-form"><!-- 여기의 name과 vo의 이름과 일치하는 곳에 입력. -->
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i>재고 관리
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="fa fa-bars"></i>Management</li>
		</ol>
		<div class="navbar-inner">
		<input type="hidden" id='page' value="${cri.page }">
				<input type="hidden" id='perPageNum' value="${cri.perPageNum }">
				재고수량 <input type="text" id="start" name="startAmount" value='${cri.startAmount }'> 이상 <input
					type="text" name="endAmount" id="end" value='${cri.endAmount }'> 이하
					<select name="searchType" id="searchType">
						<option value="n"
						<c:out value="${cri.searchType == null?'selected':''}"/>>
						미분류</option>
						<option value="1"
						<c:out value="${cri.searchType eq '1'?'selected':'' }"/>>
						의류</option>
						<option value="2"
						<c:out value="${cri.searchType eq '2'?'selected':'' }"/>>
						가전제품</option>
						<option value="3"
						<c:out value="${cri.searchType eq '3'?'selected':'' }"/>>
						식품</option>
						<option value="4"
						<c:out value="${cri.searchType eq '4'?'selected':'' }"/>>
						완구류</option>
						<option value="5"
						<c:out value="${cri.searchType eq '5'?'selected':'' }"/>>
						화장품</option>
						<option value="6"
						<c:out value="${cri.searchType eq '6'?'selected':'' }"/>>
						책 & 음반</option>
						<option value="7"
						<c:out value="${cri.searchType eq '7'?'selected':'' }"/>>
						주류</option>
					</select>
					 <input type="text" name="keyword" class="form-control searchForm"
					placeholder="Search" value='${cri.keyword }'> <button id="mySearch">Search</button>
		</div>
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>StockList</strong>
				</h2>
			</div>
				<table class="table table-striped table-advance table-hover">
					<!-- table-bordered -->
					<thead>
						<tr>
							<th>제품 상태</th>
							<th>업체명</th>
							<th>입고 날짜</th>
							<th>카테고리 코드</th>
							<th>제품 코드</th>
							<th>제품 명</th>
							<th>유통기한</th>
							<th>재고 수량</th>
							<th></th>
						</tr>
					</thead>
					<c:forEach items="${list }" var="stockList">
						<tbody>
							<tr class="active">
							 	<c:choose>
									<c:when test="${stockList.invntry_qy == '0'}">
										<td>품절</td>
									</c:when>
									<c:when
										test="${stockList.invntry_qy >= '1' && stockList.invntry_qy <= '30'}">
										<td>품절임박</td>
									</c:when>
									<c:when test="${stockList.invntry_qy >= '30'}">
										<td>정상</td>
									</c:when>
								</c:choose>
								<td>${stockList.user_id}</td>
								<td>${stockList.wrhousng_de }</td>
								<td>${stockList.lclasctgry_code }</td>
								<td>${stockList.goods_code }</td>
								<td>${stockList.goods_nm }</td>
								<td>${stockList.distb_de }</td>
								<td>${stockList.invntry_qy }</td>
								<td><input type="submit" class="delBtn btn btn-danger"
									value="삭제" /></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
		</div>
	</form>	
	</div>

</div>
<div class="text-center">
	<ul class="pagination">
	<c:if test="${msg == true}">
		<c:if test="${pageMaker.prev }">
			<li><a href="stock_Management?page=${pageMaker.makeSearch(pageMaker.startPage -1)}">&laquo;</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
			var="idx">
			<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
				<a href="stock_Management${pageMaker.makeSearch(idx)}">${idx}</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a href="stock_Management${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
		</c:if>
	</c:if>
	<c:if test="${msg == false}">
		<c:if test="${pageMaker.prev }"> <!-- stockSearch부분에 주소창에 넘겨받을 값들 다 적혀있음 -->
			<li><a href="searchStock?page=${pageMaker.makeSearch(pageMaker.startPage -1)}">&laquo;</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
			var="idx">
			<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
				<a href="searchStock${pageMaker.makeSearch(idx)}">${idx}</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a href="searchStock${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
		</c:if>
	</c:if>
	</ul>
</div>
<script src="resources/customjs/stockManagement.js"></script>