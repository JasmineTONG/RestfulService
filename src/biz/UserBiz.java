package biz;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;

/**
 * Created by m on 2016/6/2.
 */
public class UserBiz {
	private UserDao userDao;

	public UserBiz() {
		userDao = new UserDao();
	}

	public User userLogin(String username,String password) throws SQLException {
		return userDao.authenticateUser(username,password);
	}

	public int userRegister(String username,String password,String nickname) throws SQLException {
		User user = new User(username,password,nickname);
		return userDao.insertUser(user);
	}

	public int userUpdateAvatar(int userID, String imgUrl) throws SQLException {
		return userDao.updateProfileImg(userID,imgUrl);
	}

	public int userUpdatePassword(int userID, String oldPassword, String password) throws SQLException {
		return userDao.updateUserPassword(userID,oldPassword,password);
	}

	public User getUserByID(int userID) throws SQLException {
		return userDao.getUserByID(userID);
	}

	public User getUserByUsername(String username) throws SQLException {
		return userDao.getUserByUsername(username);
	}


}
