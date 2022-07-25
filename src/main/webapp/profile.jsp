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
<title>Profile</title>
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
<link rel="stylesheet" href="./css/userProfile_style.css">
<!-- Page Css -->
</head>
<%
HttpSession hs = request.getSession(false);
String userType = "viewer";
User user = new User();
if (hs.getAttribute("userDetails") == null) {
	out.println("<script type=\"text/javascript\">");
	out.println("alert('Please sign in to access this page');");
	out.println("location='signInPage.jsp';");
	out.println("</script>");
} else {
	user = (User) hs.getAttribute("userDetails");
	if (user.getRole().equals("admin")) {
		userType = "admin";
	} else {
		userType = "user";
	}
}

int uId = Integer.parseInt(request.getParameter("uId"));

UserService userSer = new UserService();
User userDetails = userSer.getUserById(uId);


if (userDetails.getuId() == 0) {
	System.out.println("Show alert!!!");
	out.println("<script type=\"text/javascript\">");
	out.println("alert('No record found for this profile!');");
	out.println("history.back();");
	out.println("</script>");
} else if (!userType.equals("admin") && (user.getuId() != userDetails.getuId())) {
	out.println("<script type=\"text/javascript\">");
	out.println("alert('You are not authorized to see this profile!');");
	out.println("history.back();");
	out.println("</script>");
}
%>
<body>
	<!-- NAV BAR -->
	<nav class="navbar" style="background-color: white;">
		<div class=" container-fluid">
			<span class="navbar-brand mb-0 h1"><div class="navbar-brand">
					<b class="brand-name">Careers.</b>
				</div></span>
		</div>
	</nav>

	<section class="profileContent">
		<div class="profileContent-div">
			<form action="UpdateProfileServlet" method="POST">
				<h2>
					<b>Profile</b>
				</h2>
				<br>
				<div class="row g-3">
					<div class="col-md-12">
						<label for="inpuName" class="form-label">Name</label> <input
							type="text" class="form-control" id="inpuName" name="name"
							value="<%=userDetails.getuName()%>"
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							required>
					</div>

					<div class="col-md-6">
						<label for="selDept" class="form-label">Interested
							department</label> <select id="deptSelect" class="form-select"
							name="deptSelect" value="<%=userDetails.getuName()%>"
							<%-- 							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%> --%>
disabled
							onchange="setDeptInTxt(this)">
							<%
							DepartmentService deptSer = new DepartmentService();
							List<Department> deptList = deptSer.getDeptList();
							for (int i = 0; i < deptList.size(); i++) {
								String outQuery = "";
								outQuery += "<option value='" + deptList.get(i).getdId() + "'";
								if (deptList.get(i).getdId() == userDetails.getuDepartmentId()) {
									outQuery += " selected='selected' >";
								} else {
									outQuery += ">";
								}
								outQuery += deptList.get(i).getdName() + "</option>";
								out.println(outQuery);
							}
							%>
						</select> <input hidden type="text" class="form-control" id="inputDiD"
							name="dID" required>
					</div>

					<div class="col-md-6">
						<label for="genderSelecet" class="form-label">Gender</label> <select
							id="genderSelecet" class="form-select"
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							name="gender">
							<option
								<%if (userDetails.getuGender().equals("Male")) {
	out.println(" selected='selected' ");
}%>>Male</option>
							<option
								<%if (userDetails.getuGender().equals("Female")) {
	out.println(" selected='selected' ");
}%>>Female</option>
						</select>
					</div>

					<div class="col-md-6">
						<label for="InputMobile" class="form-label">Mobile Number</label>
						<input type="tel" class="form-control" id="InputMobile"
							value="<%=userDetails.getuMobileNo()%>" name="mobileNo"
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							required>
					</div>

					<div class="col-md-6">
						<label for="inputEmail" class="form-label">Email Id</label> <input
							type="email" class="form-control" id="inputEmail" name="emailId"
							value="<%=userDetails.getuEmailId()%>"
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							required>
					</div>

					<div class="col-md-6">
						<label for="dob" class="form-label">Date of Birth</label> <input
							type="date" class="form-control" id="dob" name="dob"
							max="<%=java.time.LocalDate.now()%>"
							value="<%=userDetails.getuDob()%>"
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							required>
					</div>

					<div class="col-md-6">
						<label for="Location" class="form-label">Current location</label>
						<input type="text" class="form-control" id="Location"
							name="location"
							<%if (userDetails.getuLocation() != null) {
	out.println("value='" + userDetails.getuLocation() + "'");
}%>
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							required>
					</div>

					<div class="col-md-12">
						<label for="inputAbout" class="form-label">About</label>
						<textarea class="form-control" id="inputAbout" rows="3"
							<%if (userType.equals("admin") || user.getuId() != userDetails.getuId()) {
	out.println("disabled");
}%>
							name="uAbout"><%if (userDetails.getuAbout() != null) {out.print(userDetails.getuAbout());}%></textarea>
					</div>
				</div>

				<br>
				<%
				if (userType.equals("user") && user.getuId() == userDetails.getuId()) {
					out.println("<button type='submit' class='btn btn-primary-my' style='width: 100px;'>Save</button>");
				}
				%>

			</form>
		</div>
	</section>
	<script>
		window.onload = function() {
			setDeptInTxt(document.getElementById("deptSelect"))
		}

		function setDeptInTxt(dropValue) {
			var dIdbox = document.getElementById("inputDiD");
			dIdbox.value = dropValue.value;
		}
	</script>
</body>

</html>