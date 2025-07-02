
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

	// getAllUser
	public List<User> getAll() {
		List<User> listUser = new ArrayList<User>();
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLConfig.getConnection();
			String sql = "\n" + "SELECT*\n" + "FROM users u \n" + "JOIN roles r ON u.id_role = r.id\n";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int rsId = rs.getInt("id");
				String rsEmail = rs.getString("email");
				String rsPassword = rs.getString("password");
				String rsPhone = rs.getString("phone");
				String rsFullName = rs.getString("fullname");
				Roles role = new Roles();
				role.setName(rs.getString("name"));
				role.setId(rs.getInt("id_role"));
				// tach chuoi
				String[] parts = rsFullName.trim().split("\\s+");

				String lastName = parts[0];
				String firstName = parts[parts.length - 1];

				user = new User(rsId, rsEmail, role, rsPhone, rsPassword, rsFullName, firstName, lastName);
				listUser.add(user);
			}
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return listUser;
	}

	// getUserById
	public User getUserById(String userID) {
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			// connection database
			conn = MySQLConfig.getConnection();
			// create query
			String sql = "\n" + "SELECT*\n" + "FROM users u \n" + "JOIN roles r \n" + "ON u.id_role= r.id\n"
					+ "WHERE u.id = ?";
			// create object quesry
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userID));
			// excute
			rs = ps.executeQuery();
			// save data
			while (rs.next()) {
				String rsEmail = rs.getString("email");
				String rsPassword = rs.getString("password");
				String rsPhone = rs.getString("phone");
				String rsFullName = rs.getString("fullname");
				Roles role = new Roles();
				role.setName(rs.getString("name"));
				int rsID = rs.getInt("id");

				user = new User(rsID, rsEmail, role, rsPhone, rsPassword, rsFullName);

			}
		} catch (Exception e) {
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

	// insert user
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

	// check login
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

	// delete user
	public int delete(User user) {
		Connection conn = null;
		int rs = 0;
		try {
			conn = MySQLConfig.getConnection();
			String sql = "DELETE FROM users\n" + "WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			rs = ps.executeUpdate();
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e.getMessage());
			}
		}
		return rs;
	}

	// update user
	public int update(User user) {
		Connection conn = null;
		int rs = 0;
		try {

			// connect database
			conn = MySQLConfig.getConnection();
			// create query
			String sqlUser = "UPDATE users\n"
					          + "SET email=?, id_role=?, fullname=?\n"
					          + "WHERE id=?";
			// create object query
			// user
			PreparedStatement psUser = conn.prepareStatement(sqlUser);
			psUser.setString(1, user.getEmail());
			psUser.setInt(2, user.getRoles().getId());
			//kiem tra fullname
			System.out.println("fullname" +user.getFullName());
			psUser.setString(3, user.getFullName());
			psUser.setInt(4, user.getId());
			 rs = psUser.executeUpdate();
			System.out.println("updte user");
			// role
//			String sqlRole = "UPDATE roles\n" + "SET name=?\n" + "WHERE id=?";
//			PreparedStatement psRole = conn.prepareStatement(sqlRole);
//			psRole.setString(1, user.getRoles().getName());
//			psRole.setInt(2, user.getRoles().getId());
			
   System.out.println("row update : " + rs);
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return rs;
	}
}
