package e.orz.cof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UpNumDao {
	private Connection conn;

	public UpNumDao() {
		conn = DB.getConnection();
	}

	public int queryUpNum(int blogId) {
		int num = 0;
		String sql = "select upNum from blog where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public boolean checkUserLike(String userName, int blogId) {

		String sql = "select count(*) from user_like where userName=? and blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setInt(2, blogId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addUpNum(String userName, int blogId) {
		int oldNum = queryUpNum(blogId);
		String sql = "update blog set upNum=? where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, oldNum + 1);
			ps.setInt(2, blogId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean minusUpNum(String userName, int blogId) {
		int oldNum = queryUpNum(blogId);
		String sql = "update blog set upNum=? where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, oldNum - 1);
			ps.setInt(2, blogId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteLike(String userName, int blogId) {
		String sql = "delete from user_like where userName=? and blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setInt(2, blogId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean addLike(String userName, int blogId) {
		String sql = "insert into user_like VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setInt(2, blogId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
