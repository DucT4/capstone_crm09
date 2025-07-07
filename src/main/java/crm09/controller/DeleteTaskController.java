package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.services.TaskServices;
@WebServlet(name ="deleteTaskController", urlPatterns = "/deleteTaskController")
public class DeleteTaskController extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id = Integer.parseInt(req.getParameter("id"));
	TaskServices taskServices = new TaskServices();
	//exe detele
	int result = taskServices.delete(id);
	if (result>0) {
		System.out.println("delete success:" + result);
		resp.sendRedirect("taskController");
	} else {
		System.out.println("delete failed!!" );
		resp.sendRedirect("taskController");
	}
}
}
