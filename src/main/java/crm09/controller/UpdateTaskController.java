package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Projects;
import crm09.entity.Tasks;
import crm09.entity.User;
import crm09.services.TaskServices;

@WebServlet(name = "updateTaskController", urlPatterns = "/updateTaskController")
public class UpdateTaskController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lay param
		Tasks task = new Tasks();
		task.setId(Integer.parseInt(req.getParameter("id")));
		task.setName(req.getParameter("name"));
		Projects project = new Projects();
		project.setName(req.getParameter("nameProject"));
		// set project
		task.setProject(project);
		User user = new User();
		user.setFullName(req.getParameter("worker"));
		// set user
		task.setUser(user);
		task.setStatus(req.getParameter("status"));
		// chuyen doi string sang Date
		java.sql.Date begigTask = java.sql.Date.valueOf(req.getParameter("beginTask"));
		java.sql.Date endTask = java.sql.Date.valueOf(req.getParameter("endTask"));
		// set date
		task.setBeginTask(endTask);
		task.setEndTask(endTask);
		// goi services
		TaskServices taskServices = new TaskServices();
		int result = taskServices.update(task);
		if (result > 0) {
			System.out.println("update success: " + result);
			resp.sendRedirect("taskController");
		} else {
			System.out.println("update failed!!!");
			resp.sendRedirect("taskController");
		}
	}

}
