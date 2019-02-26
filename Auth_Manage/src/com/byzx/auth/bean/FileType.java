package com.byzx.auth.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 资源文件类型表
 * @filename FileType.java
 * @author Administrator
 * @date 2018年8月31日 下午3:22:46
 * @version 1.0
 * Copyright (C) 2018 
 */
public class FileType implements Serializable{
	private static final long serialVersionUID = 7529143672253148027L;
      //资源类型id
	  private int fileTypeId;
	  //类型名称
	  private String typeName;
	  //类型编码
	  private String typeCode;
	  //类型父id
	  private Integer parentId;
	  //等级
	  private Integer grade;
	  //url
	  private String url;
	  //资源文件表
	  private List<Files> files;
	

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFileTypeId() {
		return fileTypeId;
	}
	public void setFileTypeId(int fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public List<Files> getFiles() {
		return files;
	}
	public void setFiles(List<Files> files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "FileType [fileTypeId=" + fileTypeId + ", typeName=" + typeName
				+ ", typeCode=" + typeCode + ", parentId=" + parentId
				+ ", grade=" + grade + ", url=" + url + ", files=" + files
				+ "]";
	}
	
}

