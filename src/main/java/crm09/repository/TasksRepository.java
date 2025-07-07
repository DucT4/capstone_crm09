package crm09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.Projects;
import crm09.entity.Tasks;
import crm09.entity.User;

public class TasksRepository {
	// find all task
	public List<Tasks> findAll() {
		Connection conn = null;
		List<Tasks> listTasks = new ArrayList<Tasks>();
		ResultSet rs = null;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "SELECT*\n" + "FROM tasks t\n" + "JOIN projects p ON t.id_project = p.id\n"
					+ "JOIN users u ON t.id_user= u.id";
			// tao doi tg query
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("kttt");
			// luu doi tg
			while (rs.next()) {
				Tasks task = new Tasks();
				task.setId(rs.getInt("id"));
				task.setName(rs.getString("name_task"));
				task.setBeginTask(rs.getDate("start_task"));
				task.setEndTask(rs.getDate("end_task"));
				task.setStatus(rs.getString("status"));
				// set value projects
				Projects project = new Projects();
				project.setId(rs.getInt("id_project"));
				project.setName(rs.getString("name"));
				// set project cho task
				task.setProject(project);
				// set value User
				User user = new User();
				user.setId(rs.getInt("id_user"));
				user.setFullName(rs.getString("fullname"));
				// set user cho task
				task.setUser(user);
				listTasks.add(task);
				System.out.println("task :" + task);
			}
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return listTasks;
	}

	// delete task
	public int delete(int id) {
		Connection conn = null;
		int rs = 0;
		try {
			// connect databasee
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "DELETE FROM tasks WHERE id =?";
			// tao doi tg query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// thuc thi
			rs = ps.executeUpdate();
			System.out.println("row delete :" + rs);
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return rs;
	}

	// update
	public int update(Tasks task) {
		Connection conn = null;
		int rs = 0;
		try {
			// connect databasee
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "UPDATE tasks t\n" + "JOIN projects p ON t.id_project = p.id\n"
					+ "JOIN users u ON t.id_user = u.id\n" + "SET \n" + "    t.name_task = ?,\n"
					+ "    t.start_task = ?,\n" + "    t.end_task = ?,\n" + "    t.status = ?,\n" + "    p.name = ?,\n"
					+ "    u.fullname = ?\n" + "WHERE t.id = ?";
			// tao doi tg query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, task.getName());
			ps.setDate(2, task.getBeginTask());
			ps.setDate(3, task.getEndTask());
			ps.setString(4, task.getStatus());
			ps.setString(5, task.getProject().getName());
			ps.setString(6, task.getUser().getFullName());
			ps.setInt(7, task.getId());
			// thuc thi
			rs = ps.executeUpdate();
			System.out.println("row update :" + rs);
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return rs;
	}

	// add task
	public int save(Tasks task) {
		Connection conn = null;
		int rs = 0;
		try {
			// connect database
			conn = MySQLConfig.getConnection();
			// tao cau query
			String sql = "INSERT INTO tasks\n" + "(id, name_task, start_task, end_task, status, id_project, id_user)\n"
					+ "VALUES(?, ?,?, ?, ?, ?, ?)";
			// tao doi tg thuc thi query
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, task.getId());
			ps.setString(2, task.getName());
			ps.setDate(3, task.getBeginTask());
			ps.setDate(4, task.getEndTask());
			ps.setString(5, task.getStatus());
			ps.setInt(6, task.getProject().getId());
			ps.setInt(7, task.getUser().getId());
			// thuc thi
			rs = ps.executeUpdate();
			System.out.println("rs  :" + rs);
 		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return rs;
	}

}
