package com.leyufore.util;

public class PictureUtil {
	public static String getProductPicturePath(String url){
		int lastXieGang = url.lastIndexOf("/");
		return "/Users/wenrule/Desktop/毕业设计最终版/全网电商服务器图片/"+url.substring(lastXieGang +1);
	}
}
