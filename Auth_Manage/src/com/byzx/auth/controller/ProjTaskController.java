/**
 * @filename ProjTaskController.java
 * @author zhangpan
 * @date 2018��9��5�� ����5:22:51
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.ProjTask;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.ProjTaskService;
import com.byzx.auth.service.RecycleService;
import com.byzx.auth.utils.PageBean;

@Controller
@RequestMapping("/proj")
public class ProjTaskController {
	@Autowired
	ProjTaskService projTaskService;
	@Autowired
	private RecycleService recycleService;

	@RequestMapping("/list")
	public ModelAndView getAllRoles(HttpServletRequest request,
			ProjTask projTask) {
		// ��־λ(��Ϊ�ձ�ʶ��ѯ�û��б����򵼳�excel)\
		
		String flag = request.getParameter("flag");
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// ҳ��������ѯ����ƴ��
		StringBuffer buffer = new StringBuffer("");
		// ����ÿҳ��ʾ��������ǰҳ
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("projTask", projTask);
		// �����ǰҳ��ʾ������
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		List<ProjTask> list=null;
		int projNum =0;
		if(flag==null){
			// �������������ɫ����
			projNum = projTaskService.findprojTaskcount(projTask);
			if(projTask.getProjId()==null){
				String projId = projTaskService.selectProjId(user.getUserId());
				projTask.setProjId(Integer.parseInt(projId));
			}
			//��ĿId����
			request.getSession().setAttribute("projId", projTask.getProjId());
//			Integer projId = (Integer) request.getSession().getAttribute("projId");
//			projTask.setProjId(projId);
			List<SysClass> list1 = projTaskService.findClass(projTask.getProjId());
			list = projTaskService.findprojTask(map);
			request.getSession().setAttribute("listClass", list1);
		}else{
			String roleCode = (String) request.getSession().getAttribute("Rolecodes");
			map.put("roleCode", roleCode);
			map.put("userId", user.getUserId());
			list = projTaskService.selectTasks(map);
			projNum = projTaskService.selectTasksNums(map);
			
		}
//			if (list != null && list.size() > 0) {
//				request.getSession().setAttribute("classId",
//						list.get(0).getClassId());
//				int classId = (Integer) request.getSession().getAttribute(
//						"classId");
				
//			}
			// �����ݷ�װ��page������
			page = new PageBean(pageNum, projNum, currNo, list,
					"/proj/list.action", buffer.toString());
			// �����ݴ�����ʾ����
			ModelAndView view = new ModelAndView("projTask");
			view.addObject("page", page);
			view.addObject("projList", list);
			return view;
		
	}

	/**
	 * @comment ���������ִ���˽���Ψһ��У��
	 * @param user
	 * @return
	 * @version 1.0
	 */
	/*
	 * @RequestMapping("/addVerify")
	 * 
	 * @ResponseBody public JSONObject addVerify(UserInfo user) { // ���û�������Ψһ��У��
	 * int userNum = projTaskService. JSONObject json = new JSONObject(); if
	 * (userNum > 0) { json.put("msg", 1); } else { json.put("msg", 0); } //
	 * ���������ǰ�� return json; }
	 */

	/**
	 * @comment�����Ŀ����
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insetProjTask")
	@ResponseBody
	public JSONObject insetProjTask(ProjTask projTask) {

		// �����Ŀ����
		int re = projTaskService.insetProjTask(projTask);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}



	/**
	 * @comment��ѯ����ִ�е���
	 * @param user
	 * @return updateProjTask
	 * @version 1.0
	 */
	@RequestMapping("/findProjState")
	@ResponseBody
	public JSONObject findProjState(HttpSession session) {
		JSONObject json = new JSONObject();
		int projId = (Integer) session.getAttribute("projId");
		List<UserInfo> list = projTaskService.findProjState(projId);
		String names = "";
		for (UserInfo userInfo : list) {
			names += "<option value='" + userInfo.getUserId() + "'>"
					+ userInfo.getUserName() + "</option>";
		}
		json.put("names", names);
		return json;
	}

	/**
	 * 
	 * @comment �޸���Ŀ����
	 * @param projTask
	 *            ����
	 * @return json����
	 * @version 1.0
	 */
	@RequestMapping("/updateProjState")
	@ResponseBody
	public JSONObject updateProjState(ProjTask projTask) {
		// �޸���Ŀ����
		int re = projTaskService.updateProjTask(projTask);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		System.out.println(re);
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @commentɾ����Ŀ���� 
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/delProjState")
	@ResponseBody
	public JSONObject delProjState(HttpServletRequest request,Recycle recycle) {
		// ɾ����Ŀ����
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		recycle.setCreateBy(userInfo.getUserId());
		int re = recycleService.delData(recycle);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// ���������ǰ��
		return json;
	}
}
