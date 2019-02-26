/**
 * @filename ProjTaskController.java
 * @author zhangpan
 * @date 2018年9月5日 下午5:22:51
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
		// 标志位(若为空标识查询用户列表，否则导出excel)\
		
		String flag = request.getParameter("flag");
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// 页面条件查询参数拼接
		StringBuffer buffer = new StringBuffer("");
		// 设置每页显示数量，当前页
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("projTask", projTask);
		// 查出当前页显示的数据
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		List<ProjTask> list=null;
		int projNum =0;
		if(flag==null){
			// 查出符合条件角色总数
			projNum = projTaskService.findprojTaskcount(projTask);
			if(projTask.getProjId()==null){
				String projId = projTaskService.selectProjId(user.getUserId());
				projTask.setProjId(Integer.parseInt(projId));
			}
			//项目Id存入
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
			// 将数据封装至page对象中
			page = new PageBean(pageNum, projNum, currNo, list,
					"/proj/list.action", buffer.toString());
			// 将数据传至显示界面
			ModelAndView view = new ModelAndView("projTask");
			view.addObject("page", page);
			view.addObject("projList", list);
			return view;
		
	}

	/**
	 * @comment 对添加任务执行人进行唯一性校验
	 * @param user
	 * @return
	 * @version 1.0
	 */
	/*
	 * @RequestMapping("/addVerify")
	 * 
	 * @ResponseBody public JSONObject addVerify(UserInfo user) { // 对用户名进行唯一性校验
	 * int userNum = projTaskService. JSONObject json = new JSONObject(); if
	 * (userNum > 0) { json.put("msg", 1); } else { json.put("msg", 0); } //
	 * 将结果返回前端 return json; }
	 */

	/**
	 * @comment添加项目任务
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/insetProjTask")
	@ResponseBody
	public JSONObject insetProjTask(ProjTask projTask) {

		// 添加项目任务
		int re = projTaskService.insetProjTask(projTask);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}



	/**
	 * @comment查询可以执行的人
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
	 * @comment 修改项目任务
	 * @param projTask
	 *            对象
	 * @return json对象
	 * @version 1.0
	 */
	@RequestMapping("/updateProjState")
	@ResponseBody
	public JSONObject updateProjState(ProjTask projTask) {
		// 修改项目任务
		int re = projTaskService.updateProjTask(projTask);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		System.out.println(re);
		// 将结果返回前端
		return json;
	}
	/**
	 * 
	 * @comment删除项目任务 
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/delProjState")
	@ResponseBody
	public JSONObject delProjState(HttpServletRequest request,Recycle recycle) {
		// 删除项目任务
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		recycle.setCreateBy(userInfo.getUserId());
		int re = recycleService.delData(recycle);
		JSONObject json = new JSONObject();
		if (re > 0) {
			json.put("msg", 1);
		} else {
			json.put("msg", 0);
		}
		// 将结果返回前端
		return json;
	}
}
