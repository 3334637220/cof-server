package e.orz.cof;

import java.util.ArrayList;

public class Content {
	private String userName;
	private String portrait;
	private String text;
	private int upNum;
	private ArrayList<String> photoList;
	private ArrayList<String> commentList;

	public Content(String userName) {
		this.userName = userName;
		photoList = new ArrayList<>();
		commentList = new ArrayList<>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getPhotoList() {
		return photoList;
	}

	public ArrayList<String> getCommentList() {
		return commentList;
	}

	public void setPhotoList(ArrayList<String> photoList) {
		this.photoList = photoList;
	}

	public void setCommentList(ArrayList<String> commentList) {
		this.commentList = commentList;
	}

	public void addPhoto(String photo) {
		photoList.add(photo);
	}

	public void addComment(String comment) {
		commentList.add(comment);
	}

	public void setUpNum(int upNum) {
		this.upNum = upNum;
	}

	public int getUpNum() {
		return upNum;
	}

}
