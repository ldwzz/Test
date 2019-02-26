/**
 * @filename UserInfoServiceImpl.java
 * @author 刘智龙
 * @date 2018年8月16日 下午3:25:03
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.UserInfoDao;

/**
 * 
 * @comment 业务层实现类
 * @author 刘智龙
 * @date 2018年8月16日 下午3:26:38
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月16日 下午3:26:38
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * 登录
	 */
	@Override
	public UserInfo login(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.login(userInfo);
	}

	/**
	 * 查询用户权限
	 */
	@Override
	public List<AuthInfo> findAuthInfo(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.findAuthInfo(map);
	}

	/**
	 * 查询用户条数
	 */
	@Override
	public int getUserNum(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserNum(user);
	}

	/**
	 * 分页查询所有用户
	 */
	@Override
	public List<UserInfo> getAllUsers(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getAllUsers(map);
	}

	/**
	 * 校验用户注册
	 */
	@Override
	public int addVerify(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.addVerify(user);
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public int deleteUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.deleteUser(user);
	}

	/**
	 * 重置用户密码
	 */
	@Override
	public int resetPassword(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.resetPassword(user);
	}

	/**
	 * 修改用户状态
	 */
	@Override
	public int updateUserState(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateUserState(user);
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public int updateUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateUserInfo(user);
	}

	/**
	 * 添加用户
	 */
	@Override
	public int addUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.addUserInfo(user);
	}

	/**
	 * 批量删除用户
	 */
//	@Override
//	public int deleteUsers(HttpServletRequest request, UserInfo user) {
//		// TODO Auto-generated method stub
//		String userIds = request.getParameter("userIds");
//		String[] userId = userIds.split(",");
//		user.setUserIds(userId);
//		int re = userInfoDao.deleteUsers(user);
//		return re;
//	}

	/**
	 * 查询符合条件的用户组数
	 */
	@Override
	public int getUserGroupNum(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserGroupNum(groupInfo);
	}

	/**
	 * 分页查询用户组信息
	 */
	@Override
	public List<UserGroupInfo> getAllUserGroup(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getAllUserGroup(map);
	}

	/**
	 * 查询用户组信息
	 */
	@Override
	public List<UserGroupInfo> getUserGroup() {
		// TODO Auto-generated method stub
		return userInfoDao.getUserGroup();
	}

	/**
	 * 修改用户的用户组id
	 */
	@Override
	public int updateGroupId(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateGroupId(user);
	}

	/**
	 * 校验用户组code唯一
	 */
	@Override
	public int addGroupVerify(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.addGroupVerify(groupInfo);
	}

	/**
	 * 增加用户组
	 */
	@Override
	public int addGroupInfo(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.addGroupInfo(groupInfo);
	}

	/**
	 * 修改用户组状态
	 */
	@Override
	public int updateGroupState(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.updateGroupState(groupInfo);
	}

	/**
	 * 根据用户id查询角色Id
	 */
	@Override
	public String findRoleIds(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.findRoleIds(user);
	}

	/**
	 * 数据权限
	 */
	@Override
	public List<UserInfo> getUserInfos(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserInfos(map);
	}

	/**
	 * 查询不同权限数据条数
	 */
	@Override
	public int getUsersInfoNum(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.getUsersInfoNum(user);
	}

	@Override
	public UserInfo login2(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.login2(userInfo);
	}

	@Override
	public String userNameByUserId(int userId) {
		// TODO Auto-generated method stub
		return userInfoDao.userNameByUserId(userId);
	}

	@Override
	public String userIdsByUserName(String userName) {
		// TODO Auto-generated method stub
		return userInfoDao.userIdsByUserName(userName);
	}
}
