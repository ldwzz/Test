package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * @comment 系统分类
 * @author tyz
 * @date 2018年9月3日 下午3:48:25
 * @modifyUser Administrator
 * @modifyDate 2018年9月3日 下午3:48:25
 * @modifyDesc TODO
 * @version TODO
 */
public class SysClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7643787014317746617L;
	// 系统分类id
	private Integer classId;
	// 分类名称
	private String className;
	// 分类父id
	private Integer parentId;
	// 分类描述
	private String classDesc;
	// 系统类型 1是平台，2是项目，3是模块
	private String sysType;
	// 系统级别
	private Integer grade;
	// 系统分类编码
	private String classCode;
	//模块ids，用于回显
	private Integer classIds;
	
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	public String getSysType() {
		return sysType;
	}
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public Integer getClassIds() {
		return classIds;
	}
	public void setClassIds(Integer classIds) {
		this.classIds = classIds;
	}
	@Override
	public String toString() {
		return "SysClass [classId=" + classId + ", className=" + className
				+ ", parentId=" + parentId + ", classDesc=" + classDesc
				+ ", sysType=" + sysType + ", grade=" + grade + ", classCode="
				+ classCode + "]";
	}
	
	
	
	
	
	
	
	
	
	
}

/**
 * @filename SysClass.java
 * @author Administrator
 * @date 2018年9月3日 下午3:48:04
 * @version 1.0 Copyright (C) 2018
 */
