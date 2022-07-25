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

/**
 * Servlet implementation class UpdateProfileServlet Update user profile. Pages:
 * profile.jsp
 */
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProfileServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserService userService = new UserService();
		HttpSession hs = request.getSession(false);
		try {
			User user = (User) hs.getAttribute("userDetails");

			String name = request.getParameter("name");
			String dID = request.getParameter("dID");
			String gender = request.getParameter("gender");
			String mobileNo = request.getParameter("mobileNo");
			String emailId = request.getParameter("emailId");
			String dob = request.getParameter("dob");
			String location = request.getParameter("location");
			String uAbout = request.getParameter("uAbout");

			if (!MyChecker.isStringValid(name)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Name must of atleast 4 characters!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!MyChecker.isValidAge(dob)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Age cannot be less than 18!');");
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
			} else {
				User updatedUserDetails = new User();
				updatedUserDetails.setuId(user.getuId());
				updatedUserDetails.setuName(name);
				updatedUserDetails.setuGender(gender);
				updatedUserDetails.setuEmailId(emailId);
				updatedUserDetails.setuMobileNo(mobileNo);
				updatedUserDetails.setuDob(dob);
				updatedUserDetails.setuDepartmentId(Integer.valueOf(dID));
				updatedUserDetails.setuLocation(location);
				updatedUserDetails.setuAbout(uAbout.trim());
				updatedUserDetails.setRole("user");
				ServiceResponse serRes = userService.updateUserDetails(updatedUserDetails);

				if (serRes.isSuccess()) {
					hs.setAttribute("serRes", serRes);
					hs.setAttribute("userDetails", serRes.getObj());
					response.sendRedirect("./userHome.jsp");
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('" + serRes.getMessage() + " " + serRes.getMsgDesc() + "');");
					out.println("history.back();");
					out.println("</script>");
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR @UpdateProfileServlet: " + e.toString());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error while updating your profile');");
			out.println("history.back();");
			out.println("</script>");
		} finally {
			out.close();
		}
	}

}
