package servlet;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Photo;
import bean.Star;
import biz.PhotoBiz;
import biz.StarBiz;

@Path("photo")
public class PhotoServlets {
	private PhotoBiz photoBiz;

	public PhotoServlets() {
		photoBiz = new PhotoBiz();
	}

	@GET
	@Path("listPhotosByStarId")
	@Produces("application/json")
	@Consumes("text/plain")
	public String listPhotosByStarId(@QueryParam("starId") int starID) throws SQLException {
//		String starName = filterId2Key(starID);
//		photoBiz.savePhoto(starID, starName);
		
		System.out.println("listPhotosByStarId: ");
		List<Photo> album = photoBiz.listPhotosByStarID(starID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<album.size(); i++) {		
			Photo photo = album.get(i);
			JSONObject jsonPhoto = new JSONObject(photo);
			retArr.put(jsonPhoto);
		}
		return retArr.toString();
	}
	
	public String filterId2Key(int starID) {
		switch(starID) {
		case 1: return "Park%20Hae%20Jin";
		case 2: return "running%20man";
		case 3: return "eric%20Shinhwa";
		case 4: return "Song%20Joong%20Ki";
		case 5: return "Zo%20In%20Sung";
		case 6: return "Kim%20Hyun%20A%204MINUTE";
		case 7: return "Song%20Hye%20Kyo";
		case 8: return "Han%20Hyo%20Joo";
		case 9: return "Lee%20Young%20Ae";
		case 10: return "Gain%20Brown%20Eyed%20Girls";
		case 11: return "Song%20Ji%20Hyo";
		case 12: return "Lee%20Kwang%20Soo";
		case 13: return "Jung%20Yong%20Hwa";
		default: return "";
		}
	}

	//Encapsulated Web Service from BaiduImageSearchEngine
	@GET
	@Path("searchPhotosByName")
	@Produces("application/json")
	@Consumes("text/plain")
	public String searchPhotosByName(@QueryParam("starName") String starName) throws SQLException {
		StarBiz starBiz = new StarBiz();
		Star star = starBiz.getStarByName(starName);
		int starID = star.getStarId();
		String key = filterId2Key(starID);
		return photoBiz.searchPhotos(starID, key);
	}
	
}
