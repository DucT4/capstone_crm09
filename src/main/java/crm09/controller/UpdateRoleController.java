package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Roles;
import crm09.services.RoleServices;
@WebServlet (name="updateRoleController" , urlPatterns = "/updateRoleController")
public class UpdateRoleController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String roleName = req.getParameter("name");
		 String description = req.getParameter("description");
		int roleID = Integer.parseInt(req.getParameter("id"));
		RoleServices roleServices = new RoleServices();
		Roles role = new Roles(roleID, roleName, description);
		int result =roleServices.update(role);
		if (result>0) {
			System.out.println("update success: " + result);
			resp.sendRedirect("rolesController");
		}  else {
			System.out.println("update failed!!! " );
			resp.sendRedirect("rolesController");
		}
		
	}
}
