package e.orz.cof.model;

import java.util.Date;

public class Blog {
	private int blogId;
	private String userName;
	private String text;
	private int upNum;
	private Date time;
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getUpNum() {
		return upNum;
	}

	public void setUpNum(int upNum) {
		this.upNum = upNum;
	}

	public Blog() {
	}

	public Blog(int blogId, String userName, String text) {
		this.blogId = blogId;
		this.userName = userName;
		this.text = text;
	}

	public Blog(int blogId, String userName, String text, int upNum) {
		this.blogId = blogId;
		this.userName = userName;
		this.text = text;
		this.upNum = upNum;
	}

	public Blog(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
