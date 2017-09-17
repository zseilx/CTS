<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="resources/img/favicon.png">

<title>메인페이지</title>

<!-- Bootstrap CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="resources/css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="resources/css/elegant-icons-style.css" rel="stylesheet" />
<link href="resources/css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->

<link href='resources/eventCalendar/fullcalendar.min.css'
	rel='stylesheet' />

<!-- easy pie chart-->
<link
	href="resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="stylesheet" href="resources/css/owl.carousel.css"
	type="text/css">
<link href="resources/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link href="resources/css/widgets.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/style-responsive.css" rel="stylesheet" />
<link href="resources/css/xcharts.min.css" rel=" stylesheet">
<link href="resources/css/jquery-ui-1.10.4.min.css" rel="stylesheet">

<!-- @@@@@@@@@@@@@@@@@@@@@ 직접만든 css들 전부 여기사이에 넣을 것!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<!-- @@@@@@@@@@@@@@@@@@@@@ 직접만든 css들 전부 여기다 넣을 것!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<link href="resources/css/couponManagement.css" rel="stylesheet">
<link href="resources/css/stockManagement.css" rel=" stylesheet">

<!-- @@@@@@@@@@@@@@@@@@@@@ 직접만든 css들 전부 여기다 넣을 것!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<!-- @@@@@@@@@@@@@@@@@@@@@ 직접만든 css들 전부 여기다 넣을 것!!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->


<!-- javascripts -->
<script src="resources/js/jquery.js"></script>
<script src="resources/js/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
<!-- bootstrap -->
<script src="resources/js/bootstrap.min.js"></script>
<!-- nice scroll -->
<script src="resources/js/jquery.scrollTo.min.js"></script>
<script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
<!-- charts scripts -->
<script src="resources/assets/jquery-knob/js/jquery.knob.js"></script>
<script src="resources/js/jquery.sparkline.js" type="text/javascript"></script>
<script
	src="resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="resources/js/owl.carousel.js"></script>
<script src="resources/js/handlebars-v4.0.10.js"></script>


<!-- jQuery full calendar -->
<script src='resources/eventCalendar/lib/moment.min.js'></script>
<script src='resources/eventCalendar/fullcalendar.js'></script>
<script src='resources/eventCalendar/locale/ko.js'></script>


<script src="resources/js/jquery.rateit.min.js"></script>
<!-- custom select -->
<script src="resources/js/jquery.customSelect.min.js"></script>
<script src="resources/assets/chart-master/Chart.js"></script>

<!--custom script for all page-->
<script src="resources/js/scripts.js"></script>
<!-- custom script for this page-->
<script src="resources/js/sparkline-chart.js"></script>
<script src="resources/js/easy-pie-chart.js"></script>
<script src="resources/js/jquery-jvectormap-1.2.2.min.js"></script>
<script src="resources/js/jquery-jvectormap-world-mill-en.js"></script>
<script src="resources/js/xcharts.min.js"></script>
<script src="resources/js/jquery.autosize.min.js"></script>
<script src="resources/js/jquery.placeholder.min.js"></script>
<script src="resources/js/gdp-data.js"></script>
<script src="resources/js/morris.min.js"></script>
<script src="resources/js/sparklines.js"></script>
<script src="resources/js/charts.js"></script>
<script src="resources/js/jquery.slimscroll.min.js"></script>

<!-- HighChart -->

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>

<script src="resources/customjs/highChartTheme.js"></script>

<!-- webSocket -->
<script src="resources/customjs/sockjs.js"></script>

<style>
#detailEvent {
	display: none;
}
</style>

<script>

