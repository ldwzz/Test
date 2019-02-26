/**
 * @filename RoleInfoDao.java
 * @author ������
 * @date 2018��8��20�� ����3:56:17
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.RoleInfo;

/**
 * ��ɫ�־ò�ӿ�
 * 
 * @comment
 * @author ������
 * @date 2018��8��20�� ����3:56:57
 * @modifyUser ������
 * @modifyDate 2018��8��20�� ����3:56:57
 * @modifyDesc TODO
 * @version TODO
 */
public interface RoleInfoDao {

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
	 * ͨ���û�Idɾ����ɫ
	 * 
	 * @comment
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	public int deleteRolesByUserId(int userId);

	/**
	 * ͨ���û�Id����ɫid���û������ɫ
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByUser(HashMap<Object, Object> map);

	/**
	 * ��ѯ��ɫ����
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getRolesNum(RoleInfo role);

	/**
	 * ��ҳ��ѯ��ɫ��Ϣ
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<RoleInfo> findAllRoles(HashMap<Object, Object> map);

	/**
	 * ��ɫcodeΨһ��У��
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
	 * ��ɫ����/����
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
	 * ͨ���û���Idɾ����ɫ
	 * 
	 * @comment
	 * @param userId
	 * @return
	 * @version 1.0
	 */
	public int deleteRolesByGroupId(int groupId);
	
	/**
	 * ͨ���û���Id����ɫid���û������ɫ
	 * 
	 * @comment
	 * @param request
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int insertRolesByGroup(HashMap<Object, Object> map);


}
