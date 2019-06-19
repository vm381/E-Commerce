<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Wish list</h2>
	<br>
	<table>
		<tr>
			<th>Product</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="w" items="${wishlist }">
			<tr>
				<td><a href="/WebStore/product/user/details?idItem=${w.item.idItem }">${w.item.name }</a></td>
				<td><c:set var="contains" value="false" /> <c:forEach
						var="cartItem" items="${cart }">
						<c:if test="${cartItem.idItem eq w.item.idItem }">
							<c:set var="contains" value="true" />
						</c:if>
					</c:forEach> <c:if test="${not contains }">
						<a
							href="/WebStore/cart/add?idItem=${w.item.idItem }&calledFrom=wishlist"><button
								class="btn">Add to cart</button></a>
					</c:if> <c:if test="${contains }">
						<a
							href="/WebStore/cart/remove?idItem=${w.item.idItem }&calledFrom=wishlist"><button
								class="btn">Remove from cart</button></a>
					</c:if></td>
				<td><a
					href="/WebStore/cart/removeFromWishlist?idItem=${w.item.idItem }&calledFrom=wishlist"><button
							class="btn">Remove from wish list</button></a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/WebStore/report/user/wishlist"><button class="btn">Download wish list</button></a>
</div>
</body>
</html>