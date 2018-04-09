package com.chaucer.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaucer.o2o.entity.Area;
import com.chaucer.o2o.service.AreaService;



@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	    @Autowired
	    private AreaService areaService;
	    @RequestMapping(value = "/listall", method= RequestMethod.GET)
	    @ResponseBody
	    private Map<String,Object> listArea(){
	    	logger.info("===start time===");
	    	long startTime = System.currentTimeMillis();
	        Map<String, Object> modelmap = new HashMap<String,Object>();
	        List<Area> list = new ArrayList<>();
	        try{
	        	list = areaService.getAreaList();
	        	modelmap.put("rows",list);
	        	modelmap.put("total", list.size());
	        }catch(Exception e){
	        	modelmap.put("success", false);
	        	modelmap.put("errMsg", e.toString());
	        }
	       
	        long endTime = System.currentTimeMillis();
	        logger.debug("costTime[{}ms]",endTime-startTime);
	        logger.info("===end time===");
	        return modelmap;
	    }

}
