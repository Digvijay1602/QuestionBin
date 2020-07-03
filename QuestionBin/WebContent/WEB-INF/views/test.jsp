<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
<h1>QuestionBin</h1>
<h3>Hi ${userName} </h3><br>
<p> ${serverTime}</p>
<p>Question: ${subjectType}</p><br>
<form name="myForm" action="result" method="post">
<table>

<c:forEach  items="${queList}" var="question" varStatus="outer">
       
             <p>${question}</p>  
     <c:forEach items="${optionList}" var="option"  varStatus="inner">
         <c:if test="${outer.index == inner.index}">
            <p> ${option}</p>
          Answer:  <input type="text" name="correctAnswer">
         </c:if>
               
     </c:forEach>     
</c:forEach> 
</table><br>
<input type="submit" value="Finish">
</form>
</body>
</html>