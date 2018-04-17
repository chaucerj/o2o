package com.chaucer.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chaucer.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 处理缩略图，并补全文件夹
 * 
 * @author
 *
 */
public class ImageUtil {
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	// 当前项目编译路径
	private static String basePath = Thread.currentThread()
			.getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static File transferCommonMultiFile2File(
			CommonsMultipartFile cfile) {
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
	 * @param CommonsMultipartFile
	 *            ---Spring提供的读取文件的类
	 * @return 相对路径形式方便项目迁移，多系统运行
	 */
	public static String generateThumbnail(ImageHolder thumbnail,
			String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImgName());
		makeDir(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current img path is" + relativeAddr);
		File newImg = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete img path is" + PathUtil.getImgBasePath()
				+ relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File(basePath + "/watermark.jpg")),
							0.25f)
					.outputQuality(0.8f).toFile(newImg);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 处理详情图，并返回新生成图片的相对值路径
	 * 
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail,
			String targetAddr) {
		// 获取不重复的随机名
		String realFileName = getRandomFileName();
		// 获取文件的扩展名如png,jpg等
		String extension = getFileExtension(thumbnail.getImgName());
		// 如果目标路径不存在，则自动创建
		makeDir(targetAddr);
		// 获取文件存储的相对路径(带文件名)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// 获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath()
				+ relativeAddr);
		// 调用Thumbnails生成带有水印的图片
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File(basePath + "/watermark.jpg")),
							0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("创建缩图片失败：" + e.toString());
		}
		// 返回图片相对路径地址
		return relativeAddr;
	}

	public static String getRandomFileName() {
		int randNum = r.nextInt(89999) + 10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr + randNum;
	}

	// 获取文件扩展名
	private static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf("."));
	}

	// 创建目标目录所涉及到的文件夹创建
	private static void makeDir(String targetAddr) {
		String realFilePath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFilePath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 
	 * @param storePath
	 *            文件路径还是目录路径
	 */
	public static void deleteFileOrPath(String storePath) {
		File FileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if (FileOrPath.exists()) {
			if (FileOrPath.isDirectory()) {
				File files[] = FileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			FileOrPath.delete();
		}

	}

	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("E:/project/image/test.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT,
						ImageIO.read(new File(basePath + "watermark.jpg")),
						0.25f)
				.outputQuality(0.8f).toFile("E:/project/image/test1.jpg");
	}
}
