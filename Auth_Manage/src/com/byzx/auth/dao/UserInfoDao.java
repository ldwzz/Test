/**
 * @filename UserInfoDao.java
 * @author ������
 * @date 2018��8��16�� ����3:17:04
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
 * �û��־ò�ӿ�
 * 
 * @comment
 * @author ������
 * @date 2018��8��16�� ����3:17:37
 * @modifyUser ������
 * @modifyDate 2018��8��16�� ����3:17:37
 * @modifyDesc TODO
 * @version TODO
 */
public interface UserInfoDao {

	/**
	 * ��¼
	 * 
	 * @comment
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	public UserInfo login(UserInfo userInfo);

	/**
	 * ��ѯ�û�Ȩ��
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> findAuthInfo(HashMap<Object, Object> map);

	/**
	 * ��ҳ��ѯ�����û�
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<UserInfo> getAllUsers(HashMap<Object, Object> map);

	/**
	 * ��ѯ�û�����
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getUserNum(UserInfo user);

	/**
	 * У���û�ע��
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int addVerify(UserInfo user);

	/**
	 * ����û�
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int addUserInfo(UserInfo user);

	/**
	 * ɾ���û�
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int deleteUser(UserInfo user);

	/**
	 * ����ɾ���û�
	 * 
	 * @comment
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	public int deleteUsers(UserInfo user);

	/**
	 * �����û�����
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int resetPassword(UserInfo user);

	/**
	 * �޸��û�״̬
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int updateUserState(UserInfo user);

	/**
	 * �޸��û���Ϣ
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int updateUserInfo(UserInfo user);

	/**
	 * ��ѯ�����������û�����
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public int getUserGroupNum(UserGroupInfo groupInfo);

	/**
	 * ��ҳ��ѯ�û�����Ϣ
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<UserGroupInfo> getAllUserGroup(HashMap<Object, Object> map);

	/**
	 * ��ѯ�û�����Ϣ
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<UserGroupInfo> getUserGroup();

	/**
	 * �޸��û����û���id
	 * 
	 * @comment
	 * @param groupInfo
	 * @return
	 * @version 1.0
	 */
	public int updateGroupId(UserInfo user);

	/**
	 * У���û���codeΨһ
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int addGroupVerify(UserGroupInfo groupInfo);

	/**
	 * �����û���
	 * 
	 * @comment
	 * @param groupInfo
	 * @return
	 * @version 1.0
	 */
	public int addGroupInfo(UserGroupInfo groupInfo);

	/**
	 * �޸��û���״̬
	 * 
	 * @comment
	 * @param groupInfo
	 * @return
	 * @version 1.0
	 */
	public int updateGroupState(UserGroupInfo groupInfo);

	/**
	 * �����û�id��ѯ��ɫId
	 * 
	 * @comment
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public String findRoleIds(UserInfo user);

	/**
	 * ����Ȩ��
	 * 
	 * @comment
	 * @param userId
	 * @param roleCode
	 * @return
	 * @version 1.0
	 */
	public List<UserInfo> getUserInfos(HashMap<Object, Object> map);

	/**
	 * ��ѯ��ͬȨ����������
	 * 
	 * @comment
	 * @param userInfo
	 * @return
	 * @version 1.0
	 */
	public int getUsersInfoNum(UserInfo user);
	

	
	/**
	 * @comment �����û�����ѯ�û�id
	 * @param userInfo
	 * @return  UserInfo
	 * @version 1.0
	 */
		public UserInfo login2(UserInfo userInfo);
		
		
	/**
	 * @comment  �����û�id��ѯ�û���	
	 * @param userId
	 * @return String
	 * @version 1.0
	 */
		public String userNameByUserId(int userId);
	/**
	 * @comment �����û�userIds�����ܶ������ѯ����Ӧ��userName
	 * @param userName
	 * @return String
	 * @version 1.0
	 */
		public String userIdsByUserName(String userName);
			
	
	
	
}
