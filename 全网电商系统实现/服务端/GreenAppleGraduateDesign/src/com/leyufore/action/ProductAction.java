package com.leyufore.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.struts2.ServletActionContext;
import org.omg.PortableServer.Servant;

import com.leyufore.domain.Product;
import com.leyufore.domain.User;
import com.leyufore.util.ConstantUtil;
import com.leyufore.util.FactoryUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class ProductAction extends ActionSupport{
	
	private File uploadImg;
	private String uploadImgContentType;
	private String uploadImgFileName;
	
	private User user;
	private Product product;

	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public File getUploadImg() {
		return uploadImg;
	}

	public void setUploadImg(File uploadImg) {
		this.uploadImg = uploadImg;
	}

	public String getUploadImgContentType() {
		return uploadImgContentType;
	}

	public void setUploadImgContentType(String uploadImgContentType) {
		this.uploadImgContentType = uploadImgContentType;
	}

	public String getUploadImgFileName() {
		return uploadImgFileName;
	}

	public void setUploadImgFileName(String uploadImgFileName) {
		this.uploadImgFileName = uploadImgFileName;
	}
	/**
	 * 商品发布
	 */
	public void productRelease() throws IOException{
		System.out.println(JSONObject.fromObject(product));
		System.out.println(JSONObject.fromObject(user));
		JSONObject resultJson = new JSONObject();
		try {
			Product resultProduct = FactoryUtil.getProductService().productAdd(user, product);
			resultJson.put("code", 200);
			resultJson.put("product", resultProduct);
			System.out.println(resultJson);
		} catch (Exception e) {
			resultJson.put("code", 400);
			resultJson.put("msg", e.getMessage());
			System.out.println(resultJson);
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
//		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}
	/**
	 * 图片上传
	 */
	public void productImgUpload() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject resultJson = new JSONObject();
		System.out.println(uploadImgContentType);
		if(uploadImg == null || !(uploadImgContentType.equals("image/jpg")||uploadImgContentType.equals("image/jpeg"))){
			System.out.println("img为null或img非jpeg,jpg格式");
			resultJson.put("code", 400);
			resultJson.put("msg", "img为null或img非jpeg,jpg格式");
			out.println(resultJson);
			out.flush();
			out.close();
			return;
		}
		byte[] buffer = new byte[1024];
		int readBytes;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = sdf.format(new Date()) + "." + uploadImgContentType.substring(6);
		System.out.println(filename);
		FileInputStream fileInputStream = new FileInputStream(uploadImg);
		FileOutputStream fileOutputStream = new FileOutputStream(ServletActionContext.getServletContext().getRealPath("/")+"uploadImg/"+filename);
		FileOutputStream fileOutputStream2 = new FileOutputStream("/Users/wenrule/Desktop/毕业设计最终版/全网电商服务器图片/"+filename);
		while( (readBytes = fileInputStream.read(buffer)) != -1){
			fileOutputStream.write(buffer);
			fileOutputStream2.write(buffer);
		}
		fileInputStream.close();
		fileOutputStream.close();
		fileOutputStream2.close();
		
		resultJson.put("code", 200);
		resultJson.put("imgPath", "http://localhost:8080/GreenAppleGraduateDesign/uploadImg/"+ filename);
		out.println(resultJson);
		out.flush();
		out.close();
	}
	/**
	 * 登录时查询所有商品
	 */
	public void productFindAll() throws IOException{
		List<Product> productList = FactoryUtil.getProductService().productFindAll(user);
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", 200);
		resultJson.put("productList", productList);
		System.out.println("productFindAll response json :\n"+resultJson);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}
	/**
	 * 商品上架
	 */
	//客户端在设计的时候并没有保留一个product的所有信息，所以这时候从客户端发回来请求中只是product.ItemId
	public void productShelveUp() throws IOException{
		System.out.println(product.getItemId());
		JSONObject resultJson = new JSONObject();
		try {
			Product resultProdcut = FactoryUtil.getProductService().productShelve(user, ConstantUtil.ITEM_SHELVE_UP, product.getItemId());
			resultJson.put("code", 200);
			resultJson.put("product", resultProdcut);
			System.out.println("ProductAction -productShelveUp: \n"+resultJson);
		} catch (Exception e) {
			System.out.println("ProductAction -productShelveUp -exceptionMsg: \n"+e.getMessage());
			resultJson.put("code", 400);
			resultJson.put("msg", e.getMessage());
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}
	/**
	 * 商品下架
	 */
	//客户端在设计的时候并没有保留一个product的所有信息，所以这时候从客户端发回来请求中只是product.ItemId
	public void productShelveDown() throws IOException{
		System.out.println(product.getItemId());
		JSONObject resultJson = new JSONObject();
		try {
			Product resultProdcut = FactoryUtil.getProductService().productShelve(user, ConstantUtil.ITEM_SHELVE_DOWN, product.getItemId());
			resultJson.put("code", 200);
			resultJson.put("product", resultProdcut);
			System.out.println("ProductAction -productShelveDown: \n"+resultJson);
		} catch (Exception e) {
			System.out.println("ProductAction -productShelveDown -exceptionMsg: \n"+e.getMessage());
			resultJson.put("code", 400);
			resultJson.put("msg", e.getMessage());
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}

	/**
	 * 商品删除
	 */
	public void productDelete() throws IOException{
		System.out.println(product.getItemId());
		JSONObject resultJson = new JSONObject();

		try {
			long itemId = FactoryUtil.getProductService().productDelete(user, product.getItemId());
			resultJson.put("code", 200);
			resultJson.put("itemId", itemId);
			resultJson.put("prompt", "淘宝平台删除成功，1号店平台暂不提供删除，请到1号店商家后台进行删除");
		} catch (Exception e) {
			resultJson.put("code", 400);
			resultJson.put("msg", e.getMessage());
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}
}
