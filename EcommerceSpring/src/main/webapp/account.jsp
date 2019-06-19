<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%>
<div class="content">
	<sec:authorize access="isAuthenticated()">
		<h2>Account details: </h2>
		<br>
		<table>
		<tr>
			<td>Username:</td>
			<td><sec:authentication property="principal.username"/></td>
		</tr>
		<tr>
			<td>First name:</td>
			<td><sec:authentication property="principal.firstName"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><sec:authentication property="principal.lastName"/></td>
		</tr>
		<tr>
			<td>Birthday</td>
			<td><sec:authentication property="principal.birthday"/></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><sec:authentication property="principal.address"/></td>
		</tr>
		<tr>
			<td>City</td>
			<td><sec:authentication property="principal.city"/></td>
		</tr>
		<tr>
			<td>Country</td>
			<td><sec:authentication property="principal.country"/></td>
		</tr>
		<tr>
			<td>Phone</td>
			<td><sec:authentication property="principal.phone"/></td>
		</tr>
	</table>
	</sec:authorize>
</div>
</body>
</html>