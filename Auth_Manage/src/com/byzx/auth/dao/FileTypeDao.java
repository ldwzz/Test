package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;

/**
 * @filename FileInfoDao.java
 * @author Administrator
 * @date 2018��9��2�� ����4:38:39
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FileTypeDao {
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
	 * ��ѯ���ݿ��Ƿ����ظ��ļ���
	 * @comment 
	 * @param map
	 * @return
	 * @version 1.0
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
	 * ���ݵ�ǰ��pid��ѯ��id��code
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public FileType findfileTypeByPid(FileType fileType);
	/**
	 * ɾ���ļ����Ȳ�ѯ�Ƿ������ļ���
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int findContainFileType(FileType fileType);
	/**
	 * ��ѯpidΪ0��code
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public String findfileTypeByPidTwo(FileType fileType);
	
	/**
	 * ������
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public FileType findFileTypeCodePid(FileType fileType);
	/**
	 * �ļ����ز�ѯ·��
	 * pid==0
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @return
	 * @version 1.0
	 */
	public String findFileTypeCode(FileType fileType);
	/**
	 * �ļ����ز�ѯ·��
	 * pid������
	 * @comment 
	 * @param fileType
	 * @param sbf
	 * @return
	 * @version 1.0
	 */
	public FileType findFileTypeCodePID(FileType fileType);
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

