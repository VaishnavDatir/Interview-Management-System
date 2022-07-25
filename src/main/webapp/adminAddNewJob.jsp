<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add new job</title>

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
<link rel="stylesheet" href="./css/adminAddNewJob_styles.css">
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

	<section class="sec-job-frm">
		<div class="main-form">
			<form action="CreateJobServlet" method="post">
				<div class="row g-3">
					<div class="col-md-12">
						<h2 style="color: var(- -main-color);">
							<b>Add new job</b>
						</h2>
						<hr>
					</div>
					<div class="col-md-4">
						<label for="selDept" class="form-label">Department</label> <select
							id="deptSelect" class="form-select" name="deptSelect"
							onchange="setDeptInTxt(this)">
							<%
							DepartmentService deptSer = new DepartmentService();
							List<Department> deptList = deptSer.getDeptList();
							for (int i = 0; i < deptList.size(); i++) {
								out.println("<option value='" + deptList.get(i).getdId() + "'>" + deptList.get(i).getdName() + "</option>");
							}
							%>
						</select> <input hidden type="text" id="inputDiD" name="dID">
					</div>

					<div class="col-md-8">
						<label for="inpuName" class="form-label">Job Title</label> <input
							type="text" class="form-control" id="inpuName" name="jTitle"
							required>
					</div>

					<div class="col-md-12">
						<label for="inputJobDesc" class="form-label">Job
							Description</label>
						<textarea oninput="limitChar(this, 'decCharCounter')"
							class="form-control" id="inputJobDesc" rows="3" name="jDesc"></textarea>
						<p id="decCharCounter" class="char-counter"></p>

					</div>

					<div class="col-md-12">
						<label for="inputJobQualification" class="form-label">Eligibility
							Criterias</label>
						<textarea class="form-control" id="inputJobQualification" rows="3"
							name="jEleCria" oninput="limitChar(this, 'eliCharCounter')"></textarea>
						<p id="eliCharCounter" class="char-counter"></p>

					</div>

					<div class="col-md-6">
						<label for="inputJobEducation" class="form-label">Educational
							Qualification</label>
						<textarea class="form-control" id="inputJobEducation" rows="3"
							name="jEdu"></textarea>
					</div>

					<div class="col-md-6">
						<label for="inputJobExperience" class="form-label">Experience
							Required</label>
						<textarea class="form-control" id="inputJobExperience" rows="3"
							name="jExp"></textarea>
					</div>

					<div class="col-md-6">
						<label for="inpuLocation" class="form-label">Location</label> <br>
						<input type="text" class="form-control" id="inpuLocation"
							name="jLoc" required>
					</div>

					<div class="col-md-6">
						<label for="inputSalary" class="form-label">Salary <sub>(in
								pa.)</sub></label> <br> <input type="text" class="form-control"
							id="inputSalary" name="jSal" required>
					</div>

					<div class="col-md-6">
						<label for="inputPass1" class="form-label">Last submission
							Date</label> <br> <input type="date" name="jLastDT"
							id="inputDateTime" min="<%=java.time.LocalDate.now()%>"
							style="width: 200px;" required>
					</div>
					<div class="col-md-6">
						<button type="submit" class="btn btn-primary-my"
							style="float: right; border-radius: 7px; margin-top: 22px;">Create
							this job</button>
					</div>

				</div>
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

        let limitChar = (element, counter) => {
            const maxChar = 250;

            let ele = document.getElementById(element.id);
            let charLen = ele.value.length;

            let p = document.getElementById(counter);
            if (charLen !== 0) {
                p.innerHTML = charLen + '/250';
            } else {
                p.innerHTML = '';
            }
            if (charLen > maxChar) {
                ele.value = ele.value.substring(0, maxChar);
                p.innerHTML = 250 + '/250';
            }
        }
    </script>
</body>

</html>