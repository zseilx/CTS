//Highcharts.chart('graph', {
//    chart: {
//        type: 'areaspline'
//    },
//    title: {
//        text: ' '
//    },
//    legend: {
//        layout: 'vertical',
//        align: 'left',
//        verticalAlign: 'top',
//        x: 150,
//        y: 100,
//        floating: true,
//        borderWidth: 1,
//        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
//    },
//    xAxis: {
//        categories: [
//            'Mar',
//        	'Apr',
//        	'May',
//        	'Jun',
//            'Jul'
//        ],
//        plotBands: [{ // visualize the weekend
//            from: 4.5,
//            to: 6.5,
//            color: 'rgba(68, 170, 213, .2)'
//        }]
//    },
//    yAxis: {
//        title: {
//            text: ' '
//        }
//    },
//    tooltip: {
//        shared: true,
//        valueSuffix: ' units'
//    },
//    credits: {
//        enabled: false
//    },
//    plotOptions: {
//        areaspline: {
//            fillOpacity: 0.5
//        }
//    },
//    series: [{
//        name: 'totalAvg',
//        data: [3, 4, 1, 5, 2]
//    }, {
//        name: 'branchAvg',
//        data: [1, 3, 4, 2, 7]
//    }]
//});

Highcharts.chart('femaleGraph', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: 0,
        plotShadow: false
    },
    title: {
        text: '<br>female<br>Visitor',
        align: 'center',
        verticalAlign: 'middle',
        y: 40
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            dataLabels: {
                enabled: true,
                distance: -50,
                style: {
                    fontWeight: 'bold',
                    color: 'white'
                }
            },
            startAngle: -90,
            endAngle: 90,
            center: ['50%', '75%']
        }
    },
    credits: {
        enabled: false
    },
    series: [{
        type: 'pie',
        name: 'Browser share',
        innerSize: '50%',
        data: [
            [' ',   10.38],
            [' ',       56.33],
            [' ', 24.03],
            [' ',    4.77],
            [' ',     0.91],
            {
                name: 'Proprietary or Undetectable',
                y: 0.2,
                dataLabels: {
                    enabled: false
                }
            }
        ]
    }]
});

Highcharts.chart('maleGraph', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: 0,
        plotShadow: false
    },
    title: {
        text: '<br>male<br>Visitor',
        align: 'center',
        verticalAlign: 'middle',
        y: 40
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            dataLabels: {
                enabled: true,
                distance: -50,
                style: {
                    fontWeight: 'bold',
                    color: 'white'
                }
            },
            startAngle: -90,
            endAngle: 90,
            center: ['50%', '75%']
        }
    },
    credits: {
        enabled: false
    },
    series: [{
        type: 'pie',
        name: 'Browser share',
        innerSize: '50%',
        data: [
            [' ',   10.38],
            [' ',       56.33],
            [' ', 24.03],
            [' ',    4.77],
            [' ',     0.91],
            {
                name: 'Proprietary or Undetectable',
                y: 0.2,
                dataLabels: {
                    enabled: false
                }
            }
        ]
    }]
});

