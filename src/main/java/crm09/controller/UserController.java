package crm09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Roles;
import crm09.entity.User;
import crm09.services.UserServices;

@WebServlet(name = "userController", urlPatterns = {"/user-add"})
public class UserController extends HttpServlet{
	
	private UserServices userServices = new UserServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Roles> listRole = userServices.getAllRole();
		req.setAttribute("listRoles", listRole);
		
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullName = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		Roles role_Id = new Roles();
		role_Id.setId(Integer.parseInt(req.getParameter("roleId")));
		User user = new User(email, role_Id, phone, password, fullName);
		boolean isSuccess = userServices.insertuser(user);
		if (isSuccess) {
		      System.out.println("sucesss");
		} else {
		    System.out.println("failed!");
		}
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
}
