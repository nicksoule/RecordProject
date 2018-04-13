<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring MVC</title>


</head>
<body>
	<br>
	<div style="text-align:center">
		<form action="update" method=POST>
			<input type="text" name="month" placeholder="month" required><br>
			<input type="text" name="day1" placeholder="day" required><br>
			<input type="text" name="year" placeholder="year" required><br>
			<input type = "submit" value = "submit">
			
		</form>
			
			${page}
			
		
	</div>
	
	<script>
	
	</script>
</body>
</html>