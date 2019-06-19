<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Discount details</h2>
	<br>
	<h3>Discount for ${discount.date } - ${discount.percent }% off</h3>
</div>
<div>
	<table>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Stock</th>
			<th>Category</th>
			<th>Picture</th>
		</tr>
		<c:forEach var="i" items="${products }">
			<tr>
				<td><a
					href="/WebStore/product/admin/details?idItem=${i.idItem }">${i.name }</a></td>
				<td>${i.description }</td>
				<td>${i.price - i.price * discount.percent / 100 }</td>
				<td>${i.stock }</td>
				<td>${i.category.name }</td>
				<td><img src="${i.picture }" id="productImage" /></td>
			</tr>
		</c:forEach>
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