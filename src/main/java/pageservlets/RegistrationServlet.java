package pageservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import utils.MyChecker;

import java.io.IOException;
import java.io.PrintWriter;

import entities.ServiceResponse;
import entities.User;

/*
 * Used for registration purpose
 * Page: registrationPage.jsp
 * */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserService userService = new UserService();
		HttpSession hs = request.getSession();
		try {
			String name = request.getParameter("name");
			String dID = request.getParameter("dID");
			String gender = request.getParameter("gender");
			String mobileNo = request.getParameter("mobileNo");
			String emailId = request.getParameter("emailId");
			String dob = request.getParameter("dob");
			String password = request.getParameter("password");
			String cPassword = request.getParameter("cPassword");

			if (!MyChecker.isStringValid(name)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Name must of atleast 4 characters!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!MyChecker.isValidAge(dob)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('For registration your age must be 18!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!MyChecker.isMobileNoValid(mobileNo)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please enter correct mobile no. of 10 digits!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!MyChecker.isEmailValid(emailId)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please enter correct email id!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!MyChecker.isPasswordValid(password)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Password must contain minimum 6 characters, atleast one letter and one number!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!password.equals(cPassword)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Passwords does not match!');");
				out.println("history.back();");
				out.println("</script>");
			} else {

				User userDetails = new User();
				userDetails.setuName(name);
				userDetails.setuDepartmentId(Integer.valueOf(dID));
				userDetails.setuGender(gender);
				userDetails.setuDob(dob);
				userDetails.setuMobileNo(mobileNo);
				userDetails.setuEmailId(emailId);
				userDetails.setuPassword(String.valueOf(password.hashCode()));
				ServiceResponse serviceResponse = userService.register(userDetails);

				hs.setAttribute("serRes", serviceResponse);
				response.sendRedirect("signInPage.jsp");

			}
		} catch (Exception e) {
			System.out.println(e.toString());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error while registration');");
			out.println("history.back();");
			out.println("</script>");
		} finally {
			out.close();
		}
	}

}
