/**
 * @filename RoleInfoServiceImpl.java
 * @author ������
 * @date 2018��8��20�� ����4:09:01
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
 * ��ɫServiceʵ����
 * 
 * @comment
 * @author ������
 * @date 2018��8��20�� ����4:09:14
 * @modifyUser ������
 * @modifyDate 2018��8��20�� ����4:09:14
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
	@Autowired
	private RoleInfoDao roleInfoDao;

	/**
	 * ��ѯ���н�ɫ��Ϣ
	 */
	@Override
	public List<RoleInfo> getAllRoles() {
		// TODO Auto-generated method stub
		return roleInfoDao.getAllRoles();
	}

	/**
	 * ͨ��userId��ѯ��ɫId�������ַ���
	 */
	@Override
	public String getRoleIdsByUserId(int userId) {
		// TODO Auto-generated method stub
		return roleInfoDao.getRoleIdsByUserId(userId);
	}

	/**
	 * ͨ���û���Id����ɫid���û������ɫ
	 */
	@Override
	@Transactional
	public int insertRolesByUser(HttpServletRequest request, UserInfo user) {
		// TODO Auto-generated method stub
		// ɾ���û���ɫ
		roleInfoDao.deleteRolesByUserId(user.getUserId());
		// ��ȡroleIds
		String roleIds = request.getParameter("roleIds");
		// ��roleIds�ָ�Ϊ����
		String[] roleArray = roleIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", user.getUserId());
		int re = 0;
		// ����roleIds����
		for (String roleId : roleArray) {
			map.put("roleId", Integer.parseInt(roleId));
			// ͨ��userId,roleIdΪ�û������ɫ
			re = roleInfoDao.insertRolesByUser(map);
		}
		return re;
	}

	/**
	 * ��ѯ���н�ɫ����
	 */
	@Override
	public int getRolesNum(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.getRolesNum(role);
	}

	/**
	 * ��ҳ��ѯ��ɫ��Ϣ
	 */
	@Override
	public List<RoleInfo> findAllRoles(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return roleInfoDao.findAllRoles(map);
	}

	/**
	 * ��ӽ�ɫΨһ��У��
	 */
	@Override
	public int addRoleVerify(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.addRoleVerify(role);
	}

	/**
	 * ��ӽ�ɫ
	 */
	@Override
	public int addRoleInfo(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.addRoleInfo(role);
	}

	/**
	 * ��ɫ����
	 */
	@Override
	public int updateRoleState(RoleInfo role) {
		// TODO Auto-generated method stub
		return roleInfoDao.updateRoleState(role);
	}

	/**
	 * ͨ��groupId��ѯ��ɫId�������ַ���
	 */
	@Override
	public String getGroupRoles(int groupId) {
		// TODO Auto-generated method stub
		return roleInfoDao.getGroupRoles(groupId);
	}

	/**
	 * ͨ���û���Id����ɫid���û������ɫ
	 */
	@Override
	@Transactional
	public int insertRolesByGroup(HttpServletRequest request,
			UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		// ͨ���û���Idsɾ����ɫ
		roleInfoDao.deleteRolesByGroupId(groupInfo.getGroupId());
		// ��ȡroleIds�ַ���
		String roleIds = request.getParameter("roleIds");
		// ���ַ����ָ�Ϊ����
		String[] roleArray = roleIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("groupId", groupInfo.getGroupId());
		int re = 0;
		// ����roleArray����
		for (String roleId : roleArray) {
			map.put("roleId", Integer.parseInt(roleId));
			// ͨ��roleId,groupIdΪ�û�����ӽ�ɫ
			re = roleInfoDao.insertRolesByGroup(map);
		}
		return re;
	}
}
