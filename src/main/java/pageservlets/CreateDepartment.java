package pageservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.DepartmentService;

import java.io.IOException;
import java.io.PrintWriter;

import entities.ServiceResponse;

/**
 * Servlet implementation class CreateDepartment Used to create department Used
 * by admin only Pages: adminDepartment.jsp
 */
public class CreateDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateDepartment() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession hs = request.getSession(false);

		try {
			String dName = request.getParameter("deptName");
			if (dName.trim().length() < 1) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Department name cannot be empty');");
				out.println("location.reload();");
				out.println("</script>");
			} else {
				DepartmentService deptServ = new DepartmentService();
				ServiceResponse serRes = deptServ.createNewDepartment(dName);

				hs.setAttribute("serRes", serRes);

				response.sendRedirect("adminHome.jsp");

				/*
				 * out.println("<script type=\"text/javascript\">"); out.println("alert('" +
				 * serRes.getMessage() + ", " + serRes.getMsgDesc() + "');");
				 * out.println("location='adminHome.jsp';"); out.println("</script>");
				 */
			}
		} catch (Exception e) {
			System.out.println("ERROR @Registration Servlet: " + e.toString());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error while creating new department" + e.getMessage() + "');");
			out.println("history.back();");
			out.println("</script>");
		} finally {
			out.close();
		}

	}

}
