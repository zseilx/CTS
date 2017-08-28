<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="resources/customjs/sockjs.js"></script>


<script src="resources/customjs/posSystem.js"></script>

<style>
.white_content {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0, 0, 0, 0.8);
	opacity: 0;
	-webkit-transition: opacity 400ms ease-in;
	-moz-transition: opacity 400ms ease-in;
	transition: opacity 400ms ease-in;
	pointer-events: none;
}

.white_content:target {
	opacity: 1;
	pointer-events: auto;
}

.white_content>div {
	position: absolute;
	top: 25%;
	left: 25%;
	width: 50%;
	height: 50%;
	padding: 16px;
	border: 16px solid orange;
	background-color: white;
	overflow: auto;
}

td {
	text-align: center;
}
</style>

<script>

var daySalesSocket = new SockJS("/scts/daySales-ws");
var monthSalesSocket = new SockJS("/scts/monthSales-ws");
var productRankSocket = new SockJS("/scts/productRank-ws");
var customerRankSocket = new SockJS("/scts/customerRank-ws");
var sock = new SockJS("/scts/sales-ws");
var DashDaysock = new SockJS("/scts/echo-ws");

function clearText(field){
	if(field.defaultValue == field.value) field.value = '';
	else if(field.value == '') field.value = field.defaultValue;
}

$(document).ready(function(){
	

	var bhf_code = "${bhf_code}";

	$("#modalSearchGoods").click(
			function() {

				var goods_nm = $("#goods_nm").val();

				$.ajax({
					type : "GET",
					url : "searchGoodsList",
					data : {
						goods_nm : goods_nm
					},
					dataType : "json",
					success : function(data) {
						$("#searchGoodsList").empty();

						if (data.length > 0) {

							var length = data.length;

							console.log(data.length);

							for (var i = 0; i < length; i++) {
								console.log(data[i]);
								$("#searchGoodsList").append(
										$("<tr></tr>").addClass("goods").attr(
												"data-id", i));
								$(".goods[data-id=" + i + "]")
										.append(
												$("<td></td>").text(
														data[i].goods_code));
								$(".goods[data-id=" + i + "]").append(
										$("<td></td>").text(data[i].goods_nm));
								$(".goods[data-id=" + i + "]").append(
										$("<td></td>").text(data[i].goods_pc));

								console.log($(".goods[data-id=" + i + "] td"));
							}

						} else {
							$("#searchGoodsList").append(
									$("<tr></tr>").addClass("goods"));
							$(".goods").append(
									$("<td colspan='3'></td>").text(
											"물품 정보가 없습니다"));
						}
					}
				});
			});
	
	$("#settlement").on("change", function(){
		var select = $("#settlement option:selected").val();
		
		if(select == $("#settlement option").length){
			$("#doubleSettle").show();
		}else{
			$("#doubleSettle").hide();
		}
		
	});
	
	$("#settleBtn").click(function(){
		var total = $("#totalAmount").text();
		
		$("#total").text("합계 : " + total + "원");
	});
	
	
	$("#card").on("click", function() {
		var goodsList = new Array();
		var user_id = $("#user_id_payment").val();

		// 추후에 변경 필요
		
		// 현재 그냥 카드로 총금액 결제를 해버림.
		var totalAmount = $("#totalAmount").text();
		
		var setle_mth_code = parseInt( $("#settlement option:selected").val());
		
		$("#goodsList").find(".goodsItem").each(function(i, e) {
			var goods_code = $(this).find('.goods_code').text();
			var coupon_code = $(this).find('.useCoupon_code').val();
			var purchsgoods_qy = $(this).find('.purchsgoods_qy').text();

			var goodsItem = {
					goods_code : goods_code,
					coupon_code : coupon_code,
					purchsgoods_qy : purchsgoods_qy
			}
			goodsList.push(goodsItem);
		});
		
		var sendControllerData;
		var length = $("#settlement option").length;
		
		var setle_mth_code1 = new Array();
		var stprc1 = new Array();
		
		if(setle_mth_code != length){
			
			var stprc = parseInt( $("#totalAmount").text() ); // 결제 금액
		
			setle_mth_code1.push(setle_mth_code);
			stprc1.push(stprc);
			
			
		}else{
			
			
			$(".settlement").each(function(){
			   var a = $(this).find("option:selected").val();
			   var price = $(this).next().val();
			   console.log(price);
			   if(price != ""){
				   setle_mth_code1.push(parseInt(a));
			   }
			   
			});
			
			$(".tprice").each(function(){
				var price = $(this).val();
				
				if(price != "")
				{
					stprc1.push(parseInt(price));
				}
				
			});
			
	
		}
		
		sendControllerData = {
				user_id : user_id,
				setle_mth_code : setle_mth_code1,
				stprc : stprc1,
				goodsList : goodsList
		}
		
		console.log(sendControllerData);

		$.ajax({

			url: "payment",
			type: "post",
			contentType: "application/json",
			async:false,
			data: JSON.stringify({
				user_id : user_id,
				setle_mth_code : setle_mth_code1,
				stprc : stprc1,
				goodsList : goodsList
			}),
			success: function(data){
				//self.location.href = "mainPage";
				//location.reload();
				
				var urlindex = $(location).attr('href').indexOf('#');
				if(urlindex > 0) {
					$(location).attr('href', $(location).attr('href').substr(0,urlindex+1) );
				}
				window.alert("결제완료");
			},
			error: function(data) {
				console.log("에러뜸");
				console.log(data);
			}
		});


		var year = parseInt(new Date().getFullYear());
		var month = new Date().getMonth() + 1;

		if (month < 10) {
			month = "0" + month;
		}

		var mm = new Date().getMonth() - 3;
		if(mm < 10){
			mm = "0" +  mm;
		}


		var month1 = year + "-" + month;
		var date = year + "-" + month;
		var month2 = year + "-" + mm;
		var setle_mth_code = null;

		var sendData = JSON.stringify({
			month1 : month1,
			month2 : month2,
			setle_mth_code : setle_mth_code
		});


		var sockSendData = JSON.stringify({year2 : year, year1 : + (year-4)});
		

		var productRankSendData = JSON.stringify({
			date : date,
			standard : 1
		});
		
		
		
		//customerRankSocket.send();
		
		var json = JSON.stringify({
			bhf_code : bhf_code
		});
		
		DashDaysock.send(json);
		monthSalesSocket.send(sendData);
		daySalesSocket.send('text');
		sock.send(sockSendData);
		productRankSocket.send(productRankSendData);
		

	});

	
});	
	
