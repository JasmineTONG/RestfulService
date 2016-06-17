package biz;

import bean.NewsImage;
import dao.NewsImageDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/3.
 */
public class NewsImageBiz {
	NewsImageDao newsImageDao;
	public NewsImageBiz() {
		newsImageDao = new NewsImageDao();
	}

	public List<NewsImage> listNewsImagesByNewsId(int newsID) throws SQLException {
		return newsImageDao.listNewsImagesByNewsID(newsID);
	}
}
