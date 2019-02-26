/**
 * @filename UserGroup.java
 * @author 刘智龙
 * @date 2018年8月23日 下午9:52:39
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 用户组信息实体类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月23日 下午9:54:43
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月23日 下午9:54:43
 * @modifyDesc TODO
 * @version TODO
 */
public class UserGroupInfo implements Serializable {

	private static final long serialVersionUID = 3758724548232769240L;
	// 用户组id
	private Integer groupId;
	// 用户组名称
	private String groupName;
	// 用户组code
	private String groupCode;
	// 用户组描述
	private String groupDesc;
	// 用户组状态，0 启用、 1 禁用
	private String groupState;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getGroupState() {
		return groupState;
	}

	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}

	@Override
	public String toString() {
		return "UserGroupInfo [groupId=" + groupId + ", groupName=" + groupName
				+ ", groupCode=" + groupCode + ", groupDesc=" + groupDesc
				+ ", groupState=" + groupState + "]";
	}

}
