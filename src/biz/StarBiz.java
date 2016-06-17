package biz;

import bean.Star;
import dao.StarDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/3.
 */
public class StarBiz {
	StarDao starDao;

	public StarBiz() {
		starDao = new StarDao();
	}

	public Star getStarByID(int starID) throws SQLException {
		return starDao.getStarByID(starID);
	}

	public Star getStarByName(String name) throws SQLException {
		return starDao.getStarByName(name);
	}

	public List<Star> listAllStars(int userID) throws SQLException {
		return starDao.listAllStars(userID);
	}

	public List<Star> listStarsByUserID(int userID) throws SQLException {
		return starDao.listStarsByUserID(userID);
	}
}
