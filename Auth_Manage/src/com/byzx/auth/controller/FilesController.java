package com.byzx.auth.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lingala.zip4j.io.ZipOutputStream;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.FileType;
import com.byzx.auth.bean.Files;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.FileTypeService;
import com.byzx.auth.service.FilesService;
import com.byzx.auth.service.RecycleService;
import com.byzx.auth.utils.CompressUtil;


/**
 * @filename FilesController.java
 * @author Administrator
 * @date 2018��9��9�� ����5:31:12
 * @version 1.0 Copyright (C) 2018
 */
@Controller
@RequestMapping("/upload")
public class FilesController {
	@Autowired
	private RecycleService recycleService;
	@Autowired
	private FilesService filesService;
	@Autowired
	private FileTypeService fileTypeService;

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mov = new ModelAndView("index");
		return mov;
	}

	/***
	 * �ϴ��ļ� ��@RequestParamע����ָ�����ϵ�fileΪMultipartFile
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, Files files) {
		// Ҫ�������ݿ��file_tytpe_id
		String fileid = request.getParameter("fileid");
		// Ҫ�õ�·��upload
		String upload = request.getParameter("fileurl");
		// Ҫ�õ�����
		String filepswd = request.getParameter("filePswd");
		// String filename=request.getParameter("file");
		System.out.println(fileid+".."+upload+".."+filepswd);
		// �ж��ļ��Ƿ�Ϊ��
        String name=UUID.randomUUID()+file.getOriginalFilename();
		if (!file.isEmpty()) {
			try {
				// �ļ�����·��
				// file.getOriginalFilename() ��ȡ�ϴ��ļ���ԭʼ����
				String filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ upload + "\\" +name;
				// ת���ļ�
				System.out.println(filePath);
				file.transferTo(new File(filePath));
				String filename = name;
				System.out.println(fileid + ".." + filePath + ".." + filename);
				files.setFileTypeId(Integer.parseInt(fileid));
				files.setFileName(filename);
				// files.setFileUrl(filePath);
				files.setFileUrl(filename);
				files.setFilePswd(filepswd);
				System.out.println(files.toString());
				if ("".equals(filepswd)) {
					int res = filesService.insertFiles(files);
					System.out.println(res);
					/*
					 * if(res>0){ request.setAttribute("res",100); }
					 */
				} else if (filepswd != "" && filepswd != null) {
					int res = filesService.insertFilesTwo(files);
					/*
					 * if(res>0){ request.setAttribute("res",100); }
					 */
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// �ض���
		return "redirect:/files/list.action";
	}

	/*
	 * ɾ���ļ��޸�״̬
	 */
	@RequestMapping("/updateFilesState")
	@ResponseBody
	public JSONObject updateFilesState(Files files, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"userInfo");
		int id = files.getFileId();
		Recycle recycle = new Recycle("auth_manage", 4, "null", "files",
				"file_id", id, userInfo.getUserId());
		// System.out.println(files.getFileId());
		int res = recycleService.delData(recycle);
		System.out.println(res);
		if (res > 0) {
			json.put("res", 1);
		}
		// ���������ǰ�˽���
		return json;

	}

	/*
	 * ɾ���ļ�
	 */
	@RequestMapping("/deletefiles")
	@ResponseBody
	public void deletefiles(Recycle recycle, HttpServletRequest request) {
		// JSONObject json = new JSONObject();

		StringBuffer sbf = new StringBuffer();
		Files files = new Files();
		FileType fileType = new FileType();
		files.setFileId(19);
		Files f = filesService.findfileurl(files);
		f.getFileTypeId();
		f.getFileName();
		f.getFileUrl();
		fileType.setFileTypeId(f.getFileTypeId());
		FileType fFileType = fileTypeService.findFileTypeCodePid(fileType);
		Integer pId = fFileType.getParentId();

		String upload = "";
		if (pId == 0) {
			upload = fFileType.getTypeCode();
			String path = request.getSession().getServletContext()
					.getRealPath("/")
					+ upload + "\\" + f.getFileName();
			File file1 = new File(path);
			if (file1.exists()) {
				file1.delete();
			}
		} else {
			fileType.setParentId(pId);
			String finduploadByPid = fileTypeService.findfileTypeByPid(
					fileType, sbf);
			String[] fileCode = finduploadByPid.split(",");
			// System.out.println("���������������������������");
			// System.out.println(fileCode);
			if (fileCode.length > 0) {
				for (int i = fileCode.length - 1; i >= 0; i--) {
					upload += fileCode[i];
					// System.out.println(upload);
				}
			}
			upload = upload + fFileType.getTypeCode();

		}

	}

	/*
	 * ��ѯ�ļ�������
	 */
	@RequestMapping("/findfilepswd")
	@ResponseBody
	public JSONObject findfilepswd(Files files) {
		JSONObject json = new JSONObject();
		// System.out.println(files.getFileId());
		int res = filesService.findfilepswd(files);
		System.out.println(res);
		if (res > 0) {
			json.put("res", 10);
		} else {
			json.put("res", 2);
		}
		// ���������ǰ�˽���
		return json;
	}

	/**
	 * �ļ����� û������
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	@RequestMapping("/download")
	public void down(HttpServletRequest request, HttpServletResponse response,
			Files files, FileType fileType) throws Exception {
		// ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�
		StringBuffer sbf = new StringBuffer();
		String res = "";

		String fileTypeId = request.getParameter("fileTypeId");
		System.out.println(fileTypeId + "9999");
		// String fid=fileId.substring(1);
		// System.out.println(fid);
		fileType.setFileTypeId(Integer.parseInt(fileTypeId));
		// ��ѯFileType������
		FileType ft = fileTypeService.findFileTypeCodePid(fileType);
		// ��ѯfile_type���·��
		Integer pId = ft.getParentId();
		// fileType.setParentId(pId);
		if (pId == 0) {
			fileType.setFileTypeId(Integer.parseInt(fileTypeId));
			// ������Ϣ
			String findFTCode = fileTypeService.findFileTypeCode(fileType);
			// ·��
			res = findFTCode;
			String fileId = request.getParameter("fileIds");
			String fid = fileId.substring(1);
			files.setFileId(Integer.parseInt(fid));
			Files file = filesService.findfileurl(files);
			System.out.println(file.toString());
			// �ļ���
			String filename = file.getFileName();
			String upload = res;
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ upload + "\\" + filename;
			// ��ȡ������
			InputStream bis = new BufferedInputStream(new FileInputStream(
					new File(fileName)));
			// ���������������صĻ�
			// String fName =
			// String filename =file.getFileName();
			// ת�룬����ļ�����������
			filename = URLEncoder.encode(filename, "UTF-8");
			// �����ļ�����ͷ
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filename);
			// 1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����
			response.setContentType("multipart/form-data");
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			int len = 0;
			while ((len = bis.read()) != -1) {
				out.write(len);
				out.flush();
			}
			out.close();
			bis.close();
		} else {

			fileType.setParentId(ft.getParentId());
			String findFTCode = fileTypeService.findFileTypeCodePID(fileType,
					sbf);
			// fileTypeService.findFileTypeCodePid(fileType);
			String[] fileCode = findFTCode.split(",");
			System.out.println(fileCode);

			for (int i = fileCode.length - 1; i >= 0; i--) {
				// ���ص�·��
				res += fileCode[i];
				// System.out.println(res+".........");
			}
			String fileId = request.getParameter("fileIds");
			String fid = fileId.substring(1);
			files.setFileId(Integer.parseInt(fid));
			Files file = filesService.findfileurl(files);
			System.out.println(file.toString());
			String filename = file.getFileName();
			String upload = res + ft.getTypeCode();
			;
			// ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ upload + "\\" + filename;
			// String fileUrl= file.getFileUrl();
			// System.out.println(fileUrl);

			// String fileUrl=res+"/"+fname;
			// ��ȡ������
			InputStream bis = new BufferedInputStream(new FileInputStream(
					new File(fileName)));
			// ���������������صĻ�
			// String fName =
			// String filename =file.getFileName();
			// ת�룬����ļ�����������
			filename = URLEncoder.encode(filename, "UTF-8");
			// �����ļ�����ͷ
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filename);
			// 1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����
			response.setContentType("multipart/form-data");
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			int len = 0;
			while ((len = bis.read()) != -1) {
				out.write(len);
				out.flush();
			}
			out.close();
			bis.close();
		}
	}

	/**
	 * ������֤
	 */
	@RequestMapping("/getpswd")
	@ResponseBody
	public JSONObject getpswd(Files files) throws Exception {
		// ����
		JSONObject json = new JSONObject();
		System.out.println("............" + files.toString());
		Files file = filesService.findfileurlBypswd(files);
		if (file == null) {// �������
			json.put("res", 0);
		} else {// ������ȷ
			json.put("res", 1);
		}
		return json;
	}

	/**
	 * ����������
	 * 
	 * @comment
	 * @param request
	 * @param response
	 * @param files
	 * @throws Exception
	 * @version 1.0
	 */
	@RequestMapping("/downloadTwo")
	public void downloadTwo(HttpServletRequest request,
			HttpServletResponse response, Files files, FileType fileType)
			throws Exception {
		// ����
		StringBuffer sbf = new StringBuffer();
		String res = "";
		JSONObject json = new JSONObject();
		String fileId = request.getParameter("fileId");
		// filede �ļ��ڵ�id
		// String f = files.getFileIdf();
		// String fid= f.substring(1);
		// System.out.println(fid);//�ļ���id
		files.setFileId(Integer.parseInt(fileId));
		String fpswd = request.getParameter("filePswd");
		// ����fileTypeId���ϼ���������Ϣ
		String fileTypeId = request.getParameter("fileTypeId");
		// pidΪ0��code���Լ���һ��
		fileType.setFileTypeId(files.getFileTypeId());
		FileType ft = fileTypeService.findFileTypeCodePid(fileType);
		Integer pId = ft.getParentId();
		if (pId == 0) {
			// fileType.setFileTypeId(Integer.parseInt(fileTypeId));
			// String findFTCode = fileTypeService.findFileTypeCode(fileType);
			// ·��
			String upload = ft.getTypeCode();
			System.out.println(upload);
			Files file = filesService.findfileurl(files);// ����id��ѯfilenmae

			String filename = file.getFileName();
			// ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ upload + "\\" + filename;
			// ��ȡ������
			InputStream bis = new BufferedInputStream(new FileInputStream(
					new File(fileName)));
			// ת�룬����ļ�����������
			// String filename =file.getFileName();
			filename = URLEncoder.encode(filename, "UTF-8");
			// �����ļ�����ͷ
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filename);
			// 1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����
			response.setContentType("multipart/form-data");
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			int len = 0;
			while ((len = bis.read()) != -1) {
				out.write(len);
				out.flush();
			}
			out.close();
			bis.close();
		} else {
			fileType.setParentId(ft.getParentId());
			String findFTCode = fileTypeService.findFileTypeCodePID(fileType,
					sbf); // fileTypeService.findFileTypeCodePid(fileType);
			String[] fileCode = findFTCode.split(",");
			System.out.println(fileCode);
			for (int i = fileCode.length - 1; i >= 0; i--) {
				// ���ص�·��
				res += fileCode[i];
				// System.out.println(res+".........");
			}
			// String fileId=request.getParameter("fileIds");
			// String fid=fileId.substring(1);
			// files.setFileId(Integer.parseInt(fid));
			Files file = filesService.findfileurl(files);
			System.out.println(file.toString());
			String fname = file.getFileName();
			String upload = res + ft.getTypeCode();
			;
			// ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ upload + "\\" + fname;
			// String fileUrl= file.getFileUrl();
			// System.out.println(fileUrl);
			// String fileUrl=res+"/"+fname;
			// ��ȡ������
			InputStream bis = new BufferedInputStream(new FileInputStream(
					new File(fileName)));
			// ���������������صĻ�
			// String fName =
			String filename = file.getFileName();
			// ת�룬����ļ�����������
			filename = URLEncoder.encode(filename, "UTF-8");
			// �����ļ�����ͷ
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filename);
			// 1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����
			response.setContentType("multipart/form-data");
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			int len = 0;
			while ((len = bis.read()) != -1) {
				out.write(len);
				out.flush();
			}
			out.close();
			bis.close();
		}

	}

	/*
	 * ��ѯ�ļ�������
	 */
	@RequestMapping("/findzipfilepswd")
	@ResponseBody
	public JSONObject findzipfilepswd(Files files) {
		JSONObject json = new JSONObject();
		// System.out.println(files.getFileId());
		int res = filesService.findfilepswd(files);
		System.out.println(res);
		if (res > 0) {// ������
			json.put("res", 11);
		} else {// ������
			json.put("res", 12);
		}
		// ���������ǰ�˽���
		return json;
	}

	/**
	 * ��֤�Ƿ�������
	 * 
	 * @comment
	 * @param request
	 * @param files
	 * @param fileName
	 * @param zipFileName
	 * @throws IOException
	 * @version 1.0
	 */
	@RequestMapping("/zipdownload")
	@ResponseBody
	public JSONObject zipdownloadnopswd(Files files, FileType fileType,
			HttpServletRequest request) throws IOException {
		JSONObject json = new JSONObject();
		// �ļ��е�filetypeid
		StringBuffer sbf = new StringBuffer();
		int fileTypeId = fileType.getFileTypeId();
		System.out.println(fileTypeId);
		String digui = fileTypeService.findFileTypeDiGui(fileTypeId, sbf);
		System.out.println("...." + digui);
		if ("".equals(digui)) {
			int getfilesid = filesService.getfilesid(fileTypeId);
			if (getfilesid > 0) {// ������
				json.put("res", 1);
			} else {// û������
				json.put("res", 2);
			}
			return json;
		} else {
			String[] id = digui.split(",");
			String a = "";
			// int b=0;
			String res = null;
			for (int i = 0; i < id.length; i++) {
				int fileTId = Integer.parseInt(id[i]);
				if (filesService.getfilesids(fileTId) != null) {
					a += filesService.getfilesids(fileTId) + ",";
				}
			}
			System.out.println(a);
			if (a != null) {
				String[] fid = a.split(",");
				for (int i = 0; i < fid.length; i++) {
					System.out.println(fid[i]);
					int pswd = filesService.getpswd(Integer.parseInt(fid[i]));
					// int fileId=Integer.parseInt(fid[i]) ;
					// String pswd=filesService.getpswd(fileId);
					if (pswd > 0) {// ������
						json.put("res", 1);
					} else {// û������
						json.put("res", 2);
						break;
					}
					return json;
				}
			}
		}
		return json;
	}

	/**
	 * ѹ��
	 */
	@RequestMapping("/zip")
	@ResponseBody
	public JSONObject zip(FileType fileType, HttpServletRequest request)
			throws Exception {
		System.out.println(22222);
		// ����
		JSONObject json = new JSONObject();
		StringBuffer sbf = new StringBuffer();
		String fileurl = "";
		String fn = "";
		String up = "";
		// ��ѯpid
		FileType ft = fileTypeService.findFileTypeCodePid(fileType);
		// ��ѯfile_type���·��
		System.out.println(ft.toString());
		Integer pId = ft.getParentId();
		fileType.setParentId(ft.getParentId());
		String upload = fileTypeService.findfileTypeByPid(fileType, sbf);
		System.out.println(upload + "999999");
		String[] fileUrl = upload.split(",");
		String fupload = "";
		for (int i = 0; i < fileUrl.length; i++) {
			String fileup = fileUrl[i];
			fupload += fileup;
		}
		up = ft.getTypeCode();// ��ǰ·��
		fn = ft.getTypeName();
		String url = request.getSession().getServletContext().getRealPath("/")
				+ fupload + up /* + "\\" + fn */;
		System.out.println(url + "...");
		String toUrl = fupload + up + ".zip";
		System.out.println(666 + toUrl);
		File file = new File(url + ".zip");
		if (file.exists()) {
			// ѹ�����Ѵ���,ɾ��
			file.delete();
		}
		// ����ѹ��������
		String zipUrl = CompressUtil.zip(url);
		// �ж�ѹ�����Ƿ���ڣ�
		if (zipUrl != null) {
			// ���ڣ�����ѹ����·��������1
			json.put("zip", toUrl);
			json.put("res", "1");
		} else {
			// �����ڣ�ѹ��ʧ�ܣ�����0
			json.put("res", "0");
		}
		// else{
		//
		// }
		return json;
	}
}
