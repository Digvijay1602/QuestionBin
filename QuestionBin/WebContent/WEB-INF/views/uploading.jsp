<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File upload</title>
</head>
<body>
<h1>QuestionBin</h1><br>
<form action="uploaded" method="post" enctype="multipart/form-data">  
Select File: <input type="file" name="file"/>  
<input type="submit" value="Upload File"/> 

</form>
</body>
</html>