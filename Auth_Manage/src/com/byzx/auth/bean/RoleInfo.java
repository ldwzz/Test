/**
 * @filename RoleInfo.java
 * @author 刘智龙
 * @date 2018年8月20日 下午3:50:37
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 角色信息实体类
 * 
 * @comment
 * @author 刘智龙
 * @date 2018年8月26日 下午12:35:55
 * @modifyUser 刘智龙
 * @modifyDate 2018年8月26日 下午12:35:55
 * @modifyDesc TODO
 * @version TODO
 */
public class RoleInfo implements Serializable {

	/**
	 * 角色信息
	 */
	private static final long serialVersionUID = 5180320643679505473L;
	// 角色id
	private Integer roleId;
	// 角色名称
	private String roleName;
	// 角色描述
	private String roleDesc;
	// 角色code
	private String roleCode;
	// 角色状态
	private String roleState;
	// 角色创建人
	private Integer createBy;
	// 角色创建时间
	private String createTime;
	// 角色修改时间
	private String updateBy;
	// 角色修改人
	private String updateTime;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleState() {
		return roleState;
	}

	public void setRoleState(String roleState) {
		this.roleState = roleState;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "RoleInfo [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + ", roleCode=" + roleCode
				+ ", roleState=" + roleState + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", updateBy=" + updateBy
				+ ", updateTime=" + updateTime + "]";
	}

}