</script>


<%-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 상품리스트 부분 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ --%>

<div style="width:42%; height:880px; border:1px solid black; background-color:#ffffff;">
	<div class="searching" style="width:90%; height:100px; margin-left:5%; margin-top:3%;">
		<div class="userSearch" style="width:45%; height:70%; margin-left:52%; margin-top:2.3%;">
			<input type="text" value="고객명" onFocus="clearText(this)" onBlur="clearText(this)">
			<button type='submit' class='btn btn-default'>검색</button>
		</div>

		<div class="productSearch" style="width:45%; height:70%; margin-left:3%; margin-top:-10.7%;">
			<input type="text" value="물품명" onFocus="clearText(this)" onBlur="clearText(this)">
			<button type='submit' class="btn btn-default">검색</button>
		</div>
	</div>
	
	<div class="posUser" style="width:90%; height:160px; border:1px solid black; margin-left:5%; margin-top:3%;">
	</div>
	
	<div class="posSaleInfo" style="width:90%; height:300px; margin-left:5%; margin-top:3%;">
		<div id="couponMode">
			<section class="panel">
				<table class="table table-hover" style="overflow: scroll;">
					<thead>
						<tr>
							<th>쿠폰 코드</th>
							<th>쿠폰 이름</th>
							<th>할인율</th>
							<th>사용가능기간</th>
						</tr>
					</thead>
					
					<tbody id="couponList">
					</tbody>
				</table>
			</section>
		</div>
	</div>
	
	<div class="posCaculate" style="width:90%; height:150px; margin-left:5%; margin-top:6%;">
		<section class="panel">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<tr>
							<td>Price</td>
							<td></td>
							<td></td>
							<td id="totalPrice">0</td>
							<td></td>
						</tr>
						<tr>
							<td>Discount</td>
							<td></td>
							<td></td>
							<td id="totalDscnt">0</td>
							<td></td>
						</tr>
						<tr style="height:60px;">
							<td><b style="font-size:25px;">Total</b></td>
							<td></td>
							<td></td>
							<td id="totalAmount"><b style="font-size:25px;">0</b></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
	</div>
</div>

<div class="row" style="width:58%; height:400px; margin-left:42%; margin-top:-51.3%;">
	<div class="col-lg-12">
		<section class="panel">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>상품번호</th>
						<th>제조사</th>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>금액</th>
						<th>할인</th>
					</tr>
				</thead>
				<tbody id="goodsList" style="overflow: scroll;">
				</tbody>
			</table>
		</section>
	</div>
</div>


