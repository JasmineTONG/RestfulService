package bean;

public class Photo {
	private int photoId;
	
	private int starId;
	
	private String objUrl;
	
	private String fromUrl;
	
	private String photoDetail;

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
	}

	public String getObjUrl() {
		return objUrl;
	}

	public void setObjUrl(String objUrl) {
		this.objUrl = objUrl;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getPhotoDetail() {
		return photoDetail;
	}

	public void setPhotoDetail(String photoDetail) {
		this.photoDetail = photoDetail;
	}
}
