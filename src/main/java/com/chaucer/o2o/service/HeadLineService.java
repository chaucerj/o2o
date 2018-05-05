package com.chaucer.o2o.service;

import java.io.IOException;
import java.util.List;

import com.chaucer.o2o.entity.HeadLine;

public interface HeadLineService {
	public final static String HLLISTKEY = "headlinelist";

	List<HeadLine> getHeadLineList(HeadLine headLineCondition)
			throws IOException;
}
