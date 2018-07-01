package e.orz.cof;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DAO {

	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/cof?useSSL=True";
		String username = "root";
		String password = "123456";
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void addContent(Content content) {
		Connection conn = getConn();
		String sql = "insert into content VALUES (?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, content.getUserName());
			ps.setString(2, content.getPortrait());
			ps.setString(3, content.getText());
			ps.setInt(4, 0);
			ps.executeUpdate();
			for (String uri : content.getPhotoList()) {
				addPhoto(content.getUserName(), uri);
			}
			System.out.println("插入" + content.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addPhoto(String userName, String uri) {
		Connection conn = getConn();
		String sql = "insert into photo VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, uri);
			ps.executeUpdate();
			System.out.println("插入" + uri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Content> getContents() {
		ArrayList<Content> list = new ArrayList<Content>();
		Connection conn = getConn();
		String sql = "select * from content";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Content content = new Content(rs.getString(1));
				content.setPortrait(rs.getString(2));
				content.setText(rs.getString(3));
				content.setUpNum(rs.getInt(4));
				content.setPhotoList(getPhotos(content.getUserName()));
				content.setCommentList(getComments(content.getUserName()));
				list.add(content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private static ArrayList<String> getPhotos(String userName) {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = getConn();
		String sql = "SELECT uri from photo where userName=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	private static ArrayList<String> getComments(String userName) {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = getConn();
		String sql = "SELECT text from comment where userName=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
