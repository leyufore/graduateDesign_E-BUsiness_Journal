package com.leyufore.util;

import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.taobao.api.ApiException;

public class MultiplePlatformUtil {
	//商品发布
	public static Product productAdd(User user, Product productRequest) throws Exception {
		Product result = null;
		try {
			result = TaobaoUtil.productAdd(user, productRequest);
		} catch (ApiException e) {
			e.printStackTrace();
			throw new Exception("taobao apiException");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		try {
			YhdUtil.productAdd(user, result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return result;
	}
	//商品上下架
	public static boolean productShelve(User user,long shelveUpOrDown,long itemId) throws Exception{
		try {
			TaobaoUtil.productShelve(user, shelveUpOrDown, itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		try {
			YhdUtil.productShelve(user, shelveUpOrDown, itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return true;
		
	}
	//商品删除
	/**
	 * 根据itemId删除商品，该多平台提供的是淘宝平台的商品删除，由于1号店没有提供删除接口，
	 * 商家得手动到商家后台进行删除。关于1号店无法删除的提示，将在Action层提供，该Util以及Service层不处理
	 */
	public static boolean productDelete(User user,long itemId) throws Exception{
		try {
			TaobaoUtil.productDelete(user, itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}
	
	public static void main(String[] args){
		User user = FactoryUtil.getTestUser();
		Product product = FactoryUtil.getTestProduct();
		try {
			MultiplePlatformUtil.productAdd(user, product);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
