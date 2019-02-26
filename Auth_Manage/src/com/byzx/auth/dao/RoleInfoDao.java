/**
 * @filename RoleInfoDao.java
 * @author 刘智龙
 * @date 2018年8月20日 下午3:56:17
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.RoleInfo;

/**
 * 角色持久层接口
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月20日 下午3:56:57
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月20日 下午3:56:57
 * @modifyDesc TODO
 * @version TODO
 */
public interface RoleInfoDao {

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
	 * 通过用户Id删除角色
	 * 
	 * @comment
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	public int deleteRolesByUserId(int userId);

	/**
	 * 通过用户Id，角色id给用户分配角色
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByUser(HashMap<Object, Object> map);

	/**
	 * 查询角色总数
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getRolesNum(RoleInfo role);

	/**
	 * 分页查询角色信息
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<RoleInfo> findAllRoles(HashMap<Object, Object> map);

	/**
	 * 角色code唯一性校验
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
	 * 角色禁用/禁用
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
	 * 通过用户组Id删除角色
	 * 
	 * @comment
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	public int deleteRolesByGroupId(int groupId);
	
	/**
	 * 通过用户组Id，角色id给用户分配角色
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByGroup(HashMap<Object, Object> map);


}
