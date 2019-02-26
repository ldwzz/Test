/**
 * @filename UserInfoTest.java
 * @author ������
 * @date 2018��8��16�� ����3:27:25
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
 * �û���Ϣ������
 * 
 * @comment
 * @author ������
 * @date 2018��8��16�� ����3:27:45
 * @modifyUser ������
 * @modifyDate 2018��8��16�� ����3:27:45
 * @modifyDesc TODO
 * @version TODO
 * @param <V>
 */

public class UserInfoTest<V> {

	/**
	 * ��¼
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
			System.out.println("��¼�ɹ�");
		} else {
			System.out.println("��¼ʧ��");
		}
	}

	/**
	 * �û�Ȩ�޲�ѯ
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
	 * ��ҳ��ѯ�û�
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
	 * ��ѯ�û�����
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
