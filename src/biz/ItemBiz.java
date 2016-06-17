package biz;

import bean.Item;
import dao.ItemDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m on 2016/6/3.
 */
public class ItemBiz {
	ItemDao itemDao;

	public ItemBiz() {
		itemDao = new ItemDao();
	}

	public Item getItemByID(int itemID) throws SQLException {
		return itemDao.getItemByID(itemID);
	}

	public List<Item> listItemsByStarID(int starID) throws SQLException {
		return itemDao.listItemsByStarID(starID);
	}
}
