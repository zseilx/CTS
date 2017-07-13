
/**shop_Register.js 와
 * dashBoard.js에서 사용
 * 도면 이미지를 페이지에 뿌려줌
 */


var bhf_code = "${bhf_code}";

var imgLoad = function(floor) {
	//var countStory = $("#countStory").val();
	//var floor = $("#floor").val();
	
	$.ajax({
		url: "getDrawingFileName",
		type: "post",
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