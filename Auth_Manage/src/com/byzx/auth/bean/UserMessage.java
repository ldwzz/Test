/**
 * @filename UserMessage.java
 * @author ldwzz
 * @date 2018��9��4�� ����10:25:54
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * @comment �û�վ���Ź�ϵʵ����
 * @author ldwzz
 * @date 2018��9��4�� ����10:39:35
 * @modifyUser ldwzz
 * @modifyDate 2018��9��4�� ����10:39:35
 * @modifyDesc TODO
 * @version TODO
 */
public class UserMessage implements Serializable {

	private static final long serialVersionUID = 7651010432581420743L;

	// ��ϵID
	private Integer userMsgId;
	// վ����ID
	private Integer msgId;
	// վ����״̬
	private String readState;
	// ������ID
	private Integer fromUser;
	// ����������
	private String fromName;
	// �ռ���
	private Integer toUser;
	// �ռ���ID�ַ���
	private String toUserIds;
	// �ռ�����toGroupId
	private Integer toGroupId;
	// �ռ���ĿId
	private Integer projId;
	// �ռ���Ŀ��Id
	private Integer teamId;
	// ����������
	private String toName;
	// �ظ���ID
	private Integer parentId;
	// ���ʼ����ռ���
	private String parentToUserNames;

	public UserMessage() {
		super();
	}

	public UserMessage(Integer toUser) {
		super();
		this.toUser = toUser;
	}

	public Integer getUserMsgId() {
		return userMsgId;
	}

	public void setUserMsgId(Integer userMsgId) {
		this.userMsgId = userMsgId;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public Integer getFromUser() {
		return fromUser;
	}

	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public String getToUserIds() {
		return toUserIds;
	}

	public void setToUserIds(String toUserIds) {
		this.toUserIds = toUserIds;
	}

	public Integer getToGroupId() {
		return toGroupId;
	}

	public void setToGroupId(Integer toGroupId) {
		this.toGroupId = toGroupId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentToUserNames() {
		return parentToUserNames;
	}

	public void setParentToUserNames(String parentToUserNames) {
		this.parentToUserNames = parentToUserNames;
	}

	@Override
	public String toString() {
		return "UserMessage [userMsgId=" + userMsgId + ", msgId=" + msgId
				+ ", readState=" + readState + ", fromUser=" + fromUser
				+ ", fromName=" + fromName + ", toUser=" + toUser
				+ ", toUserIds=" + toUserIds + ", toGroupId=" + toGroupId
				+ ", projId=" + projId + ", teamId=" + teamId + ", toName="
				+ toName + ", parentId=" + parentId + ", parentToUserNames="
				+ parentToUserNames + "]";
	}

}
