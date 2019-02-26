package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;

/**
 * @filename FilesInfoSERVICE.java
 * @author Administrator
 * @date 2018��9��2�� ����4:58:22
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FileTypeService {
	/**
	 * ��ѯ��Դ���ͱ�
	 * @comment 
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<FileType> findFileType();
	/**
	 * ��ѯ��Դ�ļ���
	 * @comment 
	 * @return
	 * @version 1.0
	 */
	public List<Files> findFiles();
	/**
	 * ��ѯ�Ƿ����ظ��ļ���
	 */	
	public int findTypeCode(FileType fileType);
	/**
	 * �½��ļ���
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int insertFileType(FileType fileType);
	/**
	 * ɾ���ļ����Ȳ�ѯ�Ƿ������ļ���
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int findContainFileType(FileType fileType);	
    /**
     * �ݹ�ƴ��code
     * pid��Ϊ��
     * @comment 
     * @param fileType
     * @version 1.0
     */
	public	String findfileTypeByPid(FileType fileType, StringBuffer sbf);
	/**
	 * pidΪ��
	 * @comment 
	 * @version 1.0
	 */
	public String findfileTypeByPidTwo(FileType fileType);
	
	/**
	 * ��ѯȫ��
	 * @comment 
	 * @param fileType
	 * @version 1.0
	 */
	public FileType findFileTypeCodePid(FileType fileType);
	/**
	 * �ļ����ز�ѯ·��
	 * pidΪ0
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @version 1.0
	 */
	public String findFileTypeCode(FileType fileType);
	/**
	 * pid������Ҫ�ݹ�
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @return
	 * @version 1.0
	 */
	public String findFileTypeCodePID(FileType fileType, StringBuffer sbf);
	/**
	 *��ѯ���ļ���
	 * @comment 
	 * @param fileTypeId
	 * @return
	 * @version 1.0
	 */
	public  List<FileType> findFileTypeId(int fileTypeId);
	/**
	 * �ݹ�
	 */
	public String findFileTypeDiGui(int fileTypeId,StringBuffer sbf);
	
	
	
	
	
	
	
}

