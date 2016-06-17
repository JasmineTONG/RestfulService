package dao;

import bean.User;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private JdbcUtil jdbcUtil;

	public UserDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	// Insert a new user into table
	public int insertUser(User user) throws SQLException {

		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);

		String sql = "insert into user(username,password,nickname,profileImg) values(?,?,?,?)";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getProfileImg());

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


	//Update the profile image of a user
	public int updateProfileImg(int userID,String profileImg) throws SQLException {

		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);

		String sql = "update user set profileImg=? where userId=?";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, profileImg);
			ps.setInt(2, userID);

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


	//Update password of a user
	public int updateUserPassword(int userID, String oldPassword, String password) throws SQLException {

		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);

		String sql = "update user set password=? where userId=? and password=?";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, password);
			ps.setInt(2, userID);
			ps.setString(3,oldPassword);

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

	//Get a user by userId
	public User getUserByID(int userID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from user where userId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		User u = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,userID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				u = new User();
			} else {
				return null;
			}
			while (rs.next()) {
				u.setUserId(userID);
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
				u.setProfileImg(rs.getString("profileImg"));
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

		return u;
	}


	//Get a user by usename
	public User getUserByUsername(String username) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from user where username=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		User u = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				u = new User();
			} else {
				return null;
			}
			while (rs.next()) {
				u.setUserId(rs.getInt("userId"));
				u.setUsername(username);
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
				u.setProfileImg(rs.getString("profileImg"));
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

		return u;
	}


	public User authenticateUser(String username,String password) throws SQLException {
		Connection conn=jdbcUtil.getConnection();
		do{
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(conn==null);
		String sql="select * from user where username=? and password=?;";
		ResultSet rs=null;
		PreparedStatement ps=null;
		User u = null;
		try {

			ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			rs=ps.executeQuery();


			//exist
			if(rs.next()){
				u = new User();
				u.setUserId(rs.getInt("userId"));
				u.setUsername(username);
				u.setNickname(rs.getString("nickname"));
				u.setProfileImg(rs.getString("profileImg"));
				return u;
			}
			//not exist
			else{
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}
		return u;
	}



}
