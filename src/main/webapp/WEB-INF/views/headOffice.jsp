<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 지점 당 월 매출 TOP 10 -->
<div class="row" style="width:150%; height: 600px; margin-top:-1.3%; margin-left:-1.9%;">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<section class="panel" style="border: 1px solid #D5D5D5; width:100%; height: 500px; overflow-x:hidden; overflow-y:hidden">
				
				<!-- 상단 이름 -->
				<div class="nameBox"  style="width:15%; height:20%; margin-top:1%; margin-left:1%; padding-top:0.5%;">
					<h1 style="text-align:center; font-size:35px;">GradedList TOP10</h1>
				</div>
				
				<!-- 순위표 -->
				<div class="gradedList" style="width:15%; height:67%; margin-left:1%; margin-top:1%;">
					<section class="panel" style="width:100%;">
						<table class="table">	
							<tbody>		
								<tr>
									<td><a class="btn btn-primary">1</a></td>
									<td style="text-align:center; font-size:15px;">This is GradedList</td>
									<td style="text-align:center; font-size:15px;">$100.000</td>
								</tr>
								
								<tr>
									<td><a class="btn btn-default">2</a></td>
									<td style="text-align:center; font-size:15px;">This is GradedList</td>
									<td style="text-align:center; font-size:15px;">$10.000</td>
								</tr>
								
								<tr>
									<td><a class="btn btn-primary">3</a></td>
									<td style="text-align:center; font-size:15px;">This is GradedList</td>
									<td style="text-align:center; font-size:15px;">$1.000</td>
								</tr>
								
								<tr>
									<td><a class="btn btn-default">4</a></td>
									<td style="text-align:center; font-size:15px;">This is GradedList</td>
									<td style="text-align:center; font-size:15px;">$100</td>
								</tr>
								
								<tr>
									<td><a class="btn btn-primary">5</a></td>
									<td style="text-align:center; font-size:15px;">This is GradedList</td>
									<td style="text-align:center; font-size:15px;">$10</td>
								</tr>
								
								<tr>
									<td><a class="btn btn-default">6</a></td>
									<td style="text-align:center; font-size:15px;">This is GradedList</td>
									<td style="text-align:center; font-size:15px;">$0</td>
								</tr>
								
							</tbody>
						</table>
					</section>
				</div>
				
				<!-- 최근 3개월 해당 매장 매출 그래프 / default 값은 1등 매장 매출 그래프 -->
				<div class="saleGraph" style="width:25%; height:92.5%; margin-top:-21.1%; margin-left:17%;">
					
					<div class="graph" style="width:100%; height:68%;">
						<div id="graph" style="min-width: 100%; height: 100%; margin: 0 auto; margin-top:15%;"></div>
					</div>
					
					<div class="simpleBox" style="margin-right:0.1%; float:left; width:33%; height:20%; padding-left:10%;">
						<h2>$100,000</h2>
						<p>averageSale</p>	
					</div>
						
					<div class="simpleBox" style="margin-right:0.2%; float:left; width:33%; height:20%; padding-left:10%;">
						<h2>$10,000</h2>
						<p>totalSale</p>
					</div>
					
					<div class="simpleBox" style="margin-right:0.3%; float:left; width:33%; height:20%; padding-left:10%;">
						<h2>1,000</h2>
						<p>visitor</p>
					</div>
					
				</div>
				
				<!-- 해당 매장 방문 나이대 -->
				<div class="mainGuest" style="width:12%; height:92.5%; margin-top:-21%; margin-left:43%;">
					<div id="femaleGraph" style="min-width: 100%; height: 50%; max-width: 100%; margin-top:35%;"></div>
				</div>
				
				<div class="mainGuest" style="width:12%; height:92.5%; margin-top:-19.3%; margin-left:56.2%;">
					<div id="maleGraph" style="min-width: 100%; height: 50%; max-width: 100%; margin-top:2%;"></div>
				</div>		
			</section>
		</div>
	</div>
</div>

<script src="resources/customjs/headOfficeChart.js"></script>