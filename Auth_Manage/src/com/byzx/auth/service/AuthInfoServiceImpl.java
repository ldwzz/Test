/**
 * @filename AuthInfoServiceImpl.java
 * @author 刘智龙
 * @date 2018年8月21日 下午9:09:45
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
 * 权限Service实现类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月22日 下午3:54:44
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月22日 下午3:54:44
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class AuthInfoServiceImpl implements AuthInfoService {

	@Autowired
	private AuthInfoDao authInfoDao;

	/**
	 * 查询所有权限
	 */
	@Override
	public List<AuthInfo> findAllAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.findAllAuth(authInfo);
	}

	/**
	 * 通过角色Id查询权限Id
	 */
	@Override
	public String findAuthByroleId(int roleId) {
		// TODO Auto-generated method stub
		return authInfoDao.findAuthByroleId(roleId);
	}

	/**
	 * 通过角色id,权限id添加权限
	 */
	@Override
	@Transactional
	public int addAuthByRole(HttpServletRequest request, RoleInfo role) {
		// TODO Auto-generated method stub
		// 通过roleId删除roleauth表权限信息
		authInfoDao.deleteAuthInfo(role.getRoleId());
		// 获取权限ids
		String authIds = request.getParameter("authIds");
		// 将权限ids分割为数组
		String[] authArr = authIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("roleId", role.getRoleId());
		int re = 0;
		// 遍历权限id数组
		for (String authId : authArr) {
			map.put("authId", Integer.parseInt(authId));
			// 通过权限id，角色添加权限
			re = authInfoDao.addAuthByRole(map);
		}
		return re;
	}

	/**
	 * authCode唯一校验
	 */
	@Override
	public int authCodeVerify(String authCode) {
		// TODO Auto-generated method stub
		return authInfoDao.authCodeVerify(authCode);
	}

	/**
	 * 添加权限信息
	 */
	@Override
	public int insertAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.insertAuth(authInfo);
	}

	/**
	 * 修改权限信息
	 */
	@Override
	public int updateAuth(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.updateAuth(authInfo);
	}

	/**
	 * 通过权限Id查询是否权限在使用
	 */
	@Override
	public List<AuthInfo> selectAuth(int authId) {
		// TODO Auto-generated method stub
		return authInfoDao.selectAuth(authId);
	}

	/**
	 * 通过用户Id查询权限Id
	 */
	@Override
	public String findAuthByUserId(int userId) {
		// TODO Auto-generated method stub
		return authInfoDao.findAuthByUserId(userId);
	}

	/**
	 * 通过用户id,权限id给用户添加权限（user_auth）
	 */
	@Override
	public int addAuthByUser(HttpServletRequest request, UserInfo user) {
		// TODO Auto-generated method stub
		// 通过userId删除roleauth表权限信息
		authInfoDao.deleteUserAuth(user.getUserId());
		// 获取权限ids
		String authIds = request.getParameter("authIds");
		// 将权限ids分割为数组
		String[] authArr = authIds.split(",");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", user.getUserId());
		int re = 0;
		// 对权限id数组进行遍历
		for (String authId : authArr) {
			map.put("authId", Integer.parseInt(authId));
			// 通过authId，userId添加权限
			re = authInfoDao.addAuthByUser(map);
		}
		return re;
	}

	/**
	 * 查找删除子集
	 */
	@Override
	public String selectChildList(AuthInfo authInfo, StringBuffer buffer) {
		// TODO Auto-generated method stub
		buffer.append(authInfo.getAuthId()).append(",");
		// 查找子集
		List<AuthInfo> childList = authInfoDao.getChildList(authInfo);
		if (childList != null) {
			// 如果子集非空，遍历子集
			for (AuthInfo authInfo2 : childList) {
				if (authInfo2.getAuthId() != null) {
					// 将子集authId拼接成字符串
					buffer.append(authInfo2.getAuthId()).append(",");
					selectChildList(authInfo2, buffer);
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 删除子权限
	 */
	@Override
	public int deleteChildList(String[] AuthIds) {
		// TODO Auto-generated method stub
		return authInfoDao.deleteChildList(AuthIds);
	}

	/**
	 * 查询父权限
	 */
	@Override
	public AuthInfo findParent(AuthInfo authInfo) {
		// TODO Auto-generated method stub
		return authInfoDao.findParent(authInfo);
	}

	/**
	 * 恢复子权限
	 */
	@Override
	public int recoverChildList(String authIds) {
		// TODO Auto-generated method stub
		String[] authids = authIds.split(",");
		int re = authInfoDao.recoverChildList(authids);
		return re;
	}

}
