$(document).ready(function(){
	// 버튼 클릭시 업로드 되어있는 사진들 전환
	$(".leftDrawingBtns").on("click", function() {
		var countStory = parseInt($(".countStory").val());
		floor = parseInt($(".floor").val());

		if(floor > 0) {
			floor--;
			$(".floor").val(floor);
			productImgLoad(floor);
		}
	});

	$(".rightDrawingBtns").on("click", function() {
		var countStory = parseInt($(".countStory").val());
		floor = parseInt($(".floor").val());

		if(floor < countStory-1) {
			floor++;
			$(".floor").val(floor);
			productImgLoad(floor);

		}
		else if(floor == countStory-1) {
			floor++;
			$(".floor").val(floor);
			$('.blueprint').empty();
			$(".tileMap2").empty();

			var blueprint = $('.blueprint');
			$("<p>새로운 도면을 등록해주세요 </p>").appendTo(blueprint);
		}
	});
	

	
});



var productImgLoad = function(floor) {

	$.ajax({
		url: "getDrawingFileName",
		type: "post",
		data: {
			floor : floor,
		},
		async : false,
		dataType: "json",
		success: function(data) {

			if(data != null) {
				$('.blueprint').empty();

				var drawingImg = $('<img src="displayDrawing?fileName=/' + data.drw_flpth + '" style="width: 800px; height: 380px;">');
				drawingImg.appendTo($('.blueprint'));

				$(".drw_code").val(data.drw_code);

				var tileMap = $(".tileMap2");

				tileMap.empty();

				// 해당 층의 설정된 타일 갯수까지 가져올 수 있어야 함.
				for(var i=0; i<data.size_y; i++) {
					var tileRow = $("<div></div>");

					for(var j=0; j<data.size_x; j++) {
						var tileItem = $("<div></div>").addClass("tile2");
					

						tileItem.appendTo(tileRow);
					}
					tileRow.appendTo(tileMap);
				}
				var heightSize = 100 / data.size_y;
				var widthSize = 100 / data.size_x;
				$(".tileMap2 > div").css("width", "100%").css("height", heightSize + "%");
				$(".tile2").css("width", widthSize + "%").css("height", "100%").css("float", "left");

				$("#detail_categoryList").empty();
				
				/* 타일별로 색깔 저장 해주는 것 */
				var tileInfoList = data.tileInfoList;

				for(var i=0; i<tileInfoList.length; i++) {
					var info = tileInfoList[i];

					var x = info.TILE_CRDNT_X;
					var y = info.TILE_CRDNT_Y;

					var row = $("div.tileMap2 > div").eq(x);
					var col = row.find("div.tile2").eq(y);

					col.empty();
					col.attr("data-detailctgry_code", info.DETAILCTGRY_CODE);
					$("<span></span>").text( info.DETAILCTGRY_NM ).appendTo(col);
					col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.1));
					
					$("#detail_categoryList").append($("<tr></tr>").addClass("detailList").attr("data-id", info.TILE_CODE));
					
					$(".detailList[data-id=" + info.TILE_CODE +"]").append($("<td></td>").text(info.TILE_NM));
					$(".detailList[data-id=" + info.TILE_CODE +"]").append($("<td></td>").text(info.DETAILCTGRY_CODE));
					$(".detailList[data-id=" + info.TILE_CODE +"]").append($("<td></td>").text(info.DETAILCTGRY_NM));
					$(".detailList[data-id=" + info.TILE_CODE +"]").append($("<td></td>").addClass("color").append($("<div class='colorSpan' style='width:15px; float:left; height:15px'></div>")));
					$(".detailList[data-id=" + info.TILE_CODE +"] .color > .colorSpan").css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.2));
				
					$(".detailList[data-id=" + info.TILE_CODE +"] .color").append($("<span></span>").text(info.LCLASCTGRY_COLOR));
					console.log($(".colorSpan").css("background-color"));
				}


			}
			
		},
		error: function(data) {
			console.log("아작스 에러남");
		}
	});

};

function goods_locationTile(){
	$(".tile").each(function(){
		
		var totalNum = $("div.tile").index($(this));
		var RowNum = $("div.tileMap > div:first > .tile").length;

		var drw_code = $("#drw_code").val();
		var X_index = parseInt(totalNum / RowNum);
		var Y_index = totalNum % RowNum;
		
		var tile = $(this);
		
		$.ajax({

			url: "goods_locationList",
			type: "get",
			data: {
				drw_code : drw_code,
				tile_crdnt_x : X_index,
				tile_crdnt_y : Y_index
			},
			dataType: "json",
			success: function(data) {
				if(data.length > 0){
					var goodsList = new Array();
					for(var i = 0; i < data.length; i++){
							goodsList.push(data[i].goods_code);
					}
					console.log(goodsList);
										
					
					var row = $("div.tileMap2 > div").eq(X_index);
					var col = row.find("div.tile2").eq(Y_index);
					
					col.attr("data-goodsList", goodsList);
				}
			}

		});
	});
}

