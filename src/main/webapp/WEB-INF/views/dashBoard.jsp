<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="resources/customjs/sockjs.js"></script>
<script src="resources/customjs/drawingTile.js"></script>
<script src="resources/customjs/dashBoard.js"></script>
<link href="resources/customcss/tileMapClick.css" rel="stylesheet" />


<style>
#tile_info_avgTime {
	display: none;
}

#selectTile {
	display: none;
}

#tile_info>span {
	display: none
}
</style>

<script>
	var chart;
	var DashDaysock = new SockJS("/scts/echo-ws");
	var bhf_code = "${bhf_code}";

	DashDaysock.onopen = function() {
		console.log('open');
		sendMessage();
	};

	DashDaysock.onmessage = function(event) {
		daySales(event.data);
	};

	DashDaysock.onclose = function(event) {

	};

	function sendMessage() {

		var json = JSON.stringify({
			bhf_code : bhf_code
		});

		DashDaysock.send(json);
	}

	var realTimeSock = new SockJS("/scts/realtime-ws");

	realTimeSock.onopen = function() {

		realTimeSend();
		setInterval(realTimeSend, 1000);
	}

	function realTimeSend() {

		var json = JSON.stringify({
			bhf_code : bhf_code
		});

		realTimeSock.send(json);
	}

	var todayCount;

	realTimeSock.onmessage = function(event) {
		var e_data = event.data;
		e_data = JSON.parse(e_data);

		$("#todayVisitor .count").text(e_data.todayCount);
		$("#todaySales .count").text(e_data.todaySales);
		$("#monthAvgVisitor .count").text(e_data.monthAvgVisitor);
		$("#monthTotalSales .count").text(e_data.monthTotalSales);

		todayCount = e_data.realVisitor;

	}

	realTimeSock.onclose = function(event) {

	}

	var daySales = function(data) {

		data = JSON.parse(data);

		var length = data.result.length;

		if (length <= 0) {
			$("#dayday").text("일주일 간의 매출이 존재하지 않습니다.").css("line-height",
					"400px");

		} else {

			var options = {

				title : {
					text : '일매출'
				},
				subtitle : {
					text : 'Plain'
				},
				xAxis : {
					categories : []
				},
				series : [ {
					type : 'column',
					colorByPoint : true,
					data : [],
					showInLegend : false
				} ]

			}

			for (var i = 0; i < length; i++) {

				options.xAxis.categories[i] = data.result[i].bill_issu_de;
				options.series[0].data[i] = parseInt(data.result[i].totalPrice);

			}

			chart = Highcharts.chart('barChart', options);

		}

	}

	$(document).ready(
			function() {

				highchartTheme();

				Highcharts.setOptions({
					global : {
						useUTC : false
					}
				});

				var options = {
					chart : {
						type : 'spline',
						animation : Highcharts.svg, // don't animate in old IE
						marginRight : 10,
						events : {
							load : function() {

								// set up the updating of the chart each second
								var series = this.series[0];
								setInterval(function() {
									var x = (new Date()).getTime(), // current time
									y = todayCount;
									series.addPoint([ x, y ], true, true);
								}, 1000);
							}
						}
					},
					title : {
						text : '현재 매장 방문자'
					},
					xAxis : {
						type : 'datetime',
						tickPixelInterval : 150
					},
					yAxis : {
						title : {
							text : 'Value'
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					},
					tooltip : {
						formatter : function() {
							return '<b>'
									+ this.series.name
									+ '</b><br/>'
									+ Highcharts.dateFormat(
											'%Y-%m-%d %H:%M:%S', this.x)
									+ '<br/>'
									+ Highcharts.numberFormat(this.y, 2);
						}
					},
					legend : {
						enabled : false
					},
					exporting : {
						enabled : false
					},
					series : [ {
						name : '현재 매장 방문자',
						data : (function() {
							// generate an array of random data
							var data = [], time = (new Date()).getTime(), i;

							for (i = -19; i <= 0; i += 1) {
								data.push({
									x : time + i * 1000,
									y : todayCount
								});
							}
							return data;
						}())
					} ]
				}

				Highcharts.chart('charts', options);

				$('#plain').click(function() {
					chart.update({
						chart : {
							inverted : false,
							polar : false
						},
						subtitle : {
							text : 'Plain'
						}
					});
				});

				$('#polar').click(function() {

					chart.update({
						chart : {
							inverted : false,
							polar : true
						},
						subtitle : {
							text : 'Polar'
						}
					});
				});

				$('#inverted').click(function() {
					chart.update({
						chart : {
							inverted : true,
							polar : false
						},
						subtitle : {
							text : 'Inverted'
						}
					});
				});

			});
</script>
<!-- 이 부분은 일매출, 일 방문자 수 등 보임!!!!!!!!!! -->
<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" id="todayVisitor">
		<div class="info-box blue-bg">
			<i class="fa fa-cloud-download"></i>
			<div class="count">0</div>
			<div class="title">오늘 방문자</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" id="todaySales">
		<div class="info-box orange-bg2">
			<i class="fa fa-shopping-cart"></i>
			<div class="count">0</div>
			<div class="title">오늘 판매량</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" id="monthAvgVisitor">
		<div class="info-box yellow-bg2">
			<i class="fa fa-thumbs-o-up"></i>
			<div class="count">0</div>
			<div class="title">이번달 방문자</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

	<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" id="monthTotalSales">
		<div class="info-box dark-bg">
			<i class="fa fa-cubes"></i>
			<div class="count">0</div>
			<div class="title">이번달 총 판매량</div>
		</div>
		<!--/.info-box-->
	</div>
	<!--/.col-->

</div>

<!-- 하이차트 : 매출 들어갈곳 -->
<div class="row" style="height: 500px;">

	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 일매출 </header>
			<div class="panel-body text-center" id="dayday">
				<div id="barChart" style="width: 500px; height: 400px"></div>
				<button id="plain" class="btn btn-default">Plain</button>
				<button id="inverted" class="btn btn-default">Inverted</button>
				<button id="polar" class="btn btn-default">Polar</button>
			</div>
		</section>
	</div>

	<div class="col-lg-6">
		<section class="panel">
			<header class="panel-heading chartTitle"> 실시간 방문자수 </header>
			<div class="panel-body text-center">
				<div id="charts"></div>
			</div>
		</section>

	</div>


</div>

<!-- 대시보드 -->
<div class="row" style="height: 500px;">
	<div class="col-lg-9 col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>설계도면</strong>
				</h2>
				<div class="panel-actions">
					<button class="btn btn-default" id="zoneType">존 별</button>
					<button class="btn btn-default" id="categoryType">카테고리 별</button>
					<button class="btn btn-default" id="demoType">시연용</button>
					<a href="#" class="btn-setting" id="leftDrawingBtns"><i
						id="leftBtns" class="fa fa-chevron-left" aria-hidden="true"></i></a> <a
						href="#" class="btn-setting" id="rightDrawingBtns"><i
						id="rightBtns" class="fa fa-chevron-right" aria-hidden="true"></i></a>
				</div>
			</div>
			<div class="panel-body-map">
				<input type="hidden" id="tileShowType" value="2"> <input
					type="hidden" id="countStory" value="${ countStory }"> <input
					type="hidden" id="floor" value="0"> <input type="hidden"
					id="drw_code" value="0">
				<div id="blueprint"
					style="height: 380px; text-align: center; position: absolute; z-index: 1;">

					<!-- 
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<p>설계도면 파일을 등록해주세요.</p>
				 -->
					<%-- <img
						src="displayDrawing?fileName=/${ drawingList.get(0).drw_flpth }"
						style="width: 800px; height: 380px;"> --%>

				</div>


				<!-- 전체 타일 영역 잡을 것. 위에 이미지 태그와 겹칠수 있도록 정의해야함 -->
				<div class="tileMap"
					style="position: absolute; width: 800px; height: 380px; z-index: 2;">
					<!-- 타일영역 전체 감싸는 div -->
				</div>
				<!-- 타일영역 전체 감싸는 div -->

			</div>

		</div>
	</div>

	<div class="col-md-3"
		style="background-color: white; width: 350px; height: 417px; right: 20px; border: 1px solid #D5D5D5; text-align: center;">
		<div id="loadTile">
			<div style="margin-bottom: 30px; margin-top: 10px">
				<button class="btn btn-default" id="gender">성별</button>
				<button class="btn btn-default" id="age">연령별</button>
				<button class="btn btn-default" id="visitor" disabled>방문자수</button>
				<select id="duration">
					<option value="0">1일</option>
					<option value="7">1주일</option>
					<option value="30">한달</option>
				</select>
			</div>
		</div>

		<div id="selectTile">
			<div style="margin-bottom: 30px; margin-top: 10px">
				<button class="btn btn-default" id="tileGender">성별</button>
				<button class="btn btn-default" id="tileAge">연령별</button>
				<button class="btn btn-default" id="tileVisitor">방문자수</button>
				<select id="tileDuration">
					<option value="0">1일</option>
					<option value="7">1주일</option>
					<option value="90">3개월</option>
				</select>
			</div>

		</div>

		<div id="tile_info">
			<div id="tile_info_avgTime">
				존 평균 머문 시간 : <span id="avgTime"></span>
			</div>
			<span id="drw_code1"></span> <span id="X_index"></span> <span
				id="Y_index"></span>
		</div>

		<div style="margin-bottom: 20px">
			<span id="tileName">전체</span> <span id="title" data-id="1">성별</span>
			방문율
		</div>

		<div id="tile_graph"
			style="min-width: 300px; height: 200px; max-width: 300px; margin: 0 auto; border: 1px solid black"></div>

	</div>
</div>


