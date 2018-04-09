package com.chaucer.o2o.util;

public class PathUtil {
	
	public static String seperator = System.getProperty("file.separator");
	public static String getImgBasePath(){
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")){
			basePath="E:/project/image";
		}else{
			basePath="/home/chaucer/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	public static String getShopImgPath(long shopId){
		String imgPath="/upload/images/item/shop/"+shopId+"/";
		imgPath = imgPath.replace("/", seperator);
		return imgPath;
	}

}
