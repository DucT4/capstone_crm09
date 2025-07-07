package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.services.ProjectServices;
@WebServlet(name="deleteProjectController" , urlPatterns = "/deleteProjectController")
public class DeleteProjectController extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id = Integer.parseInt(req.getParameter("id"));
	//goi projectServices
	ProjectServices projectServices = new ProjectServices();
	//delete
	int result = projectServices.delete(id);
	 if (result>0 ) {
		 System.out.println("delete success: " + result);
		 resp.sendRedirect("projectController");
	 } else {
		 System.out.println("delete faild!!!");
		 resp.sendRedirect("projectController");
	}
}
}
