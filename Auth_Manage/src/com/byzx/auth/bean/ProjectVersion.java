/**
 * @filename ProjectVession.java
 * @author Administrator
 * @date 2018��9��12�� ����2:10:18
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018��9��12�� ����2:10:48
 *@modifyUser Administrator
 *@modifyDate 2018��9��12�� ����2:10:48
 *@modifyDesc  TODO
 *@version TODO
 */
public class ProjectVersion  implements Serializable{

	private static final long serialVersionUID = 2778474306914554884L;
	//�汾id
    private int versionId;
    //��Ŀ����id
    private int projId;
    //�汾��
    private String versionNum;
    //�汾˵��
    private String versionDesc;
    //������
    private int createBy;
    //����ʱ��
    private String createTime;
    //����������
    private String userName;
    //ɾ�����
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

