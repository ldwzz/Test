package com.byzx.auth.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.bean.Recycle;



/**
 * @filename FilesService.java
 * @author Administrator
 * @date 2018年9月8日 上午10:45:01
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FilesService {
	/**
	 * 将文件信息插入到files
	 * 没有密码
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public int insertFiles(Files files);
     /**
      * 将文件信息插入到files
	  * 加密
      * @comment 
      * @param files
      * @version 1.0
      */
	public int insertFilesTwo(Files files);
	/**
	 * 删除文件修改状态
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int updateFilesState(Files files);
	/**
	 * 查询密码
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public int  findfilepswd(Files files);
	/**
	 * 查询url
	 * @comment 
	 * @param files
	 * @version 1.0
	 */
	public Files findfileurl(Files files);
	/**
	 * 根据密码查询对象
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public Files findfileurlBypswd(Files files);
	/**
	 * 彻底删除
	 */
	public int deletefiles (Recycle recycle,HttpServletRequest request);
	/**
     * 查询文件
     */
	public int getfilesid(int fileTypeId);
	/**
	 * 查询密码
	 * @comment 
	 * @param fileId
	 * @return
	 * @version 1.0
	 */
	public int getpswd(int fileId);
	/**查文件id
	 * 
	 * @comment 
	 * @param fileId
	 * @return
	 * @version 1.0
	 */
	public String getfilesids(int fileTId);

}

