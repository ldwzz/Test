/**
 * @filename UserInfoServiceImpl.java
 * @author ������
 * @date 2018��8��16�� ����3:25:03
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
 * @comment ҵ���ʵ����
 * @author ������
 * @date 2018��8��16�� ����3:26:38
 * @modifyUser ������
 * @modifyDate 2018��8��16�� ����3:26:38
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * ��¼
	 */
	@Override
	public UserInfo login(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.login(userInfo);
	}

	/**
	 * ��ѯ�û�Ȩ��
	 */
	@Override
	public List<AuthInfo> findAuthInfo(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.findAuthInfo(map);
	}

	/**
	 * ��ѯ�û�����
	 */
	@Override
	public int getUserNum(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserNum(user);
	}

	/**
	 * ��ҳ��ѯ�����û�
	 */
	@Override
	public List<UserInfo> getAllUsers(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getAllUsers(map);
	}

	/**
	 * У���û�ע��
	 */
	@Override
	public int addVerify(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.addVerify(user);
	}

	/**
	 * ɾ���û���Ϣ
	 */
	@Override
	public int deleteUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.deleteUser(user);
	}

	/**
	 * �����û�����
	 */
	@Override
	public int resetPassword(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.resetPassword(user);
	}

	/**
	 * �޸��û�״̬
	 */
	@Override
	public int updateUserState(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateUserState(user);
	}

	/**
	 * �޸��û���Ϣ
	 */
	@Override
	public int updateUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateUserInfo(user);
	}

	/**
	 * ����û�
	 */
	@Override
	public int addUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.addUserInfo(user);
	}

	/**
	 * ����ɾ���û�
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
	 * ��ѯ�����������û�����
	 */
	@Override
	public int getUserGroupNum(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserGroupNum(groupInfo);
	}

	/**
	 * ��ҳ��ѯ�û�����Ϣ
	 */
	@Override
	public List<UserGroupInfo> getAllUserGroup(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getAllUserGroup(map);
	}

	/**
	 * ��ѯ�û�����Ϣ
	 */
	@Override
	public List<UserGroupInfo> getUserGroup() {
		// TODO Auto-generated method stub
		return userInfoDao.getUserGroup();
	}

	/**
	 * �޸��û����û���id
	 */
	@Override
	public int updateGroupId(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateGroupId(user);
	}

	/**
	 * У���û���codeΨһ
	 */
	@Override
	public int addGroupVerify(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.addGroupVerify(groupInfo);
	}

	/**
	 * �����û���
	 */
	@Override
	public int addGroupInfo(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.addGroupInfo(groupInfo);
	}

	/**
	 * �޸��û���״̬
	 */
	@Override
	public int updateGroupState(UserGroupInfo groupInfo) {
		// TODO Auto-generated method stub
		return userInfoDao.updateGroupState(groupInfo);
	}

	/**
	 * �����û�id��ѯ��ɫId
	 */
	@Override
	public String findRoleIds(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.findRoleIds(user);
	}

	/**
	 * ����Ȩ��
	 */
	@Override
	public List<UserInfo> getUserInfos(HashMap<Object, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserInfos(map);
	}

	/**
	 * ��ѯ��ͬȨ����������
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
