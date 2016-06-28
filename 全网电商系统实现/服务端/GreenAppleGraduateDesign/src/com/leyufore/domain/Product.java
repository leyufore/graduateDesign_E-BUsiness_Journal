package com.leyufore.domain;

public class Product {
	private int id;
	//淘宝所需数据
	private Long itemId;	//淘宝返回的itemId 同时作为1号店商品的outterId
	private String imgPath;
	private String imgTitle;
	private String title;	//商品标题
	private Long item_type;	//商品类型。1-国内跟团游 2- 国内自由行
	private String desc;	//商品描述
	private Long trip_max_day;	//最大行程天数
	private Long accomNigth;	//行程晚数
	private String prov;	//宝贝所在省
	private String city;	//宝贝所在城市
	private String from_location;	//出发地
	private String to_location;	//目的地
	
	private String fee_included_desc;	//费用包含描述
	private String fee_excluded_desc;	//费用不包含描述
	private String order_info;	//预定需知
	
	private Long backTrafficType;	//回程交通类型。1/2/3/4 分别对应 飞机/火车/汽车/船
	private String backTrafficNo;	//参考班次号，飞机需要填航班号，火车需要填车次号，汽车和船可不填
	private Long goTrafficType;	//去程交通类型。1/2/3/4 分别对应 飞机/火车/汽车/船
	private String goTrafficNo;	//参考班次号，飞机需要填航班号，火车需要填车次号，汽车和船可不填
	//日历套餐
	private String itemSku_packageName;	//套餐名称
	private Long itemSku_priceType;		//价格类型。price_type 取：1-成人价，2-儿童价，3-单房差
	private Long itemSku_stock;	//库存
	private Long itemSku_price;	//价格
		
	//1号店所需数据
	private Double weight;	//重量(毛重KG,最多两位小数)
	private Double marketPrice;	//市场价格
	
	private int state;	//商品状态	0-仓库中	1-销售中
	
	public Product(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgTitle() {
		return imgTitle;
	}
	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getItem_type() {
		return item_type;
	}
	public void setItem_type(Long item_type) {
		this.item_type = item_type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getTrip_max_day() {
		return trip_max_day;
	}
	public void setTrip_max_day(Long trip_max_day) {
		this.trip_max_day = trip_max_day;
	}
	public Long getAccomNigth() {
		return accomNigth;
	}
	public void setAccomNigth(Long accomNigth) {
		this.accomNigth = accomNigth;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFrom_location() {
		return from_location;
	}
	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}
	public String getTo_location() {
		return to_location;
	}
	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}
	public String getFee_included_desc() {
		return fee_included_desc;
	}
	public void setFee_included_desc(String fee_included_desc) {
		this.fee_included_desc = fee_included_desc;
	}
	public String getFee_excluded_desc() {
		return fee_excluded_desc;
	}
	public void setFee_excluded_desc(String fee_excluded_desc) {
		this.fee_excluded_desc = fee_excluded_desc;
	}
	public String getOrder_info() {
		return order_info;
	}
	public void setOrder_info(String order_info) {
		this.order_info = order_info;
	}
	public Long getBackTrafficType() {
		return backTrafficType;
	}
	public void setBackTrafficType(Long backTrafficType) {
		this.backTrafficType = backTrafficType;
	}
	public String getBackTrafficNo() {
		return backTrafficNo;
	}
	public void setBackTrafficNo(String backTrafficNo) {
		this.backTrafficNo = backTrafficNo;
	}
	public Long getGoTrafficType() {
		return goTrafficType;
	}
	public void setGoTrafficType(Long goTrafficType) {
		this.goTrafficType = goTrafficType;
	}
	public String getGoTrafficNo() {
		return goTrafficNo;
	}
	public void setGoTrafficNo(String goTrafficNo) {
		this.goTrafficNo = goTrafficNo;
	}
	public String getItemSku_packageName() {
		return itemSku_packageName;
	}
	public void setItemSku_packageName(String itemSku_packageName) {
		this.itemSku_packageName = itemSku_packageName;
	}
	public Long getItemSku_priceType() {
		return itemSku_priceType;
	}
	public void setItemSku_priceType(Long itemSku_priceType) {
		this.itemSku_priceType = itemSku_priceType;
	}
	public Long getItemSku_stock() {
		return itemSku_stock;
	}
	public void setItemSku_stock(Long itemSku_stock) {
		this.itemSku_stock = itemSku_stock;
	}
	public Long getItemSku_price() {
		return itemSku_price;
	}
	public void setItemSku_price(Long itemSku_price) {
		this.itemSku_price = itemSku_price;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
