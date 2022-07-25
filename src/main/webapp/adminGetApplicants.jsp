<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entities.*"%>
<%@page import="services.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Applicants</title>
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
<link rel="stylesheet" href="./css/adminUser_style.css">
<!-- Page Css -->
</head>
<%
HttpSession hs = request.getSession(false);
List<Applies> appliedUsers = new ArrayList<Applies>();
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
	} else {
		JobService jobSer = new JobService();
		int jId = Integer.parseInt(request.getParameter("jId"));
		if (jId == 0) {
	out.println("<script type=\"text/javascript\">");
	out.println("alert('No record found for this job!');");
	out.println("history.back();");
	out.println("</script>");
		}
		appliedUsers = jobSer.getAppliedUsers(jId);
	}
}
%>
<body>
	<!-- NAV BAR -->
	<nav class="navbar" style="background-color: white;">
		<div class=" container-fluid">
			<span class="navbar-brand mb-0 h1">
				<div class="navbar-brand">
					<b class="brand-name">Careers.</b>
				</div>
			</span>
		</div>
	</nav>

	<section class="home-section">
		<div class="reg-users-div">
			<h2 style="color: var(- -main-color);">
				<b>Applicants</b>
			</h2>
			<hr style="color: var(- -main-color);">

			<%
			if (appliedUsers.size() < 1) {
				out.println("<h3>No users applied for this job!</h3>");
			} else {
				out.println("<table id='customers'>");
				out.println("<tr><th>Sr No.</th>" + "<th>Name</th>" + "<th>Mobile No.</th>"
				+ "<th>Email Id</th><th>Applied on</th></tr>");
				int srNo = 1;
				for (int i = 0; i < appliedUsers.size(); i++) {
					out.println("<tr>" + "<td>" + srNo + "</td><td>" + "<a class='profile-link' href='./profile.jsp?uId="
					+ appliedUsers.get(i).getUser().getuId() + "'>" + appliedUsers.get(i).getUser().getuName() + "</a>"
					+ "</td><td>" + appliedUsers.get(i).getUser().getuMobileNo() + "</td><td>"
					+ appliedUsers.get(i).getUser().getuEmailId() + "</td><td>" + appliedUsers.get(i).getAppliedAt()
					+ "</td>" + "</tr>");
				}
				out.println("</table>");
			}
			%>
		</div>
	</section>
</body>

</html>