package com.servlet.user.parameter;

import java.util.HashMap;

public class ParamUser {
	private String username;
	private String password;
	
	public ParamUser(HashMap<String, Object> param) {
		String tempusername = param.get("username") != null ? param.get("username").toString():"";
		String temppassword = param.get("password") != null ? param.get("password").toString():"";
		setUsername(tempusername);
		setPassword(temppassword);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
