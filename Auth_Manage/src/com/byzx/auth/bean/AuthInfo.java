/**
 * @filename AuthInfo.java
 * @author ������
 * @date 2018��8��16�� ����6:53:42
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Ȩ��������
 * 
 * @comment
 * @author ������
 * @date 2018��8��16�� ����6:56:33
 * @modifyUser ������
 * @modifyDate 2018��8��16�� ����6:56:33
 * @modifyDesc TODO
 * @version TODO
 */
public class AuthInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2384115258702321445L;
	// Ȩ��id
	private Integer authId;
	// Ȩ�޸�id
	private Integer parentId;
	// Ȩ������
	private String authName;
	// Ȩ������
	private String authDesc;
	// Ȩ�޵ȼ�
	private Integer authGrade;
	// Ȩ������
	private String authType;
	// Ȩ��url
	private String authUrl;
	// Ȩ��code
	private String authCode;

	private Integer authOrder;
	// Ȩ��״̬
	private String authState;
	// Ȩ�޴�����
	private Integer createBy;
	// Ȩ�޴���ʱ��
	private String createTime;
	// Ȩ���޸���
	private Integer updateBy;
	// Ȩ���޸�ʱ��
	private String updateTime;
	//��Ȩ��
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
