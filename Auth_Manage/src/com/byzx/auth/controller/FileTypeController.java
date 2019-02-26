package com.byzx.auth.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.FileTypeService;

/**
 * @filename SourceController.java
 * @author Administrator
 * @date 2018��9��2�� ����4:34:41
 * @version 1.0
 * Copyright (C) 2018 
 */
@Controller
@RequestMapping("/files")
public class FileTypeController {
	@Autowired
	private FileTypeService fileTypeService;
	private String fileName;
	private Integer fileTypeId;
	private int findTypeCode;
	@RequestMapping("/list")
	public ModelAndView files() {
		JSONArray jsonArray = new JSONArray();
		// ��ѯ�ļ���Ϣ
		List<FileType> FilesList = fileTypeService.findFileType();
		System.out.println(FilesList.toString());
		for (FileType fileType : FilesList) {
			JSONObject json = new JSONObject();
			// ���ļ���Ϣ�Ž�json����
			// id ��ʶ ��pId ��id��name ���ƣ�open �Ƿ�չ���ڵ㣬 checked �Ƿ�ѡ��
			json.put("id", fileType.getFileTypeId());
			json.put("pId",
					fileType.getParentId() == null ? 0 : fileType.getParentId());
			json.put("name", fileType.getTypeName());
			json.put("open", false);
			json.put("grade", fileType.getGrade());
		//	json.put("typeName",fileType.getTypeName());
			
			json.put("url",fileType.getUrl());
			json.put("typeCode", fileType.getTypeCode());
//          ���ļ�״̬ɾ��������font(��ɫ)����
//			if ("0".equals(fileTypeInfo.getAuthState())) {
//				json.put("font", "{'color':'red'}");
//			}
            //��json����Ž���jsonArray����
			jsonArray.add(json);
		}
		List<Files> findFiles = fileTypeService.findFiles();
		System.out.println(findFiles.toString());
		for (Files files : findFiles) {
			// ���ļ���Ϣ�Ž�json����
			JSONObject json = new JSONObject();	
		    // id ��ʶ ��pId ��id��name ���ƣ�open �Ƿ�չ���ڵ㣬 checked �Ƿ�ѡ��
			json.put("id", "f"+files.getFileId());
			json.put("pId",
					files.getFileTypeId() == null ? 0 : files.getFileTypeId());
			json.put("name", files.getFileName());
			json.put("open", false);
//			json.put("fileName", files.getFileName());
//			json.put("fileUrl", files.getFileUrl());
//			json.put("filePswd",files.getFilePswd());
       //   ���ļ�״̬ɾ��������font(��ɫ)����
			if ("1".equals(files.getIsDelete())) {
				json.put("font", "{'color':'red'}");
			}
            //��json����Ž���jsonArray����
			jsonArray.add(json);
		}
		ModelAndView view = new ModelAndView("filesTree");
		// ��jsonArray���鷵���ļ�������
		view.addObject("jsonArray", jsonArray);
		return view;
	}
	
	/*
	 *ƴ��code
	 */
	@RequestMapping("/findfileTypeByPid")
	@ResponseBody
	public JSONObject findfileTypeByPid(FileType fileType) {
		JSONObject json = new JSONObject();		
		//��ѯ������code
		StringBuffer sbf=new StringBuffer();
		String findfileTypeByPid = fileTypeService.findfileTypeByPid(fileType,sbf);
	
		String[] fileCode =findfileTypeByPid.split(",");
		System.out.println(fileCode);
	    if(fileCode.length>0){
	    	String res="";
	    
	    for (int i =fileCode.length-1 ;i>=0 ; i--) {
	    	 res+=fileCode[i];
		}
	    //System.out.println(res+"........."); 	    
		json.put("res",res);
	    }
		// ���������ǰ�˽���
		return json;
	}
	/*
	 * ������Ŀ¼
	 */
	@RequestMapping("/insertFileType")
	@ResponseBody
	public JSONObject insertFileType(StringBuffer sbf, FileType fileType,HttpServletRequest request) throws IOException {		
		  String url=fileType.getUrl();
		  String typeCode = fileType.getTypeCode();
		  System.out.println(typeCode);
		if(url!="" && url!=null){
		  String path = request.getSession().getServletContext()
	         	.getRealPath("/")+url;  
		  File file = new File(path);	 
		  JSONObject json = new JSONObject();
		System.out.println(fileType.toString()+"44444444444444444");
		if(!file.exists()){
			System.out.println("kkkkkkkkkkkkkk");
			file.mkdirs();							
			int res = fileTypeService.findTypeCode(fileType);
			System.out.println(res+"6666666666666666666");
			if(res>0){				
				json.put("res", 1);	
				return json;
			}else{
			int ins = fileTypeService.insertFileType(fileType);			
			if (ins > 0) {
				json.put("res", 20);
			} else {
				json.put("res", 3);
			} }	}
		else{
				json.put("res", 1);
				return json;
			}  return json;}
		else {
			String path = request.getSession().getServletContext()
		         	.getRealPath("/")+typeCode;  
			File file = new File(path);	 
			JSONObject json = new JSONObject();
			//System.out.println(fileType.toString()+"44444444444444444");
			if(!file.exists()){
			//	System.out.println("kkkkkkkkkkkkkk");
				file.mkdirs();	
			//	System.out.println(fileType.getParentId());
				int res = fileTypeService.findTypeCode(fileType);
			//	System.out.println(res+"6666666666666666666");
				if(res>0){				
					json.put("res", 1);				
				}else{				
				int ins = fileTypeService.insertFileType(fileType);			
				if (ins > 0) {
					json.put("res", 20);
				} else {
					json.put("res", 3);
				} }	}return json; }		         
	        }		
		
		
	/*
	 * ɾ���ļ����޸�״̬deleteFileType
	 */
	@RequestMapping("/deleteFileType")
	@ResponseBody
	public JSONObject deleteFileType(FileType fileType,Files files) {
		JSONObject json = new JSONObject();
		System.out.println(files.getFileId());
		//�Ȳ�ѯ�Ƿ������ļ���
	    if(fileType.getFileTypeId()>0){	    	
		int res = fileTypeService.findContainFileType(fileType);
		if(res>0){
			json.put("res", 1);
		   }
	    }
		// ���������ǰ�˽���
		return json;
		
	}
	/**
	 * ��ѯ�ϴ��ļ���·��
	 */
	@RequestMapping("/findUploadUrl")
	@ResponseBody
	public JSONObject findUploadUrl(FileType fileType) {
		JSONObject json = new JSONObject();		
		//��ѯ������code
		StringBuffer sbf=new StringBuffer();		
		 int id=fileType.getParentId();
		 System.out.println(id+"..........");
		 String resUrl="";
		if(id!=0){		
		String findfileTypeByPid = fileTypeService.findfileTypeByPid(fileType,sbf);	
		String[] fileCode =findfileTypeByPid.split(",");
		System.out.println("���������������������������");
		System.out.println(fileCode);
	    if(fileCode.length>0){
	    		    
	    for (int i =fileCode.length-1 ;i>=0 ; i--) {
	    	 resUrl+=fileCode[i];
	    	 System.out.println(resUrl);
		}
	    } 	    
		json.put("resUrl",resUrl);
	     }
		else{
		  String urls = fileTypeService.findfileTypeByPidTwo(fileType);
		  System.out.println(urls+".........");
		  json.put("urls", urls);
		}
		// ���������ǰ�˽���
		return json;
	}
}

