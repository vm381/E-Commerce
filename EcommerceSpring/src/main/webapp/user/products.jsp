<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<c:if test="${not empty category}">
		<h2>Products in ${category.name }</h2>
		<br>
		<div class="grid-container">
			<c:if test="${not empty items }">
				<c:forEach var="i" items="${items }">
					<c:if test="${i.stock gt 0 }">
						<a href="/WebStore/product/user/details?idItem=${i.idItem }"><button
								class="grid-item">${i.name } ($${i.price })</button></a>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>