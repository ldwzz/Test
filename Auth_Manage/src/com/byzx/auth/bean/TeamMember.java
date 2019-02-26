/**
 * @filename TeamMember.java
 * @author Administrator
 * @date 2018年9月4日 上午10:09:46
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月4日 上午10:09:46
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:09:46
 *@modifyDesc  TODO
 *@version TODO
 */

public class TeamMember implements Serializable {

	private static final long serialVersionUID = -6596848784174897759L;
	// 项目组成员id
	private Integer memberId;
	// 项目组id
	private Integer teamId;
	// 成员id
	private Integer userId;
	//项目组名称
	private String teamName;
	//成员名称
    private String userName;
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "TeamMember [memberId=" + memberId + ", teamId=" + teamId
				+ ", userId=" + userId + ", teamName=" + teamName
				+ ", userName=" + userName + "]";
	}
	

}
