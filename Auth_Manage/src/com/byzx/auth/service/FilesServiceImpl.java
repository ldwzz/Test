package com.byzx.auth.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.dao.FileTypeDao;
import com.byzx.auth.dao.FilesDao;

/**
 * @filename FilesServiceImpl.java
 * @author Administrator
 * @date 2018年9月8日 上午10:47:03
 * @version 1.0
 * Copyright (C) 2018 
 */
@Service
public class FilesServiceImpl implements FilesService{
	@Autowired
	private FilesDao filesDao;
	@Autowired
	private FileTypeDao fileTypeDao;
    /**
     * 上传文件不加密
     */   
	@Override
	public int insertFiles(Files files) {
		// TODO Auto-generated method stub
		return filesDao.insertFiles(files);
	}
    /**
     * 上传文件不加密
     */    
	@Override
	public int insertFilesTwo(Files files) {
		// TODO Auto-generated method stub
		return filesDao.insertFilesTwo(files);
	}
    /**删除文件修改状态
     * 
     */
	@Override
	public int updateFilesState(Files files) {
		// TODO Auto-generated method stub
		return filesDao.updateFilesState(files);
	}
	/**
	 * 查询密码
	 */
	@Override
	public int findfilepswd(Files files) {
		// TODO Auto-generated method stub
		return filesDao.findfilepswd(files);
	}
	/**
	 * 查询密码
	 */
	@Override
	public Files findfileurl(Files files) {
		// TODO Auto-generated method stub
		return filesDao.findfileurl(files);
	}
	@Override
	public Files findfileurlBypswd(Files files) {
		// TODO Auto-generated method stub
		return filesDao.findfileurlBypswd(files);
	}
	@Override
	public int deletefiles(Recycle recycle,HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sbf=new StringBuffer();
		Files 	files=new Files();
		FileType fileType =new FileType();	
		files.setFileId(recycle.getKeyValue());
		Files f= filesDao.findfileurl(files);
		f.getFileTypeId(); f.getFileName();f.getFileUrl();
		fileType.setFileTypeId(f.getFileTypeId());
		FileType fFileType = fileTypeDao.findFileTypeCodePid(fileType);
		Integer pId = fFileType.getParentId();
		
		String upload ="";
		if(pId==0){
			upload=fFileType.getTypeCode();
String path = request.getSession().getServletContext().getRealPath("/")+upload+"\\"+f.getFileName();
       File file1 = new File(path);
       if(file1.exists()){
    	   file1.delete();
    	   return 1;
       }
		}
	 else{
			fileType.setParentId(pId);
			FileTypeServiceImpl fTypeService=new FileTypeServiceImpl();
			String finduploadByPid = fTypeService.findfileTypeByPid(fileType,sbf);
			String[] fileCode =finduploadByPid.split(",");
		//	System.out.println("在这里。。。。。。。。。。。。");
		//	System.out.println(fileCode);
		    if(fileCode.length>0){		    		    
		    for (int i =fileCode.length-1 ;i>=0 ; i--) {
		    	  upload+=fileCode[i];
		    //	 System.out.println(upload);
			    }
		    } 
		     upload=upload+fFileType.getTypeCode();
				upload=fFileType.getTypeCode();
				String path = request.getSession().getServletContext().getRealPath("/")+upload+"\\"+f.getFileName();
				       File file1 = new File(path);
				       if(file1.exists()){
				    	   file1.delete();
				    	   return 1;
				       }
			      
		     }
		return 0;
	}
	
	@Override
	public int getfilesid(int fileTypeId) {
		// TODO Auto-generated method stub
		return filesDao.getfilesid(fileTypeId);
	}
	@Override
	public int getpswd(int fileId) {
		// TODO Auto-generated method stub
		return filesDao.getpswd(fileId);
	}
	@Override
	public String getfilesids(int fileTId) {
		// TODO Auto-generated method stub
		return filesDao.getfilesids(fileTId);
	}

}

