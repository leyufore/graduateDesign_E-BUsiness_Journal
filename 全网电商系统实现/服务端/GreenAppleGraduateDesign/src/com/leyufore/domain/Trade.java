package com.leyufore.domain;

public class Trade {
	private int id;
	private long orederId;	//订单编号
	private int status;	//订单状态
	private String order_titile;	//商品标题
	private double price;	//商品单价
	private int count;	//购买数量
	private double totalPrice;	//订单总价
	private String buyer_nick;	//买家信息
	private String platform;	//订单平台
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getOrederId() {
		return orederId;
	}
	public void setOrederId(long orederId) {
		this.orederId = orederId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOrder_titile() {
		return order_titile;
	}
	public void setOrder_titile(String order_titile) {
		this.order_titile = order_titile;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getBuyer_nick() {
		return buyer_nick;
	}
	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
}
