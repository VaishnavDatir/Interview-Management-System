package pageservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import entities.ServiceResponse;
import entities.User;


/*
 * Apply To job SERVELET used when user clicks apply to job
 * PAGES in use: jobDetail.jsp
 * */
public class ApplyToJob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplyToJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			int jId = Integer.parseInt(request.getParameter("jId"));

			HttpSession hs = request.getSession(false);
			User user = (User) hs.getAttribute("userDetails");

			UserService userSer = new UserService();

			ServiceResponse serRes = userSer.userApplyToJob(user.getuId(), jId);
			hs.setAttribute("serRes", serRes);
			
			response.sendRedirect("userHome.jsp");
		} catch (Exception e) {
			System.out.println("Error @ ApplyToJobServlet: " + e.toString());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error while applying to job try again later');");
			out.println("history.back();");
			out.println("</script>");
		}finally {
			out.close();
		}

	}

}
