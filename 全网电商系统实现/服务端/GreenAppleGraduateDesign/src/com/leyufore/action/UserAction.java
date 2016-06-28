package com.leyufore.action;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private String username;
	private String password;
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
	
	@Override
	public String execute() throws Exception {
		System.out.println(getUsername());
		System.out.println(getPassword());
		return super.execute();
	}
	
	public String login() throws Exception {
		System.out.println(getUsername());
		System.out.println(getPassword());
		return SUCCESS;
	}
}
