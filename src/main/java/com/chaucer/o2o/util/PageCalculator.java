package com.chaucer.o2o.util;

public class PageCalculator {
	public static int calculate(int pageIndex,int pageSize){
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;//第一页和后几页开始的行数
	}

}
