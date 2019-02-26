package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;

/**
 * @filename FilesInfoSERVICE.java
 * @author Administrator
 * @date 2018年9月2日 下午4:58:22
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FileTypeService {
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
	 * 查询是否有重复文件夹
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
	 * 删除文件夹先查询是否有子文件夹
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int findContainFileType(FileType fileType);	
    /**
     * 递归拼接code
     * pid不为零
     * @comment 
     * @param fileType
     * @version 1.0
     */
	public	String findfileTypeByPid(FileType fileType, StringBuffer sbf);
	/**
	 * pid为零
	 * @comment 
	 * @version 1.0
	 */
	public String findfileTypeByPidTwo(FileType fileType);
	
	/**
	 * 查询全部
	 * @comment 
	 * @param fileType
	 * @version 1.0
	 */
	public FileType findFileTypeCodePid(FileType fileType);
	/**
	 * 文件下载查询路径
	 * pid为0
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @version 1.0
	 */
	public String findFileTypeCode(FileType fileType);
	/**
	 * pid不是零要递归
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @return
	 * @version 1.0
	 */
	public String findFileTypeCodePID(FileType fileType, StringBuffer sbf);
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

