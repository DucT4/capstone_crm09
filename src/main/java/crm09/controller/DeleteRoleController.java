package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Roles;
import crm09.services.RoleServices;

@WebServlet(name = "deleteRoleController", urlPatterns = "/deleteRoleController")
public class DeleteRoleController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int roleID = Integer.parseInt(req.getParameter("id"));
		RoleServices roleServices = new RoleServices();
		Roles role = roleServices.getRoleById(roleID);
		System.out.println("role: " + role);
		int result = roleServices.delete(role);
		System.out.println("result: " + result);
		if (result>0) {
			System.out.println("delete success"); 
			resp.sendRedirect("rolesController");
		} else {
			 System.out.println("delete failed!!");
			 resp.sendRedirect("rolesController");
		}
		System.out.println("result: " + result);
		
	}
}
