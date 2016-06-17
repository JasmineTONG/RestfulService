package dao;

import bean.Follow;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by m on 2016/6/3.
 */
public class FollowDao {
	private JdbcUtil jdbcUtil;

	public FollowDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	
	//Insert a follow into the table
	public int insertFollow(Follow follow) throws SQLException {

		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);

		String sql = "insert into follow(userId,starId) values(?,?)";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1,follow.getUserId());
			ps.setInt(2,follow.getStarId());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return 0;

	}


	//Delete a follow from the table
	public int deleteFollow(Follow follow) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "delete from follow where userID=? AND starId=?";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, follow.getUserId());
			ps.setInt(2,follow.getStarId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return 0;
	}
	
	
	
}
