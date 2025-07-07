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
import crm09.entity.User;
import crm09.services.ProjectServices;
import crm09.services.UserServices;

@WebServlet(name = "getListAddTaskController", urlPatterns = "/getListAddTaskController")
public class getListAddTaskController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> listUser = new ArrayList<User>();
		UserServices userServices = new UserServices();
		listUser = userServices.getAll();
		// luu list user
		req.setAttribute("LIST_USER", listUser);
         //
		List<Projects> listProjects = new ArrayList<Projects>();
		ProjectServices projectServices = new ProjectServices();
		listProjects = projectServices.findAll();
         // luu list project
		req.setAttribute("LIST_PROJECT", listProjects);
         req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}
}
