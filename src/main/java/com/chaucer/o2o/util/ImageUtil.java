package com.chaucer.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
/**
 * 处理缩略图，并补全文件夹
 * @author c_zhengsiyi-001
 *
 */
public class ImageUtil {
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	private static String basePath = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	
	public static File transferCommonMultiFile2File(CommonsMultipartFile cfile){
		File file = new File(cfile.getOriginalFilename());
		try {
			cfile.transferTo(file);
		} catch (IllegalStateException e) {
		logger.error(e.toString());
		} catch (IOException e) {
		logger.error(e.toString());
		}
		return file;
	}
   /**
    * @param   CommonsMultipartFile ---Spring提供的读取文件的类
    * @return  相对路径形式方便项目迁移，多系统运行
    */
	public static String generateThumbnail(InputStream ips,String filename,String targetAddr){
		String realFileName = getRandomFileName();
		String extension = getFileExtension(filename);
		makeDir(targetAddr);
		String realativeAddr = targetAddr + realFileName + extension;
		logger.debug("current img path is"+realativeAddr);
		File newImg = new File(PathUtil.getImgBasePath()+realativeAddr);
		logger.debug("current complete img path is"+PathUtil.getImgBasePath()+realativeAddr);
		try{
			Thumbnails.of(ips).size(200, 200).watermark(
					Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"watermark.jpg")),
					0.25f).outputQuality(0.8f).toFile(newImg);
		}catch(IOException e){
			logger.error(e.toString());
		}
		return realativeAddr;
	}
	public static String getRandomFileName(){
		int randNum = r.nextInt(89999)+10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr+randNum;
	}
	//获取文件扩展名
	private static String getFileExtension(String filename){
		return filename.substring(filename.lastIndexOf("."));
	}
	//创建目标目录所涉及到的文件夹创建
	private static void makeDir(String targetAddr){
		String realFilePath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(realFilePath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
	}
    public static void main(String[] args) throws IOException {
	Thumbnails.of(new File("E:/project/image/test.jpg")).size(200, 200).
	watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"watermark.jpg")),0.25f ).
	outputQuality(0.8f).toFile("E:/project/image/test1.jpg");
   }
}
