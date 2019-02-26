/**
 * @filename ProjectInfo.java
 * @author Administrator
 * @date 2018��9��4�� ����10:08:28
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018��9��4�� ����10:08:28
 *@modifyUser Administrator
 *@modifyDate 2018��9��4�� ����10:08:28
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectInfo implements Serializable {

	private static final long serialVersionUID = -2050893396986593817L;

	// ��Ŀ����id
	private Integer projId;
	// ϵͳ����
	private Integer classId;
	// ��Ŀ����
	private String projName;
	// ��Ŀ���
	private String projNum;
	// ��Ŀ����
	private String projDesc;
	// ��������
	private String vistorType;
	// ����ʱ��
	private String createTime;
	// ������
	private Integer createBy;
	// ��ʼʱ��
	private String startTime;
	// ����ʱ��
	private String endTime;
	// �ܹ�ʱ
	private Float ableDay;
	// ��Ŀ״̬
	private String projState;
	// ��Ŀ������
	private Integer projChief;
	// ��ע
	private String remark;
	//��Ŀ����������
	private String projNames;


	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjNum() {
		return projNum;
	}

	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}

	public String getProjDesc() {
		return projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}

	public String getVistorType() {
		return vistorType;
	}

	public void setVistorType(String vistorType) {
		this.vistorType = vistorType;
	}

	public String getCreateTime() {
		return createTime==null?createTime:createTime.substring(0,10);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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

	public Float getAbleDay() {
		return ableDay;
	}

	public void setAbleDay(Float ableDay) {
		this.ableDay = ableDay;
	}

	public String getProjState() {
		return projState;
	}

	public void setProjState(String projState) {
		this.projState = projState;
	}

	public Integer getProjChief() {
		return projChief;
	}

	public void setProjChief(Integer projChief) {
		this.projChief = projChief;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjNames() {
		return projNames;
	}

	public void setProjNames(String projNames) {
		this.projNames = projNames;
	}

	@Override
	public String toString() {
		return "ProjectInfo [projId=" + projId + ", classId=" + classId
				+ ", projName=" + projName + ", projNum=" + projNum
				+ ", projDesc=" + projDesc + ", vistorType=" + vistorType
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", ableDay=" + ableDay + ", projState=" + projState
				+ ", projChief=" + projChief + ", remark=" + remark
				+ ", projNames=" + projNames + "]";
	}
	
}
