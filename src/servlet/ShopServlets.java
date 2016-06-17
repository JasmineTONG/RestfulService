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
import bean.Shop;
import biz.ShopBiz;

@Path("shop")
public class ShopServlets {
	private ShopBiz shopBiz;
	
	public ShopServlets() {
		shopBiz = new ShopBiz();
	}

	@GET
	@Path("listShopsByUserId")
	@Produces("application/json")
	@Consumes("text/plain")
	public String listShopsByStarId(@QueryParam("userId") int userID) throws SQLException {
		System.out.println("listShopsByUserId: ");
		List<Shop> starShops = shopBiz.listShopsByUserID(userID);
		JSONArray retArr = new JSONArray();
		for (int i=0; i<starShops.size(); i++) {		
			Shop starShop = starShops.get(i);
			System.out.println("==="+starShop.getShopName());
			JSONObject jsonShop = new JSONObject(starShop);
			retArr.put(jsonShop);
		}
		return retArr.toString();
	}
	
	
	
}
