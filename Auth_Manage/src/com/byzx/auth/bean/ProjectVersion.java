/**
 * @filename ProjectVession.java
 * @author Administrator
 * @date 2018年9月12日 下午2:10:18
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月12日 下午2:10:48
 *@modifyUser Administrator
 *@modifyDate 2018年9月12日 下午2:10:48
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectVersion  implements Serializable{

	private static final long serialVersionUID = 2778474306914554884L;
	//版本id
    private int versionId;
    //项目主键id
    private int projId;
    //版本号
    private String versionNum;
    //版本说明
    private String versionDesc;
    //创建人
    private int createBy;
    //创建时间
    private String createTime;
    //创建人名称
    private String userName;
    //删除标记
    private  int isDelete;
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public int getProjId() {
		return projId;
	}
	public void setProjId(int projId) {
		this.projId = projId;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
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
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "ProjectVersion [versionId=" + versionId + ", projId=" + projId
				+ ", versionNum=" + versionNum + ", versionDesc=" + versionDesc
				+ ", createBy=" + createBy + ", createTime=" + createTime
				+ ", userName=" + userName + ", isDelete=" + isDelete + "]";
	}
	
}

