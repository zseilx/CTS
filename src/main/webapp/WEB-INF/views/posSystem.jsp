<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
</style>

<%-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 상품리스트 부분 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ --%>
<div class="row" style="height: 400px;">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading"> 상품 리스트 </header>
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
<div class="row">
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

		<section class="panel">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<tr>
							<td>소계</td>
							<td id="totalPrice">0</td>
						</tr>
						<tr>
							<td>할인</td>
							<td id="totalDscnt">0</td>
						</tr>
						<tr>
							<td>합계</td>
							<td id="totalAmount">0</td>
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
					href="#cardOpen"><button class="btn btn-default" type="button">결제</button></a>
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
		<button class="btn btn-default" type="button" id="getGoods">검색</button>
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
		</div>

		<div id="couponMode">
			<section class="panel">
				<table class="table table-hover" style="overflow: scroll;">
					<thead>
						<tr>
							<th>적용가능상품</th>
							<th>쿠폰이름</th>
							<th>할인율</th>
							<th>사용가능기간</th>
						</tr>
					</thead>
					<tbody id="couponList">
					</tbody>
				</table>
				<div>
					<button class="btn btn-default" type="button" id="useCoupon">등록</button>
					<button class="btn btn-default" type="button" id="exitCouponList">
						<a href="#">취소</a>
					</button>
				</div>
			</section>
		</div>
	</div>
</div>

<div class="white_content modalPanel" id="cardOpen">
	<div>
		<p>결제</p>
		<select id="settlement">
			<option value="1">카드</option>
			<option value="2">현금</option>
			<option value="3">복합결제</option>
		</select> 
		<br /> 
		고객아이디 <input type="text" name="user_id_payment" id="user_id_payment"
			value="user15" />
		<button class="btn btn-default" type="button" id="card">결제</button>
		<a href="#">닫기</a>
	</div>
</div>

<div class="white_content modalPanel" id="moneyOpen">
	<div>
		<p>현금 결제</p>
		<a href="#">닫기</a>
	</div>
</div>

<div class="white_content modalPanel" id="mixOpen">
	<div>
		<p>복합 결제</p>
		<a href="#">닫기</a>
	</div>
</div>