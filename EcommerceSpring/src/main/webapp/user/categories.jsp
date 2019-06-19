<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Categories</h2>
	<br>
	<div class="grid-container">
		<c:forEach var="c" items="${categories }">
			<a
				href="/WebStore/product/user/showProducts?idCategory=${c.idCategory }"><button
					class="grid-item">${c.name }</button></a>
		</c:forEach>
	</div>
</div>
</body>
</html>