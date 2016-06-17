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

import bean.Album;
import biz.AlbumBiz;

@Path("album")
public class AlbumServlets {
	private AlbumBiz albumBiz;

	public AlbumServlets() {
		albumBiz = new AlbumBiz();
	}

	@GET
	@Path("listAlbumsByUserId")
	@Produces("application/json")
	@Consumes("text/html")
	public String listAlbumsByUserID(@QueryParam("userId") int userID) throws SQLException {
		System.out.println("listAlbumsByUserId: ");
		List<Album> albumStore = albumBiz.listAlbumsByUserID(userID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<albumStore.size(); i++) {		
			Album album = albumStore.get(i);
			JSONObject jsonAlbum = new JSONObject(album);
			retArr.put(jsonAlbum);
		}
		return retArr.toString();
	}
	
	
}
