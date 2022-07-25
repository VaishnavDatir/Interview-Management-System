<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Job</title>

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&family=Source+Sans+Pro:wght@400;700&display=swap"
	rel="stylesheet">

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>

<!-- My Css -->
<link rel="stylesheet" href="./css/base.css">
<!-- Base Css -->
<link rel="stylesheet" href="./css/adminJobPage_styles.css">
<!-- Page Css -->
</head>
<%@page import="entities.*"%>
<%@page import="services.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
HttpSession hs = request.getSession(false);
if (hs.getAttribute("userDetails") == null) {
	out.println("<script type=\"text/javascript\">");
	out.println("alert('Please sign in to access this page');");
	out.println("location='signInPage.jsp';");
	out.println("</script>");
} else {
	User user = (User) hs.getAttribute("userDetails");
	if (!user.getRole().equals("admin")) {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('You cannot access this page');");
		out.println("history.back();");
		out.println("</script>");
	}
}
%>
<%
JobService jobSer = new JobService();
List<Job> jobList = jobSer.getAllJobs();

%>
<body>
	<!-- NAV BAR -->
	<nav class="navbar navbar-expand-lg" style="background-color: white;">
		<div class="container-fluid">
			<div class="navbar-brand">
				<b class="brand-name">Careers</b>
			</div>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div class="navbar-nav me-auto mb-2 mb-lg-0"></div>
				<div class="d-flex">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link"
							href="./adminHome.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="./adminUser.html">Users</a></li>
						<li class="nav-item"><a class="nav-link"
							href="./adminDepartment.html">Department</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>


	<section class="job-section">
		<div class="jobs-div">
			<div class="row">
				<div class="col-md-6">
					<h2>
						<b>Posted Jobs</b>
					</h2>
				</div>
				<div class="col-md-6 div-add-new-job">
					<a class="a-add-new-job" href="./adminAddNewJob.jsp"
						style="float: right;">Add new job</a>
				</div>
			</div>
			<hr style="color: var(- -main-color);">
			
			<%
			if (jobList.size() == 0) {
				out.println("<h3>No jobs are available</h3>");
			} else {
				out.println("<div class='row g-3'>");
				for (int i = 0; i < jobList.size(); i++) {
					out.println("<div class='col-md-12'>");
					out.println("<a class='a-job-card' href='./jobDetail.jsp?jId=" + jobList.get(i).getjId() + "'>");
					out.println("<div class='job-card'>");
					out.println("<div class='jTitle'>" + jobList.get(i).getjTitle() + "</div>");
					out.println("<div class='jLoction'>" + jobList.get(i).getjLocation() + ", Last date: <b>"+jobList.get(i).getjLastDT()+"</b></div>");
					out.println("<div class='jDescription'>" + jobList.get(i).getjDescription() + "</div>");
					out.println("</div>");
					out.println("</a>");
					out.println("</div>");
				}
				out.println("</div>");
			}
			%>
		</div>
	</section>

	<div class="modal" tabindex="-1" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="mTitle">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p id="mDescription">Modal body text goes here.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		window.onload = function() {
	<%if (hs.getAttribute("serRes") != null) {
	ServiceResponse serRes = (ServiceResponse) hs.getAttribute("serRes");
	out.println("showModal('" + serRes.getMessage() + "', '" + serRes.getMsgDesc() + "');");
	hs.removeAttribute("serRes");
} ;%>
		};

		function showModal(success, message) {
			var myModal = new bootstrap.Modal(document
					.getElementById('myModal'), {})
			var mTitle = document.getElementById('mTitle');
			var mDescription = document.getElementById('mDescription');
			mTitle.innerText = success;
			mDescription.innerText = message

			myModal.toggle();

		}
	</script>
</body>

</html>