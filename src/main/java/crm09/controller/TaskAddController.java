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

@WebServlet(name = "taskAddController", urlPatterns = "/taskAddController")
public class TaskAddController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get param
		Tasks task = new Tasks();
		Projects project = new Projects();

		project.setId(Integer.parseInt(req.getParameter("projectId")));
		// set project id
		task.setProject(project);
		User user = new User();
		user.setId(Integer.parseInt(req.getParameter("userId")));
		// set user id
		task.setUser(user);
		task.setName(req.getParameter("nameTask"));
		// chuyen doi string -> Date
		java.sql.Date beginTask = java.sql.Date.valueOf(req.getParameter("beginTask"));
		// set begin task
		task.setBeginTask(beginTask);
		java.sql.Date endTask = java.sql.Date.valueOf(req.getParameter("endTask"));
		// set end task
		task.setEndTask(endTask);
		// goi services
		TaskServices taskServices = new TaskServices();
		int result = taskServices.save(task);
		if (result > 0) {
			System.out.println("add success: " + result);
			resp.sendRedirect("getListAddTaskController");
		} else {
			System.out.println("add failed");
			resp.sendRedirect("getListAddTaskController");
		}
	}
}
