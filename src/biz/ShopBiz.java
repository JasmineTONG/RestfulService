package biz;

import bean.Shop;
import dao.ShopDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/6.
 */
public class ShopBiz {
	ShopDao shopDao;

	public ShopBiz() {
		shopDao = new ShopDao();
	}

	public Shop getShopByShopID(int shopID) throws SQLException {
		return shopDao.getShopByShopID(shopID);
	}

	public List<Shop> listShopsByStarID(int starID) throws SQLException {
		return shopDao.listShopsByStarID(starID);
	}

	public List<Shop> listShopsByUserID(int userID) throws SQLException {
		return shopDao.listShopsByUserID(userID);
	}


}
