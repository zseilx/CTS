<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> -->



<script>

/**shop_Register.js 와
 * dashBoard.js에서 사용
 * 도면 이미지를 페이지에 뿌려줌
 */


var bhf_code = "${bhf_code}";

var imgLoad = function(floor) {
	//var countStory = $("#countStory").val();
	//var floor = $("#floor").val();

	console.log(floor);
	
	$.ajax({
		url: "getDrawingFileName",
		type: "get",
		data: {
			floor : floor,
			bhf_code : bhf_code
		},
		dataType: "json",
		success: function(data) {

			if(data != null) {
				$('#blueprint').empty();

				var drawingImg = $('<img src="displayDrawing?fileName=/' + data.drw_flpth + '" style="width: 800px; height: 380px;">');
				drawingImg.appendTo($('#blueprint'));

				$("#drw_code").val(data.drw_code);

				var tileMap = $(".tileMap");

				tileMap.empty();

				// 해당 층의 설정된 타일 갯수까지 가져올 수 있어야 함.
				for(var i=0; i<data.size_y; i++) {
					var tileRow = $("<div></div>");

					//tileRow.css("width", "100%");
					//tileRow.css("height", heightSize + "%");

					for(var j=0; j<data.size_x; j++) {
						var tileItem = $("<div></div>").addClass("tile");
						//tileItem.css("width", widthSize + "%");
						//tileItem.css("height", "100%");
						//tileItem.css("float", "left");

						tileItem.appendTo(tileRow);
					}
					tileRow.appendTo(tileMap);
				}
				var heightSize = 100 / data.size_y;
				var widthSize = 100 / data.size_x;
				$(".tileMap > div").css("width", "100%").css("height", heightSize + "%");
				$(".tile").css("width", widthSize + "%").css("height", "100%").css("float", "left");


				/* 타일별로 색깔 저장 해주는 것 */
				var tileInfoList = data.tileInfoList;
				var grade = tileInfoList.length / 3;


				for(var i=0; i<tileInfoList.length; i++) {
					var info = tileInfoList[i];

					var x = info.TILE_CRDNT_X;
					var y = info.TILE_CRDNT_Y;

					var row = $("div.tileMap > div").eq(x);
					var col = row.find("div.tile").eq(y);

					var alpha = 0.05;
					/*if(info.probability > 0.4) {
			alpha = 0.5;
		}*/
					if(i < grade) {
						alpha = 0.8;
					}
					else if(i < grade*2) {
						alpha = 0.5;
					}
					else {
						alpha = 0.2
					}


					
					//col.text( info.DETAILCTGRY_NM+ ( info.probability*100) + "%" );
					//col.text( (info.probability*100) + "%" );
					col.empty();

					$("<p></p>").text( info.DETAILCTGRY_NM ).appendTo(col);
					$("<p></p>").text( (info.probability*100) + "%" ).appendTo(col);

					//col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, alpha));
					col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.2));
					
					//col.css("background-color", "#" + info.LCLASCTGRY_COLOR);
					//col.css("opacity", 0.1 + (info.probability * 0.5));
					//col.css("opacity", alpha);

				}


			}

			else {
				window.alert("도면이 없습니다. 등록해주세요");
			}
		},
		error: function(data) {
			console.log("아작스 에러남");
		}
	});
};


function loadTile(floor){

	$.ajax({
		url: "getDrawingFileName",
		type: "post",
		data: {
			floor : floor
		},
		dataType: "json",
		success: function(data) {

			if(data != null) {

/*				var tileMap = $(".tileMap");

				tileMap.empty();

				// 해당 층의 설정된 타일 갯수까지 가져올 수 있어야 함.
				for(var i=0; i<data.size_y; i++) {
					var tileRow = $("<div></div>");

					//tileRow.css("width", "100%");
					//tileRow.css("height", heightSize + "%");

					for(var j=0; j<data.size_x; j++) {
						var tileItem = $("<div></div>").addClass("tile");
						//tileItem.css("width", widthSize + "%");
						//tileItem.css("height", "100%");
						//tileItem.css("float", "left");

						tileItem.appendTo(tileRow);
					}
					tileRow.appendTo(tileMap);
				}
				var heightSize = 100 / data.size_y;
				var widthSize = 100 / data.size_x;
				$(".tileMap > div").css("width", "100%").css("height", heightSize + "%");
				$(".tile").css("width", widthSize + "%").css("height", "100%").css("float", "left");
*/

				/* 타일별로 색깔 저장 해주는 것 */
				var tileInfoList = data.tileInfoList;
				var grade = tileInfoList.length / 3;


				for(var i=0; i<tileInfoList.length; i++) {
					var info = tileInfoList[i];

					var x = info.TILE_CRDNT_X;
					var y = info.TILE_CRDNT_Y;

					var row = $("div.tileMap > div").eq(x);
					var col = row.find("div.tile").eq(y);

					var alpha = 0.05;
					/*if(info.probability > 0.4) {
			alpha = 0.5;
		}*/
					if(i < grade) {
						alpha = 0.8;
					}
					else if(i < grade*2) {
						alpha = 0.5;
					}
					else {
						alpha = 0.2
					}

					//col.text( info.DETAILCTGRY_NM+ ( info.probability*100) + "%" );
					//col.text( (info.probability*100) + "%" );
					col.empty();
					$("<p></p>").text( info.DETAILCTGRY_NM ).appendTo(col);
					$("<p></p>").text( (info.probability*100) + "%" ).appendTo(col);
					
					//col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, alpha));
					col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.2));
					
					//col.css("background-color", "#" + info.LCLASCTGRY_COLOR);
					//col.css("opacity", 0.1 + (info.probability * 0.5));
					//col.css("opacity", alpha);
				}
				
				// 타일에 현재 사람 있을때 색깔 변경해주기 (시연용)
				var testTileColor = data.testTileColor;
				for(var i=0; i<testTileColor.length; i++) {

					var tile = testTileColor[i];
					
					var x = tile.TILE_CRDNT_X;
					var y = tile.TILE_CRDNT_Y;

					var row = $("div.tileMap > div").eq(x);
					var col = row.find("div.tile").eq(y);

					var str = col.css("background-color").replace("0.2", "0.8");
					col.css("background-color", str);
					//col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.8));
				}
				//$("#floor").val(floor+1);

			}
		}

	});
}

