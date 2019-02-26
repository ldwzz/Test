/**
 * @filename UserGroup.java
 * @author ������
 * @date 2018��8��23�� ����9:52:39
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * �û�����Ϣʵ����
 * 
 * @comment
 * @author ������
 * @date 2018��8��23�� ����9:54:43
 * @modifyUser ������
 * @modifyDate 2018��8��23�� ����9:54:43
 * @modifyDesc TODO
 * @version TODO
 */
public class UserGroupInfo implements Serializable {

	private static final long serialVersionUID = 3758724548232769240L;
	// �û���id
	private Integer groupId;
	// �û�������
	private String groupName;
	// �û���code
	private String groupCode;
	// �û�������
	private String groupDesc;
	// �û���״̬��0 ���á� 1 ����
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
