package pageservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.JobService;

import java.io.IOException;
import java.io.PrintWriter;

import entities.Job;
import entities.ServiceResponse;

/**
 * Servlet implementation class CreateJobServlet
 * Used to create new job by admin
 * pages used: adminAddNewJob.jsp
 */
public class CreateJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateJobServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession hs = request.getSession(false);

		try {
			String dId = request.getParameter("dID");
			String jTitle = request.getParameter("jTitle");
			String jDesc = request.getParameter("jDesc");
			String jEleCria = request.getParameter("jEleCria");
			String jEdu = request.getParameter("jEdu");
			String jExp = request.getParameter("jExp");
			String jLoc = request.getParameter("jLoc");
			String jSal = request.getParameter("jSal");
			String jLastDT = request.getParameter("jLastDT");
			
			Job job = new Job();
			job.setjDepartmentId(Integer.valueOf(dId));
			job.setjTitle(jTitle);
			job.setjDescription(jDesc);
			job.setjEleCria(jEleCria);
			job.setjEduQual(jEdu);
			job.setjExpReq(jExp);
			job.setjLocation(jLoc);
			job.setjSalary(jSal);
			job.setjLastDT(jLastDT);

			JobService jobSer = new JobService();
			ServiceResponse serRes = jobSer.createNewJob(job);
			
			hs.setAttribute("serRes" ,serRes); 
			
			response.sendRedirect("adminJobsPage.jsp");
		} catch (Exception e) {
			System.out.println(e.toString());
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error while creating new job');");
			out.println("history.back();");
			out.println("</script>");
		}

	}

}
