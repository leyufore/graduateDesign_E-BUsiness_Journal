package com.leyufore.service;

import java.util.List;

import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.leyufore.util.ConstantUtil;
import com.leyufore.util.FactoryUtil;
import com.leyufore.util.MultiplePlatformUtil;

import javassist.compiler.ast.NewExpr;
import net.sf.json.JSONObject;

public class ProductService {
	// 商品发布
	public Product productAdd(User user, Product product) throws Exception {
		Product queryProduct = FactoryUtil.getProductDao().findByProductTitle(product.getTitle());
		if (queryProduct != null) {
			throw new Exception("商品标题不能重复");
		}
		System.out.println("商品标题没有重复");
		try {
			Product resultProduct = MultiplePlatformUtil.productAdd(user, product);
			// 设置商品销售中状态
			resultProduct.setState(ConstantUtil.ITEM_STATE_SALING);
			// 平台没报错信息,则往数据库添加产品数据
			int id = FactoryUtil.getProductDao().insert(resultProduct);
			System.out.println(id);
			return FactoryUtil.getProductDao().findByProductId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 商品查询所有
	public List<Product> productFindAll(User user) {
		List<Product> productList = FactoryUtil.getProductDao().findAll();
		return productList;
	}

	// 商品上下架
	public Product productShelve(User user, long shelveUpOrDown, long itemId) throws Exception {
		System.out.println("ProductService -productShelve -shelveUpOrDown :"+shelveUpOrDown);
		if (shelveUpOrDown != ConstantUtil.ITEM_SHELVE_UP && shelveUpOrDown != ConstantUtil.ITEM_SHELVE_DOWN) {
			throw new Exception("shelveUpOrDown参数有误");
		}
		Product resultProduct = FactoryUtil.getProductDao().findByProductItemId(itemId);
		if (resultProduct == null) {
			throw new Exception("itemId不存在于数据库");
		}
		try {
			MultiplePlatformUtil.productShelve(user, shelveUpOrDown, itemId);
			System.out.println("ProductService - productShelve - success");
		} catch (Exception e) {
			throw e;
		}
		//到达此处时，对接平台的商品上下架已经成功
		if (shelveUpOrDown == ConstantUtil.ITEM_SHELVE_UP) {
			resultProduct.setState(ConstantUtil.ITEM_STATE_SALING);
		} else {
			resultProduct.setState(ConstantUtil.ITEM_STATE_STORING);
		}
		FactoryUtil.getProductDao().update(resultProduct);
		return resultProduct;
	}
	/**
	 * 根据itemId删除商品，该多平台提供的是淘宝平台的商品删除，由于1号店没有提供删除接口，
	 * 商家得手动到商家后台进行删除。关于1号店无法删除的提示，将在Action层提供，该Util以及Service层不处理。
	 * 此处返回删除的产品ID
	 */
	public long productDelete(User user,long itemId) throws Exception{
		Product product = FactoryUtil.getProductDao().findByProductItemId(itemId);
		if(product == null){
			throw new Exception("itemId不存在于数据库中");
		}
		try {
			MultiplePlatformUtil.productDelete(user, itemId);
		} catch (Exception e) {
			throw e;
		}
		FactoryUtil.getProductDao().delete(product);
		return product.getItemId();
	
	}
	
	public static void main(String[] args) {
		User user = FactoryUtil.getTestUser();
		Product product = FactoryUtil.getTestProduct();

		ProductService productService = new ProductService();
		try {
			productService.productAdd(user, product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
