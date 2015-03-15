var STOCK_CHART = {

	loadHistoryData : function() {

		var chartData = [];
		
		$.ajax({
			url : "CompanyJsonDataServlet",
			async: false,
			dataType : "json"
		}).success(
				function(stockJsonData) {
					$.each(stockJsonData,function(index,aHistory){
						chartData.push({
							date: aHistory.priceDate,
							closingPrice: aHistory.closingPrice,
							openPrice: aHistory.openPrice,
							highPrice: aHistory.highPrice,
							lowPrice: aHistory.lowPrice
						})
					});	
				});
		return chartData;
	},

	createChart : function(jsonData) {
		console.log(jsonData);
		AmCharts.makeChart("chartdiv", {
			type : "stock",
			pathToImages : "./resources/js/amcharts/images/",
			dataDateFormat : "YYYY-MM-DD",
			dataSets :

			[ {
				dataProvider : jsonData,
				fieldMappings : [ {
					fromField : "closingPrice",
					toField : "value"
				}, {
					fromField : "priceDate",
					toField : "date"
				}, {
					fromField : "openPrice",
					toField : "openPrice"
				}, {
					fromField : "lowPrice",
					toField : "lowPrice"
				}, {
					fromField : "highPrice",
					toField : "highPrice"
				}
				],
				categoryField : "date"
			} ],

			panels : [ {

				legend : {},

				stockGraphs : [ {
					fillColors: "#7F8d19",
					balloonText: "Open:<b>[[open]]</b><br>Low:<b>[[low]]</b><br>High:<b>[[high]]</b><br>Close:<b>[[close]]</b><br>",
					id : "graph1",
					closeField : "value",
					openField : "openPrice",
					highField : "highPrice",
					lowField : "lowPrice",
					type : "candlestick",
					title : "Stock Price",
					valueField : "value",
					fillAlphas : .7
				} ]
			} ],

			panelsSettings : {
				startDuration : 1
			},

			categoryAxesSettings : {
				dashLength : 5
			},

			valueAxesSettings : {
				dashLength : 5
			},

			chartScrollbarSettings : {
				graph : "graph1",
				graphType : "line"
			},

			chartCursorSettings : {
				valueBalloonsEnabled : true,
				valueLineEnabled: true
			},

			periodSelector : {
				periods : [ {
					period : "DD",
					count : 1,
					label : "1 day"
				}, {
					period : "DD",
					selected : true,
					count : 5,
					label : "5 days"
				}, {
					period : "MM",
					count : 1,
					label : "1 month"
				}, {
					period : "YYYY",
					count : 1,
					label : "1 year"
				}, {
					period : "YTD",
					label : "YTD"
				}, {
					period : "MAX",
					label : "MAX"
				} ]
			}
		});
	},

	initStockChart : function() {
		
		historyData = STOCK_CHART.loadHistoryData();

		stockChart = STOCK_CHART.createChart(historyData);
	}

};

$(document).ready(function() {
	STOCK_CHART.initStockChart();
});