<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entities.*"%>
<%@page import="services.*"%>
<%@page import="utils.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Job Details</title>

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
<link rel="stylesheet" href="./css/jobDetails_styles.css">
<!-- Page Css -->
</head>

<%
HttpSession hs = request.getSession(false);
String userType = "viewer";
User user = new User();
if (hs.getAttribute("userDetails") != null) {
	user = (User) hs.getAttribute("userDetails");
	if (user.getRole().equals("admin")) {
		userType = "admin";
	} else {
		userType = "user";
	}
}
%>

<%
int jId = Integer.parseInt(request.getParameter("jId"));

JobService jobSer = new JobService();
Job jobDetail = jobSer.getJobById(jId);

DepartmentService deptSer = new DepartmentService();
Department dept = deptSer.getDepartmentById(jobDetail.getjDepartmentId());

if (jobDetail.getjId() == 0) {
	out.println("<script type=\"text/javascript\">");
	out.println("alert('No record found for this job!');");
	out.println("history.back();");
	out.println("</script>");
}

boolean isUserApplied = jobSer.isUserAppliedToJob(user.getuId(), jobDetail.getjId());
%>
<body>
	<!-- NAV BAR -->
	<nav class="navbar" style="background-color: white;">
		<div class=" container-fluid">
			<span class="navbar-brand mb-0 h1"><div class="navbar-brand"><b>Careers.</b></div></span>
		</div>
	</nav>

	<section class="project-mains">
		<div class="project-main-div">
			<div class="row">
				<div class="col-md-8">
					<h2>
						<b><%=jobDetail.getjTitle()%></b>
					</h2>
				</div>
				<%
				if (userType.equals("admin")) {
					out.println("<div class='col-md-4'>");
					out.println("<a class='btn-secondary-my' href='./adminGetApplicants.jsp?jId=" + jobDetail.getjId()
					+ "' style='text-decoration: none; float: right;'>Get applicant details</a>");
					out.println("</div>");
				}
				%>


			</div>
			Posted On: <b><%=jobDetail.getjCreatedAt()%></b> <br> <br>
			<div class="row g-3">
				<div class="col-md-3">
					<div style="text-align: center;">
						<b>Department</b><br><%=dept.getdName()%>
					</div>
				</div>

				<div class="col-md-3">
					<div style="text-align: center;">
						<b>Salary (Rs)</b><br><%=jobDetail.getjSalary()%>
					</div>
				</div>

				<div class="col-md-3">
					<div style="text-align: center;">
						<b>Location</b><br>
						<%=jobDetail.getjLocation()%>
					</div>
				</div>

				<div class="col-md-3">
					<div style="text-align: center;">
						<b>Apply By</b><br>
						<%=jobDetail.getjLastDT()%>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="project-details">
		<div class="project-details-div">
			<h5>
				<b>Job Description</b>
			</h5>
			<p><%=jobDetail.getjDescription()%></p>

			<b>Eligibility Criteria</b>
			<p><%=jobDetail.getjEleCria()%></p>

			<b>Educational Qualification</b>
			<p><%=jobDetail.getjEduQual()%></p>

			<b>Experience Required</b>
			<p><%=jobDetail.getjExpReq()%></p>
			<br>
			<div style="text-align: center;">
				<%
				if (userType.equals("viewer")) {
					out.println(
					"<a class='btn-secondary-my' href='./signInPage.jsp' style='text-decoration: none;'>Sign in to apply</a>");
				} else if (userType.equals("user")) {

					if (MyChecker.isActive(jobDetail.getjLastDT()) && !isUserApplied) {
						out.println("<a class='btn-primary-my' href='ApplyToJob?jId=" + jobDetail.getjId()
						+ "' style='text-decoration: none;'>Apply to this job</a>");
					} else if (isUserApplied) {
						out.println("<h6 style='color=var(--text-light-gery)'>You have already applied to this job</h6>");
					} else {
						out.println("<h6 style='color=var(--text-light-gery)'>You cannot apply for this job now!</h6>");
					}
				}
				%>
			</div>
		</div>
	</section>
</body>

</html>