package crm09.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm09.entity.User;
import crm09.services.UserServices;

@WebServlet(name = "userTableController", urlPatterns = "/userTableController")
public class UserTableController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> listUser = new ArrayList<User>();
		UserServices userServices = new UserServices();
		listUser = userServices.getAll();
		req.setAttribute("LIST_USER", listUser);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
}
