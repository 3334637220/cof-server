package e.orz.cof.service;

import e.orz.cof.dao.PictureDao;

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
}
