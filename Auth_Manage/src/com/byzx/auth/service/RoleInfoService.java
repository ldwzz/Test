/**
 * @filename RoleInfoService.java
 * @author 刘智龙
 * @date 2018年8月20日 下午4:07:40
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;

/**
 * 角色业务层
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月20日 下午4:07:51
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月20日 下午4:07:51
 * @modifyDesc TODO
 * @version TODO
 */
public interface RoleInfoService {
	/**
	 * 查询所有角色信息
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<RoleInfo> getAllRoles();

	/**
	 * 通过userId查询角色Id，返回字符串
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public String getRoleIdsByUserId(int userId);

	/**
	 * 通过用户Id，角色id给用户分配角色
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByUser(HttpServletRequest request, UserInfo user);

	/**
	 * 查询角色总数
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getRolesNum(RoleInfo role);

	/**
	 * 分页查询用户信息
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<RoleInfo> findAllRoles(HashMap<Object, Object> map);

	/**
	 * 添加角色唯一性校验
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	public int addRoleVerify(RoleInfo role);

	/**
	 * 添加角色
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	public int addRoleInfo(RoleInfo role);

	/**
	 * 角色禁用
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	public int updateRoleState(RoleInfo role);
	
	/**
	 * 通过groupId查询角色Id，返回字符串
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public String getGroupRoles(int groupId);
	
	/**
	 * 通过用户组Id，角色id给用户分配角色
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByGroup(HttpServletRequest request, UserGroupInfo groupInfo);
}
