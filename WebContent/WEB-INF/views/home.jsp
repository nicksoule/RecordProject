<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<form action="search">
<input type="text" name="search" required>
<input type="submit" value="submit">
</form>
<c:forEach var="myVar" items="${recList}">
					<img src="${myVar.image}" height=200><br>
					<p>${myVar.title}</p><br>
					<p>$${myVar.price}</p><br>
					
				
			</c:forEach>

</body>
</html>