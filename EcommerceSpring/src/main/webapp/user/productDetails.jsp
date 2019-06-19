<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Product details</h2>
	<br>
	<c:if test="${not empty item }">
		<table>
			<tr>
				<td>Name:</td>
				<td>${item.name }</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>${item.description }</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td>$${item.price }</td>
			</tr>
			<tr>
				<td>Stock:</td>
				<td>${item.stock }</td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><a
					href="/WebStore/product/user/showProducts?idCategory=${item.category.idCategory }">${item.category.name }</a></td>
			</tr>
			<tr>
				<td>Picture:</td>
				<td><img src="${item.picture }" id="productImage"></td>
			</tr>
		</table>

		<c:set var="contains" value="false" />
		<c:forEach var="cartItem" items="${cart }">
			<c:if test="${cartItem.idItem eq item.idItem }">
				<c:set var="contains" value="true" />
			</c:if>
		</c:forEach>

		<c:if test="${not contains }">
			<a
				href="/WebStore/cart/add?idItem=${item.idItem }&calledFrom=details"><button
					class="btn">Add to cart</button></a>
		</c:if>
		<c:if test="${contains }">
			<a
				href="/WebStore/cart/remove?idItem=${item.idItem }&calledFrom=details"><button
					class="btn">Remove from cart</button></a>
		</c:if>

		<c:if test="${empty wish }">
			<a href="/WebStore/cart/addToWishlist?idItem=${item.idItem }"><button
					class="btn">Add to wish list</button></a>
		</c:if>
		<c:if test="${not empty wish }">
			<a
				href="/WebStore/cart/removeFromWishlist?idItem=${item.idItem }&calledFrom=details"><button
					class="btn">Remove from wish list</button></a>
		</c:if>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>