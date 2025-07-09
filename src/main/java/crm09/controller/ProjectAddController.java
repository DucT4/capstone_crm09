package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Projects;
import crm09.services.ProjectServices;
@WebServlet(name="projectAddController" , urlPatterns = "/projectAddController")
public class ProjectAddController extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
  Projects project = new Projects();
  //name
   project.setName(req.getParameter("nameProject"));
   //begin day
    java.sql.Date beginDate = java.sql.Date.valueOf(req.getParameter("beginDay"));
	project.setBeginDay(beginDate);
	//end day
	java.sql.Date endDay = java.sql.Date.valueOf(req.getParameter("endDay"));
	project.setEndDate(endDay);
	//goi services
	ProjectServices projectServices = new ProjectServices();
	int result = projectServices.save(project);
	if (result>0) {
		System.out.println("add thanh cong " + result);
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	} else {
		System.out.println("add that bai ");
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}
	 
}
}
