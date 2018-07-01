package e.orz.cof.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PictureDao {
	private Connection conn;

	public PictureDao() {
		conn = DB.getConnection();
	}

	public boolean addPicture(int blogId, String imageUrl) {
		String sql = "insert into picture VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			ps.setString(2, imageUrl);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletePicture(int blogId) {
		String sql = "delete * from user_like where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
