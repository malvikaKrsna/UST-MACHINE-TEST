package com.ust.beans;

public class Login {
	private int userId;
	private  String username;
	private String password;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.username = userName;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Login [userId=" + userId + ", userName=" + username
				+ ", password=" + password + "]";
	}
	
	

}
