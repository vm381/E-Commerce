<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../header.jsp"%>
<div class="content">
	<sf:form modelAttribute="item" action="/WebStore/product/admin/edit"
		method="post">
		<sf:hidden path="idItem" value="${item.idItem }" />
		<label>Name: </label>
		<br>
		<sf:input path="name" value="${item.name }" />
		<br>
		<label>Description: </label>
		<br>
		<sf:input path="description" value="${item.description }" />
		<br>
		<label>Price: </label>
		<br>
		<sf:input path="price" value="${item.price }" />
		<br>
		<label>Stock</label>
		<br>
		<sf:input path="stock" value="${item.stock }" />
		<br>
		<label>Category: </label>
		<br>
		<sf:select items="${categories }" path="category" itemLabel="name" />
		<br>
		<sf:hidden path="picture" value="${item.picture }"/>
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }">
		<input type="submit" class="btn" value="Save">
	</sf:form>
	<c:if test="${not empty success }">
		<div class="success">${success }</div>
	</c:if>
	<c:if test="${not empty errMsg }">
		<div class="error">${errMsg }</div>
	</c:if>
</div>
</body>
</html>