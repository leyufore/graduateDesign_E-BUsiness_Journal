package com.leyufore.domain;

public class User {
	private int id;
	private String username;
	private String password;
	private String session_taobao;
	private String session_yhd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSession_taobao() {
		return session_taobao;
	}
	public void setSession_taobao(String session_taobao) {
		this.session_taobao = session_taobao;
	}
	public String getSession_yhd() {
		return session_yhd;
	}
	public void setSession_yhd(String session_yhd) {
		this.session_yhd = session_yhd;
	}
	
}
