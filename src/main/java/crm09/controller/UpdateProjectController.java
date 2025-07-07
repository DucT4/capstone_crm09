package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.repository.ProjectsRepository;
@WebServlet (name ="updateProjectController" , urlPatterns = "/updateProjectController")
public class UpdateProjectController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ProjectsRepository projectRepository = new ProjectsRepository();
	resp.sendRedirect("projectController");
}
}
