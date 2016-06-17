package biz;

import bean.Photo;
import dao.PhotoDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/6.
 */
public class PhotoBiz {
	PhotoDao photoDao;

	public PhotoBiz() {
		photoDao = new PhotoDao();
	}

	public int addPhoto(int starId,String objUrl,String fromUrl,String photoDetail) throws SQLException {
		Photo photo = new Photo();
		photo.setStarId(starId);
		photo.setObjUrl(objUrl);
		photo.setFromUrl(fromUrl);
		photo.setPhotoDetail(photoDetail);
		return photoDao.insertPhoto(photo);
	}


	public Photo getPhotoByPhotoID(int photoID) throws SQLException {
		return photoDao.getPhotoByPhotoID(photoID);
	}


	public List<Photo> listPhotosByStarID(int starID) throws SQLException {
		return photoDao.listPhotosByStarID(starID);
	}
	
	public boolean savePhoto(int starID,String key) throws SQLException {
		return photoDao.savePhoto(starID,key);
	}
	
	public String searchPhotos(int starID, String key) {
		return photoDao.searchPhotos(starID, key);
	}
}
