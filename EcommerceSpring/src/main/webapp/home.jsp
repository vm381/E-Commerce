<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="header.jsp"%>
<div class="content">
	<h1>
		Welcome,
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.firstName" />
			<sec:authentication property="principal.lastName" />
		</sec:authorize>
	</h1>
	<br>
	<sec:authorize access="hasRole('ADMIN')">
		<h2>Orders</h2>
		<br>
		<form action="/WebStore/report/orders" method="get">
			<label>Date: </label> <br> <input type="date" name="date">
			<br> <br> <input type="submit" class="btn"
				value="Get report">
		</form>
	</sec:authorize>
	<sec:authorize access="hasRole('USER')">
		<h2>Discounts</h2>
		<br>
		<div class="grid-container">
			<c:forEach var="i" items="${items }">
				<c:if test="${i.key.stock gt 0 }">
					<a href="/WebStore/product/user/details?idItem=${i.key.idItem }"><button
							class="grid-item">${i.key.name } (${i.value }% off:
							$${i.key.price - i.key.price * i.value / 100})</button></a>
				</c:if>
			</c:forEach>
		</div>
	</sec:authorize>
	<c:if test="${not empty errMsg }">
		<div class="erros">${errMsg }</div>
	</c:if>
</div>
</body>
</html>