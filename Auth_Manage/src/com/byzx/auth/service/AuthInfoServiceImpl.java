/**
 * @filename AuthInfoServiceImpl.java
 * @author ������
 * @date 2018��8��21�� ����9:09:45
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

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.AuthInfoDao;

/**
 * Ȩ��Serviceʵ����
 * 
 * @comment
 * @author ������
 * @date 2018��8��22�� ����3:54:44
 * @modifyUser ������
 * @modifyDate 2018��8��22�� ����3:54:44
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class AuthInfoServiceImpl implements AuthInfoService {

	@Autowired
	private AuthInfoDao authInfoDao;

	/**
	 * ��ѯ����Ȩ��
	 */
	@Override
	public List<AuthInfo> findAllAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.findAllAuth(authInfo);
	}

	/**
	 * ͨ����ɫId��ѯȨ��Id
	 */
	@Override
	public String findAuthByroleId(int roleId) {
		// TODO Auto-generated method stub
		return authInfoDao.findAuthByroleId(roleId);
	}

	/**
	 * ͨ����ɫid,Ȩ��id���Ȩ��
	 */
	@Override
	@Transactional
	public int addAuthByRole(HttpServletRequest request, RoleInfo role) {
		// TODO Auto-generated method stub
		// ͨ��roleIdɾ��roleauth��Ȩ����Ϣ
		authInfoDao.deleteAuthInfo(role.getRoleId());
		// ��ȡȨ��ids
		String authIds = request.getParameter("authIds");
		// ��Ȩ��ids�ָ�Ϊ����
		String[] authArr = authIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("roleId", role.getRoleId());
		int re = 0;
		// ����Ȩ��id����
		for (String authId : authArr) {
			map.put("authId", Integer.parseInt(authId));
			// ͨ��Ȩ��id����ɫ���Ȩ��
			re = authInfoDao.addAuthByRole(map);
		}
		return re;
	}

	/**
	 * authCodeΨһУ��
	 */
	@Override
	public int authCodeVerify(String authCode) {
		// TODO Auto-generated method stub
		return authInfoDao.authCodeVerify(authCode);
	}

	/**
	 * ���Ȩ����Ϣ
	 */
	@Override
	public int insertAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.insertAuth(authInfo);
	}

	/**
	 * �޸�Ȩ����Ϣ
	 */
	@Override
	public int updateAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.updateAuth(authInfo);
	}

	/**
	 * ͨ��Ȩ��Id��ѯ�Ƿ�Ȩ����ʹ��
	 */
	@Override
	public List<AuthInfo> selectAuth(int authId) {
		// TODO Auto-generated method stub
		return authInfoDao.selectAuth(authId);
	}

	/**
	 * ͨ���û�Id��ѯȨ��Id
	 */
	@Override
	public String findAuthByUserId(int userId) {
		// TODO Auto-generated method stub
		return authInfoDao.findAuthByUserId(userId);
	}

	/**
	 * ͨ���û�id,Ȩ��id���û����Ȩ�ޣ�user_auth��
	 */
	@Override
	public int addAuthByUser(HttpServletRequest request, UserInfo user) {
		// TODO Auto-generated method stub
		// ͨ��userIdɾ��roleauth��Ȩ����Ϣ
		authInfoDao.deleteUserAuth(user.getUserId());
		// ��ȡȨ��ids
		String authIds = request.getParameter("authIds");
		// ��Ȩ��ids�ָ�Ϊ����
		String[] authArr = authIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", user.getUserId());
		int re = 0;
		// ��Ȩ��id������б���
		for (String authId : authArr) {
			map.put("authId", Integer.parseInt(authId));
			// ͨ��authId��userId���Ȩ��
			re = authInfoDao.addAuthByUser(map);
		}
		return re;
	}

	/**
	 * ����ɾ���Ӽ�
	 */
	@Override
	public String selectChildList(AuthInfo authInfo, StringBuffer buffer) {
		// TODO Auto-generated method stub
		buffer.append(authInfo.getAuthId()).append(",");
		// �����Ӽ�
		List<AuthInfo> childList = authInfoDao.getChildList(authInfo);
		if (childList != null) {
			// ����Ӽ��ǿգ������Ӽ�
			for (AuthInfo authInfo2 : childList) {
				if (authInfo2.getAuthId() != null) {
					// ���Ӽ�authIdƴ�ӳ��ַ���
					buffer.append(authInfo2.getAuthId()).append(",");
					selectChildList(authInfo2, buffer);
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * ɾ����Ȩ��
	 */
	@Override
	public int deleteChildList(String[] AuthIds) {
		// TODO Auto-generated method stub
		return authInfoDao.deleteChildList(AuthIds);
	}

	/**
	 * ��ѯ��Ȩ��
	 */
	@Override
	public AuthInfo findParent(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.findParent(authInfo);
	}

	/**
	 * �ָ���Ȩ��
	 */
	@Override
	public int recoverChildList(String authIds) {
		// TODO Auto-generated method stub
		String[] authids = authIds.split(",");
		int re = authInfoDao.recoverChildList(authids);
		return re;
	}

}
