package crm09.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Tasks;
import crm09.services.TaskServices;
@WebServlet(name ="taskController" , urlPatterns = "/taskController")
public class TasksController extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<Tasks> listTask = new ArrayList<Tasks>();
	TaskServices taskServices = new TaskServices();
	//lay list
	listTask= taskServices.findAll();
	//kiem tra
	System.out.println("list task: " + listTask);
	//luu vao requestScope
	req.setAttribute("LIST_TASK", listTask);
	//chuyen huong
	req.getRequestDispatcher("task.jsp").forward(req, resp);
}
}
