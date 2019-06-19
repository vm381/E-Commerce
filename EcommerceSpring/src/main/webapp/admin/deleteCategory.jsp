<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Delete category</h2>
	<c:if test="${not empty category }">
		<div class="warning">
			If you delete category, all products in this category will be
			deleted, too. Are you sure?<br> <a
				href="/WebStore/category/admin/delete?idCategory=${category.idCategory }"><button
					class="btn">Delete</button></a> <a href="/WebStore/category/admin"><button
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