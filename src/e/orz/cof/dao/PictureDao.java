package e.orz.cof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import e.orz.cof.model.Picture;

public class PictureDao {
	private Connection conn;

	public PictureDao() {
		conn = DB.getConnection();
	}

	public boolean addPicture(int blogId, String imageUrl) {
		String sql = "insert into blog_picture VALUES (?,?)";
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
		String sql = "delete from blog_picture where blogId=?";
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

	public ArrayList<Picture> getPicturesById(int blogId) {
		ArrayList<Picture> pictures = new ArrayList<>();
		String sql = "select * from blog_picture where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Picture picture = new Picture();
				picture.setBlogId(blogId);
				picture.setImageUrl(rs.getString(2));
				pictures.add(picture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictures;
	}

}
