/**
 * @filename ProjectTeam.java
 * @author Administrator
 * @date 2018年9月4日 上午10:08:54
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月4日 上午10:08:54
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:08:54
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectTeam implements Serializable {

	private static final long serialVersionUID = -7155908456598275402L;
	// 项目组id
	private Integer teamId;
	// 项目主键id
	private Integer projId;
	// 项目组编码
	private String teamCode;
	// 项目组名称
	private String teamName;
	// 项目组描述
	private String teamDesc;
	// 项目组负责人
	private Integer projChief;
	//用户名
	private String userName;
	//项目名称
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
