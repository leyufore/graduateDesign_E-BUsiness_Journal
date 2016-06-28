package com.leyufore.util;

import com.leyufore.dao.ProductDao;
import com.leyufore.dao.UserDao;
import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.leyufore.service.ProductService;

public class FactoryUtil {
	private static ProductDao productDao = new ProductDao();
	private static ProductService productService = new ProductService();
	
	public static ProductDao getProductDao(){
		return productDao;
	}
	public static ProductService getProductService(){
		return productService;
	}
	
	
	public static Product getTestProduct(){
		Product product = new Product();
		product.setImgPath("uploadImg/travel01.jpg");
		product.setImgTitle("travel01.jpg");
		product.setTitle("这是测试的商品标题");
		product.setItem_type(ConstantUtil.ITEM_TYPE_TRAVEL_FREEDOM);
		product.setDesc("这是测试的商品描述");
		product.setTrip_max_day(5l);
		product.setAccomNigth(3L);
		product.setProv("广东");
		product.setCity("广州");
		product.setFrom_location("杭州");
		product.setTo_location("上海");
		product.setFee_included_desc("这是测试的费用包含描述");
		product.setFee_excluded_desc("这是测试的费用不包含描述");
		product.setOrder_info("这是测试的预定须知");
		product.setBackTrafficType(ConstantUtil.ITEM_TRAFFIC_TYPE_PLANE);
		product.setBackTrafficNo("测试的回程交通班次号");
		product.setGoTrafficType(ConstantUtil.ITEM_TRAFFIC_TYPE_PLANE);
		product.setGoTrafficNo("测试的回程交通班次号");
		product.setItemSku_priceType(ConstantUtil.ITEMSKU_PRICETYPE_ADULT);
		product.setItemSku_stock(20L);
		product.setItemSku_price(999L);
		product.setWeight(0.01);
		product.setMarketPrice(1000D);
		product.setState(ConstantUtil.ITEM_STATE_SALING);
		return product;
	}
	public static User getTestUser(){
		return new UserDao().findByUserName("leyufore");
	}
}
