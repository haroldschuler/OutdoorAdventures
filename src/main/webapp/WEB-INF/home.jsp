<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Outdoor Adventures</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<style>
        body {
            background-image: url("images/adventure_home_pic.jpg");
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-xl sticky-top navbar-dark bg-dark">
        <div class="container-fluid site_navbar_custom">
            <a class="navbar-brand" href="/">Outdoor Adventures</a>
            <!-- Add back in for a search bar -->
            <!-- <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form> -->
            <div class="" id="navbarDark">
                <ul class="navbar-nav me-auto mb-2 mb-xl-0">
                	<li class="nav-item">
                        <a class="nav-link" href="/gear">Search By Gear</a>
                    </li>
                    <c:set var="userId" scope="session" value="${userId}"/>
					<c:choose>
						<c:when test="${userId!=null}">
							<li class="nav-item">
		                        <a class="nav-link" href="/new/post">New Post</a>
		                    </li>
		                    <li class="nav-item">
		                        <a class="nav-link" href="/posts/user/${userId}">My Posts</a>
		                    </li>
		                    <li class="nav-item">
		                        <a class="nav-link" href="/logout">Logout</a>
		                    </li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
		                        <a class="nav-link" href="/login">Login</a>
		                    </li>
		                    <li class="nav-item">
		                        <a class="nav-link" href="/register">Register</a>
		                    </li>
						</c:otherwise>
					</c:choose>
                </ul>
            </div>
        </div>
    </nav>
    <div class="content">
        <div class="activity_box transparent_box">
            <div class="activity_title_div">
                <h2>Which adventure would you like to explore?</h2>
            </div>
            <div class="activity_holder">
            	<c:forEach var="activity" items="${allActivities}">
	                <a href="/posts/${activity.activityName.toLowerCase()}/${activity.id}">
		                <div class="activity" onmouseover="activityMouseover(this)" onmouseout="activityMouseout(this)">
		                	<img src="/images/activities/${activity.activityName}.png" alt="${activity.activityName}">
		                	<h5>${activity.activityName}</h5>
		                </div>
	                </a>
            	</c:forEach>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/script.js"></script>
</body>
</html>