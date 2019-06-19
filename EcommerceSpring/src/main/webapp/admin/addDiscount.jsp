<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Add discount</h2>
	<sf:form modelAttribute="discount" action="add" method="post">
		<label>Date: </label>
		<br>
		<sf:input type="date" path="date" />
		<br>
		<label>Percent: </label>
		<br>
		<sf:input type="number" path="percent" />
		<br>
		<label>Products: </label>
		<br>
		<table>
			<tr>
				<th></th>
				<th>Name</th>
				<th>Price</th>
			</tr>
			<c:forEach var="p" items="${products }">
				<tr>
					<td><sf:checkbox path="items" value="${p }" /></td>
					<td>${p.name }</td>
					<td>$${p.price }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" class="btn" value="Add">
	</sf:form>
	<br>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>