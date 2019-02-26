package com.byzx.auth.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.bean.Recycle;



/**
 * @filename FilesService.java
 * @author Administrator
 * @date 2018��9��8�� ����10:45:01
 * @version 1.0
 * Copyright (C) 2018 
 */
public interface FilesService {
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
	 * @version 1.0
	 */
	public Files findfileurl(Files files);
	/**
	 * ���������ѯ����
	 * @comment 
	 * @param files
	 * @return
	 * @version 1.0
	 */
	public Files findfileurlBypswd(Files files);
	/**
	 * ����ɾ��
	 */
	public int deletefiles (Recycle recycle,HttpServletRequest request);
	/**
     * ��ѯ�ļ�
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
	/**���ļ�id
	 * 
	 * @comment 
	 * @param fileId
	 * @return
	 * @version 1.0
	 */
	public String getfilesids(int fileTId);

}

