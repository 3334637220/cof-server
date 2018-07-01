package e.orz.cof;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CofService {

	public static String getContents() {
		return listToJson(DAO.getContents()).toString();
	}

	public static JSONArray listToJson(List<Content> list) {
		JSONArray jsonArray = new JSONArray();
		for (Content c : list) {
			JSONObject jo = new JSONObject();
			jo.put("userName", c.getUserName());
			jo.put("portrait", c.getPortrait());
			jo.put("text", c.getText());
			jo.put("upNum", c.getUpNum());
			JSONArray comments = new JSONArray();
			for (String s : c.getCommentList()) {
				comments.add(c);
			}
			jo.put("comments", comments);
			JSONArray photos = new JSONArray();
			for (String s : c.getPhotoList()) {
				photos.add(s);
			}
			jo.put("photos", photos);
			jsonArray.add(jo);
		}

		return jsonArray;

	}

	public static void addContents(String fileName) {
		try {
			Scanner scan;
			scan = new Scanner(new FileInputStream(new File(fileName)));
			while (scan.hasNextLine()) {
				String userName = scan.nextLine();
				Content content = new Content(userName);
				content.setText(scan.nextLine());
				content.setPortrait(scan.nextLine());
				String s;
				while (scan.hasNextLine() && !(s = scan.nextLine()).isEmpty()) {
					content.addPhoto(s);
				}
				DAO.addContent(content);
			}
			scan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
