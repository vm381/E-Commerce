<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Insert category</h2>
	<sf:form modelAttribute="category" action="/WebStore/category/admin/insert" method="post">
		<label>Category name: </label><br> <sf:input path="name"/><br>
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }">
		<input type="submit" class="btn"
			value="Add">
	</sf:form>
	<br>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>