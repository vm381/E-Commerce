<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Delete category</h2>
	<c:if test="${not empty item }">
		<div class="warning">
			Do you really want to remove product ${item.name } from stock?<br>
			<a href="/WebStore/product/admin/remove?idItem=${item.idItem }"><button
					class="btn">Remove</button></a> <a href="/WebStore/product/admin"><button
					class="btn">Cancel</button></a>
		</div>
		<br>
	</c:if>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>