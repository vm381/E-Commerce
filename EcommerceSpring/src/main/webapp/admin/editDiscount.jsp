<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Edit discount</h2>
	<c:if test="${not empty discount }">
		<sf:form modelAttribute="discount"
			action="/WebStore/discount/admin/edit" method="post">
			<label>Date: </label>
			<br>
			<sf:input type="date" path="date" value="${discount.date }" />
			<br>
			<label>Percent: </label>
			<br>
			<sf:input type="number" path="percent" value="${discount.percent }" />
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
			<sf:hidden path="idDiscount"
				value="${discount.idDiscount }" />
			<input type="submit" class="btn" value="Edit">
		</sf:form>
		<br>
	</c:if>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>