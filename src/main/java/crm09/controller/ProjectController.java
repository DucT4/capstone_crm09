package crm09.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Projects;
import crm09.services.ProjectServices;
@WebServlet(name ="projectController" ,urlPatterns = "/projectController")
public class ProjectController extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//tao list
	 List<Projects > listProject = new ArrayList<Projects>();
	 //new services
	ProjectServices projectServices = new ProjectServices();
	// goi findAll -> add vao list
	 listProject = projectServices.findAll();
	 System.out.println("list project: " + listProject.size());
	 //luu vao requestScope
	 req.setAttribute("LIST_PROJECT", listProject);
	 req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	 
}
}
