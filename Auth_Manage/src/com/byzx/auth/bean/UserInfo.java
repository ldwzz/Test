/**
 * @filename userInfo.java
 * @author ������
 * @date 2018��8��16�� ����8:29:03
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * �û���Ϣʵ����
 * 
 * @comment
 * @author ������
 * @date 2018��8��26�� ����12:36:27
 * @modifyUser ������
 * @modifyDate 2018��8��26�� ����12:36:27
 * @modifyDesc TODO
 * @version TODO
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1354971021684511307L;
	// �û�id
	private Integer userId;
	// �û���id
	private Integer groupId;
	// �û��ǳ�
	private String nickName;
	// �û���
	private String userName;
	// �û�����
	private String userPwd;
	// �û�״̬��0 δ��� ��1 �����
	private String userState;
	// 1 ��������Ա �� 2 ����Ա �� 3 ��ͨ�û�
	private String userType;
	// ɾ��״̬��0 ������ 1 ��ɾ��
	private String isDelete;
	// ������
	private String createBy;
	// ����ʱ��
	private String createTime;
	// �޸���
	private Integer updateBy;
	// �޸�ʱ��
	private String updateTime;
	// ��ʼʱ��
	private String startTime;
	// ����ʱ��
	private String endTime;
	// �û�id����
	private String[] userIds;
	// �û�������
	private String groupName;

	public UserInfo() {
		super();
	}

	public UserInfo(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		if (createTime != null) {
			return createTime.substring(0, 10);
		} else {
			return createTime;
		}
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime.substring(0, 10);
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", groupId=" + groupId
				+ ", nickName=" + nickName + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", userState=" + userState
				+ ", userType=" + userType + ", isDelete=" + isDelete
				+ ", createBy=" + createBy + ", createTime=" + createTime
				+ ", updateBy=" + updateBy + ", updateTime=" + updateTime
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
