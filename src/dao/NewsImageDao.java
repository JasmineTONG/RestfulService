package dao;

import bean.NewsImage;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/6/3.
 */
public class NewsImageDao {
	private JdbcUtil jdbcUtil;

	public NewsImageDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	public List<NewsImage> listNewsImagesByNewsID(int newsID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from news_img WHERE newsId=?";
		PreparedStatement ps = null;
		List<NewsImage> list = new ArrayList<NewsImage>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,newsID);
			rs = ps.executeQuery();

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				NewsImage image= new NewsImage();

				image.setNewsImgId(rs.getInt("newsImgId"));
				image.setNewsId(newsID);
				image.setNewsImgUrl(rs.getString("newsImgUrl"));

				list.add(image);
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
