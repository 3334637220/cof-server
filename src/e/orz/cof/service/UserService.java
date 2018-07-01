package e.orz.cof.service;

import e.orz.cof.dao.UserDao;
import e.orz.cof.model.User;

public class UserService {
	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public User checkLogin(String userName, String password) {
		User user = userDao.queryUser(userName);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	public boolean register(String userName, String password, String faceUrl) {
		if (checkUserExist(userName)) {
			return false;
		}
		return userDao.insertUser(userName, password, faceUrl);
	}

	public boolean checkUserExist(String userName) {
		User user = userDao.queryUser(userName);
		if (user != null) {
			return true;
		}
		return false;
	}

}
