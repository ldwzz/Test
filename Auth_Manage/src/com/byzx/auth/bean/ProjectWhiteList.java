/**
 * @filename ProjectWhiteList.java
 * @author Administrator
 * @date 2018年9月4日 上午10:09:21
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月4日 上午10:09:21
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:09:21
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectWhiteList implements Serializable {

	private static final long serialVersionUID = -4033013759901630575L;
	// 白名单id
	private Integer userListId;
	// 项目主键id
	private Integer projId;
	// 成员id
	private Integer userId;
	// 用户名
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserListId() {
		return userListId;
	}

	public void setUserListId(Integer userListId) {
		this.userListId = userListId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ProjectWhiteList [userListId=" + userListId + ", projId="
				+ projId + ", userId=" + userId + ", userName=" + userName
				+ "]";
	}


}
