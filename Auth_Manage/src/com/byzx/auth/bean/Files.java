package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * ��Դ�ļ���
 * @filename Files.java
 * @author Administrator
 * @date 2018��8��31�� ����2:40:27
 * @version 1.0
 * Copyright (C) 2018 
 */
public class Files implements Serializable{
	private static final long serialVersionUID = -4234626457523559178L;
      //�ļ�id  
	  private int fileId;
	  //ƴ�ӵ�id
	  private String fileIdf;
	  //��Դ����id
      private Integer  fileTypeId;
      //�ļ�����
      private String fileName;
      //�ļ�����
      private String fileDesc;
      //�ļ�url
      private String fileUrl;
      //�ļ���Կ
      private String filePswd;
      //����ʱ��
      private String createTime;
      //������
      private Integer createBy;
      //�޸�ʱ��
      private String updateTime;
      //�޸���
      private Integer updateBy;
      //ɾ�����
      private String isDelete;
      
	
	public String getFileIdf() {
		return fileIdf;
	}
	public void setFileIdf(String fileIdf) {
		this.fileIdf = fileIdf;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public Integer getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(Integer fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFilePswd() {
		return filePswd;
	}
	public void setFilePswd(String filePswd) {
		this.filePswd = filePswd;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "Files [fileId=" + fileId + ", fileIdf=" + fileIdf
				+ ", fileTypeId=" + fileTypeId + ", fileName=" + fileName
				+ ", fileDesc=" + fileDesc + ", fileUrl=" + fileUrl
				+ ", filePswd=" + filePswd + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateTime=" + updateTime
				+ ", updateBy=" + updateBy + ", isDelete=" + isDelete + "]";
	}
	
	
	      
}