var DashDaysock = new SockJS("/scts/echo-ws");

	var bhf_code = "${bhf_code}";
	$(document)
			.ready(
					function() {


						if (bhf_code != "1") {
							deliveryNoti();
						}

						$
								.ajax({
									url : "notification",
									type : "GET",
									data : {
										reciever : bhf_code
									},
									async:false,
									dataType : "json",
									success : function(data) {

										console.log(data);

										var noti = $(".notification");

										noti.children().remove();

										var length = data.result.length;

										noti
												.append($("<div class='notify-arrow notify-arrow-blue'></div>"));

										$("#notiCnt").text(data.notiCnt);

										if (length <= 0) {
											noti.append($("<li></li>").append(
													$("<p></p>").addClass(
															"blue").text(
															"알림이 없습니다.")));
										} else {
											noti.append($("<li></li>").append(
													$("<p></p>").addClass(
															"blue").text(
															"알림이 있습니다.")));

											for (var i = 0; i < length; i++) {

												noti
														.append($("<li></li>")
																.attr(
																		"data-id",
																		data.result[i].ntcn_code)
																.attr(
																		"bbsctt_code",
																		data.result[i].bbsctt_code)
																.attr("event_begin_de", data.result[i].event_begin_de)
																.append(
																		$(
																				"<a></a>")
																				.attr(
																						"href",
																						"#")
																				.text(
																						data.result[i].bbsctt_sj)));
												$(
														"li[data-id="
																+ data.result[i].ntcn_code
																+ "] a")
														.append(
																$(
																		"<span></span>")
																		.addClass(
																				"small italic pull-right")
																		.text(
																				data.result[i].dateCha
																						+ " days"));

												if (i == 4) {
													break;
												}

											}

										}
									}

								});
						

						$(document)
								.on(
										"click",
										".notification li",
										function() {

											var nctn_code = $(this).attr(
													"data-id");

											var bbsctt_code = $(this).attr(
													"bbsctt_code");

											$
													.ajax({

														url : "notiEventDetail",
														type : "GET",
														data : {
															nctn_code : nctn_code,
															bbsctt_code : bbsctt_code,
															reciever : bhf_code
														},
														dataType : "json",
														success : function(data) {

															var length = data.result.length;

															$("#notiCnt")
																	.text(
																			data.notiCnt);

															for (var i = 0; i < length; i++) {
																$(
																		"#detailEvent #bbsctt_sj")
																		.text(
																				data.result[i].bbsctt_sj);
																$(
																		"#detailEvent #event_begin_de")
																		.text(
																				data.result[i].event_begin_de);
																$(
																		"#detailEvent #event_end_de")
																		.text(
																				data.result[i].event_begin_de);
																$(
																		"#detailEvent #bbsctt_cn")
																		.text(
																				data.result[i].bbsctt_cn);

															}

															$("#detailEvent")
																	.modal();

														}

													});

										});

						$(".closeModal").click(function() {
							$(".modal-layout").modal('hide');
						});

						if (bhf_code != "1") {
							setInterval(deliveryNoti, 3000);
						}
	
						var notifi = "${noti}";
						
						if(notifi == "1"){
							notiAlert();	
						}
						

					});

	function deliveryNoti() {

		var bhf_code = "${bhf_code}"

		$.ajax({
			url : "deliveryNoti",
			type : "GET",
			data : {
				bhf_code : bhf_code
			},
			dataType : "json",
			success : function(data) {
				var length = data.delivery.length;

				var inbox = $(".inbox");

				inbox.children().remove();

				var inboxCnt = parseInt($(".inboxCnt").text());

				if (length > inboxCnt) {
					setTimeout(function() {
						$(".header").append($("<div id='notiDiv'></div>"));
						$("#notiDiv").css("position", "absolute");
						$("#notiDiv").css("z-index", "1000");
						$("#notiDiv").css("text-align", "center");
						$("#notiDiv").css("background-color", "#FAECC5");
						$("#notiDiv").css("width", "500px");
						$("#notiDiv").html("<h3>새로운 배송 주문이 왔습니다.</h3>");
						$("#notiDiv").css("color", "black");
						$("#notiDiv").css("right", "100px");
						$("#notiDiv").show();
					}, 500);
					

					var json = JSON.stringify({
						 bhf_code : bhf_code
					});
					
					DashDaysock.send(json);

				}

				setTimeout(function() {

					$("#notiDiv").hide();

				}, 4000);

				$(".inboxCnt").text(length);

				if (length <= 0) {
					inbox
							.append($("<li></li>").append(
									$("<p></p>").addClass("blue").text(
											"배송 목록이 없습니다.")));
				} else {
					inbox
							.append($("<li></li>").append(
									$("<p></p>").addClass("blue").text(
											"배송 목록이 있습니다.")));
				}

				for (var i = 0; i < length; i++) {

					inbox
							.append($("<li></li>").attr("data-id",
									data.delivery[i].bill_code).append(
									$("<a></a>").attr("href", "#").text(
											data.delivery[i].user_id
													+ " 배송 주문하였습니다.")));

					if (i == 4) {
						break;
					}

				}
			

			}
		});

	}
	
	
	function notiAlert(){
		var tf = false;
		$(".notification li").each(function(){
			
			if($(this).is("[event_begin_de]") == true){
				
				var event_begin_de = $(this).attr("event_begin_de").split(" ")[0];
				event_begin_de = new Date(event_begin_de);
				date = new Date();

				if(event_begin_de >= date){
					tf = true;
				}
				
			}
		
			
		});
		
		if(tf == true){
			setTimeout(function() {
				$(".header").append($("<div id='notiDiv'></div>"));
				$("#notiDiv").css("position", "absolute");
				$("#notiDiv").css("z-index", "1000");
				$("#notiDiv").css("text-align", "center");
				$("#notiDiv").css("background-color", "#FAECC5");
				$("#notiDiv").css("width", "500px");
				$("#notiDiv").html("<h3>새로운 알림이 왔습니다.</h3>");
				$("#notiDiv").css("color", "black");
				$("#notiDiv").css("right", "100px");
				$("#notiDiv").show();
			}, 1000);

		}

		setTimeout(function() {

			$("#notiDiv").hide();

		}, 2000);
	}
	

