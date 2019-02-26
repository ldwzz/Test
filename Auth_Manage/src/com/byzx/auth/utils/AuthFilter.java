/**
 * @filename AuthFilter.java
 * @author ������
 * @date 2018��8��25�� ����9:28:15
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
 * ������
 * 
 * @comment
 * @author ������
 * @date 2018��8��26�� ����5:55:49
 * @modifyUser ������
 * @modifyDate 2018��8��26�� ����5:55:49
 * @modifyDesc TODO
 * @version TODO
 */
public class AuthFilter implements Filter {
	/**
	 * ����
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * doFilter����
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// ��ȡ�����ַ
		String path = request.getServletPath();
		// ������.action��β(��ȥ��¼����֤��action)
		if (path.endsWith("action") && !(path.indexOf("/login.action") != -1)
				&& !(path.indexOf("/valiCode.action") != -1) && !(path.indexOf("/index.action") != -1)) {
			// ��ȡsession����
			Object object = request.getSession().getAttribute("userInfo");
			if (object == null) {
				// ��session�洢�Ķ���Ϊ�գ�����ת��¼ҳ��
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>window.location.href='"
								+ request.getContextPath()
								+ "/pages/login.jsp';</script>");
				return;
			}
		}
		// ����ִ��
		arg2.doFilter(request, response);
	}

	/**
	 * ��ʼ��
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
