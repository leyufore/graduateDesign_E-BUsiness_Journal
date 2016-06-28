package com.leyufore.util;

import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.yhd.YhdClient;
import com.yhd.request.product.ProductAddRequest;
import com.yhd.request.product.ProductImgUploadRequest;
import com.yhd.request.product.ProductShelvesdownUpdateRequest;
import com.yhd.request.product.ProductShelvesupUpdateRequest;
import com.yhd.response.product.ProductAddResponse;
import com.yhd.response.product.ProductImgUploadResponse;
import com.yhd.response.product.ProductShelvesdownUpdateResponse;
import com.yhd.response.product.ProductShelvesupUpdateResponse;

import net.sf.json.JSONObject;

public class YhdUtil {
	private static String url = "http://openapi.yhd.com/app/api/rest/router";
	private static String appKey = "10210016051300004009";
	private static String appSecret = "62e5d7be6e967fc4b1892a6240f7f964";

	//商品发布
	public static Product productAdd(User user, Product product) throws Exception {
		YhdClient yhd = new YhdClient(url, appKey, appSecret);
		// 新增商品
		ProductAddRequest request = new ProductAddRequest();
		request.setWeight(product.getWeight());
		request.setProductDescription(product.getDesc());
		request.setCategoryId(970505L);
		request.setProdAttributeItemInfoList("23732:126662,23734:126679,23395:123141");
		request.setBrandId(926569L);
		request.setProductType(0);
		request.setProductSalePrice(Double.parseDouble(String.valueOf(product.getItemSku_price())));
		request.setVirtualStockNum(product.getItemSku_stock());
		request.setProductMarketPrice(product.getMarketPrice());
		request.setMerchantCategoryId("1082715");
		request.setProductCname(product.getTitle());
		request.setProductNamePrefix("测试品牌");
		request.setCanSale(1);
		request.setElectronicCerticate("否");
		request.setOuterId(String.valueOf(product.getItemId()));
		System.out.println(user.getSession_yhd());
		ProductAddResponse response = yhd.excute(request, user.getSession_yhd());
		PrintUtil.print("yhd product add response : ", response.getBody());
		
		if(JSONObject.fromObject(response.getBody()).getJSONObject("response").containsKey("errInfoList")){
			throw new Exception("1号店商品新增失败");
		}
		System.out.println("1号店商品新增成功");
		// 上传图片
		ProductImgUploadRequest request_productImgUpload = new ProductImgUploadRequest();
		request_productImgUpload.setOuterId(String.valueOf(product.getItemId()));
		request_productImgUpload.setMainImageName(product.getImgTitle());
		String[] paths = new String[1];
		// paths[0] = "/Users/wenrule/Desktop/全网电商平台图片/travel01.jpg";
		String fileLocalPath = PictureUtil.getProductPicturePath(product.getImgPath());
		paths[0] = fileLocalPath;
		ProductImgUploadResponse response_productImgUpload1 = yhd.excute(request_productImgUpload,
				user.getSession_yhd());
		ProductImgUploadResponse response_productImgUpload2 = yhd.excute(request_productImgUpload, paths);
		PrintUtil.print("yhd 上传图片 response_productImgUpload1", response_productImgUpload1.getBody());
		PrintUtil.print("yhd 上传图片 response_productImgUpload2", response_productImgUpload2.getBody());

		if(JSONObject.fromObject(response_productImgUpload2.getBody()).getJSONObject("response").containsKey("errInfoList")){
			throw new Exception("1号店图片上传失败");
		}
		System.out.println("1号店商品发布成功");
		return product;
	}

	//商品上下架
	public static boolean productShelve(User user,long shelveUpOrDown,long itemId) throws Exception{
		YhdClient yhd = new YhdClient(url, appKey, appSecret);
		if(shelveUpOrDown == ConstantUtil.ITEM_SHELVE_UP){
			//产品上架
			ProductShelvesupUpdateRequest request = new ProductShelvesupUpdateRequest();
			request.setOuterId (String.valueOf(itemId));
			ProductShelvesupUpdateResponse response = yhd.excute(request, user.getSession_yhd());
			System.out.println("yhdUtil-productShelve up response: " + response.getBody());
			if(JSONObject.fromObject(response.getBody()).getJSONObject("response").containsKey("errInfoList")){
				throw new Exception("1号店商品上架失败");
			}else{
				return true;
			}
		}else{
			//产品下架
			ProductShelvesdownUpdateRequest requeset = new ProductShelvesdownUpdateRequest();
			requeset.setOuterId(String.valueOf(itemId));
			ProductShelvesdownUpdateResponse response = yhd.excute(requeset,user.getSession_yhd());
			System.out.println(" yhdUtil-productShelve down response:下架 " + response.getBody());
			if(JSONObject.fromObject(response.getBody()).getJSONObject("response").containsKey("errInfoList")){
				throw new Exception("1号店商品下架失败");
			}else{
				return true;
			}
		}
	}

	/**
	 * 1号店没有删除接口，删除商品需要到商家后台手动处理
	 */
	
	public static void main(String[] args) {
		Product product = FactoryUtil.getTestProduct();
		User user = FactoryUtil.getTestUser();
//		product.setItemId(2200779854451L);
		product.setTitle("这是新的测试标题");
		product.setItemId(123123321L);
		try {
			YhdUtil.productAdd(user, product);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
