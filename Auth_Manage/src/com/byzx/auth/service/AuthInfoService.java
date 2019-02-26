/**
 * @filename AuthInfoService.java
 * @author ������
 * @date 2018��8��21�� ����9:08:58
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.RoleInfo;
import com.byzx.auth.bean.UserInfo;

/**
 * 
 * @comment
 * @author ������
 * @date 2018��8��21�� ����9:09:16
 * @modifyUser ������
 * @modifyDate 2018��8��21�� ����9:09:16
 * @modifyDesc TODO
 * @version TODO
 */
public interface AuthInfoService {
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
	 * @comment ͨ����ɫid,Ȩ��id���Ȩ��
	 * @param map request,role����
	 * @return ��ӳɹ�����1�����򷵻�0
	 * @version 1.0
	 */
	public int addAuthByRole(HttpServletRequest request, RoleInfo role);

	/**
	 * @comment authCodeΨһУ��
	 * @param authCode  Ȩ��code�ַ���
	 * @return Ψһ����0�����򷵻�1
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
	 * ͨ���û�id,Ȩ��id���û����Ȩ�ޣ�user_auth��
	 * 
	 * @comment
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int addAuthByUser(HttpServletRequest request, UserInfo user);

	// /**
	// * ͨ����ɫId�����ѯȨ��Id
	// *
	// * @comment
	// * @param roleId
	// * @return
	// * @version 1.0
	// */
	// public String findAuthByroles(HashMap<Object,Object> map);

	/**
	 * ��ѯ�Ӽ�
	 * 
	 * @comment
	 * @param authInfo
	 * @return
	 * @version 1.0
	 */
	public String selectChildList(AuthInfo authInfo, StringBuffer buffer);

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
	 * �ָ���Ȩ��
	 * 
	 * @comment
	 * @param authIds
	 * @return
	 * @version 1.0
	 */
	public int recoverChildList(String authIds);

}
