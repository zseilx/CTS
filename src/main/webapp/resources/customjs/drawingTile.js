
/**shop_Register.js 와
 * dashBoard.js에서 사용
 * 도면 이미지를 페이지에 뿌려줌
 */

var loadTile = function(floor){



	var tileShowType = $("#tileShowType").val();

	if(tileShowType == 0) {
		loadZone(floor);
	
	}

	else if(tileShowType == 1) {
		loadCategory(floor);
	
	}
	else {
		loadDemo(floor);
		
	}
}

//타일과 도면 표시용
var imgLoad = function(floor) {
	//var countStory = $("#countStory").val();
	//var floor = $("#floor").val();

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
				$('#blueprint').empty();

				var drawingImg = $('<img src="./resources/img/drawingFile.JPG" style="width: 800px; height: 380px;">');
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

				for(var i=0; i<tileInfoList.length; i++) {
					var info = tileInfoList[i];

					var x = info.TILE_CRDNT_X;
					var y = info.TILE_CRDNT_Y;

					var row = $("div.tileMap > div").eq(x);
					var col = row.find("div.tile").eq(y);

					col.empty();

					//$("<p></p>").text( info.DETAILCTGRY_NM ).appendTo(col);
					//$("<p></p>").text( (info.probability*100) + "%" ).appendTo(col);

					col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.2));

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

var loadDemo = function(floor) {


	$.ajax({
		url: "getDrawingFileName",
		type: "post",
		data: {
			floor : floor
		},
		dataType: "json",
		success: function(data) {

			if(data != null) {

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
					col.attr("data-detailctgry_code", info.DETAILCTGRY_CODE);
					$("<span></span>").text( info.DETAILCTGRY_NM ).appendTo(col);


					//$("<p></p>").text( (info.probability*100) + "%" ).appendTo(col);

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

					col.append("<img src='resources/img/run.png'/>");



					var str = col.css("background-color").replace("0.2", "0.8");
					col.css("background-color", str);
					//col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, 0.8));
				}
				//$("#floor").val(floor+1);

			}
		}

	});
};

var loadCategory = function(floor) {

	$.ajax({

		url: "loadCategory",
		type: "post",
		data: {
			floor : floor
		},
		dataType: "json",
		success: function(data) {
			var categoryList = data.categoryList;
			var alpha;
			var grade = categoryList.length / 3;


			for(var i=0; i<categoryList.length; i++) {
				var info = categoryList[i];

				var x = info.TILE_CRDNT_X;
				var y = info.TILE_CRDNT_Y;

				var row = $("div.tileMap > div").eq(x);
				var col = row.find("div.tile").eq(y);

				alpha = 0.05;

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
				$("<span></span>").text( info.DETAILCTGRY_NM ).appendTo(col);
				$("</br>").appendTo(col);
				$("<span></span>").text( (info.probability*100) + "%" ).appendTo(col);

				//col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, alpha));
				col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, alpha));

				//col.css("background-color", "#" + info.LCLASCTGRY_COLOR);
				//col.css("opacity", 0.1 + (info.probability * 0.5));
				//col.css("opacity", alpha);
			}
		}

	});
};

var loadZone = function(floor) {

	$.ajax({

		url: "loadZone",
		type: "post",
		data: {
			floor : floor
		},
		dataType: "json",
		success: function(data) {
			var zoneList = data.zoneList;
			var grade = zoneList.length / 8;
			var alpha = 0.5;
			var color;
			var fontColor;
			var result;
			for(var i=0; i<zoneList.length; i++) {
				var info = zoneList[i];

				var x = info.TILE_CRDNT_X;
				var y = info.TILE_CRDNT_Y;

				var row = $("div.tileMap > div").eq(x);
				var col = row.find("div.tile").eq(y);

				result = colorSelecter(Math.floor(info.probability * 100));

				color = result.split("-")[0];
				fontColor =  result.split("-")[1];

				console.log(color + " " +  fontColor);

				col.empty();


				$("<p style='color:"+ fontColor +"'></p>").text( Math.floor(info.probability * 100) + "%" ).appendTo(col);

				//col.css("background-color", hexToRgbA("#" + info.LCLASCTGRY_COLOR, alpha));
				col.css("background-color", color);

				//col.css("background-color", "#" + info.LCLASCTGRY_COLOR);
				//col.css("opacity", 0.1 + (info.probability * 0.5));
				//col.css("opacity", alpha);
			}

			for(var j=0; j < data.categoryList.length; j++){

				var row = $("div.tileMap > div").eq(data.categoryList[j].TILE_CRDNT_X);
				var col = row.find("div.tile").eq(data.categoryList[j].TILE_CRDNT_Y);


				col.attr("data-detailctgry_code", data.categoryList[j].DETAILCTGRY_CODE);
				$("<span style='color:"+ col.find("p").css("color") +"'></span>").text( data.categoryList[j].DETAILCTGRY_NM ).appendTo(col);
				$("</br>").appendTo(col);

			}


		}

	});
};

var loadDetailCategory = function(floor){
	$.ajax({
		url: "loadDetailCategory",
		type: "GET",
		data: 
		{
			floor : floor
		},
		dataType: "json",
		success: function(data) {

			if(data != null) {

				console.log(data);
				for(var i=0; i<data.categoryList.length; i++) {
					var info = data.categoryList[i];

					var x = info.TILE_CRDNT_X;
					var y = info.TILE_CRDNT_Y;

					var row = $("div.tileMap > div").eq(x);
					var col = row.find("div.tile").eq(y);
					console.log(row);
					console.log(col);

					col.empty();
					col.attr("data-detailctgry_code", info.DETAILCTGRY_CODE);
					$("<span></span>").text( info.DETAILCTGRY_NM ).appendTo(col);
					$("</br>").appendTo(col);


				}
			}
			else {

			}
		},
		error: function(data) {

		}
	});
}

/*
 * 존별로 표시할 때 컬러로 등급을 정하는 함수
 * 숫자가 낮은것일 수록 등급이 높은것임
 */
var colorSelecter = function(probability) {

	var color;
	var fontColor;
	probability = Math.floor(probability);

	if(probability >= 0 && probability <= 10){

		color = "rgba(250, 236, 197, 1)";
		fontColor = "black";

	}else if(probability >= 11 && probability <= 20){

		color="rgba(255, 228, 0, 1)";
		fontColor = "black";

	}else if(probability >= 21 && probability <= 30){

		color="rgba(171, 242, 0, 1)";
		fontColor = "black";

	}else if(probability >= 31 && probability <= 40){

		color="rgba(34, 116, 28, 1)";
		fontColor = "white";

	}else if(probability >= 41 && probability <= 50){

		color="rgba(255, 94, 0, 1)";
		fontColor = "white";

	}else if(probability >= 51 && probability <= 60){

		color="rgba(255, 0, 0, 1)";
		fontColor = "white";

	}else if(probability >= 61 && probability <= 70){

		color="rgba(95, 0, 255, 1)";
		fontColor = "white";

	}else if(probability >= 71 && probability <= 80){

		color="rgba(0, 51, 153, 1)";
		fontColor = "white";

	}else if(probability >= 81 && probability <= 100){

		color="rgba(0, 34, 102, 1)";
		fontColor = "white";
	}

	return color + "-" + fontColor;
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