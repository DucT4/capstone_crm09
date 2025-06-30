package crm09.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.config.MySQLConfig;
import crm09.entity.Roles;
import crm09.entity.User;
import crm09.services.UserServices;
import crm09.utils.Md5Helper;

@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	/**
	 * Bước 1 : Phải suy nghĩ được logic ( nghiệp vụ ) của tính năng là đang muốn
	 * làm gì. Bước 2 : Xác định câu truy vấn để đáp ứng cho nghiệp vụ đó. Bước 3 :
	 * Tiến hành code theo logic nghiệp vụ yêu cầu
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bước 1 : Lấy tham số email và password bên UI login.jsp khi người dùng click
		// button đăng nhập
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserServices userServices = new UserServices();
		User user = new User();
		user = userServices.checkLogin(email, password);
		System.out.println(user);
		if (user != null) {
			System.out.println("Login successfull!!");
			String name = user.getRoles().getName();
			System.out.println("role :" + name);
			Cookie cookieRole = new Cookie("role", name);
			resp.addCookie(cookieRole);
			// get list user 
			List<User> listUser = new ArrayList<User>();
			 listUser= userServices.getAll();
			 System.out.println("list user: " +listUser.get(0).getFirstName() );
			req.setAttribute("LIST_USER", listUser);
			System.out.println("user: " + listUser.size());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else {
			System.out.println("Login Failed!!!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}

	}

}
