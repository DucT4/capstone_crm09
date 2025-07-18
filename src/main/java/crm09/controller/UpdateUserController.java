package crm09.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm09.entity.Roles;
import crm09.entity.User;
import crm09.services.UserServices;

@WebServlet(name = "updateController", urlPatterns = "/updateController")
public class UpdateUserController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("idUser"));
		String lastName = req.getParameter("lastName");
		String firstName = req.getParameter("firstName");
		String email = req.getParameter("email");	
		String roleName = req.getParameter("roleName");
		System.out.println("role name: " + roleName);
		String fullName = (firstName + " " + lastName).trim();
		Roles role = new Roles();
		   
		  if (roleName.equalsIgnoreCase("ADMIN")) {
			  role.setId(1);  
		  } else if(roleName.equalsIgnoreCase("LEAD")) {
			  role.setId(2);  
		  } else if(roleName.equalsIgnoreCase("EMPLOYEE")) {
			  role.setId(3);  
		  }
//		  role.setName(roleName);
	   	System.out.println("role ID  + role name: " + role.getId() +  roleName);
		User user = new User(userId, email, role, fullName);

		
		UserServices userService = new UserServices();
		int rowUpdate = userService.update(user);
		if (rowUpdate > 0) {
			System.out.println("success rows update: " + roleName);
			resp.sendRedirect("userTableController");
		} else {
			System.out.println("update failed!!!!");
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		}
	}
}










