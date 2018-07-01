package e.orz.cof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import e.orz.cof.model.User;

public class UserDao {
	private Connection conn;

	public UserDao() {
		conn = DB.getConnection();
	}

	public User queryUser(String userName) {
		User user = null;
		String sql = "select * from user where userName=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean insertUser(String userName, String password, String faceUrl) {
		String sql = "insert into user VALUES (?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, faceUrl);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
