<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Cart</h2>
	<br>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
	<c:set var="total" value="0" />
	<table>
		<tr>
			<th>Product</th>
			<th>Price</th>
			<th>Discount</th>
			<th>Amount</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${cartItems }">
			<tr>
				<td>${item.key.name }</td>
				<td>$${item.key.price - item.key.price * item.value / 100 }</td>
				<td>${item.value }% off</td>
				<td><a
					href="/WebStore/cart/remove?idItem=${item.key.idItem }&calledFrom=cart"><button
							class="btn">Remove from cart</button></a></td>
			</tr>
			<c:set var="total" value="${total + item.key.price }"/>
		</c:forEach>
	</table>
	<a href="/WebStore/cart/checkout"><button class="btn">Checkout (total: $${total })</button></a>
</div>
</body>
</html>