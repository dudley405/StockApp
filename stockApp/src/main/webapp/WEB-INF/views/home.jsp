<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<h2> Companies </h2>
<c:forEach var="company" items="${companies}">
	<p>${company.companyName} -- ${company.stockTicker}</p>
</c:forEach>

</body>
</html>
