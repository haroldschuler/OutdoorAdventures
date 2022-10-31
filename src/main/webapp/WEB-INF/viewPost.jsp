<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>${post.title}</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<style>
        body {
            background-image: url("../../images/adventure_view_post.jpg");
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
    <div class="content flex-column">
    	<div class="post_title_block">
    		<div class="view_post_left_col view_post_col transparent_box" id="view_post_title">
	    		<h1>${post.title}</h1>
    			<h4>${post.location}</h4>
    			<h5><fmt:formatDate value="${post.startDate}" pattern="MMMM dd, YYYY"/>
		        		 - <fmt:formatDate value="${post.endDate}" pattern="MMMM dd, YYYY"/></h5>
    			<h5>Posted By: <a href="/posts/user/${post.user.id}">${post.user.userName}</a></h5>
    		</div>
    		<div class="view_post_col view_post_right_col">
    		</div>
    	</div>
    	<div class="column_container">
	        <div class="view_post_col view_post_left_col transparent_box">
        		<c:forEach var="image" items="${post.images}">
        			<img src="${image.identifier}" alt="picture">
        			<div>
	        			<p>${image.description}</p>
        			</div>
        		</c:forEach>
        	</div>
	       	<div>
		        <div class="view_post_col transparent_box view_post_right_col">
		        	<h3>Tagged activities:</h3>
		        	<div class="tagged_activity_holder">
			        	<c:forEach var="activity" items="${post.activities}">
			        		<a href="/posts/${activity.activityName.toLowerCase()}/${activity.id}">
				                <div class="tagged_activity" onmouseover="activityMouseover(this)" onmouseout="activityMouseout(this)">
				                	<%-- <img src="/images/activities/${activity.activityName}.png" alt="${activity.activityName}"> --%>
				                	<img src="https://raw.githubusercontent.com/haroldschuler/OutdoorAdventures/main/src/main/resources/static/images/activities/${activity.activityName.toLowerCase()}.png" alt="${activity.activityName}">
				                	<h5>${activity.activityName}</h5>
				                </div>
		                	</a>
			        	</c:forEach>
		        	</div>
		        </div>
		        <div class="view_post_col transparent_box view_post_right_col">
		        	<h3>Gear Used:</h3>
		        	<div class="gear_used_holder">
			        	<c:forEach var="gear" items="${post.gear}">
			        		<h6>${gear.gearName} (${gear.gearCategory.category})</h6>
			        		<p>${post.user.userName}'s rating: ${gear.rating}/10</p>
			        		<p>Here's what they said about it: ${gear.review}</p>
			        	</c:forEach>
		        	</div>
		        </div>
	       	</div>
    	</div>
    </div>
    <script type="text/javascript" src="/js/script.js"></script>
</body>
</html>





















