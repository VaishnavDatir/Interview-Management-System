<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Departments</title>
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
<link rel="stylesheet" href="./css/adminDept_styles.css">
<!-- admin Home Page Css -->
</head>
<%@page import="entities.*"%>
<%@page import="services.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
HttpSession hs = request.getSession(false);
if(hs.getAttribute("userDetails") == null){
	out.println("<script type=\"text/javascript\">");
	out.println("alert('Please sign in to access this page');");
	out.println("location='signInPage.jsp';");
	out.println("</script>");
}else{
	User user = (User) hs.getAttribute("userDetails");
	if(!user.getRole().equals("admin") ){
		out.println("<script type=\"text/javascript\">");
		out.println("alert('You cannot access this page');");
		out.println("history.back();");
		out.println("</script>");
	}
}
%>

<%
DepartmentService deptSer = new DepartmentService();
List<Department> deptList = deptSer.getDeptList();

%>
<body>
	<!-- NAV BAR -->
    <nav class="navbar navbar-expand-lg" style="background-color: white;">
        <div class="container-fluid">
            <div class="navbar-brand"><b class="brand-name">Careers.</b>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="navbar-nav me-auto mb-2 mb-lg-0">

                </div>
                <div class="d-flex">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="./adminHome.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./adminUser.html">Users</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./adminJobsPage">Jobs</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>

	<section class="dept-sec">
		<div class="dept-content">
			<div class="row g-3">
				<div class="col-md-10">
					<h2>
						<b>Available departments</b>
					</h2>
				</div>
				<div class="col-md-2 div-add-new-dept">
					<!-- <a class="a-add-new-dept" href="http://">Add new department</a> -->
					<a type="button" class="a-add-new-dept" data-bs-toggle="modal"
						data-bs-target="#exampleModal" > Add department </a>
				</div>
			</div>
			<hr style="color: var(- -main-color);">
			<%
			if (deptList.size() == 0) {
				out.println("<h3>No departments are available</h3>");
			} else {
				out.println("<div class='row g-3'>");
				for (int i = 0; i < deptList.size(); i++) {
					out.println("<div class='col-md-3'>");
					out.println("<div class='div-dept-txt'>" + deptList.get(i).getdName() + "</div>");
					out.println("</div>");
				}
				out.println("</div>");
			}
			%>
		</div>
	</section>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add new
						department</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="CreateDepartment" method="post">
					<div class="modal-body">
						<label for="inputDept" class="form-label">Department name</label>
						<input type="text" class="form-control" id="inputDept"
							name="deptName" required>
					</div>
					<div class="modal-footer">
						<a class="btn btn-secondary" data-bs-dismiss="modal"
							style="padding: 10px;">Close</a>
						<button type="submit button" class="btn btn-primary-my">Add</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>