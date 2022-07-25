<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Page</title>
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
<link rel="stylesheet" href="./css/signInPage_styles.css">
<!-- Sign In  Page Css -->
</head>
<%@page import="entities.*"%>
<%@page import="services.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<body>
	<!-- NAV BAR -->
	<nav class="navbar">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1"><div class="navbar-brand">
					<b class="brand-name" style="color: white;">Careers.</b>
				</div></span>
		</div>
	</nav>

	<div class="regContent">
		<div class="formContent">
			<h2>
				<b>Sign up</b>
			</h2>
			<h6 style="color: var(- -text-light-gery);">Please enter details
				to register yourself.</h6>
			<br>
			<form action="RegistrationServlet" method="Post">
				<div class="row g-3">
					<div class="col-md-8">
						<label for="inpuName" class="form-label">Name</label> <input
							type="text" class="form-control" id="inpuName" name="name"
							required>
					</div>

					<div class="col-md-4">
						<label for="genderSelecet" class="form-label">Gender</label> <select
							id="genderSelecet" class="form-select" name="gender">
							<option>Male</option>
							<option>Female</option>
						</select>
					</div>

					<div class="col-md-8">
						<label for="selDept" class="form-label">Interested
							department</label> <select id="deptSelect" class="form-select"
							name="deptSelect" onchange="setDeptInTxt(this)">
							<%
							DepartmentService deptSer = new DepartmentService();
							List<Department> deptList = deptSer.getDeptList();
							for (int i = 0; i < deptList.size(); i++) {
								out.println("<option value='" + deptList.get(i).getdId() + "'>" + deptList.get(i).getdName() + "</option>");
							}
							%>
						</select> <input hidden type="text" id="inputDiD" name="dID">
					</div>

					<div class="col-md-4">
						<label for="dob" class="form-label">Date of Birth</label> <input
							type="date" class="form-control" id="dob" name="dob"
							max="<%=java.time.LocalDate.now()%>" required>
					</div>



					<div class="col-md-6">
						<label for="InputMobile" class="form-label">Mobile Number</label>
						<input type="tel" class="form-control" id="InputMobile"
							name="mobileNo" required>
					</div>

					<div class="col-md-6">
						<label for="inputEmail" class="form-label">Email Id</label> <input
							type="email" class="form-control" id="inputEmail" name="emailId"
							required>
					</div>

					<div class="col-md-6">
						<label for="inputPass1" class="form-label">Password</label> <input
							type="password" class="form-control" id="inputPass1"
							name="password" required>
					</div>

					<div class="col-md-6">
						<label for="inputPass2" class="form-label">Confirm
							Password</label> <input type="password" class="form-control"
							id="inputPass2" name="cPassword" required>
					</div>
				</div>

				<br>
				<button type="submit" class="btn btn-primary-my"
					style="width: 100%; border-radius: 100px;">Register</button>
			</form>
			<br>
			<center>
				Already have an account? <span> <a href="./signInPage.jsp"
					class="a-acc-btn">Sign in</a>
				</span>
			</center>
		</div>
	</div>
	<script>
		window.onload = function() {
			setDeptInTxt(document.getElementById("deptSelect"))
		}

		function setDeptInTxt(dropValue) {
			console.log(dropValue.value);
			var dIdbox = document.getElementById("inputDiD");
			dIdbox.value = dropValue.value;
		}
	</script>
</body>

</html>