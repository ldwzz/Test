/**
 * @filename AuthInfoDao.java
 * @author 刘智龙
 * @date 2018年8月21日 下午9:00:42
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.AuthInfo;

public interface AuthInfoDao {

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
	 * 通过角色Id删除权限
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public int deleteAuthInfo(int roleId);

	/**
	 * 通过角色id,权限id添加权限
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthByRole(HashMap<Object, Object> map);

	/**
	 * authCode唯一校验
	 * 
	 * @comment
	 * @param authCode
	 * @return
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
	 * 通过用户Id删除user_auth权限
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public int deleteUserAuth(int userId);

	/**
	 * 通过用户id,权限id给用户添加权限（user_auth）
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthByUser(HashMap<Object, Object> map);

	/**
	 * 通过角色Id数组查询权限Id
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public String findAuthByroles(HashMap<Object, Object> map);

	/**
	 * 通过一组角色ID删除权限
	 * 
	 * @comment
	 * @param roleIds
	 * @return
	 * @version 1.0
	 */
	public int deleteAuthByRoles(String roleIds[]);

	/**
	 * 给用户组添加权限
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthGroup(HashMap<Object, Object> map);

	/**
	 * 查找子集
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> getChildList(AuthInfo authInfo);

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
	 * 删除子权限
	 * 
	 * @comment
	 * @param AuthIds
	 * @return
	 * @version 1.0
	 */
	public int recoverChildList(String AuthIds[]);

}
