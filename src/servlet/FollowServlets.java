package servlet;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import biz.FollowBiz;

@Path("follow")
public class FollowServlets {
	private FollowBiz followBiz;
	
	public FollowServlets() {
		followBiz = new FollowBiz();
	}

	@GET
	@Path("followStar")
	@Produces("application/json")
	@Consumes("text/plain")
	public String followStar(@QueryParam("userId") int userID,
			@QueryParam("starId") int starID) throws SQLException {
		int flag = followBiz.followStar(userID, starID);
		JSONObject retObj = new JSONObject();
		retObj.put("flag", flag);
		return retObj.toString();
	}

	@GET
	@Path("cancelFollowStar")
	@Produces("application/json")
	@Consumes("text/plain")
	public String cancelFollowStar(@QueryParam("userId") int userID,
			@QueryParam("starId") int starID) throws SQLException {
		int flag = followBiz.cancelFollowStar(userID, starID);
		JSONObject retObj = new JSONObject();
		retObj.put("flag", flag);
		return retObj.toString();
	}
	
	
}
