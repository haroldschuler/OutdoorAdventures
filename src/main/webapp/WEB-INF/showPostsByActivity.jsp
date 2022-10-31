<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>${chosenActivity.activityName} Posts</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<style>
        body {
            background-image: url("../../images/adventure_search_posts_by_activity.jpg");
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-xl sticky-top navbar-dark bg-dark">
        <div class="container-fluid site_navbar_custom">
            <a class="navbar-brand" href="/">Outdoor Adventures</a>
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
    <div class="content flex-column align-items-center">
        <div class="search_results_title_bar transparent_box">
            <h1>${chosenActivity.activityName}</h1>
            <h4>Here are some ${chosenActivity.activityName.toLowerCase()} adventures:</h4>
        </div>
        <div class="posts_container">
        	<c:forEach var="post" items="${allPosts}">
	        	<a href="/posts/view/${post.id}">
		        	<div class="post_box transparent_box" onmouseover="postMouseover(this)" onmouseout="postMouseout(this)">
		        		<!-- <img src="/images/adventure_home_pic.jpg" alt="picture"> -->
		        		<img src="${post.images[0].identifier}" alt="picture">
		        		<div class="title_in_post_box">
			        		<h6>${post.title}</h6>
		        		</div>
		        		<p>Where: ${post.location}</p>
		        		<p>When: <fmt:formatDate value="${post.startDate}" pattern="MMMM dd, YYYY"/>
		        		 - <fmt:formatDate value="${post.endDate}" pattern="MMMM dd, YYYY"/></p>
		        		<p id="list_title">Activity tags: </p>
							<ul>
								<c:forEach var="activity" items="${post.activities}">
									<li>${activity.activityName}</li> 
								</c:forEach>
							</ul>
						<p>Posted By: ${post.user.userName}</p>
		        	</div>
	        	</a>
        	</c:forEach>
        </div>
    </div>
    <script type="text/javascript" src="/js/script.js"></script>
</body>
</html>