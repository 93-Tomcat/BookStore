package com.atguigu.bookstore.utils;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 编写web项目中相关的工具方法
 * @author 93
 *
 */
public class WebUtils {
	//自动将参数设置给指定对象的方法
	public static <T>T param2Bean(HttpServletRequest request,T t){
		//获取数据源
		Map<String,String[]> map = request.getParameterMap();
		//调用beanUtils的方法将map数据设置给指定对象
		try {
			BeanUtils.populate(t,map);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return t;
	}
}
