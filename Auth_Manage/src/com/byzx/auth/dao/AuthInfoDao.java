/**
 * @filename AuthInfoDao.java
 * @author ������
 * @date 2018��8��21�� ����9:00:42
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.AuthInfo;

public interface AuthInfoDao {

	/**
	 * ��ѯ����Ȩ��
	 * 
	 * @comment
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> findAllAuth(AuthInfo authInfo);

	/**
	 * ͨ����ɫId��ѯȨ��Id
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public String findAuthByroleId(int roleId);

	/**
	 * ͨ����ɫIdɾ��Ȩ��
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public int deleteAuthInfo(int roleId);

	/**
	 * ͨ����ɫid,Ȩ��id���Ȩ��
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthByRole(HashMap<Object, Object> map);

	/**
	 * authCodeΨһУ��
	 * 
	 * @comment
	 * @param authCode
	 * @return
	 * @version 1.0
	 */
	public int authCodeVerify(String authCode);

	/**
	 * ���Ȩ����Ϣ
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public int insertAuth(AuthInfo authInfo);

	/**
	 * �޸�Ȩ����Ϣ
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public int updateAuth(AuthInfo authInfo);

	/**
	 * ͨ��Ȩ��Id��ѯ�Ƿ�Ȩ����ʹ��
	 * 
	 * @comment
	 * @param authId
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> selectAuth(int authId);

	/**
	 * ͨ���û�Id��ѯȨ��Id
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public String findAuthByUserId(int userId);

	/**
	 * ͨ���û�Idɾ��user_authȨ��
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public int deleteUserAuth(int userId);

	/**
	 * ͨ���û�id,Ȩ��id���û����Ȩ�ޣ�user_auth��
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthByUser(HashMap<Object, Object> map);

	/**
	 * ͨ����ɫId�����ѯȨ��Id
	 * 
	 * @comment
	 * @param roleId
	 * @return
	 * @version 1.0
	 */
	public String findAuthByroles(HashMap<Object, Object> map);

	/**
	 * ͨ��һ���ɫIDɾ��Ȩ��
	 * 
	 * @comment
	 * @param roleIds
	 * @return
	 * @version 1.0
	 */
	public int deleteAuthByRoles(String roleIds[]);

	/**
	 * ���û������Ȩ��
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthGroup(HashMap<Object, Object> map);

	/**
	 * �����Ӽ�
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public List<AuthInfo> getChildList(AuthInfo authInfo);

	/**
	 * ɾ����Ȩ��
	 * 
	 * @comment
	 * @param AuthIds
	 * @return
	 * @version 1.0
	 */
	public int deleteChildList(String AuthIds[]);

	/**
	 * ��ѯ��Ȩ��
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public AuthInfo findParent(AuthInfo authInfo);
	
	/**
	 * ɾ����Ȩ��
	 * 
	 * @comment
	 * @param AuthIds
	 * @return
	 * @version 1.0
	 */
	public int recoverChildList(String AuthIds[]);

}
