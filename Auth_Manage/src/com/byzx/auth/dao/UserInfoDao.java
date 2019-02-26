/**
 * @filename UserInfoDao.java
 * @author 刘智龙
 * @date 2018年8月16日 下午3:17:04
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;

/**
 * 用户持久层接口
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月16日 下午3:17:37
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月16日 下午3:17:37
 * @modifyDesc TODO
 * @version TODO
 */
public interface UserInfoDao {

	/**
	 * 登录
	 * 
	 * @comment
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	public UserInfo login(UserInfo userInfo);

	/**
	 * 查询用户权限
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> findAuthInfo(HashMap<Object, Object> map);

	/**
	 * 分页查询所有用户
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<UserInfo> getAllUsers(HashMap<Object, Object> map);

	/**
	 * 查询用户条数
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getUserNum(UserInfo user);

	/**
	 * 校验用户注册
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int addVerify(UserInfo user);

	/**
	 * 添加用户
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int addUserInfo(UserInfo user);

	/**
	 * 删除用户
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int deleteUser(UserInfo user);

	/**
	 * 批量删除用户
	 * 
	 * @comment
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	public int deleteUsers(UserInfo user);

	/**
	 * 重置用户密码
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int resetPassword(UserInfo user);

	/**
	 * 修改用户状态
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int updateUserState(UserInfo user);

	/**
	 * 修改用户信息
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int updateUserInfo(UserInfo user);

	/**
	 * 查询符合条件的用户组数
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getUserGroupNum(UserGroupInfo groupInfo);

	/**
	 * 分页查询用户组信息
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<UserGroupInfo> getAllUserGroup(HashMap<Object, Object> map);

	/**
	 * 查询用户组信息
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<UserGroupInfo> getUserGroup();

	/**
	 * 修改用户的用户组id
	 * 
	 * @comment
	 * @param groupInfo
	 * @return
	 * @version 1.0
	 */
	public int updateGroupId(UserInfo user);

	/**
	 * 校验用户组code唯一
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int addGroupVerify(UserGroupInfo groupInfo);

	/**
	 * 增加用户组
	 * 
	 * @comment
	 * @param groupInfo
	 * @return
	 * @version 1.0
	 */
	public int addGroupInfo(UserGroupInfo groupInfo);

	/**
	 * 修改用户组状态
	 * 
	 * @comment
	 * @param groupInfo
	 * @return
	 * @version 1.0
	 */
	public int updateGroupState(UserGroupInfo groupInfo);

	/**
	 * 根据用户id查询角色Id
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public String findRoleIds(UserInfo user);

	/**
	 * 数据权限
	 * 
	 * @comment
	 * @param userId
	 * @param roleCode
	 * @return
	 * @version 1.0
	 */
	public List<UserInfo> getUserInfos(HashMap<Object, Object> map);

	/**
	 * 查询不同权限数据条数
	 * 
	 * @comment
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	public int getUsersInfoNum(UserInfo user);
	

	
	/**
	 * @comment 根据用户名查询用户id
	 * @param userInfo
	 * @return  UserInfo
	 * @version 1.0
	 */
		public UserInfo login2(UserInfo userInfo);
		
		
	/**
	 * @comment  根据用户id查询用户名	
	 * @param userId
	 * @return String
	 * @version 1.0
	 */
		public String userNameByUserId(int userId);
	/**
	 * @comment 根据用户userIds（可能多个）查询出对应的userName
	 * @param userName
	 * @return String
	 * @version 1.0
	 */
		public String userIdsByUserName(String userName);
			
	
	
	
}
