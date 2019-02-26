

package com.byzx.auth.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;





public class GetIpAddress {

	
	/**

	 * 获取访问用户的客户端IP（适用于公网与局域网）.

	 */

	public static final String getIpAddr(final HttpServletRequest request)

	        throws Exception {

	    if (request == null) {

	        throw (new Exception("getIpAddr method HttpServletRequest Object is null"));

	    }

	    String ipString = request.getHeader("x-forwarded-for");

	    if (ipString==null || ipString.length()==0 || "unknown".equalsIgnoreCase(ipString)) {

	        ipString = request.getHeader("Proxy-Client-IP");

	    }

	    if (ipString==null || ipString.length()==0 || "unknown".equalsIgnoreCase(ipString)) {

	        ipString = request.getHeader("WL-Proxy-Client-IP");

	    }

	    if (ipString==null ||  ipString.length()==0  || "unknown".equalsIgnoreCase(ipString)) {

	        ipString = request.getRemoteAddr();

	    }

	 

	    // 多个路由时，取第一个非unknown的ip

	    final String[] arr = ipString.split(",");

	    for (final String str : arr) {

	        if (!"unknown".equalsIgnoreCase(str)) {

	            ipString = str;

	            break;

	        }

	    }

	 

	    return ipString;

	}
	
	
	
}

/**
 * @filename GetIpAddress.java
 * @author Administrator
 * @date 2018年9月10日 下午2:51:09
 * @version 1.0
 * Copyright (C) 2018 
 */
