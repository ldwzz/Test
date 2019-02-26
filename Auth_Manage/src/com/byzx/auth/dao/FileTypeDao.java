package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;

/**
 * @filename FileInfoDao.java
 * @author Administrator
 * @date 2018年9月2日 下午4:38:39
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FileTypeDao {
	/**
	 * 查询资源类型表
	 * @comment 
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<FileType> findFileType();
	/**
	 * 查询资源文件表
	 * @comment 
	 * @return
	 * @version 1.0
	 */
	public List<Files> findFiles();	
	/**
	 * 查询数据库是否有重复文件夹
	 * @comment 
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public int findTypeCode(FileType fileType);
	/**
	 * 新建文件夹
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int insertFileType(FileType fileType);
	/**
	 * 根据当前的pid查询父id的code
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public FileType findfileTypeByPid(FileType fileType);
	/**
	 * 删除文件夹先查询是否有子文件夹
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int findContainFileType(FileType fileType);
	/**
	 * 查询pid为0的code
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public String findfileTypeByPidTwo(FileType fileType);
	
	/**
	 * 查所有
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public FileType findFileTypeCodePid(FileType fileType);
	/**
	 * 文件下载查询路径
	 * pid==0
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @return
	 * @version 1.0
	 */
	public String findFileTypeCode(FileType fileType);
	/**
	 * 文件下载查询路径
	 * pid不是零
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @return
	 * @version 1.0
	 */
	public FileType findFileTypeCodePID(FileType fileType);
	/**
	 *查询子文件夹
	 * @comment 
	 * @param fileTypeId
	 * @return
	 * @version 1.0
	 */
	public  List<FileType> findFileTypeId(int fileTypeId);
	/**
	 * 递归
	 */
	public String findFileTypeDiGui(int fileTypeId,StringBuffer sbf);
    
}

