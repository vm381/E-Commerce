<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Categories</h2>
	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th></th>
		</tr>
		<c:if test="${not empty categories }">
			<c:forEach var="c" items="${categories }">
				<tr>
					<td>${c.name }</td>
					<td><a
						href="/WebStore/category/admin/edit?idCategory=${c.idCategory }">Edit</a></td>
					<td><a
						href="/WebStore/category/admin/confirmDelete?idCategory=${c.idCategory }">Delete</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<a class="insert" href="/WebStore/category/admin/insert">Add</a>
</div>
</body>
</html>