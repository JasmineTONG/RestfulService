package biz;

import bean.Album;
import dao.AlbumDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/6.
 */
public class AlbumBiz {
	AlbumDao albumDao;

	public AlbumBiz() {
		albumDao = new AlbumDao();
	}

	public Album getAlbumByAlbumId(int albumID) throws SQLException {
		return albumDao.getAlbumByAlbumID(albumID);
	}

	public List<Album> listAlbumsByUserID(int userID) throws SQLException {
		return albumDao.listAlbumsByUserID(userID);
	}
}