<%-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 아래 버튼 부분 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ --%>
<div style="border:1px solid black; width:100%; margin-top:50%;">
	<div class="col-lg-4">
		<section class="panel">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<tr>
							<td>바코드</td>
							<td><input type="text" name="goods_code" id="goods_code" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default numberPad" type="button">1</button>
				<button class="btn btn-default numberPad" type="button">2</button>
				<button class="btn btn-default numberPad" type="button">3</button>
				<br>
				<button class="btn btn-default numberPad" type="button">4</button>
				<button class="btn btn-default numberPad" type="button">5</button>
				<button class="btn btn-default numberPad" type="button">6</button>
				<br>
				<button class="btn btn-default numberPad" type="button">7</button>
				<button class="btn btn-default numberPad" type="button">8</button>
				<button class="btn btn-default numberPad" type="button">9</button>
				<br>
				<button class="btn btn-default numberPad" type="button">D</button>
				<button class="btn btn-default numberPad" type="button">0</button>
				<button class="btn btn-default numberPad" type="button">C</button>
				<br>

			</div>
		</section>

	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<button class="btn btn-default" type="button" id="getGoods">등록</button>
				<br>
				<button class="btn btn-default" type="button" id="cancleGoods">취소</button>
				<br>
				<button class="btn btn-default" type="button" id="additionGoods">수량+</button>
				<br>
				<button class="btn btn-default" type="button" id="subtractGoods">수량-</button>
				<br>
			</div>
		</section>
	</div>

	<div class="col-lg-2">
		<section class="panel">
			<div class="panel-body">
				<a href="#searchGoodsOpen"><button class="btn btn-default"
						type="button" id="searchGoods">상품검색</button></a> <br> <a
					href="#couponPointOpen"><button class="btn btn-default"
						type="button" id="couponPoint">쿠폰 포인트</button></a> <br> <a
					href="#cardOpen"><button class="btn btn-default" type="button" id="settleBtn">결제</button></a>
				<!-- <button class="btn btn-default" type="button" id="card">신용카드 결제</button> -->
				<!-- <br>
				<a href="#moneyOpen"><button class="btn btn-default" type="button" id="money">현금 결제</button></a>
				<br>
				<a href="#mixOpen"><button class="btn btn-default" type="button" id="mix">복합 결제</button></a>
				<br> -->
			</div>
		</section>
	</div>
</div>

<div class="white_content modalPanel" id="searchGoodsOpen">
	<div>
		<p>상품검색</p>
		<input type="text" name="goods_nm" id="goods_nm" />
		<button class="btn btn-default" type="button" id="modalSearchGoods">검색</button>
		<h3>물품 정보</h3>
		<section class="panel">

			<table class="table table-hover">
				<thead>
					<tr>
						<th>물품 번호</th>
						<th>물품 이름</th>
						<th>물품 가격</th>
					</tr>

				</thead>
				<tbody id="searchGoodsList">
				</tbody>
			</table>
		</section>

		<br> <a href="#">닫기</a>
	</div>
</div>

<div class="white_content modalPanel" id="couponPointOpen">
	<div>
		<div id="inputMode">
			<p>쿠폰 포인트</p>
			고객아이디 <input type="text" name="user_id" id="user_id" />
			<button class="btn btn-default" type="button" id="getUserCoupon">검색</button>
			<br>
			<div>
					<button class="btn btn-default" type="button" id="useCoupon">등록</button>
					<button class="btn btn-default" type="button" id="exitCouponList">취소</button>
				</div>
		</div>
	</div>
</div>

<div class="white_content modalPanel" id="cardOpen">
	<div class="form-group">
		<h2>결제방법 선택</h2>
		<div class="form-group">
			<select id="settlement" class="form-control">
				<c:forEach items="${list}" var="list">
					<option value="${list.setle_mth_code}">${list.setle_mth_nm}</option>
				</c:forEach>
				<option value="${fn:length(list)+1}">복합결제</option>
			</select>
		</div>

		<div class="form-group">
			<h4>고객아이디</h4>
			<input type="text" name="user_id_payment" class="form-control"
				id="user_id_payment" value="user15" />
		</div>
		

		<div class="form-group" id="doubleSettle" style="display:none;">
			<h2>복합결제</h2>
			<div class="form-group">
				<h4>결제1</h4>
				<select class="settlement" class="form-control">
					<c:forEach items="${list}" var="list">
						<option value="${list.setle_mth_code}">${list.setle_mth_nm}</option>
					</c:forEach>
				</select>
				<input type="number" class="form-control tprice" />원
			</div>
			<div class="form-group">
				<h4>결제2</h4>
				<select class="settlement" class="form-control">
					<c:forEach items="${list}" var="list">
						<option value="${list.setle_mth_code}">${list.setle_mth_nm}</option>
					</c:forEach>
				</select>
				<input type="number" class="form-control tprice" />원
			</div>
			<div class="form-group">
				<h4>결제3</h4>
				<select class="settlement" class="form-control ">
					<c:forEach items="${list}" var="list">
						<option value="${list.setle_mth_code}">${list.setle_mth_nm}</option>
					</c:forEach>
				</select>
				<input type="number" class="form-control tprice" />원
			</div>
		</div>
		<div id="total" style="float:right;">
			
		</div>
		<div>
			<button class="btn btn-default" type="button" id="card">결제</button>
		</div>
		<a href="#">닫기</a>
	</div>
</div>



