<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/customcss/couponManagement.css" rel="stylesheet" />

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="icon-envelope-l"></i> 쿠폰 관리
		</h3>

		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="icon-envelope-l"></i>Coupon</li>
		</ol>
		<!-- page start -->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel" style="width:60%;">
					<header class="panel-heading"> 쿠폰 등록</header>
					<div class="panel-body">
						<div class="form">
							<form class="form-validate form-horizontal" id="couponForm"
								action="insertCoupon" method="post">
								<div class="form-group">
									<label for="coupon_name" class="control-label col-lg-2">쿠폰
										이름 <span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<input class="form-control" id="coupon_name" name="coupon_nm"
											type="text" style="width: 100%;" required />
										<input type="hidden" id="codes" value="${max_code}">
									</div>

									<label for="coupon_insert" class="control-label col-lg-2">적용
										분류 <span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<select id="selection" class="btn btn-default dropdown-toggle"
											style="width: 100%;">
											<option value="category">상품카테고리별</option>
											<option value="product">물품별</option>
										</select>
									</div>
								</div>

								<div class="form-group ">
									<label for="coupon_info" class="control-label col-lg-2">쿠폰
										내용 <span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<input class="form-control" id="coupon_info"
											name="coupon_cntnts" type="text" style="width: 100%;"
											required />
									</div>


									<div id="c_div">
										<label for="category" class="control-label col-lg-2">카테고리
											<span class="required">*</span>
										</label>
										<div class="col-lg-2">
											<select id="selection"
												class="btn btn-default dropdown-toggle" style="width: 100%;">
												<option>커피</option>
												<option>서적</option>
												<option>유제품</option>
											</select>
										</div>
									</div>

									<div id="p_div">
										<label for="category" class="control-label col-lg-2">적용
											물품 <span class="required">*</span>
										</label>
										<div class="col-lg-3">
											<input type="text" id="selectGoods" class="form-control"
												style="width: 100%;" readonly="readonly">
											<input type="hidden" id="selectGcode">
										</div>
									</div>
								</div>

								<div class="form-group ">
									<label for="coupon_sales" class="control-label col-lg-2">쿠폰
										할인율<span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<input class="form-control" id="coupon_sales"
											name="coupon_dscnt" type="text" style="width: 100%;" required />
									</div>

									<label for="coupon_co" class="control-label col-lg-2">쿠폰
										갯수<span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<input type="text" id="coupon_co" name="coupon_co" class="form-control"
											style="width: 100%;">
									</div>
								</div>

								<div class="form-group ">
									<label for="regDate" class="control-label col-lg-2">쿠폰
										등록 날짜 <span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<input class="form-control" id="regDate"
											name="coupon_begin_de" type="date" style="width: 100%;"
											required />
									</div>
								</div>

								<div class="form-group ">
									<label for="finDate" class="control-label col-lg-2">쿠폰
										종료 날짜 <span class="required">*</span>
									</label>
									<div class="col-lg-3">
										<input class="form-control" id="finDate" name="coupon_end_de"
											type="date" style="width: 100%;" required />
									</div>
								</div>

								<div class="form-group" style="margin-left: 30%;">
									<div class="col-lg-offset-2 col-lg-10">
										<button class="btn btn-primary" id="couponSave" type="submit">Save</button>
										<button class="cancel btn btn-default" id="couponCancel"
											type="button">Cancel</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>

				<section class="panel"
					style="width: 38%; height: 30%; margin-left: 62%; margin-top: -27.7%;">
					<header class="panel-heading"> 물품 리스트</header>
					<div class="panel-body">
						<div class="form">
							<input type="text" id="search" class="form-control">
							<button type="submit" id="searching" class="btn btn-default">검색</button>
							<div class="form-group" style="overflow: scroll; height: 300px;">
								<table class="table table-striped table-advance table-hover">
									<thead>
										<tr>
											<th style="text-align: center;"></th>
											<th style="text-align: center;"><i class="icon_profile"></i>
												Product_no</th>
											<th style="text-align: center;"><i class="icon_pin_alt"></i>
												Product_name</th>
											<th style="text-align: center;"><i class="icon_pin_alt"></i>
												Price(won)</th>
										</tr>
									</thead>

									<tbody id="productList">
										<c:forEach items="${ GoodsList }" var="vo">
											<tr>
												<td style="text-align: center;"></td>
												<td style="text-align: center;">${ vo.goods_code }</td>
												<td style="text-align: center;">${ vo.goods_nm }</td>
												<td style="text-align: center;">${ vo.goods_pc }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</div>

<script src="resources/customjs/couponRegister.js"></script>