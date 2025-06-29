package crm09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.Roles;
import crm09.entity.User;
import crm09.utils.Md5Helper;

public class UserRepository {
	public int save(User user) {
		// save: ham tao du lieu
		int count = 0;
		String query = "INSERT INTO users (email, password, id_role, fullname, phone) \n" + "VALUES(?,?,?,?,?)";

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, Md5Helper.getMd5(user.getPassword()));
			Roles role = new Roles();
			statement.setInt(3, user.getRoles().getId());
			statement.setString(4, user.getFullName());
			statement.setString(5, user.getPhone());
			count = statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Lỗi save " + e.getMessage());
		}

		return count;
	}

	public User checkLogin(String email, String password) {
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			// connect databse
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "\n" + "SELECT*\n" + "FROM users u \n" + "JOIN roles r ON u.id_role = r.id\n"
					+ "where u.email= ? and u.password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, Md5Helper.getMd5(password));
			// thuc thi
			rs = ps.executeQuery();
			// luu lai
			while (rs.next()) {
				String rsEmail = rs.getString("email");
				String rsPassword = rs.getString("password");
				String rsPhone = rs.getString("phone");
				String rsFullName = rs.getString("fullname");
				Roles role = new Roles();
				role.setName(rs.getString("name"));
				role.setId(rs.getInt("id_role"));
				user = new User(rsEmail, role, rsPhone, rsPassword, rsFullName);
			}
		} catch (Exception e) {
			System.out.println("Check login " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return user;
	}
}
