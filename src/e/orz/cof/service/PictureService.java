package e.orz.cof.service;

import java.util.ArrayList;

import e.orz.cof.dao.PictureDao;
import e.orz.cof.model.Picture;

public class PictureService {
	private PictureDao pictureDao;

	public PictureService() {
		pictureDao = new PictureDao();
	}

	public boolean addPicture(int blogId, String imageUrl) {
		return pictureDao.addPicture(blogId, imageUrl);
	}

	public boolean deletePicture(int blogId) {
		return pictureDao.deletePicture(blogId);
	}

	public ArrayList<Picture> getPicturesById(int blogId) {
		return pictureDao.getPicturesById(blogId);
	}
}
