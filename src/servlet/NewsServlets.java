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

import bean.News;
import bean.Star;
import biz.NewsBiz;

@Path("news")
public class NewsServlets {
	private NewsBiz newsBiz;
	
	public NewsServlets() {
		newsBiz = new NewsBiz();
	}

	@GET
	@Path("listNewsByUserId")
	@Produces("application/json")
	@Consumes("text/plain")
	public String listNewsByUserID(@QueryParam("userId") int userID) throws SQLException {
		List<News> newsList = newsBiz.listNewsByUserID(userID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<newsList.size(); i++) {		
			News news = newsList.get(i);
			JSONObject jsonNews = new JSONObject(news);
			retArr.put(jsonNews);
		}
		return retArr.toString();
	}
	
	
}
