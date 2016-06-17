package dao;

import bean.Item;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/6/2.
 */
public class ItemDao {
	private JdbcUtil jdbcUtil;

	public ItemDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	public Item getItemByID(int itemID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from item where itemId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Item item = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,itemID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				item = new Item();
			} else {
				return null;
			}
			while (rs.next()) {
				item.setItemId(itemID);
				item.setStarId(rs.getInt("starId"));
				item.setItemName(rs.getString("itemName"));
				item.setItemDescription(rs.getString("itemDescription"));
				item.setPrice(rs.getString("price"));
				item.setItemUrl(rs.getString("itemUrl"));
				item.setItemLink(rs.getString("itemLink"));
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		}

		return item;
	}


	//List all the items
	public List<Item> listItemsByStarID(int starID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from item where starId=?";
		PreparedStatement ps = null;
		List<Item> list = new ArrayList<Item>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, starID);

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();

				item.setItemId(rs.getInt("itemId"));
				item.setStarId(starID);
				item.setItemName(rs.getString("itemName"));
				item.setItemDescription(rs.getString("itemDescription"));
				item.setPrice(rs.getString("price"));
				item.setItemUrl(rs.getString("itemUrl"));
				item.setItemLink(rs.getString("itemLink"));

				list.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		}

		return list;

	}
}
