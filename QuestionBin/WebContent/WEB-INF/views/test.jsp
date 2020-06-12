<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
</head>
<body>
<h1>QuestionBin</h1>
<h3>Hi ${userName} </h3><br>
<p> ${serverTime}</p>
<p>Question :</p><br>
<form action="question" method="post">
<table>
<c:forEach items="${quesList}" var="question">
        <tr>
            <td>${question}</td>         
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>