<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Insert Product</h2>
	<sf:form modelAttribute="item" action="/WebStore/product/admin/insert"
		method="post" enctype="multipart/form-data">
		<label>Product name: </label>
		<br>
		<sf:input path="name" />
		<br>
		<label>Description: </label>
		<br>
		<sf:input path="description" />
		<br>
		<label>Price: </label>
		<br>
		<sf:input path="price" />
		<br>
		<label>Stock: </label>
		<br>
		<sf:input path="stock" />
		<br>
		<label>Category: </label>
		<br>
		<sf:select items="${categories }" path="category" itemLabel="name" />
		<br>
		<label>Image: </label>
		<br>
		<input type="file" name="image">
		<br>
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }">
		<input type="submit" class="btn" value="Add">
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