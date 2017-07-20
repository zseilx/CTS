$("#couponCancel").on("click",function(){
	$('#couponForm').attr("method", "get");
	$('#couponForm').attr("action", "coupon_Management");
	$('#couponForm').submit();
});

$("#searching").on("click", function(){
	
	var goodsName = $("#search").val();
	$.ajax({
		type:"get",
		url:"search",
		data:{
			goodsName:goodsName
		},
		dataType:"json",
		
		success:function(data){			
			if(data!=null){
				$("#productList").empty();
				
				var length = data.result.length;
				for(var i=0; i<length; i++) {
					
					var products = $("<tr class='product'></tr>");
					$("<td><input type='radio' name='goodsCodeList'></td>").appendTo(products);
					$("<td></td>").addClass("goods_code").text(data.result[i].goods_code).appendTo(products);
					$("<td></td>").addClass("goods_nm").text(data.result[i].goods_nm).appendTo(products);
					$("<td></td>").addClass("goods_pc").text(data.result[i].goods_pc).appendTo(products);
	
				}
			}else {
				$("<td></td>").text("검색된 물품이 없습니다.").appendTo(products);		
			}
		}
	});
	
	
});


$(document).on("change", "input[name=goodsCodeList]",function(){
	
	
	var goods_code = $(this).parent().next().text();

	var goods_nm = $(this).parent().next().next().text();
	
	console.log(goods_code + "  " +  goods_nm);
	
	$("#selectGoods").val(goods_nm);
	$("#selectGcode").val(goods_code);
	
	
});



$("#couponSave").on("click",function(){

	var code = parseInt($("#codes").val())+1;
	var goods_code = $("#selectGcode").val();
	var coupon_co = $("#coupon_co").val();
	
	$.ajax({
		type:"post",
		url:"insertCoupon",
		data:{
			coupon_code:coupon_code,
			goods_code:goods_code,
			coupon_co:coupon_co,
			coupon_nm:coupon_nm,
			coupon_cntnts:coupon_cntnts,
			coupon_dscnt:coupon_dscnt,
			coupon_begin_de:coupon_begin_de,
			coupon_end_de:coupon_end_de
		}
	});
});	