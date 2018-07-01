package e.orz.cof.service;

import e.orz.cof.dao.UpNumDao;

public class UpNumService {

	private UpNumDao upNumDao;

	public UpNumService() {
		upNumDao = new UpNumDao();
	}

	public boolean addUpNum(String userName, int blogId) {
		return upNumDao.addUpNum(userName, blogId) && upNumDao.addLike(userName, blogId);
	}

	public boolean minusUpNum(String userName, int blogId) {
		return upNumDao.minusUpNum(userName, blogId) && upNumDao.deleteLike(userName, blogId);
	}

	public boolean checkUserLike(String userName, int blogId) {
		return upNumDao.checkUserLike(userName, blogId);
	}
}
