<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Discounts</h2>
	<table>
		<tr>
			<th>Date</th>
			<th>Percent off</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="d" items="${discounts }">
			<tr>
				<td><a href="/WebStore/discount/admin/details?idDiscount=${d.idDiscount }">${d.date }</a></td>
				<td>${d.percent }</td>
				<td><a href="/WebStore/discount/admin/edit?idDiscount=${d.idDiscount }">Edit</a></td>
				<td><a href="/WebStore/discount/admin/delete?idDiscount=${d.idDiscount }">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a class="insert" href="/WebStore/discount/admin/add">Add</a>
</div>
</body>
</html>