/**
 * @filename ProjectDynamic.java
 * @author Administrator
 * @date 2018年9月4日 上午10:08:05
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月4日 上午10:08:05
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:08:05
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectDynamic implements Serializable {

	private static final long serialVersionUID = 3367722942051624913L;
	private Integer dynamicId;
	// 项目主键id
	private Integer projId;
	// 动态描述
	private String dynamicDesc;
	// 创建时间
	private String createTime;
	// 操作人
	private String userName;
	// 回复id
	private Integer parentId;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 删除标记
	private String isDelete;
	//评论
	private List<ProjectDynamic> comment;
	//回复人
	private String userNames;
	//父id字符串
	private String parents;

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

	public Integer getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getDynamicDesc() {
		return dynamicDesc;
	}

	public void setDynamicDesc(String dynamicDesc) {
		this.dynamicDesc = dynamicDesc;
	}

	public String getCreateTime() {
		return createTime == null ? createTime : createTime.substring(0, 16);
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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


	public List<ProjectDynamic> getComment() {
		return comment;
	}

	public void setComment(List<ProjectDynamic> comment) {
		this.comment = comment;
	}
    
	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	@Override
	public String toString() {
		return "ProjectDynamic [dynamicId=" + dynamicId + ", projId=" + projId
				+ ", dynamicDesc=" + dynamicDesc + ", createTime=" + createTime
				+ ", userName=" + userName + ", parentId=" + parentId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", isDelete=" + isDelete + ", comment=" + comment + "]";
	}




}
