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

import bean.Star;
import biz.StarBiz;

@Path("star")
public class StarServlets {
	private StarBiz starBiz;

	public StarServlets() {
		starBiz = new StarBiz();
	}
	
	@GET
	@Path("listStarsByUserId")
	@Produces("application/json")
	@Consumes("text/plain")
	public String listStarsByUserID(@QueryParam("userId") int userID) throws SQLException {
		List<Star> starList = starBiz.listStarsByUserID(userID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<starList.size(); i++) {		
			Star star = starList.get(i);
			JSONObject jsonStar = new JSONObject(star);
			retArr.put(jsonStar);
		}
		return retArr.toString();
	}

	@GET
	@Path("listAllStars")
	@Produces("application/json")
	@Consumes("text/plain")
	public String listAllStars(@QueryParam("userId") int userID) throws SQLException {
		List<Star> starList = starBiz.listAllStars(userID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<starList.size(); i++) {		
			Star star = starList.get(i);
			JSONObject jsonStar = new JSONObject(star);
			retArr.put(jsonStar);
		}
		return retArr.toString();
	}

	@GET
	@Path("getStarById")
	@Produces("application/json")
	@Consumes("text/plain")
	public String getStarByID(@QueryParam("starId") int starID) throws SQLException {
		Star star = starBiz.getStarByID(starID);
		JSONObject jsonStar = new JSONObject(star);
		return jsonStar.toString();
	}

}
