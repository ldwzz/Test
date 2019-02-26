/**
 * @filename ProjectTeam.java
 * @author Administrator
 * @date 2018��9��4�� ����10:08:54
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018��9��4�� ����10:08:54
 *@modifyUser Administrator
 *@modifyDate 2018��9��4�� ����10:08:54
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectTeam implements Serializable {

	private static final long serialVersionUID = -7155908456598275402L;
	// ��Ŀ��id
	private Integer teamId;
	// ��Ŀ����id
	private Integer projId;
	// ��Ŀ�����
	private String teamCode;
	// ��Ŀ������
	private String teamName;
	// ��Ŀ������
	private String teamDesc;
	// ��Ŀ�鸺����
	private Integer projChief;
	//�û���
	private String userName;
	//��Ŀ����
	private String projName;
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getProjId() {
		return projId;
	}
	public void setProjId(Integer projId) {
		this.projId = projId;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamDesc() {
		return teamDesc;
	}
	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}
	public Integer getProjChief() {
		return projChief;
	}
	public void setProjChief(Integer projChief) {
		this.projChief = projChief;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "ProjectTeam [teamId=" + teamId + ", projId=" + projId
				+ ", teamCode=" + teamCode + ", teamName=" + teamName
				+ ", teamDesc=" + teamDesc + ", projChief=" + projChief
				+ ", userName=" + userName + "]";
	}

	

}
