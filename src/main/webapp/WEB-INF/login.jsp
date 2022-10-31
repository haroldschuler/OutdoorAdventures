<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<style>
        body {
            background-image: url("images/adventure_login_reg.jpg");
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-xl sticky-top navbar-dark bg-dark">
        <div class="container-fluid site_navbar_custom">
            <a class="navbar-brand" href="/">Outdoor Adventures</a>
            <div class="" id="navbarDark">
                <ul class="navbar-nav me-auto mb-2 mb-xl-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="content">
        <div class="login_column">
	        <div class="login_box transparent_box">
	            <div class="login_title">
	                <h1>Login</h1>
	            </div>
	            <form:form action="/login" method="post" modelAttribute="newLogin">
	                <div class="login_form_input">
		                <form:label path="email" class="form-label">Email Address:</form:label>
		                <br>
		                <form:errors path="email" class="text-danger"/>
		                <form:input path="email" class="form-control"/>
	                </div>
	                <div class="login_form_input">
		                <form:label path="password" class="form-label">Password:</form:label>
		                <br>
		                <form:errors path="password" class="text-danger"/>
		                <form:input type="password" path="password" class="form-control"/>
	                </div>
	                <br>
	                <input type="submit" value="Login" class="btn btn-outline-dark">
	            </form:form>
	            <br>
	            <a href="/register">Not a member? Register here</a>
            </div>
        </div>
    </div>
</body>
</html>