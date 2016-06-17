package dao;

import bean.Shop;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/6/6.
 */
public class ShopDao {
	private JdbcUtil jdbcUtil;

	public ShopDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

    public Shop getShopByShopID(int shopID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from shop where shopId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Shop shop = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,shopID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				shop = new Shop();
			} else {
				return null;
			}
			while (rs.next()) {
				shop.setShopId(shopID);
				shop.setStarId(rs.getInt("starId"));
				shop.setShopName(rs.getString("shopName"));
				shop.setShopProfile(rs.getString("shopProfile"));
				shop.setShopDescription(rs.getString("shopDescription"));
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

		return shop;
	}


	public List<Shop> listShopsByStarID(int starID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from shop WHERE starId=?";
		PreparedStatement ps = null;
		List<Shop> list = new ArrayList<Shop>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				Shop shop = new Shop();

				shop.setShopId(rs.getInt("shopId"));
				shop.setStarId(starID);
				shop.setShopName(rs.getString("shopName"));
				shop.setShopProfile(rs.getString("shopProfile"));
				shop.setShopDescription(rs.getString("shopDescription"));

				list.add(shop);
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


	public List<Shop> listShopsByUserID(int userID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from shop NATURAL JOIN (SELECT starId,userId FROM  follow WHERE userId=?) AS flollows";
		PreparedStatement ps = null;
		List<Shop> list = new ArrayList<Shop>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, userID);
			
			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				Shop shop = new Shop();

				shop.setShopId(rs.getInt("shopId"));
				shop.setStarId(rs.getInt("starId"));
				shop.setShopName(rs.getString("shopName"));
				shop.setShopProfile(rs.getString("shopProfile"));
				shop.setShopDescription(rs.getString("shopDescription"));

				list.add(shop);
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
