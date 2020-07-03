<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>result</title>
</head>
<body>
<h1>QuestionBin</h1>
<h3>Hi ${userName} </h3>
<p> ${serverTime}</p>
<h2>Question: ${subjectType}</h2>
<table border="2|1"><tr>
<td><h2>Correct Answer= ${correct}</h2></td>
<td><h2>Wrong Answer= ${wrong} </h2></td>
<td><h1>You Score= ${score} </h1></td>
</tr></table>
<br>
<h2>Wrong Questions With Correct Answers:-</h2>
<table>
<c:forEach  items="${wrongAnswerList}" var="WrongAnswer" varStatus="outer">
             <p>${WrongAnswer}</p> 
            
             </c:forEach>
             </table>
</body>
</html>