<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="./resources/js/amcharts/style.css" type="text/css">

    <script src="./resources/js/amcharts/amcharts.js" type="text/javascript"></script>
    <script src="./resources/js/amcharts/serial.js" type="text/javascript"></script>
    <script src="./resources/js/amcharts/amstock.js" type="text/javascript"></script>
    <script src="./resources/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    
    <script type="text/javascript">
    
    $.ajax({
  url: "CompanyJsonDataServlet",
  dataType: "json"
}).success(function(stockJsonData) {
 	console.log(stockJsonData);
});

   AmCharts.makeChart("chartdiv", {
    type: "stock",
    pathToImages: "./resources/js/amcharts/images/",
    dataDateFormat: "YYYY-MM-DD",
    dataSets: 
    
    [{
        dataProvider: [{
            date: "2011-06-01",
            val: 10
        }, {
            date: "2011-06-02",
            val: 11
        }, {
            date: "2011-06-03",
            val: 12
        }, {
            date: "2011-06-04",
            val: 11
        }, {
            date: "2011-06-05",
            val: 10
        }, {
            date: "2011-06-06",
            val: 11
        }, {
            date: "2011-06-07",
            val: 13
        }, {
            date: "2011-06-08",
            val: 14
        }, {
            date: "2011-06-09",
            val: 17
        }, {
            date: "2011-06-10",
            val: 13
        }],
        fieldMappings: [{
            fromField: "val",
            toField: "value"
        }],
        categoryField: "date"
    }],

    panels: [{

        legend: {},

        stockGraphs: [{
            id: "graph1",
            valueField: "value",
            type: "line",
            title: "MyGraph",
            fillAlphas: 1
        }]
    }],

    panelsSettings: {
        startDuration: 1
    },

    categoryAxesSettings: {
        dashLength: 5
    },

    valueAxesSettings: {
        dashLength: 5
    },

    chartScrollbarSettings: {
        graph: "graph1",
        graphType: "line"
    },

    chartCursorSettings: {
        valueBalloonsEnabled: true
    },

    periodSelector: {
        periods: [{
            period: "DD",
            count: 1,
            label: "1 day"
        }, {
            period: "DD",
            selected: true,
            count: 5,
            label: "5 days"
        }, {
            period: "MM",
            count: 1,
            label: "1 month"
        }, {
            period: "YYYY",
            count: 1,
            label: "1 year"
        }, {
            period: "YTD",
            label: "YTD"
        }, {
            period: "MAX",
            label: "MAX"
        }]
    }
});
        </script>
	
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<h2> Companies </h2>
	
<p>${companies.companyName} -- ${companies.stockTicker}</p>

<div id="chartdiv" style="width:100%; height:400px;"></div>

</body>
</html>
