package crm09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm09.config.MySQLConfig;
import crm09.entity.Roles;
import crm09.utils.Md5Helper;

public class UserRepository {
	public int save(String email, String password, int roleID, String fullname, String phone) {
		// save: ham tao du lieu
		int count = 0;
		String query = "INSERT INTO users (email, password, id_role, fullname, phone) \n" + "VALUES(?,?,?,?,?)";

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, Md5Helper.getMd5(password));
            statement.setInt(3, roleID);
            statement.setString(4, fullname);
            statement.setString(5, phone);
			count = statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Lỗi save " + e.getMessage());
		}

		return count;
	}
}
