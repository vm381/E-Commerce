<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Edit category</h2>
	<c:if test="${not empty category }">
		<sf:form modelAttribute="category"
			action="/WebStore/category/admin/edit" method="post">
			<label>Category name: </label>
			<br>
			<sf:input path="name" value="${category.name }" />
			<br>
			<sf:hidden value="${category.idCategory }" path="idCategory" />
			<input type="hidden" name="${_csrf.parameterName }"
				value="${_csrf.token }">
			<input type="submit" class="btn" value="Edit">
		</sf:form>
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