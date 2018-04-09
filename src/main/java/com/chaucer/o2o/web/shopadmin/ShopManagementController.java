package com.chaucer.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.chaucer.o2o.dto.ShopExecution;
import com.chaucer.o2o.entity.Area;
import com.chaucer.o2o.entity.Shop;
import com.chaucer.o2o.entity.ShopCategory;
import com.chaucer.o2o.entity.UserInfo;
import com.chaucer.o2o.enums.ShopStatusEnum;
import com.chaucer.o2o.exceptions.ShopOperationException;
import com.chaucer.o2o.service.AreaService;
import com.chaucer.o2o.service.ShopCategoryService;
import com.chaucer.o2o.service.ShopService;
import com.chaucer.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService
					.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {

		Map<String, Object> modelmap = new HashMap<String, Object>();
		// 1.接收
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelmap.put("success", false);
			modelmap.put("errMsg", e.getMessage());
			return modelmap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultiPartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultiPartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiPartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multiPartHttpServletRequest
					.getFile("shopImg");
		} else {
			modelmap.put("success", false);
			modelmap.put("errMsg", "上传图片不能为空");
			return modelmap;
		}
		// 2.注册
		if (shop != null && shopImg != null) {
			UserInfo user = new UserInfo();
			// 从session获取
			user.setUserId(1L);
			shop.setOwner(user);
			// File shopImgfile = new
			// File(PathUtil.getImgBasePath()+ImageUtil.getRandomFileName());
			// try {
			// shopImgfile.createNewFile();
			// } catch (IOException e) {
			// modelmap.put("success", false);
			// modelmap.put("errMsg", e.getMessage());
			// return modelmap;
			// }
			// try {
			// InputStream2File(shopImg.getInputStream(),shopImgfile);
			// } catch (IOException e) {
			// modelmap.put("success", false);
			// modelmap.put("errMsg", e.getMessage());
			// return modelmap;
			// }
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(),
						shopImg.getOriginalFilename());
				if (se.getStatus() == ShopStatusEnum.CHECK.getStatus()) {
					modelmap.put("success", true);
				} else {
					modelmap.put("success", false);
					modelmap.put("errMsg", se.getStatusInfo());
				}
				return modelmap;
			} catch (ShopOperationException e) {
				modelmap.put("success", false);
				modelmap.put("errMsg", e.getMessage());
				return modelmap;
			} catch (IOException e) {
				modelmap.put("success", false);
				modelmap.put("errMsg", e.getMessage());
				return modelmap;
			}

		} else {
			modelmap.put("success", false);
			modelmap.put("errMsg", "请输入店铺信息");
			return modelmap;
		}
	}

	// private static void InputStream2File(InputStream ips,File file){
	// OutputStream ops = null;
	// try{
	// ops = new FileOutputStream(file);
	// int bytesread = 0;
	// byte[] buffer= new byte[1024];
	// while((bytesread=ips.read(buffer))!=-1){
	// ops.write(buffer, 0, bytesread);
	// }
	// }catch(Exception e){
	// throw new RuntimeException("调用InputStreamFile产生异常"+e.getMessage());
	// }finally{
	// try{
	// if(ops!=null){
	// ops.close();
	// }
	// if(ips!=null){
	// ips.close();
	// }
	// }catch(IOException e){
	// throw new RuntimeException("InputStream2File关闭流发生错误"+e.getMessage());
	// }
	// }
	// }
}
