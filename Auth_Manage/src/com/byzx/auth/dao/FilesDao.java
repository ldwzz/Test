package com.byzx.auth.dao;

import java.util.List;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.bean.Recycle;

/**
 * @filename FilesDao.java
 * @author Administrator
 * @date 2018��9��8�� ����10:49:16
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FilesDao {
	/**
	 * ���ļ���Ϣ���뵽files
	 * û������
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public int insertFiles(Files files);
	/**
	 * ���ļ���Ϣ���뵽files
	 * ����
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public int insertFilesTwo(Files files);
	/**
	 * ɾ���ļ��޸�״̬
	 * @comment 
	 * @param fileType
	 * @return
	 * @version 1.0
	 */
	public int updateFilesState(Files files);
	/**
	 * ��ѯ����
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public int  findfilepswd(Files files);
	/**
	 * ��ѯurl
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public Files findfileurl(Files files);
	/**
	 * ���������ѯ�ļ�
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public Files findfileurlBypswd(Files files);
	/**
	 * ����ɾ��
	 */
	public int deletefiles (Recycle recycle);
	/**
     * ��ѯ�ļ�������
     */
	public int getfilesid(int fileTypeId);
	/**
	 * ��ѯ����
	 * @comment 
	 * @param fileId
	 * @return
	 * @version 1.0
	 */
	public int getpswd(int fileId);
	/**
	 * ���ļ�id
	 * @comment 
	 * @param fileId
	 * @return
	 * @version 1.0
	 */
	public String getfilesids(int fileTId);
}

