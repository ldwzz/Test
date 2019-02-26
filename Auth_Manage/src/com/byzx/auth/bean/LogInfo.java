

package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;

/**
 *@comment 日志表类
 *@author tyz
 *@date 2018年9月7日 下午3:16:37
*@modifyUser Administrator
*@modifyDate 2018年9月7日 下午3:16:37
*@modifyDesc  TODO
 *@version TODO
 */
public class LogInfo implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 3149015259032048148L;
//日志表主键id
private Integer logId;
//请求地址
private String ipAddress;	
//请求url
private String  url;
//项目主键id
private  Integer projId;
//类型1 系统    2 项目动态 3 任务动态
private String logType;
//内容
private String logInfo;
//异常信息
private  String exception;	
//创建时间
private String  createTime;
//创建人
private Integer  createBy;

//开始时间
private String createStartTime;
//结束时间
private String createEndTime;

//userName用于前端展示
private String userName;
//userIds用于前端展示
private String userIds;

//projName用于前端展示
private String projName;
//projIds用于前端展示
private String  projIds;



public LogInfo() {
	super();
	// TODO Auto-generated constructor stub
}



//插入数据的构造方法
public LogInfo(String ipAddress, String url, Integer projId, String logType,
		String logInfo, String exception, Integer createBy) {
	super();
	this.ipAddress = ipAddress;
	this.url = url;
	this.projId = projId;
	this.logType = logType;
	this.logInfo = logInfo;
	this.exception = exception;
	this.createBy = createBy;
}



public Integer getLogId() {
	return logId;
}
public void setLogId(Integer logId) {
	this.logId = logId;
}
public String getIpAddress() {
	return ipAddress;
}
public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Integer getProjId() {
	return projId;
}
public void setProjId(Integer projId) {
	this.projId = projId;
}
public String getLogType() {
	return logType;
}
public void setLogType(String logType) {
	this.logType = logType;
}
public String getLogInfo() {
	return logInfo;
}
public void setLogInfo(String logInfo) {
	this.logInfo = logInfo;
}
public String getException() {
	return exception;
}
public void setException(String exception) {
	this.exception = exception;
}
public String getCreateTime() {
	return createTime;
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
public String getCreateStartTime() {
	return createStartTime;
}
public void setCreateStartTime(String createStartTime) {
	this.createStartTime = createStartTime;
}
public String getCreateEndTime() {
	return createEndTime;
}
public void setCreateEndTime(String createEndTime) {
	this.createEndTime = createEndTime;
}










public String getUserName() {
	return userName;
}



public void setUserName(String userName) {
	this.userName = userName;
}



public String getUserIds() {
	return userIds;
}



public void setUserIds(String userIds) {
	this.userIds = userIds;
}



public String getProjName() {
	return projName;
}



public void setProjName(String projName) {
	this.projName = projName;
}



public String getProjIds() {
	return projIds;
}



public void setProjIds(String projIds) {
	this.projIds = projIds;
}



@Override
public String toString() {
	return "LogInfo [logId=" + logId + ", ipAddress=" + ipAddress + ", url="
			+ url + ", projId=" + projId + ", logType=" + logType
			+ ", logInfo=" + logInfo + ", exception=" + exception
			+ ", createTime=" + createTime + ", createBy=" + createBy
			+ ", createStartTime=" + createStartTime + ", createEndTime="
			+ createEndTime + ", userName=" + userName + ", userIds=" + userIds
			+ ", projName=" + projName + ", projIds=" + projIds + "]";
}










	
}

/**
 * @filename LogInfo.java
 * @author Administrator
 * @date 2018年9月7日 下午3:12:52
 * @version 1.0
 * Copyright (C) 2018 
 */
