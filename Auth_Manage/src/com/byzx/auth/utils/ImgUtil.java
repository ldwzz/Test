package com.byzx.auth.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImgUtil {
	public static String loadImg(HttpServletRequest request,
			MultipartFile pictureFile) throws IllegalStateException, IOException  {
			String imgPath = null;
			//�ϴ�ͼƬ
			
			if(!pictureFile.isEmpty()){
				// ʹ��UUID��ͼƬ������ȥ���ĸ���-��
				String name = UUID.randomUUID().toString().replaceAll("-", "");
				// ��ȡ�ļ�����չ��
				String ext = FilenameUtils.getExtension(pictureFile
						.getOriginalFilename());
				// ����ͼƬ�ϴ�·��
				String url = request.getSession().getServletContext()
						.getRealPath("/images");
				pictureFile.transferTo(new File(url + "/" + name + "." + ext));
				// ��װ��Ҫ�洢��ͼƬ·��
				imgPath = "images/" + name + "." + ext;			
				
			}
			return imgPath;
		}
}
