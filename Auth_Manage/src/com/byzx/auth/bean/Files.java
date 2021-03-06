package com.byzx.auth.bean;

import java.io.Serializable;

/**
 * 资源文件表
 * @filename Files.java
 * @author Administrator
 * @date 2018年8月31日 下午2:40:27
 * @version 1.0
 * Copyright (C) 2018 
 */
public class Files implements Serializable{
	private static final long serialVersionUID = -4234626457523559178L;
      //文件id  
	  private int fileId;
	  //拼接的id
	  private String fileIdf;
	  //资源类型id
      private Integer  fileTypeId;
      //文件名称
      private String fileName;
      //文件描述
      private String fileDesc;
      //文件url
      private String fileUrl;
      //文件秘钥
      private String filePswd;
      //创建时间
      private String createTime;
      //创建人
      private Integer createBy;
      //修改时间
      private String updateTime;
      //修改人
      private Integer updateBy;
      //删除标记
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