</script>

</head>

<body>
	<!-- container section start -->
	<section id="container" class="">


		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>

			<!--logo start-->
			<a href="mainPage" class="logo">Team8 CTS <span class="lite">${ bhf_nm }
					지점</span></a>
			<!--logo end-->

			<div class="nav search-row" id="top_menu">
				<!--  search form start -->
				<ul class="nav top-menu">
					<li>
						<form class="navbar-form"></form>
					</li>
				</ul>
				<!--  search form end -->
			</div>

			<div class="top-nav notification-row">
				<!-- notificatoin dropdown start-->
				<ul class="nav pull-right top-menu">

					<!-- inbox notificatoin start-->
					<c:choose>
						<c:when test="${bhf_code != 1}">
							<li id="mail_notificatoin_bar" class="dropdown"><a
								data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
									class="fa fa-truck"></i> <span
									class="badge bg-important inboxCnt">${delivery}</span>
							</a>
						</c:when>
					</c:choose>
					<ul class="dropdown-menu extended inbox">

					</ul>
					</li>
					<!-- inbox notificatoin end -->
					<!-- alert notification start-->
					<li id="alert_notificatoin_bar" class="dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
							class="icon-bell-l"></i> <span class="badge bg-important"
							id="notiCnt">0</span>
					</a>
						<ul class="dropdown-menu extended notification">

						</ul></li>
					<!-- alert notification end-->





					<!-- user login dropdown start-->
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <span class="profile-ava">
								<img alt="" src="resources/img/avatar1_small.jpg">
						</span> <span class="username">${ user_id }</span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<li class="eborder-top"><a href="myProfile"><i
									class="icon_profile"></i> My Profile</a></li>
							<li><a href="login"><i class="icon_key_alt"></i> Log Out</a>
							</li>
						</ul></li>
					<!-- user login dropdown end -->
				</ul>
				<!-- notificatoin dropdown end-->
			</div>
		</header>
		<!--header end-->



		<aside>
			<c:choose>
				<c:when test="${bhf_code == 1}">
					<div id="sidebar" class="nav-collapse ">
						<!-- sidebar menu start-->
						<ul class="sidebar-menu">
							<li><a class="" href="headOfficeMain"> <i
									class="fa fa-home" aria-hidden="true"></i><span>Home
										Main</span></a></li>

							<li><a class="" href="product_List"> <i
									class="fa fa-tags" aria-hidden="true"></i> <span>물품 관리</span>
							</a></li>

							<li><a class="" href="coupon_Management"> <i
									class="icon_piechart"></i> <span>쿠폰 관리</span>
							</a></li>

							<li><a class="" href="event_Management"> <i
									class="fa fa-calendar" aria-hidden="true"></i> <span>이벤트
										관리</span>
							</a></li>

			

						</ul>
						<!-- sidebar menu end-->
					</div>
				</c:when>

				<c:when test="${bhf_code != 1}">
					<div id="sidebar" class="nav-collapse ">
						<ul class="sidebar-menu">
							<li class="active"><a class="" href="mainPage"> <i
									class="icon_house_alt"></i> <span>Dashboard</span>
							</a></li>
							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_document_alt"></i> <span>매장 관리</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub">
									<li><a class="" href="shop_Register">매장 등록</a></li>
									<li><a class="" href="sales_Management">매출 관리</a></li>
									<li><a class="" href="stock_Management">재고 관리</a></li>
								</ul></li>

							<li><a class="" href="event_Management"> <i
									class="fa fa-calendar" aria-hidden="true"></i> <span>이벤트
										관리</span>
							</a></li>

							<li><a class="" href="coupon_Management"> <i
									class="icon_piechart"></i> <span>쿠폰 관리</span>

							</a></li>

							<li><a class="" href="posSystem"> <i
									class="icon_piechart"></i> <span>포스</span>

							</a></li>
							
							<li><a class="" href="delivery_Management"> <i
									class="fa fa-truck"></i> <span>배송</span>
							</a></li>

							<li><a class="" href="help_List"> <i
									class="fa fa-question"></i> <span>문의 사항</span>
							</a></li>
							
							

						</ul>
					</div>
				</c:when>
			</c:choose>
			<!-- sidebar menu end-->
		</aside>
		<!--sidebar end-->


		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기위로는 공통으로 들어감 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기위로는 공통으로 들어감 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기위로는 공통으로 들어감 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기위로는 공통으로 들어감 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기위로는 공통으로 들어감 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기위로는 공통으로 들어감 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->

		<section id="main-content" style="overflow-x: hidden; overflow-y:hidden;">
			<section class="wrapper">
				<c:if test="${ main_content != null }">
					<jsp:include page="${ main_content }.jsp" />
				</c:if>
			</section>
		</section>

	</section>
	<!-- container section start -->

	<div id="detailEvent" class="modal-layout"
		style="width: 55%; height: 310px">
		<div>
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 이벤트 </header>
					<div class="panel-body">
						<div class="form">
							<div class="form-group">
								<label for="name" class="control-label col-lg-3">이벤트명 <span
									class="required">*</span>
								</label>
								<div id="bbsctt_sj"></div>

							</div>

							<div class="form-group ">

								<label for="price" class="control-label col-lg-3"> 이벤트
									시작일자 <span class="required">*</span>
								</label>
								<div id="event_begin_de"></div>
							</div>

							<div class="form-group ">

								<label for="amount" class="control-label col-lg-3"> 이벤트
									종료일자 <span class="required">*</span>
								</label>
								<div id="event_end_de"></div>


							</div>


							<div class="form-group ">
								<label for="ccomment" class="control-label col-lg-3">이벤트
									설명</label>

								<div id="bbsctt_cn"></div>

							</div>

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button class="btn btn-default closeModal">닫기</button>
								</div>
							</div>

						</div>

					</div>
				</section>
			</div>
		</div>
	</div>
	<form class="formObj"></form>



	<script>
		var eventSocket = new SockJS("/scts/event-ws");

		eventSocket.onmessage = function(event) {
			var data = event.data;
			data = JSON.parse(data);

			var reciever = "${bhf_code}";

			if (data.eventNotification[0].reciever == reciever) {

				var noti = $(".notification");

				noti.children().remove();

				var length = data.eventNotification.length;

				noti
						.append($("<div class='notify-arrow notify-arrow-blue'></div>"));

				var notiCount = data.notiCnt;
				var notiCnt = parseInt($("#notiCnt").text());

				if (notiCount > notiCnt) {
					setTimeout(function() {
						$(".header").append($("<div id='notiDiv'></div>"));
						$("#notiDiv").css("position", "absolute");
						$("#notiDiv").css("z-index", "1000");
						$("#notiDiv").css("text-align", "center");
						$("#notiDiv").css("background-color", "#FAECC5");
						$("#notiDiv").css("width", "500px");
						$("#notiDiv").html("<h3>새로운 알림이 왔습니다.</h3>");
						$("#notiDiv").css("color", "black");
						$("#notiDiv").css("right", "100px");
						$("#notiDiv").show();
					}, 1000);

				}

				setTimeout(function() {

					$("#notiDiv").hide();

				}, 2000);

				$("#notiCnt").text(data.notiCnt);

				if (length <= 0) {
					noti.append($("<li></li>").append(
							$("<p></p>").addClass("blue").text("알림이 없습니다.")));
				} else {
					noti.append($("<li></li>").append(
							$("<p></p>").addClass("blue").text("알림이 있습니다.")));
				}

				for (var i = 0; i < length; i++) {

					noti.append($("<li></li>").attr("data-id",
							data.eventNotification[i].ntcn_code).attr(
							"bbsctt_code",
							data.eventNotification[i].bbsctt_code).attr("event_begin_de", data.eventNotification[i].event_begin_de).append(
							$("<a></a>").attr("href", "#").text(
									data.eventNotification[i].bbsctt_sj)));
					$(
							"li[data-id=" + data.eventNotification[i].ntcn_code
									+ "] a").append(
							$("<span></span>").addClass(
									"small italic pull-right")
									.text(
											data.eventNotification[i].dateCha
													+ " days"));

					if (i == 4) {
						break;
					}

				}
			}

		}

		$(document)
				.on(
						"click",
						".inbox li",
						function() {

							var bill_code = $(this).attr("data-id");

							$('.formObj')
									.append(
											"<input type='hidden' name='bill_code' value='"+ bill_code +"'/>");
							$(".formObj").attr("action", "delivery_detail");
							$(".formObj").attr("method", "post");
							$(".formObj").submit();

						});
						

	</script>

</body>
</html>
