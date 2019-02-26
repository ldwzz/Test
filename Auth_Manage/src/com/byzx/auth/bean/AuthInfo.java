/**
 * @filename AuthInfo.java
 * @author 刘智龙
 * @date 2018年8月16日 下午6:53:42
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 权限详情类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月16日 下午6:56:33
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月16日 下午6:56:33
 * @modifyDesc TODO
 * @version TODO
 */
public class AuthInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2384115258702321445L;
	// 权限id
	private Integer authId;
	// 权限父id
	private Integer parentId;
	// 权限名称
	private String authName;
	// 权限详情
	private String authDesc;
	// 权限等级
	private Integer authGrade;
	// 权限类型
	private String authType;
	// 权限url
	private String authUrl;
	// 权限code
	private String authCode;

	private Integer authOrder;
	// 权限状态
	private String authState;
	// 权限创建者
	private Integer createBy;
	// 权限创建时间
	private String createTime;
	// 权限修改人
	private Integer updateBy;
	// 权限修改时间
	private String updateTime;
	//子权限
	private List<AuthInfo> childList;

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public Integer getAuthGrade() {
		return authGrade;
	}

	public void setAuthGrade(Integer authGrade) {
		this.authGrade = authGrade;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Integer getAuthOrder() {
		return authOrder;
	}

	public void setAuthOrder(Integer authOrder) {
		this.authOrder = authOrder;
	}

	public String getAuthState() {
		return authState;
	}

	public void setAuthState(String authState) {
		this.authState = authState;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<AuthInfo> getChildList() {
		return childList;
	}

	public void setChildList(List<AuthInfo> childList) {
		this.childList = childList;
	}

}
