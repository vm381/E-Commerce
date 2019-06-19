<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div>
	<h2>Product details</h2>
	<br>
	<table>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Stock</th>
			<th>Category</th>
			<th>Picture</th>
		</tr>
		<tr>
			<td>${item.name }</td>
			<td>${item.description }</td>
			<td>$${item.price }</td>
			<td>${item.stock }</td>
			<td>${item.category.name }</td>
			<td><img src="${item.picture }" id="productImage" /></td>
		</tr>
	</table>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>