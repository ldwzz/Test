

package com.byzx.auth.bean;

import java.io.Serializable;

public class Recycle implements Serializable{

private static final long serialVersionUID = 5400211938674345967L;
private  Integer recyId;//回收站id
private  String  dbUser;//数据库用户
private  Integer classId;//系统分类
private  String  intro;//描述
private  String  tableName;//表名称
private  String  keyName;//主键名称
private  String  createTime;//创建时间
private  Integer keyValue;//主键值
private  Integer createBy;//创建人

private  String userName;//用户名字，用于展示的时候用



private  String  createStartTime;//用于搜索的时候用，开始时间
private  String  createEndTime;//用于搜索的时候用，结束时间
//原始的构造方法
public Recycle() {
	super();
	// TODO Auto-generated constructor stub
}

//构造方法用于删除信息所用

public Recycle( String dbUser, Integer classId, String intro,
		String tableName, String keyName, Integer keyValue,Integer createBy) {
	super();
	this.dbUser = dbUser;
	this.classId = classId;
	this.intro = intro;
	this.tableName = tableName;
	this.keyName = keyName;
	this.keyValue = keyValue;
	this.createBy=createBy;
}





public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public Integer getRecyId() {
	return recyId;
}
public void setRecyId(Integer recyId) {
	this.recyId = recyId;
}
public String getDbUser() {
	return dbUser;
}
public void setDbUser(String dbUser) {
	this.dbUser = dbUser;
}
public Integer getClassId() {
	return classId;
}
public void setClassId(Integer classId) {
	this.classId = classId;
}
public String getIntro() {
	return intro;
}
public void setIntro(String intro) {
	this.intro = intro;
}
public String getTableName() {
	return tableName;
}
public void setTableName(String tableName) {
	this.tableName = tableName;
}
public String getKeyName() {
	return keyName;
}
public void setKeyName(String keyName) {
	this.keyName = keyName;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public Integer getKeyValue() {
	return keyValue;
}
public void setKeyValue(Integer keyValue) {
	this.keyValue = keyValue;
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

@Override
public String toString() {
	return "Recycle [recyId=" + recyId + ", dbUser=" + dbUser + ", classId="
			+ classId + ", intro=" + intro + ", tableName=" + tableName
			+ ", keyName=" + keyName + ", createTime=" + createTime
			+ ", keyValue=" + keyValue + ", createBy=" + createBy
			+ ", userName=" + userName + ", createStartTime=" + createStartTime
			+ ", createEndTime=" + createEndTime + "]";
}









}

/**
 * @filename Recycle.java
 * @author Administrator
 * @date 2018年8月31日 上午9:23:38
 * @version 1.0
 * Copyright (C) 2018 
 */
