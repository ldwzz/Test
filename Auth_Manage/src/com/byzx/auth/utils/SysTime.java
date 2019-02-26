

package com.byzx.auth.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysTime {

	public static String sysTime(){
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		System.out.println(df.format(day));
		return df.format(day).toString(); 
	}
	
	
}

/**
 * @filename SysTime.java
 * @author Administrator
 * @date 2018年9月10日 下午5:21:22
 * @version 1.0
 * Copyright (C) 2018 
 */
