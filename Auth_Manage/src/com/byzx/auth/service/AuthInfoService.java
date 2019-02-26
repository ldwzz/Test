/**
 * @filename AuthInfoService.java
 * @author 刘智龙
 * @date 2018年8月21日 下午9:08:58
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserInfo;

/**
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月21日 下午9:09:16
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月21日 下午9:09:16
 * @modifyDesc TODO
 * @version TODO
 */
public interface AuthInfoService {
	/**
	 * 查询所有权限
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> findAllAuth(AuthInfo authInfo);

	/**
	 * 通过角色Id查询权限Id
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public String findAuthByroleId(int roleId);

	/**
	 * @comment 通过角色id,权限id添加权限
	 * @param map request,role对象
	 * @return 添加成功返回1，否则返回0
	 * @version 1.0
	 */
	public int addAuthByRole(HttpServletRequest request, RoleInfo role);

	/**
	 * @comment authCode唯一校验
	 * @param authCode  权限code字符串
	 * @return 唯一返回0，否则返回1
	 * @version 1.0
	 */
	public int authCodeVerify(String authCode);

	/**
	 * 添加权限信息
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public int insertAuth(AuthInfo authInfo);

	/**
	 * 修改权限信息
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public int updateAuth(AuthInfo authInfo);

	/**
	 * 通过权限Id查询是否权限在使用
	 * 
	 * @comment
	 * @param authId
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> selectAuth(int authId);

	/**
	 * 通过用户Id查询权限Id
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public String findAuthByUserId(int userId);

	/**
	 * 通过用户id,权限id给用户添加权限（user_auth）
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthByUser(HttpServletRequest request, UserInfo user);

	// /**
	// * 通过角色Id数组查询权限Id
	// *
	// * @comment
	// * @param roleId
	// * @return
	// * @version 1.0
	// */
	// public String findAuthByroles(HashMap<Object,Object> map);

	/**
	 * 查询子集
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public String selectChildList(AuthInfo authInfo, StringBuffer buffer);

	/**
	 * 删除子权限
	 * 
	 * @comment
	 * @param AuthIds
	 * @return
	 * @version 1.0
	 */
	public int deleteChildList(String AuthIds[]);

	/**
	 * 查询父权限
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public AuthInfo findParent(AuthInfo authInfo);

	/**
	 * 恢复子权限
	 * 
	 * @comment
	 * @param authIds
	 * @return
	 * @version 1.0
	 */
	public int recoverChildList(String authIds);

}
