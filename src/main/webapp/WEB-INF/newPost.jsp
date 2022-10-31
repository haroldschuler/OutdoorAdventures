<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Make a New Post</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
	<style>
        body {
            background-image: url("../images/adventure_new_post.jpg");
            background-attachment: fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-xl sticky-top navbar-dark bg-dark">
        <div class="container-fluid site_navbar_custom">
            <a class="navbar-brand" href="/">Outdoor Adventures</a>
            <%-- <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form> --%>
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
    		<h1>Post your adventure</h1>
    	</div>
       	<form:form action="/new/post" method="post" modelAttribute="newPost" enctype="multipart/form-data"
       	>
	    	<div class="column_container">
		        <div class="new_post_col transparent_box" id="new_post_left_col">
		        	<form:label path="title" class="form-label">Name your adventure: <span class="text-danger"><c:out value="${titleError}"/></span></form:label>
		        	<form:errors path="title" class="text-danger"/>
			        <form:input path="title" class="form-control"/>
					<br>
		        	<form:label path="location" class="form-label">Where: <span class="text-danger"><c:out value="${locationError}"/></span></form:label>
		        	<form:errors path="location" class="text-danger"/>
			        <form:input path="location" class="form-control"/>
		        	<br>
    			    <form:label path="startDate" class="form-label">Date left:</form:label>
		        	<form:errors path="startDate" class="text-danger"/>
		        	<form:input type="date" path="startDate" class="form-control"/>
		        	<br>
		        	<form:label path="endDate" class="form-label">Date returned:</form:label>
		        	<form:errors path="endDate" class="text-danger"/>
		        	<form:input type="date" path="endDate" class="form-control"/>
		        	<br>
		        	<h5>Select up to 6 images (at least 2):</h5>
		        	<%-- <%for (int i=1; i <= 6; i++) {%>
			        	<div>
				        	<label for="image<%=i%>File" class="form-label">Image <%=i%>: <span class="text-danger"><c:out value="${imageError}"/></span></label>
				        	<c:out value="${error}"/>
				        	<input type="file" name="imageFile" id="image<%=i%>File" class="form-control">
							<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
							<br>
						</div>
		        	<%} %> --%>
					<!-- First File -->
		        	<label for="imageOneFile" class="form-label">Pick your first image: <span class="text-danger"><c:out value="${image1Error}"/></span></label>
		        	<input type="file" name="imageFile" id="imageOneFile" class="form-control">
					<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
					<br>
					<!-- Second File -->
					<label for="imageTwoFile" class="form-label">Pick your second image: <span class="text-danger"><c:out value="${image2Error}"/></span></label>
		        	<input type="file" name="imageFile" id="imageTwoFile" class="form-control">
					<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
					<br>
					<!-- Third File -->
					<label for="imageThreeFile" class="form-label">Pick your third image: <span class="text-danger"><c:out value="${image3Error}"/></span></label>
		        	<input type="file" name="imageFile" id="imageThreeFile" class="form-control">
					<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
					<br>
					<!-- Fourth File -->
					<label for="imageFourFile" class="form-label">Pick your fourth image: <span class="text-danger"><c:out value="${image4Error}"/></span></label>
		        	<input type="file" name="imageFile" id="imageFourFile" class="form-control">
					<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
					<br>
					<!-- Fifth File -->
					<label for="imageFiveFile" class="form-label">Pick your fifth image: <span class="text-danger"><c:out value="${image5Error}"/></span></label>
		        	<input type="file" name="imageFile" id="imageFiveFile" class="form-control">
					<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
					<br>
					<!-- Sixth File -->
					<label for="imageSixFile" class="form-label">Pick your sixth image: <span class="text-danger"><c:out value="${image6Error}"/></span></label>
		        	<input type="file" name="imageFile" id="imageSixFile" class="form-control">
					<textarea name="imageDescription" class="form-control" placeholder="Say something about the picture"></textarea>
		        </div>
		        <div class="right_column_container" id="new_post_right_col">
			        <div class="new_post_col transparent_box">
			        	<div class="new_post_activities">
			        		<h5 class="form-label">Add activity tags to your post (at least 1)</h5>
		        			<form:errors path="activities" class="text-danger"/>
		        			<div class="activity_checkboxes">
			        			<c:forEach var="activity" items="${activities}">
			        				<div>
			        					<form:checkbox path="activities" value="${activity.id}" class="form-check-input" id="activity${activity.id}"/>
				        				<form:label path="activities" for="activity${activity.id}" class="form-check-label form-label">${activity.activityName}</form:label>
			        				</div>
			        			</c:forEach>
		        			</div>
			        	</div>
			        </div>
	        		<%for (int i=0; i < 3; i++) {%>					        	
				        <div class="new_post_col transparent_box">
				        	<div class="new_post_gear">
				        		<h5>Add gear you used: <span class="text-danger"><c:out value="${gear1Error}"/></span></h5>
					        		<div class="gear_entry">
					        			<label for="gearName" class="form-label">Gear Name / Description:</label>
								        <input name="gearName" id="gearName" class="form-control"/>
							        	<br>
							        	<div class="d-flex justify-content-between">
								        	<label for="gearCategory" class="form-label">Gear Category:</label>
								        	<select name="gearCategory" id="gearCategory" class="select_dropdown">
									        	<c:forEach var="gearCategory" items="${allGearCategories}">
										        	<option value="${gearCategory.id}">${gearCategory.category}</option>
									        	</c:forEach>
								        	</select>
							        	</div>
							        	<br>
							        	<div class="d-flex justify-content-between">
								        	<label for="gearRating" class="form-label">Your Rating (1-10):</label>
								        	<select name="gearRating" id="gearRating" class="select_dropdown">
									        	<%for (int j=1; j <= 10; j++) {%>
										        	<option value="<%= j %>"><%= j %></option>
									        	<%} %>
								        	</select>
							        	</div>
							        	<label for="gearReview" class="form-label">Your Review:</label>
							        	<textarea name="gearReview" id="gearReview" class="form-control"></textarea>
							        	<br>
					        		</div>
				        	</div>
				        </div>
		        	<%} %>
		        </div>
	    	</div>
	    	<form:input type="hidden" path="user" value="${userId}"/>
	    	<div class="d-flex justify-content-center">
		    	<input type="submit" value="Post!" class="btn btn-outline-dark btn-lg mb-5 transparent_box rounded-3">
	    	</div>
       	</form:form>
    </div>
</body>
</html>