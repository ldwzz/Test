/**
 * @filename RoleInfoServiceImpl.java
 * @author 刘智龙
 * @date 2018年8月20日 下午4:09:01
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.RoleInfoDao;

/**
 * 角色Service实现类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月20日 下午4:09:14
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月20日 下午4:09:14
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
	@Autowired
	private RoleInfoDao roleInfoDao;

	/**
	 * 查询所有角色信息
	 */
	@Override
	public List<RoleInfo> getAllRoles() {
		// TODO Auto-generated method stub
		return roleInfoDao.getAllRoles();
	}

	/**
	 * 通过userId查询角色Id，返回字符串
	 */
	@Override
	public String getRoleIdsByUserId(int userId) {
		// TODO Auto-generated method stub
		return roleInfoDao.getRoleIdsByUserId(userId);
	}

	/**
	 * 通过用户组Id，角色id给用户分配角色
	 */
	@Override
	@Transactional
	public int insertRolesByUser(HttpServletRequest request, UserInfo user) {
		// TODO Auto-generated method stub
		// 删除用户角色
		roleInfoDao.deleteRolesByUserId(user.getUserId());
		// 获取roleIds
		String roleIds = request.getParameter("roleIds");
		// 将roleIds分割为数组
		String[] roleArray = roleIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", user.getUserId());
		int re = 0;
		// 遍历roleIds数组
		for (String roleId : roleArray) {
			map.put("roleId", Integer.parseInt(roleId));
			// 通过userId,roleId为用户分配角色
			re = roleInfoDao.insertRolesByUser(map);
		}
		return re;
	}

	/**
	 * 查询所有角色数量
	 */
	@Override
	public int getRolesNum(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.getRolesNum(role);
	}

	/**
	 * 分页查询角色信息
	 */
	@Override
	public List<RoleInfo> findAllRoles(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleInfoDao.findAllRoles(map);
	}

	/**
	 * 添加角色唯一性校验
	 */
	@Override
	public int addRoleVerify(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.addRoleVerify(role);
	}

	/**
	 * 添加角色
	 */
	@Override
	public int addRoleInfo(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.addRoleInfo(role);
	}

	/**
	 * 角色禁用
	 */
	@Override
	public int updateRoleState(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.updateRoleState(role);
	}

	/**
	 * 通过groupId查询角色Id，返回字符串
	 */
	@Override
	public String getGroupRoles(int groupId) {
		// TODO Auto-generated method stub
		return roleInfoDao.getGroupRoles(groupId);
	}

	/**
	 * 通过用户组Id，角色id给用户分配角色
	 */
	@Override
	@Transactional
	public int insertRolesByGroup(HttpServletRequest request,
			UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		// 通过用户组Ids删除角色
		roleInfoDao.deleteRolesByGroupId(groupInfo.getGroupId());
		// 获取roleIds字符串
		String roleIds = request.getParameter("roleIds");
		// 将字符串分割为数组
		String[] roleArray = roleIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("groupId", groupInfo.getGroupId());
		int re = 0;
		// 遍历roleArray数组
		for (String roleId : roleArray) {
			map.put("roleId", Integer.parseInt(roleId));
			// 通过roleId,groupId为用户组添加角色
			re = roleInfoDao.insertRolesByGroup(map);
		}
		return re;
	}
}
