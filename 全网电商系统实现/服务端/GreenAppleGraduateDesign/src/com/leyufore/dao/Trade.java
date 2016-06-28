package com.leyufore.dao;

public class Trade {
	private int id;
	private long tid; // 订单id
	private String travel_start_date; // 旅行开始时间
	private String travel_end_date; // 旅行结束时间
	private String contact_name; // 联系人姓名
	private String contact_telep; // 联系人电话
	private String traveler_name; // 出行人姓名
	private String sex; // 出行人性别
	// 证件类型；0身份证，1护照，2学生证，3军人证，4回乡证，5台胞证，6港澳台胞，7国际海员，8外国人永久居留证，10警官证，11士兵证，12台湾通行证，13入台证，9其他证件
	private long credentials_ty;	//证件类型
	private String credentials_c;	//证件号码
	private long traveler_type;	//出行人类型 0成人，1儿童，2婴儿
	private int platformId;	//平台Id
}
