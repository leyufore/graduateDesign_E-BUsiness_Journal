package com.leyufore.util;

import java.util.ArrayList;
import java.util.List;

import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.PontusTravelItemSkuInfo;
import com.taobao.api.domain.PontusTravelPrices;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest;
import com.taobao.api.request.AlitripTravelItemShelveRequest;
import com.taobao.api.request.AlitripTravelItemSkuOverrideRequest;
import com.taobao.api.request.ItemDeleteRequest;
import com.taobao.api.request.PictureUploadRequest;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest.PontusTravelBookingRuleInfo;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest.PontusTravelFreedomItemExt;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest.PontusTravelGroupItemExt;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest.PontusTravelItemBaseInfo;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest.PontusTravelItemTraffic;
import com.taobao.api.request.AlitripTravelItemBaseAddRequest.PontusTravelItemTrafficInfo;
import com.taobao.api.response.AlitripTravelItemBaseAddResponse;
import com.taobao.api.response.AlitripTravelItemShelveResponse;
import com.taobao.api.response.AlitripTravelItemSkuOverrideResponse;
import com.taobao.api.response.ItemDeleteResponse;
import com.taobao.api.response.PictureUploadResponse;

import net.sf.json.JSONObject;

public class TaobaoUtil {
	//旅游应用
	private static String url = "http://gw.api.tbsandbox.com/router/rest";
	private static String appKey = "1023166646";
	private static String appSecret = "sandbox8587c2e61042aacc476e891d3";
	
	//商品添加
	public static Product productAdd(User user,Product product) throws Exception{
		//上传图片
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		PictureUploadRequest req = new PictureUploadRequest();
		req.setPictureCategoryId(0L);
		String fileLocalPath = PictureUtil.getProductPicturePath(product.getImgPath());
		System.out.println("taobao picture path : " + fileLocalPath);
		req.setImg(new FileItem(fileLocalPath));
		req.setImageInputTitle(product.getImgTitle());
		
		PictureUploadResponse rsp = client.execute(req, user.getSession_taobao());
		PrintUtil.print("picture upload response", rsp.getBody());
		if (JSONObject.fromObject(rsp.getBody()).containsKey("error_response")) {
			throw new Exception("淘宝平台图片上传失败");
		}
		System.out.println("淘宝平台上传成功");
		JSONObject pictureJson = JSONObject.fromObject(rsp.getBody()).getJSONObject("picture_upload_response").getJSONObject("picture");
		PrintUtil.print("picture upload response-- picture id", pictureJson.get("picture_id"));
		PrintUtil.print("picture upload response-- picture path", pictureJson.get("picture_path"));
		
		//发布商品
		AlitripTravelItemBaseAddRequest req_travelItemBaseAdd = new AlitripTravelItemBaseAddRequest();
		//商品基本信息
		PontusTravelItemBaseInfo obj1 = new PontusTravelItemBaseInfo();
		obj1.setTitle(product.getTitle());
		obj1.setItemType(product.getItem_type());
		obj1.setDesc(product.getDesc());
		obj1.setTripMaxDays(product.getTrip_max_day());
		obj1.setAccomNights(product.getAccomNigth());
		obj1.setProv(product.getProv());
		obj1.setCity(product.getCity());
		obj1.setFromLocations(product.getFrom_location());
		obj1.setToLocations(product.getTo_location());
		List<String> picUrlsList = new ArrayList<String>();
		picUrlsList.add(pictureJson.getString("picture_path"));
		obj1.setPicUrls(picUrlsList);
		req_travelItemBaseAdd.setBaseInfo(obj1);
		
		//预定规则
		List<PontusTravelBookingRuleInfo> list3 = new ArrayList<PontusTravelBookingRuleInfo>();
		PontusTravelBookingRuleInfo obj4 = new PontusTravelBookingRuleInfo();
		list3.add(obj4);
		obj4.setRuleType("Fee_Included");
		obj4.setRuleDesc(product.getFee_included_desc());
		PontusTravelBookingRuleInfo obj_fee_Excluded = new PontusTravelBookingRuleInfo();
		list3.add(obj_fee_Excluded);
		obj_fee_Excluded.setRuleType("Fee_Excluded");
		obj_fee_Excluded.setRuleDesc(product.getFee_excluded_desc());
		PontusTravelBookingRuleInfo obj_orderInfo = new PontusTravelBookingRuleInfo();
		list3.add(obj_orderInfo);
		obj_orderInfo.setRuleType("Order_Info");
		obj_orderInfo.setRuleDesc(product.getOrder_info());
		req_travelItemBaseAdd.setBookingRules(list3);
		
		//自由行必填信息
		PontusTravelFreedomItemExt obj5 = new PontusTravelFreedomItemExt();
		PontusTravelItemTrafficInfo obj17 = new PontusTravelItemTrafficInfo();
		obj17.setTrafficType(product.getBackTrafficType());
		List<PontusTravelItemTraffic> list19 = new ArrayList<PontusTravelItemTraffic>();
		PontusTravelItemTraffic obj20 = new PontusTravelItemTraffic();
		list19.add(obj20);
		if(product.getBackTrafficNo() != null){
			obj20.setTrafficNo(product.getBackTrafficNo());
		}
		obj17.setTraffics(list19);
		obj5.setBackTrafficInfo(obj17);
		PontusTravelItemTrafficInfo obj21 = new PontusTravelItemTrafficInfo();
		obj21.setTrafficType(product.getGoTrafficType());
		List<PontusTravelItemTraffic> list23 = new ArrayList<PontusTravelItemTraffic>();
		PontusTravelItemTraffic obj24 = new PontusTravelItemTraffic();
		list23.add(obj24);
		if(product.getGoTrafficNo() != null){
			obj24.setTrafficNo(product.getGoTrafficNo());
		}
		obj21.setTraffics(list23);
		obj5.setGoTrafficInfo(obj21);
		req_travelItemBaseAdd.setFreedomItemExt(obj5);
		
		//跟团游必填信息
		PontusTravelGroupItemExt obj25 = new PontusTravelGroupItemExt();
		obj25.setRouteType(1L);
		PontusTravelItemTrafficInfo obj29 = new PontusTravelItemTrafficInfo();
		obj29.setTrafficType(product.getBackTrafficType());
		obj25.setBackTrafficInfo(obj29);
		PontusTravelItemTrafficInfo obj33 = new PontusTravelItemTrafficInfo();
		obj33.setTrafficType(product.getGoTrafficType());
		obj25.setGoTrafficInfo(obj33);
		req_travelItemBaseAdd.setGroupItemExt(obj25);
		
		AlitripTravelItemBaseAddResponse rsp_travelItemBaseAdd = client.execute(req_travelItemBaseAdd, user.getSession_taobao());
		PrintUtil.print("item base add response :", rsp_travelItemBaseAdd.getBody());
		JSONObject itemAddJson = JSONObject.fromObject(rsp_travelItemBaseAdd.getBody()).getJSONObject("alitrip_travel_item_base_add_response").getJSONObject("travel_item");
		long itemId = itemAddJson.getLong("item_id");
		PrintUtil.print("item base add response -- item_id", itemId);
		product.setItemId(itemId);
		
		//日历价格库存信息补全
		AlitripTravelItemSkuOverrideRequest req_itemSkuOverrideRequest = new AlitripTravelItemSkuOverrideRequest();
		req_itemSkuOverrideRequest.setItemId(product.getItemId());
		List<PontusTravelItemSkuInfo> list2 = new ArrayList<PontusTravelItemSkuInfo>();
		PontusTravelItemSkuInfo obj3 = new PontusTravelItemSkuInfo();
		list2.add(obj3);
		obj3.setPackageName("测试套餐");
		product.setItemSku_packageName("测试套餐");
		obj3.setOuterSkuId("123321");
		List<PontusTravelPrices> list6 = new ArrayList<PontusTravelPrices>();
		PontusTravelPrices obj7 = new PontusTravelPrices();
		list6.add(obj7);
		obj7.setPriceType(product.getItemSku_priceType());
		obj7.setStock(product.getItemSku_stock());
		obj7.setPrice(product.getItemSku_price()*100);
		obj7.setDate(StringUtils.parseDateTime("2016-09-19 09:42:24"));
		obj3.setPrices(list6);
		req_itemSkuOverrideRequest.setSkus(list2);
		AlitripTravelItemSkuOverrideResponse rsp_itemSkuOverride = client.execute(req_itemSkuOverrideRequest, user.getSession_taobao());
		PrintUtil.print("itemSku override response :", rsp_itemSkuOverride.getBody());
		
		if (JSONObject.fromObject(rsp_itemSkuOverride.getBody()).containsKey("error_response")) {
			throw new Exception("淘宝平台商品新增失败");
		}
		System.out.println("淘宝平台商品新增成功");
		//商品上架
		AlitripTravelItemShelveRequest req_itemShelve = new AlitripTravelItemShelveRequest();
		req_itemShelve.setItemId(product.getItemId());
		req_itemShelve.setItemStatus(ConstantUtil.ITEM_SHELVE_UP);
		AlitripTravelItemShelveResponse rsp_itemShelve = client.execute(req_itemShelve, user.getSession_taobao());
		PrintUtil.print("item shelve response :", rsp_itemShelve.getBody());
	
		if (JSONObject.fromObject(rsp_itemShelve.getBody()).containsKey("error_response")) {
			throw new Exception("淘宝平台商品上架失败");
		}
		System.out.println("淘宝平台商品上架成功");
		return product;
	}
	
