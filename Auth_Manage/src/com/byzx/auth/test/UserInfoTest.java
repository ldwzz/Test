/**
 * @filename UserInfoTest.java
 * @author 刘智龙
 * @date 2018年8月16日 下午3:27:25
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.UserInfoService;
import com.byzx.auth.utils.PageBean;

/**
 * 用户信息测试类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月16日 下午3:27:45
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月16日 下午3:27:45
 * @modifyDesc TODO
 * @version TODO
 * @param <V>
 */

public class UserInfoTest<V> {

	/**
	 * 登录
	 * 
	 * @comment
	 * @version 1.0
	 */
	@Test
	public void login() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-beans.xml");
		UserInfoService userInfoService = (UserInfoService) context
				.getBean("userInfoServiceImpl");
		UserInfo userInfo = new UserInfo("admin", "123456");
		UserInfo info = userInfoService.login(userInfo);
		if (info != null) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
	}

	/**
	 * 用户权限查询
	 * 
	 * @comment
	 * @version 1.0
	 */
	@Test
	public void findAuthInfo() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-beans.xml");
		UserInfoService userInfoService = (UserInfoService) context
				.getBean("userInfoServiceImpl");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", 1);
		map.put("parentId", 0);
		List<AuthInfo> authInfo = userInfoService.findAuthInfo(map);
		System.out.println(authInfo.toString());
	}

	/**
	 * 分页查询用户
	 * @comment 
	 * @version 1.0
	 */
	@Test
	public void getAllUsers() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-beans.xml");
		UserInfoService userInfoService = (UserInfoService) context
				.getBean("userInfoServiceImpl");
		PageBean page= new PageBean(2, 1);
		page.setTotalPage(2);
		UserInfo user = new UserInfo();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("user", user);
		System.out.println(map.toString());
		List<UserInfo> allUsers = userInfoService.getAllUsers(map);
		System.out.println(allUsers.toString());
	}

	/**
	 * 查询用户条数
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	@Test
	public void getUserNum() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-beans.xml");
		UserInfoService userInfoService = (UserInfoService) context
				.getBean("userInfoServiceImpl");
		int num = userInfoService.getUserNum(new UserInfo());
		System.out.println(num);
	}
}
