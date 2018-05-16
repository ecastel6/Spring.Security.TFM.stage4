<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
	<spring:url value="/resources/core/css/app.css" var="coreCss"/>
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
	<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
	<spring:url value="/resources/images/MIWlogo.png" var="applogo"/>
	<spring:url value="/" var="urlHome"/>
	<spring:url value="/books/add" var="urlAddBook"/>
	<spring:url value="/books" var="urlBooks"/>
	<spring:url value="/logout" var="urlLogout"/>
	<spring:url value="/users" var="urlUsers"/>

	<link href="${bootstrapCss}" rel="stylesheet"/>
	<link href="${coreCss}" rel="stylesheet"/>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Web security - Spring Security</title>
	<script src="${bootstrapJs}"></script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="/"><img src="${applogo}" class="img-rounded" alt="Spring Security"></a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${urlUsers}"><span class="glyphicon glyphicon-user"></span> Users</a></li>
			<li><a href="${urlBooks}"><span class="glyphicon glyphicon-book"></span> Books</a></li>
			<li><a href="${urlLogout}"><span class="glyphicon glyphicon-log-out"></span> Logout ${loggedinuser}</a></li>
		</ul>
	</div>
</nav>