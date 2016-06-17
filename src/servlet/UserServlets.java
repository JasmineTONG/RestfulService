package servlet;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.sun.jersey.core.header.FormDataContentDisposition;

import bean.User;
import biz.UserBiz;


@Path("user")
public class UserServlets {
	private UserBiz userBiz;

	public UserServlets() {
		userBiz = new UserBiz();
	}
	
	@POST
	@Path("userLogin")
	@Produces("application/json")
	@Consumes("text/plain")
	public String userLogin(@QueryParam("username") String username,
			@QueryParam("password") String password) throws SQLException {
		System.out.println("@userLogin: username= "+username);
		
		User user = userBiz.userLogin(username, password);
		JSONObject jsonUser;
		JSONObject retObj = new JSONObject();
		
		if (user!=null) {
			jsonUser = new JSONObject(user);
			retObj.put("flag", 0);
			retObj.put("user", jsonUser);
		}
		else {
			retObj.put("flag", -1);
			retObj.put("user", "null");
		}
		return retObj.toString();
	}

	@POST
	@Path("userRegister")
	@Produces("application/json")
	@Consumes("text/plain")
	public String userRegister(@QueryParam("username") String username,
			@QueryParam("password") String password,
			@QueryParam("nickname") String nickname) throws SQLException {
		System.out.println("@userResgister: username= "+username);
		
		int flag = userBiz.userRegister(username, password, nickname);
		User user = new User(username,password,nickname);
		JSONObject jsonUser = new JSONObject(user);
		
		JSONObject retObj = new JSONObject();
		retObj.put("flag", flag);
		if (flag==-1) retObj.put("user", "null");
		else retObj.put("user", jsonUser);
		return retObj.toString();
	}

	@POST
	@Path("userUpdatePassword")
	@Produces("application/json")
	@Consumes("text/plain")
	public String userUpdatePassword(
			@QueryParam("userId") int userID,
			@QueryParam("oldPassword") String oldPassword,
			@QueryParam("newPassword") String newPassword) throws SQLException {
		int flag = userBiz.userUpdatePassword(userID, oldPassword, newPassword);
		JSONObject retObj = new JSONObject();
		retObj.put("flag", flag);
		return retObj.toString();
	}
	
	
}