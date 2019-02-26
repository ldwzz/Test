/**
 * @filename TeamMember.java
 * @author Administrator
 * @date 2018��9��4�� ����10:09:46
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018��9��4�� ����10:09:46
 *@modifyUser Administrator
 *@modifyDate 2018��9��4�� ����10:09:46
 *@modifyDesc  TODO
 *@version TODO
 */

public class TeamMember implements Serializable {

	private static final long serialVersionUID = -6596848784174897759L;
	// ��Ŀ���Աid
	private Integer memberId;
	// ��Ŀ��id
	private Integer teamId;
	// ��Աid
	private Integer userId;
	//��Ŀ������
	private String teamName;
	//��Ա����
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
