<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="resources/customcss/headOffice.css" />
<!-- 지점 당 월 매출 TOP 10 -->
<div class="row" style="width:150%; height: 600px; margin-top:-1.3%; margin-left:-1.9%;">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<section class="panel" style="border: 1px solid #D5D5D5; width:100%; height: 500px; overflow-x:hidden; overflow-y:hidden">
				
				<!-- 상단 이름 -->
				<div class="nameBox"  style="width:15%; height:20%; margin-top:1%; margin-left:1%; padding-top:1.2%;">
					<h1 style="text-align:center; font-size:35px;">GradedList TOP10</h1>
				</div>
				
				<!-- 순위표 -->
				<div class="gradedList" style="width:15%; height:64%; margin-left:1%; margin-top:1%; overflow:hidden;">
					<section class="panel" style="width:100%;">
						<table class="table">	
							<tbody class="rankBody">
							</tbody>
						</table>
					</section>
				</div>
				
				<!-- 최근 3개월 해당 매장 매출 그래프 / default 값은 1등 매장 매출 그래프 -->
				<div class="saleGraph" style="width:25%; height:92.5%; margin-top:-21.1%; margin-left:17%;">
					
					<div class="graph" style="width:100%; height:68%;">
						<div id="graph" style="min-width: 100%; height: 100%; margin: 0 auto; margin-top:15%;"></div>
					</div>
					
					<div class="simpleBox" id="avgSale" style="margin-right:0.1%; float:left; width:33%; height:20%; padding-left:10%;">
						<h2>$100,000</h2>
						<p>averageSale</p>	
					</div>
						
					<div class="simpleBox" id="toSale" style="margin-right:0.2%; float:left; width:33%; height:20%; padding-left:10%;">
						<h2>$10,000</h2>
						<p>totalSale</p>
					</div>
					
					<div class="simpleBox" id="visitor" style="margin-right:0.3%; float:left; width:33%; height:20%; padding-left:10%;">
						<h2>1,000</h2>
						<p>visitor</p>
					</div>
					
				</div>
				
				<!-- 해당 매장 방문 나이대 -->
				<div class="mainGuest" style="width:12%; height:92.5%; margin-top:-21%; margin-left:43%;">
						<div class="wordBox" style="margin-left:10%; margin-top:20%;">
							<h3>MainVisitor</h3>
							<p>MainVisitor's Percentage</p>
						</div>
						<div id="femaleGraph" style="min-width: 100%; height: 50%; max-width: 100%; margin-top:15%;"></div>
				</div>
					
				<div class="mainGuest" style="width:12%; height:92.5%; margin-top:-15.3%; margin-left:56.2%;">
					<div id="maleGraph" style="min-width: 100%; height: 50%; max-width: 100%; margin-top:2%;"></div>
				</div>	
			</section>
		</div>
	</div>
</div>

<div class="row" style="width:150%; height: 600px; margin-top:-3%; margin-left:-1.9%;">
	<div class="col-lg-12">
		<section class="panel" style="background: #eeeeee; width:100%; height: 500px; overflow-x:hidden; overflow-y:hidden">
				
			<!-- 지점 검색창 -->
			<div class="gradedList" style="border-top:1px solid #D5D5D5; background-color:#fefefe; border-bottom:1px solid #D5D5D5; width:16%; height:100%; margin-left:1%; margin-top:1%; float:left;">
				<div class="underBar" style="border-bottom:1px solid #D5D5D5; height:15%; text-align:center; "><h3>Branch Office Info</h3></div>
					
				<div class="searchingBar">
					<input class="name" type="text" style="width:80%; border:1px solid #D5D5D5; "/>
					<button class="searchingBtn btn btn-default" style="width:65px; height:30px; margin-top:5px; margin-bottom:5px; font-size:10px;">검색</button>
				</div>
				
				<div class="branchList" style="height:340px;">
					<section class="panel" style="width:100%; height:100%; overflow-y:scroll;">
						<table class="table">	
							<thead>
								<tr>
									<th style="text-align: center;"><i class="icon_profile"></i>
										bhf_code</th>
									<th style="text-align: center;"><i class="icon_pin_alt"></i>
										bhf_nm</th>
									<th style="text-align: center;"><i class="icon_pin_alt"></i>
										bnf_telno</th>
								</tr>
							</thead>
							<tbody class="listBody">		
								<c:forEach items="${ branchList }" var="branch">
									<tr>	
										<td class="bhf_code" style="text-align: center;">${ branch.bhf_code }</td>
										<td style="text-align: center;">${ branch.bhf_nm }</td>
										<td style="text-align: center;">${ branch.bhf_telno }</td>
									</tr>
								</c:forEach>	
							</tbody>
						</table>
					</section>
				</div>
			</div>
				
			<!-- 검색된 지점에 대한 그래프 -->
			<div class="gradedList" style="border-top:1px solid #D5D5D5; background-color:#fefefe; border-bottom:1px solid #D5D5D5; width:48%; height:100%; margin-left:3%; margin-top:1%; float:left;">
				<div class="underBar" style="border-bottom:1px solid #D5D5D5; height:15%; text-align:center;"><h3>Branch Office's Data Graph</h3></div>
				<div id="branchOffice" style="margin-top:4%; height:75%; text-align:center;"></div>
			</div>	
		</section>
	</div>
</div>


<script src="resources/customjs/headOfficeChart.js"></script>