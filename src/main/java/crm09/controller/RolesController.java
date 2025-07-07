package crm09.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Roles;
import crm09.services.RoleServices;

@WebServlet(name = "rolesController", urlPatterns = "/rolesController")
public class RolesController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Roles> listRole = new ArrayList<>();
		RoleServices roleServices = new RoleServices();
		int result = roleServices.setActive();
		listRole = roleServices.findAll();
		System.out.println("list role: " + listRole.size());
		req.setAttribute("LIST_ROLE", listRole);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);

	}
}
