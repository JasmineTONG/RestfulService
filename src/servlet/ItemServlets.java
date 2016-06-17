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

import bean.Item;
import bean.Star;
import biz.ItemBiz;

@Path("item")
public class ItemServlets {
	private ItemBiz itemBiz;
	
	public ItemServlets() {
		itemBiz = new ItemBiz();
	}

	@GET
	@Path("listItemsByStarId")
	@Produces("application/json")
	@Consumes("text/plain")
	public String listItemsByStarID(@QueryParam("starId") int starID) throws SQLException {
		List<Item> items = itemBiz.listItemsByStarID(starID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<items.size(); i++) {		
			Item item = items.get(i);
			JSONObject jsonItem = new JSONObject(item);
			retArr.put(jsonItem);
		}
		return retArr.toString();
	} 
	
	
}
