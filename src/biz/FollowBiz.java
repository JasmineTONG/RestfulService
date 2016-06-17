package biz;

import bean.Follow;
import dao.FollowDao;

import java.sql.SQLException;

/**
 * Created by m on 2016/6/3.
 */
public class FollowBiz {
	FollowDao followDao;

	public FollowBiz() {
		followDao = new FollowDao();
	}

	public int followStar(int userID,int starID) throws SQLException {
		Follow follow = new Follow();
		follow.setUserId(userID);
		follow.setStarId(starID);

		return followDao.insertFollow(follow);
	}


	public int cancelFollowStar(int userID,int starID) throws SQLException {
		Follow follow = new Follow();
		follow.setUserId(userID);
		follow.setStarId(starID);

		return followDao.deleteFollow(follow);
	}
}
