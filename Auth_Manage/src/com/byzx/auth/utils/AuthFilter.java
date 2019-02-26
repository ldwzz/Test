/**
 * @filename AuthFilter.java
 * @author 刘智龙
 * @date 2018年8月25日 上午9:28:15
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月26日 下午5:55:49
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月26日 下午5:55:49
 * @modifyDesc TODO
 * @version TODO
 */
public class AuthFilter implements Filter {
	/**
	 * 销毁
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * doFilter方法
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 获取请求地址
		String path = request.getServletPath();
		// 拦截以.action结尾(除去登录，验证码action)
		if (path.endsWith("action") && !(path.indexOf("/login.action") != -1)
				&& !(path.indexOf("/valiCode.action") != -1) && !(path.indexOf("/index.action") != -1)) {
			// 获取session对象
			Object object = request.getSession().getAttribute("userInfo");
			if (object == null) {
				// 若session存储的对象为空，则跳转登录页面
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>window.location.href='"
								+ request.getContextPath()
								+ "/pages/login.jsp';</script>");
				return;
			}
		}
		// 正常执行
		arg2.doFilter(request, response);
	}

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
