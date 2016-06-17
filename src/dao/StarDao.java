package dao;

import bean.Star;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by m on 2016/6/2.
 */
public class StarDao {
	private JdbcUtil jdbcUtil;

	public StarDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	//Get a star by starID
	public Star getStarByID(int starID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from star where starId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Star star = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,starID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				star = new Star();
			} else {
				return null;
			}
			while (rs.next()) {
				star.setStarId(starID);
				star.setStarName(rs.getString("starName"));
				star.setStarProfile(rs.getString("starProfile"));
				star.setBirth(rs.getString("birth"));
				star.setConstellation(rs.getString("constellation"));
				star.setBloodType(rs.getString("bloodType"));
				star.setHeight(rs.getString("height"));
				star.setWeight(rs.getString("weight"));
				star.setJob(rs.getString("job"));
				star.setMasterWork(rs.getString("masterWork"));
				star.setFollowed(true);
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

		return star;
	}


	//Get a star by name
	public Star getStarByName(String name) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from star where starId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Star star = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				star = new Star();
			} else {
				return null;
			}
			while (rs.next()) {
				star.setStarId(rs.getInt("starId"));
				star.setStarName(name);
				star.setStarProfile(rs.getString("starProfile"));
				star.setBirth(rs.getString("birth"));
				star.setConstellation(rs.getString("constellation"));
				star.setBloodType(rs.getString("bloodType"));
				star.setHeight(rs.getString("height"));
				star.setWeight(rs.getString("weight"));
				star.setJob(rs.getString("job"));
				star.setMasterWork(rs.getString("masterWork"));
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

		return star;
	}


	//List all the stars
	public List<Star> listAllStars(int userID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from star LEFT JOIN follow ON star.starId = follow.starId ORDER BY starName DESC";
		PreparedStatement ps = null;
		List<Star> list = new ArrayList<Star>();

		//Avoid duplication
		Set<Integer> starIdSet = new HashSet<Integer>();

		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {

				int starId = rs.getInt("starId");

				if (starIdSet.add(starId)) {
					Star star = new Star();

					star.setStarId(starId);
					star.setStarName(rs.getString("starName"));
					star.setStarProfile(rs.getString("starProfile"));
					star.setBirth(rs.getString("birth"));
					star.setConstellation(rs.getString("constellation"));
					star.setBloodType(rs.getString("bloodType"));
					star.setHeight(rs.getString("height"));
					star.setWeight(rs.getString("weight"));
					star.setJob(rs.getString("job"));
					star.setMasterWork(rs.getString("masterWork"));
					star.setFollowed(userID == rs.getInt("userId") ? true : false);

					list.add(star);
				}

				if (userID == rs.getInt("userId")) {
					for (Star s : list) {
						if (s.getStarId() == starId) {
							s.setFollowed(true);
						}
					}

				}

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


	//List all the stars by userID
	public List<Star> listStarsByUserID(int userID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from star NATURAL JOIN (select * from follow where userId=?) as follows ORDER  BY  starName DESC";
		PreparedStatement ps = null;
		List<Star> list = new ArrayList<Star>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,userID);

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				Star star = new Star();

				star.setStarId(rs.getInt("starId"));
				star.setStarName(rs.getString("starName"));
				star.setStarProfile(rs.getString("starProfile"));
				star.setBirth(rs.getString("birth"));
				star.setConstellation(rs.getString("constellation"));
				star.setBloodType(rs.getString("bloodType"));
				star.setHeight(rs.getString("height"));
				star.setWeight(rs.getString("weight"));
				star.setJob(rs.getString("job"));
				star.setMasterWork(rs.getString("masterWork"));
				star.setFollowed(true);

				list.add(star);
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

//	public static void main(String[] args) {
//		StarDao dao = new StarDao();
//		try {
//			List<Star> list = dao.listAllStars(3);
//			for(Star star:list) {
//				System.out.println("starID:"+star.getStarId()+" , "+" followed:" + star.isFollowed());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
}
