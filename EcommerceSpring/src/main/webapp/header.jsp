<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebStore</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/styles.css">
</head>
<body>
	<div class="menu">
		<a href="/WebStore/home" id="logo"><img src="${pageContext.request.contextPath }/images/e-commerce-icon-lg.png" width="50" height="45"></a>
		<sec:authorize access="hasRole('ADMIN')">
			<a href="/WebStore/category/admin"><button class="btn">Categories</button></a>
			<a href="/WebStore/product/admin"><button class="btn">Products</button></a>
			<a href="/WebStore/discount/admin"><button class="btn">Discounts</button></a>
		</sec:authorize>
		<sec:authorize access="hasRole('USER')">
			<a href="/WebStore/category/user"><button class="btn">Categories</button></a>
			<a href="/WebStore/cart/wishlist"><button class="btn">Wish list</button></a>
			<a href="/WebStore/cart"><button class="btn">Cart</button></a>
		</sec:authorize>
		<a href="/WebStore/home/account"><button class="btn">Account</button></a> <a
			href="/WebStore/auth/logout"><button class="btn">Logout</button></a>
	</div>