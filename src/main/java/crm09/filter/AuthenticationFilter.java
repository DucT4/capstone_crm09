package crm09.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


                                     //urlPattern: filter sẽ kích hoạt khi người  gọi đường dẫn 

@WebFilter(filterName =  "authenticationFilter" ,urlPatterns = {"/user-add"})
public class AuthenticationFilter extends HttpFilter{
  @Override
protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
		throws IOException, ServletException {
	 //code chay o day
	Cookie[] cookie = req.getCookies();
	String role="";
	for (Cookie cookie2 : cookie) {
		String name = cookie2.getName();
		 String value = cookie2.getValue();
		 if (name.equals("role")) {
			 role = value;
		 }
	}
	  //cho phép đi tiêps
	if (role.equals("ADMIN")){
		  // trong 1 doFilter thi chi dc phep co 1 chain
		  chain.doFilter(req, res);
	} else {
		res.sendRedirect("login");
	}
	
	
}
}








