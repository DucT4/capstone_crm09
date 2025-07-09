package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.entity.Roles;
import crm09.services.RoleServices;
@WebServlet (name ="roleAddController" , urlPatterns ="/roleAddController")
public class RoleAddController extends HttpServlet {
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 Roles role = new Roles();
  role.setName(req.getParameter("roleName"));
role.setDescription(req.getParameter("decription"));
RoleServices roleServices = new RoleServices();
int result = roleServices.save(role);
if (result>0) {
	System.out.println("add thanh cong: " + result);
 req.getRequestDispatcher("role-add.jsp").forward(req, resp);
} else {
	System.out.println("add that bai ");
	 req.getRequestDispatcher("role-add.jsp").forward(req, resp);
}
}
}
