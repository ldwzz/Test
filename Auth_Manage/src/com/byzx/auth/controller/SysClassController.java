

package com.byzx.auth.controller;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.AuthInfo;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.SysClassService;

/**
 *@comment 绯荤粺鍒嗙被鐨刢ontroller
 *@author tyz
 *@date 2018骞�鏈�鏃�涓嬪崍6:14:07
*@modifyUser Administrator
*@modifyDate 2018骞�鏈�鏃�涓嬪崍6:14:07
*@modifyDesc  TODO
 *@version TODO
 */

@Controller
@RequestMapping("/sysClass")
public class SysClassController {
@Autowired
	private SysClassService  sysClassService;
	
/**
 * @comment 绯荤粺澶ф爲鐨勫睍绀�
 * @param authInfo1
 * @return
 * @version 1.0
 */
@RequestMapping("/sysClassList")
public ModelAndView sysClassTree() {
	JSONArray jsonArray = new JSONArray();
	// 鏌ヨ绯荤粺鍒嗙被淇℃伅
       List<SysClass> sysClassList= sysClassService.findAllSysClass();
	// 瀵圭粨鏋滆繘琛岄亶鍘�
	for (SysClass sysClass: sysClassList) {
		JSONObject json = new JSONObject();
		// 灏嗗垎绫讳俊鎭斁杩沯son瀵硅薄
		json.put("id", sysClass.getClassId());
		json.put("pId",sysClass.getParentId() == null ? 0 : sysClass.getParentId());
		json.put("name", sysClass.getClassName());
		json.put("open", false);
		json.put("sysType", sysClass.getSysType());//鍒嗙被绫诲瀷
		json.put("classDesc", sysClass.getClassDesc()); //鍒嗙被鎻忚堪
		json.put("grade", sysClass.getGrade());//鍒嗙被绾у埆
        json.put("classCode", sysClass.getClassCode());//鍒嗙被code
		// 灏唈son瀵硅薄鏀捐繘杩沯sonArray鏁扮粍
		jsonArray.add(json);
	}
	ModelAndView view = new ModelAndView("sysClassTree");
	// 灏唈sonArray鏁扮粍杩斿洖鏉冮檺鏍戠晫闈�
	view.addObject("jsonArr", jsonArray);
	return view;
}
	
/**
 * @comment 娣诲姞绯荤粺鍒嗙被
 * @param sysClass
 * @return 
 * @version 1.0
 */
@RequestMapping("/addSysClass")
@ResponseBody
public JSONObject addSysClass(SysClass sysClass) {
	JSONObject json = new JSONObject();
	//鍏堝垽鏂紶杩囨潵鐨刢lassCode鍜宑lassName鏄惁瀛樺湪
	    int re= sysClassService.findSum(sysClass);
	    if(re>0){
	    	json.put("msg", 3);
	    }else{
	    	int er = sysClassService.insertSysClass(sysClass);
	    	if(er>0){
	    		json.put("msg", 1);
	    	}else{
	    		json.put("msg", 2);
	    	}
            }

	return json;
}

//淇敼绯荤粺鍒嗙被
@RequestMapping("/updateSysClass")
@ResponseBody
public JSONObject updateSysClass(SysClass sysClass) {
	JSONObject json = new JSONObject();
     int re= sysClassService.updateSysClass(sysClass);
    if (re > 0) {
		json.put("msg", 1);
	} else {
		json.put("msg", 0);
	}
	// 灏嗙粨鏋滆繑鍥炲墠绔�
	return json;
}







	
	
}

/**
 * @filename SysClassController.java
 * @author Administrator
 * @date 2018骞�鏈�鏃�涓嬪崍6:13:52
 * @version 1.0
 * Copyright (C) 2018 
 */
