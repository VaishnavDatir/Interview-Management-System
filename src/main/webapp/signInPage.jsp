<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign In</title>

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
<body>
	<!-- NAV BAR -->
	<nav class="navbar">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1"><a class="navbar-brand"
				href="./index.html"><b class="brand-name"  style="color: white;">Careers.</b></a></span>
		</div>
	</nav>

	<div class="mainContent">
		<div class="formContent">
			<form action="LoginServlet" method="POST">
				<h2>
					<b>Sign in</b>
				</h2>
				<h6 style="color: var(- -text-light-gery);">Please enter
					details to sign in.</h6>
				<br>
				<div class="mb-3">
					<label for="inputEmail1" class="form-label">Email address</label> <input
						type="email" class="form-control" id="inputEmail1"
						name="userEmail" required>
				</div>

				<div class="mb-3">
					<label for="inputPassword" class="form-label">Password</label> <input
						type="password" class="form-control" id="inputPassword"
						name="userPassword" required>
				</div>

				<button type="submit" class="btn btn-primary-my"
					style="width: 100%; border-radius: 100px;">Sign in</button>
				<br> <br>
				<center>
					Not registered yet? <span> <a href="./registrationPage.jsp"
						class="a-acc-btn">Create an account</a>
					</span>
				</center>
			</form>
		</div>
	</div>

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
	<%HttpSession hs = request.getSession(false);
if (hs.getAttribute("serRes") != null) {
	ServiceResponse serRes = (ServiceResponse) hs.getAttribute("serRes");
	if (serRes.isSuccess()) {
		out.println("showModal('Successsfull', 'Your account has been successfully created. Please sign in');");
	} else {
		String msgStr = serRes.getMsgDesc().replace("'", "");
		if (msgStr.contains("Duplicate")) {
			msgStr = "Mobile no or Email Id already exists!";
		}
		out.println("showModal('" + serRes.getMessage() + "', '" + msgStr + "');");
	}
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