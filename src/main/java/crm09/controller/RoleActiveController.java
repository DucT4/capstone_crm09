package crm09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm09.services.RoleServices;



@WebServlet(name="roleActiveController" , urlPatterns = "/roleActiveController")
public class RoleActiveController extends HttpServlet{
       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//cap nhat trang thai cua bang roles sau do hien thi len role-table.jsp
    	   RoleServices roleServices = new RoleServices();
    	   System.out.println("role active" );
    	   int result = roleServices.setActive();
    	   System.out.println("result: " + result);
    		req.getRequestDispatcher("role-table.jsp").forward(req, resp);

    }
}
