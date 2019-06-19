<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<sec:authorize access="!hasRole('ANONYMOUS')">
	<%
		response.sendRedirect("/WebStore/home");
	%>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link href="${pageContext.request.contextPath }/static/styles.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<h2>Register</h2>
	<sf:form modelAttribute="user" action="saveUser" method="post">
		<label>Username: </label>
		<br>
		<sf:input path="username" />
		<br>
		<label>Password: </label>
		<br>
		<sf:password path="password" />
		<br>
		<label>First name: </label>
		<br>
		<sf:input path="firstName" />
		<br>
		<label>Last name: </label>
		<br>
		<sf:input path="lastName" />
		<br>
		<label>Birthday: </label>
		<br>
		<sf:input type="date" path="birthday" />
		<br>
		<label>Address: </label>
		<br>
		<sf:input path="address" />
		<br>
		<label>City: </label>
		<br>
		<sf:input path="city" />
		<br>
		<label>Country: </label>
		<br>
		<sf:input path="country" />
		<br>
		<label>Phone: </label>
		<br>
		<sf:input path="phone" />
		<br>
		<input type="submit" class="btn" value="Register">
	</sf:form>
	<c:if test="${not empty errorMsg }">
		<div class="error">${errorMsg }</div>
	</c:if>
	<a href="/WebStore/auth/login"><button class="btn">Back</button></a>
</body>
</html>