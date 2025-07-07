package crm09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.taglibs.standard.extra.spath.Step;

import crm09.config.MySQLConfig;
import crm09.entity.Roles;
import crm09.entity.User;
import crm09.services.UserServices;

public class RolesRepository {
	// get all list
	public List<Roles> findAll() {
		List<Roles> listRole = new ArrayList<Roles>();

		String query = "SELECT * FROM roles";

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int idRole = resultSet.getInt("id");
				String nameRole = resultSet.getString("name");
				String descriptionRole = resultSet.getString("description");
				Boolean isActive = resultSet.getBoolean("isActive");
				Roles role = new Roles(idRole, nameRole, descriptionRole, isActive);
				listRole.add(role);
			}

		} catch (Exception e) {
			System.out.println("Lỗi findAll " + e.getMessage());
		}

		return listRole;
	}

	// getRoleById
	public Roles getRoleById(int id) {
		Roles role = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// create query
			String sql = "SELECT * FROM roles WHERE id =?";
			// create object excute query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// execute
			rs = ps.executeQuery();
			// save role
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				int rsID = rs.getInt("id");
				role = new Roles(rsID, name, description);
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
		return role;
	}

	// delete
	public int delete(Roles role) {
		Connection conn = null;
		int rs = 0;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// create query
			String sql = "DELETE FROM roles\n" + "WHERE id=?";
			// create object query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, role.getId());

			// execute
			ps.executeUpdate();
			System.out.println("row delete: " + rs);
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

	// update
	public int update(Roles role) {
		Connection conn = null;
		int rs = 0;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// create sql
			String sql = "UPDATE roles\n" + "SET name=?, description=?\n" + "WHERE id=?";
			// create object query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, role.getName());
			ps.setString(2, role.getDescription());
			ps.setInt(3, role.getId());
			// execute
			rs = ps.executeUpdate();
			System.out.println("rows update: " + rs);
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

	// check active role
	// nếu khôgn có user nào hoạt động trên idRole thì có set isActive về 0
	public int setActive() {
		Connection conn = null;
		// bien dung de dem idRole
		int count = 0;
		int rs = 0;
		try {
			conn = MySQLConfig.getConnection();
			Roles role = new Roles();
			for (Roles r : this.findAll()) {
				UserServices userServices = new UserServices();
				for (User u : userServices.getAll()) {
					if (u.getRoles().getId() == r.getId()) {
						count++;
					}
				}
			
				// query
				String sql = "UPDATE roles\n" + "SET isActive=?\n" + "WHERE id=?";
				// create object query
				PreparedStatement ps = conn.prepareStatement(sql);
				if (count > 0) {
					ps.setBoolean(1, true);

				} else {
					ps.setBoolean(1, false);
				}
				ps.setInt(2, r.getId());
				//execute ps
				rs = ps.executeUpdate();
				//set gia tri lai cho bien count
				count =0;
				System.out.println("ac role 1: " + r.isActive());
			}
		} catch (Exception e) {
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return rs;
	}

}
