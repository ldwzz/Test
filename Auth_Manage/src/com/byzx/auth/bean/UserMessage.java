/**
 * @filename UserMessage.java
 * @author ldwzz
 * @date 2018年9月4日 上午10:25:54
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * @comment 用户站内信关系实体类
 * @author ldwzz
 * @date 2018年9月4日 上午10:39:35
 * @modifyUser ldwzz
 * @modifyDate 2018年9月4日 上午10:39:35
 * @modifyDesc TODO
 * @version TODO
 */
public class UserMessage implements Serializable {

	private static final long serialVersionUID = 7651010432581420743L;

	// 关系ID
	private Integer userMsgId;
	// 站内信ID
	private Integer msgId;
	// 站内信状态
	private String readState;
	// 发件人ID
	private Integer fromUser;
	// 发件人姓名
	private String fromName;
	// 收件人
	private Integer toUser;
	// 收件人ID字符串
	private String toUserIds;
	// 收件部门toGroupId
	private Integer toGroupId;
	// 收件项目Id
	private Integer projId;
	// 收件项目组Id
	private Integer teamId;
	// 发件人姓名
	private String toName;
	// 回复父ID
	private Integer parentId;
	// 父邮件的收件人
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
