<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<style type="text/css">
.sublist{
/*border:1px solid blue;*/
width:45%;
margin:50px;

}
</style>
</head>
<body>
<div class=""container>
<div class="container p-3 my-3 bg-primary text-white">
<h1 class="title">Question Bin</h1>

</div>
<div class="sublist">
<form action="question" method="post">
<center>
<h2>Enter Details</h2>
	Enter your name:	<input type="text" name="userName"><br><br> 
	 <p>Choose subject :
			<select id="question" name="subjectType">
			 <option value=" "> </option>
			 <c:forEach items="${subjectList}" var="questiontype">
				<option  value="${questiontype} ">${questiontype}</option>
				</c:forEach>
			</select>
		</p>
		 <input type="submit" value="Enter"><br><br>
		 </center>
		 </form>
		 </div>
		 </div>
		 </body>
</html>