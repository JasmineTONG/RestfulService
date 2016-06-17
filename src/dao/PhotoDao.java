package dao;

import bean.Photo;
import util.JdbcUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by m on 2016/6/2.
 */
public class PhotoDao {
	private JdbcUtil jdbcUtil;

	public PhotoDao() {
		jdbcUtil = JdbcUtil.getInstance();
	}

	public int insertPhoto(Photo photo) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);

		String sql = "insert into photo(starId,objUrl,fromUrl,photoDetail) values(?,?,?,?)";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, photo.getStarId());
			ps.setString(2, photo.getObjUrl());
			ps.setString(3, photo.getFromUrl());
			ps.setString(4, photo.getPhotoDetail());

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
	
	
	
	public Photo getPhotoByPhotoID(int photoID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from photo where photoId=?;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Photo photo = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,photoID);
			rs = ps.executeQuery();
			if (!rs.wasNull()) {
				photo = new Photo();
			} else {
				return null;
			}
			while (rs.next()) {
				photo.setPhotoId(photoID);
				photo.setStarId(rs.getInt("starId"));
				photo.setObjUrl(rs.getString("objUrl"));
				photo.setFromUrl(rs.getString("fromUrl"));
				photo.setPhotoDetail(rs.getString("photoDetail"));
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

		return photo;
	}
	
	
	public List<Photo> listPhotosByStarID(int starID) throws SQLException {
		Connection conn = jdbcUtil.getConnection();
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (conn == null);
		String sql = "select * from photo WHERE starId=?";
		PreparedStatement ps = null;
		List<Photo> list = new ArrayList<Photo>();
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1,starID);
			rs = ps.executeQuery();

			while (conn == null) ;
			rs = ps.executeQuery();
			while (rs.next()) {
				Photo photo = new Photo();

				photo.setPhotoId(rs.getInt("photoId"));
				photo.setStarId(starID);
				photo.setObjUrl(rs.getString("ObjUrl"));
				photo.setFromUrl(rs.getString("fromUrl"));
				photo.setPhotoDetail(rs.getString("photoDetail"));

				list.add(photo);
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
	
	public boolean savePhoto(int starID,String key){
		System.out.println("photoDao.savePhoto()...");
		BufferedReader reader = null;
		 String result = null;
		 StringBuffer sbf = new StringBuffer();
		 String httpUrl = "http://apis.baidu.com/image_search/search/search";
		 String httpArg = "word="+key+"&pn=0&rn=60&ie=utf-8";
		 httpUrl = httpUrl + "?" + httpArg;
		 
		 try {
		        URL url = new URL(httpUrl);
		        System.out.println("..."+httpUrl);
		        HttpURLConnection connection = (HttpURLConnection) url
		                .openConnection();
		        connection.setRequestMethod("GET");
		        // set apikey HTTP header
		        connection.setRequestProperty("apikey",  "c5c7ff6a944e74a59a830112fc3702b3");
		        connection.connect();
		        InputStream is = (InputStream) connection.getInputStream();
		        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        String strRead = null;
		        while ((strRead = reader.readLine()) != null) {
		            sbf.append(strRead);
		            sbf.append("\r\n");
		        }
		        reader.close();
		        result = sbf.toString();
		        System.out.println("result..."+result);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		 JSONObject jsonObject = new JSONObject(result);
		 JSONObject data = jsonObject.getJSONObject("data");
		 JSONArray picArr = data.getJSONArray("ResultArray");
		 System.out.println("photoDao.savePhoto()...picArr.length= "+picArr.length());
		 
		 for(int i=0; i<picArr.length(); i++){
			JSONObject pic = picArr.getJSONObject(i);
			String objUrl = pic.getString("ObjUrl");
			System.out.println("photoDao.savePhoto()...pic.getString(ObjUrl)..."+objUrl);
			String FromUrl = pic.getString("FromUrl");
			String detail = pic.getString("Desc");
	   		System.out.println("objUrl: "+objUrl+"\n"+"Desc:"+detail);
	   		insert(pic,starID);
		 }
		return true;
		 
	}
		 
	public void insert(JSONObject pic, int starId) {
		Connection conn = jdbcUtil.getConnection();
		
		String objUrl = pic.getString("ObjUrl").trim();
		String fromUrl = pic.getString("FromUrl");
		String detail = pic.getString("Desc");
		try {
			
			PreparedStatement st = null;
			
			String sql="INSERT INTO `photo` (objUrl,fromUrl,photoDetail,starId) VALUES (?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1,objUrl);
			st.setString(2, fromUrl);
			st.setString(3,detail);
			st.setInt(4,starId);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String string2Unicode(String s) {  
	    try {  
	      StringBuffer out = new StringBuffer("");  
	      byte[] bytes = s.getBytes("unicode");  
	      for (int i = 2; i < bytes.length - 1; i += 2) {  
	        out.append("u");  
	        String str = Integer.toHexString(bytes[i + 1] & 0xff);  
	        for (int j = str.length(); j < 2; j++) {  
	          out.append("0");  
	        }  
	        String str1 = Integer.toHexString(bytes[i] & 0xff);  
	  
	        out.append(str);  
	        out.append(str1);  
	        out.append(" ");  
	      }  
	      return out.toString().toUpperCase();  
	    }  
	    catch (UnsupportedEncodingException e) {  
	      e.printStackTrace();  
	      return null;  
	    }  
	 }
	
	
	public String searchPhotos(int starID,String key){
		System.out.println("photoDao.searchPhotos()...");
		BufferedReader reader = null;
		 String result = null;
		 StringBuffer sbf = new StringBuffer();
		 String httpUrl = "http://apis.baidu.com/image_search/search/search";
		 String httpArg = "word="+key+"&pn=0&rn=60&ie=utf-8";
		 httpUrl = httpUrl + "?" + httpArg;
		 
		 try {
		        URL url = new URL(httpUrl);
		        System.out.println("..."+httpUrl);
		        HttpURLConnection connection = (HttpURLConnection) url
		                .openConnection();
		        connection.setRequestMethod("GET");
		        // set apikey HTTP header
		        connection.setRequestProperty("apikey",  "c5c7ff6a944e74a59a830112fc3702b3");
		        connection.connect();
		        InputStream is = (InputStream) connection.getInputStream();
		        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        String strRead = null;
		        while ((strRead = reader.readLine()) != null) {
		            sbf.append(strRead);
		            sbf.append("\r\n");
		        }
		        reader.close();
		        result = sbf.toString();
		        System.out.println("result..."+result);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		 JSONObject jsonObject = new JSONObject(result);
		 JSONObject data = jsonObject.getJSONObject("data");
		 JSONArray picArr = data.getJSONArray("ResultArray");
		 System.out.println("photoDao.savePhoto()...picArr.length= "+picArr.length());
		 
		 for(int i=0; i<picArr.length(); i++){
			JSONObject pic = picArr.getJSONObject(i);
			String objUrl = pic.getString("ObjUrl");
			System.out.println("photoDao.savePhoto()...pic.getString(ObjUrl)..."+objUrl);
			String FromUrl = pic.getString("FromUrl");
			String detail = pic.getString("Desc");
	   		System.out.println("objUrl: "+objUrl+"\n"+"Desc:"+detail);
	   		insert(pic,starID);
		 }
		return picArr.toString();
		 
	}

}
