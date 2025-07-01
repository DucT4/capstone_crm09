package crm09.controller;

import java.io.IOException;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Roles;
import crm09.entity.User;
import crm09.services.UserServices;

@WebServlet(name = "updateController", urlPatterns = "/updateController")
public class UpdateController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int roleId = Integer.parseInt(req.getParameter("idRole"));
		int userId = Integer.parseInt(req.getParameter("idUser"));
		String lastName = req.getParameter("lastName");
		String firstName = req.getParameter("firsName");
		String email = req.getParameter("email");
		String roleName = req.getParameter("roleName");
		String fullName = (firstName + " " + lastName).trim();
		User user = new User();
		user.setId(userId);
		user.setEmail(email);
		user.setFullName(fullName);
		Roles role = new Roles();
		role.setId(roleId);
		role.setName(roleName);
		UserServices userService = new UserServices();
		int rowUpdate = userService.update(user);
		if (rowUpdate > 0) {
			System.out.println("success rows update: " + roleName);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else {
			System.out.println("update failed!!!!");
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		}
	}
}










