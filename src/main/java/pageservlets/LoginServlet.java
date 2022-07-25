package pageservlets;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class LoginServlet
 * 
 * Used for login check where user is authenticated
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		UserService userService = new UserService();
		HttpSession hs = request.getSession();

		try {
			String emailId = request.getParameter("userEmail");
			String password = request.getParameter("userPassword");

			User userDetails = new User();

			if (!MyChecker.isEmailValid(emailId)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please enter correct email id!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (!MyChecker.isPasswordValid(password)) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Password must contain minimum 6 characters, atleast one letter and one number!');");
				out.println("history.back();");
				out.println("</script>");
			} else if (emailId.equals("admin@ims.com") && password.equals("password@123")) {
				userDetails.setRole("admin");
				userDetails.setValid(true);

				hs.setAttribute("userDetails", userDetails);

				RequestDispatcher rd = request.getRequestDispatcher("adminHome.jsp");
				rd.forward(request, response);
			} else {
				ServiceResponse serviceResponse = userService.login(emailId, password);
				if (serviceResponse.isSuccess()) {
					hs.setAttribute("userDetails", serviceResponse.getObj());

					response.sendRedirect("./userHome.jsp");
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('" + serviceResponse.getMessage() + " " + serviceResponse.getMsgDesc() + "');");
					out.println("history.back();");
					out.println("</script>");
				}
			}
		} catch (Exception e) {
			System.out.println("Error @ LoginServlet: " + e.toString());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error while sign in');");
			out.println("history.back();");
			out.println("</script>");
		} finally {
			out.close();
		}
	}

}
