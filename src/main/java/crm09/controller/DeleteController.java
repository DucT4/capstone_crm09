
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

@WebServlet(name = "deleteController", urlPatterns = "/deleteController")
public class DeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println("ud " + id);
		UserServices userServices = new UserServices();
		User user = new User();
		user = userServices.getUserById(id);
		System.out.println("user" + user);
		if (user != null) {
			int row = userServices.delete(user);
			System.out.println("rows delete: " + row);
			// get list user
			List<User> listUser = new ArrayList<User>();
			listUser = userServices.getAll();
			HttpSession s = req.getSession();
			s.setAttribute("LIST_USER", listUser);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else {
			System.out.println("delete failed!!!");
			req.setAttribute("FAILED", "DELETE LOST");
		}

	}
}
