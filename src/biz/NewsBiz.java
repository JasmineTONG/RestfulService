package biz;

import bean.News;
import dao.NewsDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/3.
 */
public class NewsBiz {
	NewsDao newsDao;

	public NewsBiz() {
		newsDao = new NewsDao();
	}

	public News getNewsByNewsID(int newsID) throws SQLException {
		return newsDao.getNewsByNewsID(newsID);
	}

	public List<News> listAllNews() throws SQLException {
		return newsDao.listAllNews();
	}

	public List<News> listNewsByUserID(int userID) throws SQLException {
		return newsDao.listNewsByUserID(userID);
	}
}
