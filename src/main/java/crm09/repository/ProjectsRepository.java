package crm09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.Projects;

public class ProjectsRepository {
	// get all list
	public List<Projects> findAll() {
		List<Projects> list_Project = new ArrayList<Projects>();
		Projects project = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// create query
			String sql = "SELECT *\n" + "FROM projects";
			// create object query
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				project = new Projects();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				project.setStatus(rs.getString("status"));
				project.setBeginDay(rs.getDate("start_day"));
				project.setEndDate(rs.getDate("end_day"));
				list_Project.add(project);
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

		return list_Project;

	}

	// get Poject by id
	public Projects getProjectById(int id) {
		Connection conn = null;
		Projects project = null;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// tao query
			String sql = "SELECT *\n" + "FROM projects \n" + "WHERE id=?";
			// tao doi tg query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// execute
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				project = new Projects();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				project.setStatus(rs.getString("status"));
				project.setBeginDay(rs.getDate("start_day"));
				project.setEndDate(rs.getDate("end_day"));

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

		return project;
	}
	// detele

	public int delete(int id) {
		Connection conn = null;
		int rs = 0;
		try {
			// ket noi database
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "DELETE FROM projects\n" + "WHERE id=?";
			// tao doi tg thuc thi cau query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// thuc thi
			rs = ps.executeUpdate();
			System.out.println("rows delete: " + rs);
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

	public int update(Projects project) {
		Connection conn = null;
		int rs = 0;
		try {
			// ket noi database
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "UPDATE projects\n"
					+ "SET name=?, start_day=?, end_day=?\n"
					+ "WHERE id=?";
			// tao doi tg thuc thi cau query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, project.getName());
			ps.setDate(2, project.getBeginDay());
			ps.setDate(3, project.getEndDate());
			ps.setInt(4, project.getId());
			// thuc thi
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
}
