<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="question" method="post">
	Enter your name:	<input type="text" name="userName"><br> 
	 <p>
			Choose subject :
			
			 <select id="question" name="question1">
			 <c:forEach items="${subjectList}" var="questiontype">
			 <option value=" "> </option>
				<option value="${questiontype} ">${questiontype}</option>
				</c:forEach>
			</select>
			 
		</p>
		
		 <input type="submit" value="Enter">
		 </form>
</body>
</html>