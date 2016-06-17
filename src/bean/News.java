package bean;

import java.sql.Date;
import java.util.List;

public class News {
	private int newsId;
	
	private int starId;
	
	private String text;
	
	private Date newsTime;
	
	private String starName;
	
	private List<NewsImage> newsImgs;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}

	public String getStarName() {
		return starName;
	}

	public void setStarName(String starName) {
		this.starName = starName;
	}

	public List<NewsImage> getNewsImgs() {
		return newsImgs;
	}

	public void setNewsImgs(List<NewsImage> newsImgs) {
		this.newsImgs = newsImgs;
	}
}