	//商品上下架
	public static boolean productShelve(User user,long shelveUpOrDown,long itemId) throws Exception{
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		AlitripTravelItemShelveRequest req_itemShelve = new AlitripTravelItemShelveRequest();
		req_itemShelve.setItemId(itemId);
		req_itemShelve.setItemStatus(shelveUpOrDown);
		AlitripTravelItemShelveResponse rsp_itemShelve = client.execute(req_itemShelve,user.getSession_taobao());
		System.out.println("TaobaoUtil -- productShelve response :"+rsp_itemShelve.getBody());
		if(JSONObject.fromObject(rsp_itemShelve.getBody()).containsKey("error_response")){
			throw new Exception("淘宝平台上下架失败");
		}else{
			System.out.println("淘宝平台上下架成功");
			return true;
		}
	}
	
	//商品删除
	public static boolean productDelete(User user,long itemId) throws Exception{
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
		ItemDeleteRequest request = new ItemDeleteRequest();
		request.setNumIid(itemId);
		ItemDeleteResponse response;
		response = client.execute(request, user.getSession_taobao());
		System.out.println("淘宝平台 productDelete response:");
		System.out.println(response.getBody());
		if(JSONObject.fromObject(response.getBody()).containsKey("error_response")){
			throw new Exception("淘宝平台商品删除失败");
		}else{
			System.out.println("淘宝平台商品删除成功");
		}
		return true;
	}
	
	public static void main(String[] args){
//		User user = new UserDao().findByUserName("leyufore");
//		Product product = FactoryUtil.getTestProduct();
//		try {
//			TaobaoUtil.productDelete(user, 2200781687786L);
//		} catch (ApiException e) {
//			e.printStackTrace();
//		} 

	}
	
}
