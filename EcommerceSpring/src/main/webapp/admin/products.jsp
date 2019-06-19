<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Products</h2>
	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th></th>
		</tr>
		<c:if test="${not empty items }">
			<c:forEach var="i" items="${items }">
				<tr>
					<td><a
						href="/WebStore/product/admin/details?idItem=${i.idItem }">${i.name }</a></td>
					<td><a
						href="/WebStore/product/admin/edit?idItem=${i.idItem }">Edit</a></td>
					<td><a
						href="/WebStore/product/admin/confirm?idItem=${i.idItem }">Delete</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<a class="insert" href="/WebStore/product/admin/insert">Add</a>
</div>
</body>
</html>