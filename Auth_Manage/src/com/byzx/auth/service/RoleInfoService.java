/**
 * @filename RoleInfoService.java
 * @author ������
 * @date 2018��8��20�� ����4:07:40
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
 * ��ɫҵ���
 * 
 * @comment
 * @author ������
 * @date 2018��8��20�� ����4:07:51
 * @modifyUser ������
 * @modifyDate 2018��8��20�� ����4:07:51
 * @modifyDesc TODO
 * @version TODO
 */
public interface RoleInfoService {
	/**
	 * ��ѯ���н�ɫ��Ϣ
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<RoleInfo> getAllRoles();

	/**
	 * ͨ��userId��ѯ��ɫId�������ַ���
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public String getRoleIdsByUserId(int userId);

	/**
	 * ͨ���û�Id����ɫid���û������ɫ
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByUser(HttpServletRequest request, UserInfo user);

	/**
	 * ��ѯ��ɫ����
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getRolesNum(RoleInfo role);

	/**
	 * ��ҳ��ѯ�û���Ϣ
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<RoleInfo> findAllRoles(HashMap<Object, Object> map);

	/**
	 * ��ӽ�ɫΨһ��У��
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	public int addRoleVerify(RoleInfo role);

	/**
	 * ��ӽ�ɫ
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	public int addRoleInfo(RoleInfo role);

	/**
	 * ��ɫ����
	 * 
	 * @comment
	 * @param role
	 * @return
	 * @version 1.0
	 */
	public int updateRoleState(RoleInfo role);
	
	/**
	 * ͨ��groupId��ѯ��ɫId�������ַ���
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public String getGroupRoles(int groupId);
	
	/**
	 * ͨ���û���Id����ɫid���û������ɫ
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByGroup(HttpServletRequest request, UserGroupInfo groupInfo);
}
