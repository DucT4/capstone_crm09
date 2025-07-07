package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Projects;
import crm09.repository.ProjectsRepository;
import crm09.services.ProjectServices;

@WebServlet(name = "updateProjectController", urlPatterns = "/updateProjectController")
public class UpdateProjectController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Projects project = new Projects();
		project.setId(Integer.parseInt(req.getParameter("id")));
		project.setName(req.getParameter("name"));
		java.sql.Date beginDay = java.sql.Date.valueOf(req.getParameter("beginDay"));
		project.setBeginDay(beginDay);
		java.sql.Date endDay = java.sql.Date.valueOf(req.getParameter("endDay"));
		project.setEndDate(endDay);
		// goi services
		ProjectServices projectServices = new ProjectServices();
		int result = projectServices.update(project);
		if (result > 0) {
			System.out.println("update success: " + result);
			resp.sendRedirect("projectController");
		} else {
			System.out.println("update failed!!!");
			resp.sendRedirect("projectController");
		}

	}
}
