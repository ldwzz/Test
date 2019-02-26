/**
 * @filename ProjectInfo.java
 * @author Administrator
 * @date 2018年9月4日 上午10:08:28
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月4日 上午10:08:28
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:08:28
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectInfo implements Serializable {

	private static final long serialVersionUID = -2050893396986593817L;

	// 项目主键id
	private Integer projId;
	// 系统分类
	private Integer classId;
	// 项目名称
	private String projName;
	// 项目编号
	private String projNum;
	// 项目描述
	private String projDesc;
	// 访问类型
	private String vistorType;
	// 创建时间
	private String createTime;
	// 创建人
	private Integer createBy;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 总工时
	private Float ableDay;
	// 项目状态
	private String projState;
	// 项目负责人
	private Integer projChief;
	// 备注
	private String remark;
	//项目负责人名称
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
