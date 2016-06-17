package dao;

import bean.News;
import bean.NewsImage;
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
public class NewsDao {
	private JdbcUtil jdbcUtil;

	public NewsDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	public News getNewsByNewsID(int newsID) throws SQLException {
		NewsImageDao newsImgDao = new NewsImageDao();
		StarDao starDao = new StarDao();
		
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from news where newsId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		News news = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,newsID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				news = new News();
			} else {
				return null;
			}
			while (rs.next()) {
				news.setNewsId(newsID);
				news.setStarId(rs.getInt("starId"));
				news.setNewsTime(rs.getDate("newsTime"));
				news.setText(rs.getString("text"));

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

		return news;
	}
	
	
	public List<News> listAllNews() throws SQLException {
		NewsImageDao newsImgDao = new NewsImageDao();
		StarDao starDao = new StarDao();
		
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from news ORDER BY newsTime DESC";
		PreparedStatement ps = null;
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				News news = new News();

				news.setNewsId(rs.getInt("newsId"));
				news.setStarId(rs.getInt("starId"));
				news.setNewsTime(rs.getDate("newsTime"));
				news.setText(rs.getString("text"));

				list.add(news);
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


	public List<News> listNewsByUserID(int userID) throws SQLException {
		
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from news NATURAL JOIN (SELECT starId,starName FROM star) AS stars NATURAL JOIN (SELECT starId,userId FROM follow WHERE userId=?) AS follows NATURAL JOIN news_img ORDER BY newsTime DESC";
		PreparedStatement ps = null;
		List<News> list = new ArrayList<News>();
		ResultSet rs = null;

		Set<Integer> newsIdSet = new HashSet<Integer>();

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,userID);
			rs = ps.executeQuery();

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {

				int newsId = rs.getInt("newsId");

				if (newsIdSet.add(newsId)) {
					News news = new News();

					news.setNewsId(rs.getInt("newsId"));
					news.setStarId(rs.getInt("starId"));
					news.setNewsTime(rs.getDate("newsTime"));
					news.setText(rs.getString("text"));
					news.setStarName(rs.getString("starName"));
					news.setNewsImgs(new ArrayList<NewsImage>());

					list.add(news);
				}

				NewsImage image = new NewsImage();
				image.setNewsImgId(rs.getInt("newsImgId"));
				image.setNewsId(rs.getInt("newsId"));
				image.setNewsImgUrl(rs.getString("newsImgUrl"));
				list.get(list.size() - 1).getNewsImgs().add(image);

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
//		NewsDao dao = new NewsDao();
//		try {
//			List<News> list = dao.listNewsByUserID(2);
//			for(News news:list) {
//				System.out.print(news.getNewsId()+" , "+news.getStarId()+" , "+news.getStarName()+" : ");
//				for(NewsImage image:news.getNewsImgs()) {
//					System.out.print(image.getNewsImgId() + " ");
//				}
//				System.out.println();
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