var hexToRgbA = function(hex, alpha){
    var c;
    if(/^#([A-Fa-f0-9]{3}){1,2}$/.test(hex)){
        c= hex.substring(1).split('');
        if(c.length== 3){
            c= [c[0], c[0], c[1], c[1], c[2], c[2]];
        }
        c= '0x'+c.join('');
        return 'rgba('+[(c>>16)&255, (c>>8)&255, c&255].join(',')+',' + alpha + ')';
    }
    throw new Error('Bad Hex');
}

</script>



<script src="resources/customjs/shop_Register.js"></script>
<link href="resources/customcss/tileMapClick.css" rel="stylesheet" />

<style>
/* The Modal (background) */
.modal, .tileModal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 220px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-row {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding-top: 16px;
	width: 53%;
	height: 66%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

.tileModal-row {
	position: relative;
	background-color: #fefefe;
	margin: auto;
	padding-top: 16px;
	width: 43%;
	height: 40%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	-webkit-animation-name: animatetop;
	-webkit-animation-duration: 0.4s;
	animation-name: animatetop;
	animation-duration: 0.4s
}

/* Add Animation */
@
-webkit-keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
@
keyframes animatetop {
	from {top: -300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}
.panel-heading {
	border: 1px solid #D5D5D5;
}
</style>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">
			<i class="fa fa fa-bars"></i> 매장 등록
		</h3>
		<ol class="breadcrumb">
			<li><i class="fa fa-home"></i><a href="index">Home</a></li>
			<li><i class="fa fa-bars"></i>Management</li>
		</ol>
	</div>
</div>

<!-- page start-->
<div class="row">
	<div class="col-lg-9 col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>설계도면</strong>
				</h2>
				<div class="panel-actions">
					<a href="#" class="btn-setting" id="leftDrawingBtns"><i
						id="leftBtns" class="fa fa-chevron-left" aria-hidden="true"></i></a> <a
						href="#" class="btn-setting" id="rightDrawingBtns"><i
						id="rightBtns" class="fa fa-chevron-right" aria-hidden="true"></i></a>
					<a href="#" class="btn-setting" id="MyBtn"><i id="MyBtn"
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<div class="panel-body-map">
				<input type="hidden" id="countStory" value="${ countStory }">
				<input type="hidden" id="floor" value="0"> <input
					type="hidden" id="drw_code" value="0">
				<div id="blueprint"
					style="height: 380px; text-align: center; position: absolute; z-index: 1;">
					<!-- 
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<p>설계도면 파일을 등록해주세요.</p>
				 -->
					<%-- 
					<img
					
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
		style="background-color: white; width: 340px; height: 417px; position: absolute; top: 230px; left: 78%; border: 1px solid #D5D5D5; text-align: center;">
		
		<div id="tile_info"></div>
		
	</div>
</div>

<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>존 리스트</strong>
				</h2>

				<div class="panel-actions">
					<a href="#" class="btn-setting" id="tileBtn"><i id="tileBtn"
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<section class="panel col-lg-12"
				style="overflow: scroll; height: 430px;">

				<table class="table table-striped table-advance table-hover">
					<tbody>
						<tr>
							<th style="text-align: center;"><i class="fa fa-square"></i>
								타일명</th>
							<th style="text-align: center;"><i class="fa fa-clock-o"></i>
								X축</th>
							<th style="text-align: center;"><i class="fa fa-users"></i>
								Y축</th>
							<th style="text-align: center;"><i class="fa fa-users"></i>
								비콘설정</th>
						</tr>


						<c:forEach items="${ tileList }" var="vo">
							<tr>
								<td style="text-align: center;">${ vo.get("TILE_NM") }</td>
								<td style="text-align: center;">${ vo.get("TILE_CRDNT_X") }</td>
								<td style="text-align: center;">${ vo.get("TILE_CRDNT_Y") }</td>
								<td style="text-align: center;">${ vo.get("beaconset") }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</div>
	</div>

	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-map-marker red"></i><strong>비콘 리스트</strong>
				</h2>

				<div class="panel-actions">
					<a href="#" class="btn-setting" id="tileBtn"><i id="tileBtn"
						class="fa fa-plus" aria-hidden="true"></i></a>
				</div>
			</div>
			<section class="panel col-lg-12"
				style="overflow: scroll; height: 430px;">

				<table class="table table-striped table-advance table-hover">
					<tbody>
						<tr>
							<th style="text-align: center;"><i
								class="fa fa-check-square-o"></i> 메이저</th>
							<th style="text-align: center;"><i
								class="fa fa-check-square-o"></i> 마이너</th>
							<th style="text-align: center;"><i class="fa fa-flag"></i>
								상태</th>
						</tr>

						<c:forEach items="${ beaconList }" var="beacon">
							<tr>
								<td style="text-align: center;">${ beacon.beacon_mjr }</td>
								<td style="text-align: center;">${ beacon.beacon_mnr }</td>
								<td style="text-align: center;">${ beacon.beacon_sttus }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</div>
	</div>
</div>

<div id="Mymodal" class="modal" style="z-index: 3;">
	<div class="modal-row">
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading"> 설계도면 등록 </header>
				<div class="panel-body">
					<div class="form">
						<form class="form-validate form-horizontal" id="feedback_form"
							method="post" action="shop_RegisterForm"
							enctype="multipart/form-data">

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">지점코드 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<!-- <input class="form-control" id="name" name="bhf_code"
										type="text" required /> -->
									<select name="bhf_code" id="bhf_code">
										<option value="">지점선택</option>
									</select>
								</div>

							</div>

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">층정보 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<!-- <input class="form-control" id="level_info"
										name="floorinfo_floor" type="text" required /> -->
									<select name="floorinfo_floor" id="floorinfo_floor">
										<option value="">층선택</option>
									</select>
								</div>
							</div>

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">타일 XY<span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<input type="number" name="size_x" required value="3" min="2"
										max="10"> X <input type="number" name="size_y"
										required value="3" min="2" max="10">
								</div>
							</div>

							<div class="form-group ">
								<label for="cname" class="control-label col-lg-2">첨부파일 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<input type="file" name="file" required>
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button class="btn btn-primary" id="drawingSave" type="submit">Save</button>
									<button class="btn btn-default" id="shopCancel" type="button">Cancel</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</section>
		</div>
	</div>
</div>

<div id="tileModal" class="modal" style="z-index: 3;">
	<div class="tileModal-row">
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading"> 존 정보 등록 </header>
				<div class="panel-body">
					<div class="form">
						<form class="form-validate form-horizontal" id="feedback_form"
							method="post" action="">

							<div class="form-group ">
								<label for="name" class="control-label col-lg-2">타일 명 <span
									class="required">*</span>
								</label>
								<div class="col-lg-10">
									<input class="form-control" id="tile_nm" name="tile_nm"
										type="text" required />
								</div>
							</div>
							<!-- 
						<div class="form-group ">
							<label for="major" class="control-label col-lg-2">비콘 시리얼
								번호 <span class="required">*</span> <br>(Major)
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="major" name="fullname"
									type="text" required />
							</div>
						</div>

						<div class="form-group ">
							<label for="minor" class="control-label col-lg-2">비콘 시리얼
								번호 <span class="required">*</span> <br>(Minor)
							</label>
							<div class="col-lg-10">
								<input class="form-control" id="minor" name="fullname"
									type="text" required />
							</div>
						</div>

						<div class="form-group ">
							<label for="ccomment" class="control-label col-lg-2">비콘
								부가 정보</label>
							<div class="col-lg-10">
								<textarea class="form-control " id="ccomment" name="comment"
									required></textarea>
							</div>
						</div>
-->

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button class="btn btn-primary" id="tileSave" type="submit">Save</button>
									<button class="btn btn-default" id="tileCancel" type="button">Cancel</button>
								</div>
							</div>
						</form>
					</div>

				</div>
			</section>
		</div>
	</div>
</div>

<div id="listModal" class="modal" style="z-index: 3;">
	<div class="modal-row" style="overflow: scroll">
		<table class="table table-striped table-advance table-hover">
			<thead>
				<tr>
					<th style="text-align: center;"><i class="fa fa-bullseye"></i>
						비콘 코드</th>
					<th style="text-align: center;"><i
						class="fa fa-check-square-o"></i> 메이저</th>
					<th style="text-align: center;"><i
						class="fa fa-check-square-o"></i> 마이너</th>
					<th style="text-align: center;"><i class="fa fa-flag"></i> 상태</th>
				</tr>
			</thead>

			<tbody id="beaconList">

			</tbody>

		</table>
	</div>
</div>

<script src="resources/customjs/shopRegister.js"></script>