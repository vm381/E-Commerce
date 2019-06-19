<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ include file="../header.jsp"%>
<div class="content">
	<h2>Delete discount</h2>
	<c:if test="${not empty discount }">
		<div class="warning">
			Are you sure you want to delete discount?<br> <a
				href="/WebStore/discount/admin/confirm?idDiscount=${discount.idDiscount }"><button
					class="btn">Delete</button></a> <a href="/WebStore/discount/admin"><button
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