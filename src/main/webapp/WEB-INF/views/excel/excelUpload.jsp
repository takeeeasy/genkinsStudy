<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert  here</title>
<script src = "./resources/js/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	$('#fBtn').on('click', function(){
		$('#excelFrm').attr("action", "excelListBoard").attr("method", "post").submit();
	})
})
</script>
</head>
<body>
<form name = "excelFrm" id = "excelFrm" enctype="multipart/form-data">
<table>
	<thead>
		<tr>
			<th>File Name</th>
			<th>Browse</th>
		</tr>
	</thead>
	
	<tbody>
		<tr>
			<td><input type = "file" name = "fileName" id = "fileName" ></td>
			<td><input type = "button" name = "fBtn" id = "fBtn" value = "submit"></td>
		</tr>	
	</tbody>
</table>
</form>
</body>
</html>