$(document).ready(function(){
	

	$.ajax({
		type:"get",
		url:"monthlyTotalSale",
		dataType : "json",
		success:function(data){
			
			$("#graph").empty();
//			alert('hi?');
			
			
			var length = data.result.length;
			console.log(data);
			if(length>0){

				var options = {
					    chart: {
					        type: 'areaspline'
					    },
					    title: {
					        text: ' '
					    },
					    legend: {
					        layout: 'vertical',
					        align: 'left',
					        verticalAlign: 'top',
					        x: 150,
					        y: 100,
					        floating: true,
					        borderWidth: 1,
					        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
					    },
					    xAxis: {
					        categories: [

					        ],
					        plotBands: [{ // visualize the weekend
					            from: 4.5,
					            to: 6.5,
					            color: 'rgba(68, 170, 213, .2)'
					        }]
					    },
					    yAxis: {
					        title: {
					            text: ' '
					        }
					    },
					    tooltip: {
					        shared: true,
					        valueSuffix: ' units'
					    },
					    credits: {
					        enabled: false
					    },
					    plotOptions: {
					        areaspline: {
					            fillOpacity: 0.5
					        }
					    },
					    series: []

				}

				options.series[0] = {};
				options.series[0].data = [];
				options.series[0].name = [];
				
				for (var i = 0; i < length; i++) {

					options.xAxis.categories[i] = data.result[i].date;
					options.series[0].data[i] = parseInt(data.result[i].monthlyTotalSale);
					options.series[0].name = "BranchTotalPrice";

				}

				chart = Highcharts.chart('graph', options);
			}
		}
	});
	
	var trVal = $(".listBody").find("tr:first").find("td:first").text();
//	alert(trVal);
	
	$.ajax({
		type:"get",
		url:"daySales",
		data:{
			bhf_code:1
		},
//		dataType:"json",
		success:function(data){
			
			$("#branchOffice").empty();
			
			data = JSON.parse(data);
				
			var length = data.result.length;
			
			if(length <= 0){
				$("#branchOffice").text("매출이 존재하지 않습니다.");
				
					
			}else{
				$("#branchOffice").css("margin-top","1%")
				var options = {
					title : {
						text : '일매출'
					},
					chart: {
			            inverted: true,
			            polar: false
			        },
					subtitle : {
						text : ''
					},
					xAxis : {
						categories : []
					},
					yAxis : {
						title: {
				            text: ' '
				        }
					},
					credits: {
					    enabled: false
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

				chart = Highcharts.chart('branchOffice', options);
				
				}

			}
	});
	
	$.ajax({
		type:"get",
		url:"branchGrade",
		dataType:"json",
		success:function(data){			
			if(data!=null){
				$(".rankBody").empty();
				
				var length = data.result.length;
				
				for(var i=0; i<length; i++) {
					
					var rank_list = $("<tr class='rank'></tr>");
//					$("<td><input type='checkbox' class='checked'></td>").appendTo(branch_list);
					if(i%2==0){
						$("<td><a class='btn btn-primary'>"+(i+1)+"</a></td>").appendTo(rank_list);
						$("<td></td>").addClass("bhf_nm").text(data.result[i].bhf_nm).appendTo(rank_list);
						$("<td></td>").addClass("totalPrice").text(data.result[i].totalPrice+"원").appendTo(rank_list);
						
						rank_list.appendTo($(".rankBody"));
					}else if(i%2==1){
						$("<td><a class='btn btn-default'>"+(i+1)+"</a></td>").appendTo(rank_list);
						$("<td></td>").addClass("bhf_nm").text(data.result[i].bhf_nm).appendTo(rank_list);
						$("<td></td>").addClass("totalPrice").text(data.result[i].totalPrice+"원").appendTo(rank_list);
						
						rank_list.appendTo($(".rankBody"));
					}
					
				}
				
				$('.rankBody tr').on("click",function(){
					
					var rankVal = $(this).find("td:first").text();
					alert(rankVal);
					
				});
				
			}
		}
	});
});

$(".searchingBtn").on("click", function(){
	
	var bhf_nm = $(".name").val();
	
	alert(bhf_nm);
	
	$.ajax({
		type:"get",
		url:"bhfSearching",
		data:{
			bhf_nm:bhf_nm
		},
		dataType:"json",
		
		success:function(data){			
			if(data!=null){
				$(".listBody").empty();
				
				var length = data.result.length;
				
				for(var i=0; i<length; i++) {
					
					var branch_list = $("<tr class='branch'></tr>");
//					$("<td><input type='checkbox' class='checked'></td>").appendTo(branch_list);
					$("<td></td>").addClass("bhf_code").text(data.result[i].bhf_code).appendTo(branch_list);
					$("<td></td>").addClass("bhf_nm").text(data.result[i].bhf_nm).appendTo(branch_list);
					$("<td></td>").addClass("bhf_telno").text(data.result[i].bhf_telno).appendTo(branch_list);
					
					branch_list.appendTo($(".listBody"));
					
					clickTr();
				}
				
			}else {
				$("<td></td>").text("검색된 물품이 없습니다.").appendTo(branch_list);
//				alert('hii');
			}
		},
		error:function(){
		}
	});
});

function clickTr() {
	
		$('.listBody tr').on("click",function(){
	
			var trVal = $(this).find("td:first").text();
//			alert(trVal);
			
			$.ajax({
				type:"get",
				url:"daySales",
				data:{
					bhf_code:trVal
				},
//				dataType:"json",
				success:function(data){
					
					$("#branchOffice").empty();
					
					data = JSON.parse(data);
						
					var length = data.result.length;
					
					if(length <= 0){
						$("#branchOffice").text("일주일 간의 매출이 존재하지 않습니다.").css("line-height", "400px");
						
							
					}else{
						$("#branchOffice").css("margin-top","1%")
						var options = {
							title : {
								text : '일매출'
							},
							chart: {
					            inverted: true,
					            polar: false
					        },
							subtitle : {
								text : ''
							},
							xAxis : {
								categories : []
							},
							yAxis : {
								title: {
						            text: ' '
						        }
							},
							credits: {
							    enabled: false
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

						chart = Highcharts.chart('branchOffice', options);
						
					}

				}
			});
		});
}

clickTr();
