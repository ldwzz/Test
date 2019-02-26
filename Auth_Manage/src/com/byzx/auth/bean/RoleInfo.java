/**
 * @filename RoleInfo.java
 * @author ������
 * @date 2018��8��20�� ����3:50:37
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * ��ɫ��Ϣʵ����
 * 
 * @comment
 * @author ������
 * @date 2018��8��26�� ����12:35:55
 * @modifyUser ������
 * @modifyDate 2018��8��26�� ����12:35:55
 * @modifyDesc TODO
 * @version TODO
 */
public class RoleInfo implements Serializable {

	/**
	 * ��ɫ��Ϣ
	 */
	private static final long serialVersionUID = 5180320643679505473L;
	// ��ɫid
	private Integer roleId;
	// ��ɫ����
	private String roleName;
	// ��ɫ����
	private String roleDesc;
	// ��ɫcode
	private String roleCode;
	// ��ɫ״̬
	private String roleState;
	// ��ɫ������
	private Integer createBy;
	// ��ɫ����ʱ��
	private String createTime;
	// ��ɫ�޸�ʱ��
	private String updateBy;
	// ��ɫ�޸���
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
