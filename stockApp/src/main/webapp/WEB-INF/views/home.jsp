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
     <script src="./resources/js/stock-chart.js" type="text/javascript"></script>
	
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
