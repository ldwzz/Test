package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.dao.FileTypeDao;
import com.byzx.auth.dao.FilesDao;

/**
 * @filename FilesInfoServiceImpl.java
 * @author Administrator
 * @date 2018年9月2日 下午5:02:40
 * @version 1.0
 * Copyright (C) 2018 
 */
@Service
public class FileTypeServiceImpl implements FileTypeService{
    @Autowired
    private FileTypeDao fileTypeDao;
	@Autowired
	private FilesDao filesDao; 
	@Override
	public List<FileType> findFileType() {		
		return fileTypeDao.findFileType();
	}
	@Override
	public List<Files> findFiles() {
		return fileTypeDao.findFiles();
	}
	@Override
	public int findTypeCode(FileType fileType) {
		// TODO Auto-generated method stub
		return fileTypeDao.findTypeCode(fileType);
	}
	@Override
	public int insertFileType(FileType fileType) {
		// TODO Auto-generated method stub
		return fileTypeDao.insertFileType(fileType);
	}
	@Override
	public int findContainFileType(FileType fileType) {
		// TODO Auto-generated method stub
		return fileTypeDao.findContainFileType(fileType);
	}
/*	@Override
	public String findfileTypeByPid(FileType fileType) {
		StringBuffer sbf=null;
		FileType  ft=fileTypeDao.findfileTypeByPid(fileType);	
		if(ft!=null){
     sbf.append(ft.getTypeCode()).append("/").append(",");
         findfileTypeByPid(ft);
		}	  		
		return sbf.toString();
	}	*/

	@Override	
	public String findfileTypeByPid(FileType fileType,StringBuffer sbf) {		
		FileType ft = fileTypeDao.findfileTypeByPid(fileType); 
		if(ft!=null){				
		sbf.append(ft.getTypeCode()).append("\\").append(",");
		findfileTypeByPid(ft,sbf);		
		}
		return sbf.toString();
	}
	@Override
	public String findfileTypeByPidTwo(FileType fileType) {
		// TODO Auto-generated method stub
		return fileTypeDao.findfileTypeByPidTwo(fileType);
	}
	
	
	
	//查询所有信息
	@Override
	public FileType findFileTypeCodePid(FileType fileType) {
		// TODO Auto-generated method stub
		return fileTypeDao.findFileTypeCodePid(fileType);
	}
	//pid为0只查询code
	@Override
	public String findFileTypeCode(FileType fileType) {
		// TODO Auto-generated method stub
		return fileTypeDao.findFileTypeCode(fileType);
	}
	//pid不为0只查询code
	@Override
	public String findFileTypeCodePID(FileType fileType, StringBuffer sbf) {
		// TODO Auto-generated method stub
		 FileType ft = fileTypeDao.findFileTypeCodePID(fileType);
	if(ft!=null){				
		sbf.append(ft.getTypeCode()).append("\\").append(",");
		findFileTypeCodePID(ft,sbf);		
				}
	return sbf.toString();
		
	}
	@Override
	public List<FileType> findFileTypeId(int fileTypeId) {
		
		return fileTypeDao.findFileTypeId(fileTypeId);
	}
	@Override
	public String findFileTypeDiGui(int fileTypeId, StringBuffer sbf) {
		List<FileType> list = fileTypeDao.findFileTypeId(fileTypeId);
		 for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=null){
			sbf.append(list.get(i).getFileTypeId()+",");
			findFileTypeDiGui(list.get(i).getFileTypeId(),sbf);	
			}
		}
		return sbf.toString();
	}


}

