package dao;

import bean.Album;
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
public class AlbumDao {
	private JdbcUtil jdbcUtil;

	public AlbumDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}
	
	
	public int insertAlbum(Album album) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);

		String sql = "insert into album(starId,albumName,albumProfile,albumDescription) values(?,?,?,?)";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, album.getAlbumId());
			ps.setString(2, album.getAlbumName());
			ps.setString(3, album.getAlbumProfile());
			ps.setString(4, album.getAlbumDescription());

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
	
	public Album getAlbumByAlbumID(int albumID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from album where albumId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Album album = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,albumID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				album = new Album();
			} else {
				return null;
			}
			while (rs.next()) {
				album.setAlbumId(albumID);
				album.setStarId(rs.getInt("starId"));
				album.setAlbumProfile(rs.getString("albumProfile"));
				album.setAlbumDescription(rs.getString("albumDescription"));
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

		return album;
	}
	
	
	public List<Album> listAlbumsByUserID(int userID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from album NATURAL JOIN (SELECT starId,userId FROM follow WHERE userId=?) AS stars";
		PreparedStatement ps = null;
		List<Album> list = new ArrayList<Album>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,userID);

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				Album album = new Album();

				album.setAlbumId(rs.getInt("albumId"));
				album.setStarId(rs.getInt("starId"));
				album.setAlbumName(rs.getString("albumName"));
				album.setAlbumProfile(rs.getString("albumProfile"));
				album.setAlbumDescription(rs.getString("albumDescription"));

				list.add(album);
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
