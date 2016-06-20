		//错误率图
 	 	function pic() {
				var chart = {
					type : 'bar'
				};
				var title = {
					text : '知识点错误率'
				};
				var xAxis = {
					categories : xAxisData,
					title : {
						text : null
					}
				};
				var yAxis = {
					min : 0,
					title : {
						text : '',
						align : 'high'
					},
					labels : {
						overflow : 'justify'
					}
				};
				var tooltip = {
					valueSuffix : '%'
				};
				var plotOptions = {
					bar : {
						dataLabels : {
							enabled : true
						}
					}
				};
				var legend = {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'top', x : 0,y : 0,
								floating : true,
								borderWidth : 1,
								backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
								shadow : true
				};
				var credits = {
					enabled : false
				};
				var series = [ {
					name : '错误率',
					data : seriesData
				} ];

				
				var json = {};
					json.chart = chart;
					json.title = title;
					json.tooltip = tooltip;
					json.xAxis = xAxis;
					json.yAxis = yAxis;
					json.series = series;
					json.plotOptions = plotOptions;
					json.legend = legend;
					json.credits = credits;
					$('#container').highcharts(json);
			}  
		
		
		