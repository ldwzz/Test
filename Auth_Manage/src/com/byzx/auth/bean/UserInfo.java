/**
 * @filename userInfo.java
 * @author 刘智龙
 * @date 2018年8月16日 上午8:29:03
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 用户信息实体类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月26日 下午12:36:27
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月26日 下午12:36:27
 * @modifyDesc TODO
 * @version TODO
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1354971021684511307L;
	// 用户id
	private Integer userId;
	// 用户组id
	private Integer groupId;
	// 用户昵称
	private String nickName;
	// 用户名
	private String userName;
	// 用户密码
	private String userPwd;
	// 用户状态，0 未审核 、1 已审核
	private String userState;
	// 1 超级管理员 、 2 管理员 、 3 普通用户
	private String userType;
	// 删除状态，0 正常、 1 已删除
	private String isDelete;
	// 创建人
	private String createBy;
	// 创建时间
	private String createTime;
	// 修改人
	private Integer updateBy;
	// 修改时间
	private String updateTime;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 用户id数组
	private String[] userIds;
	// 用户组名称
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
