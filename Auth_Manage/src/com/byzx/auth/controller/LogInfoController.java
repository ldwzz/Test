package com.byzx.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.LogInfo;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.LogInfoService;
import com.byzx.auth.service.ProjectInfoService;
import com.byzx.auth.service.UserInfoService;
import com.byzx.auth.utils.PageBean;

/**
 * @comment 日志controller层
 * @author tyz
 * @date 2018年9月7日 下午3:25:12
 * @modifyUser Administrator
 * @modifyDate 2018年9月7日 下午3:25:12
 * @modifyDesc TODO
 * @version TODO
 */
@Controller
@RequestMapping("/log_info")
public class LogInfoController {
	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ProjectInfoService projectInfoService;

	/**
	 * @comment 根据不同的用户登录进来查看自己的回收站显示的信息
	 * @param request
	 * @param recycle
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/logInfoList")
	public ModelAndView getAllLogInfo(HttpServletRequest request,LogInfo logInfo) {
		// 如果userName有值得话，根据他模糊查询到对应的多个userIds
		if (logInfo.getUserName() != null && logInfo.getUserName() != "") {
			String userIds = userInfoService.userIdsByUserName(logInfo.getUserName());
			if(userIds==null){
				userIds="s";
				logInfo.setUserIds(userIds);
			}else{
				logInfo.setUserIds(userIds);
			}
			
		}

		if (logInfo.getProjName() != null && logInfo.getProjName() != "") {
			System.out.println(logInfo.getProjName());
			String projIds = projectInfoService.projIdsByProjName(logInfo.getProjName());
			System.out.println(projIds);
			 if(projIds==null){
				 projIds="-1";
				 logInfo.setProjIds(projIds);
			 }else{
				 logInfo.setProjIds(projIds); 
			 }
			
		}

		// 初始化总条数
		int totalNum = 0;
		// 当前页
		int currNo = (request.getParameter("currNo") != null) ? Integer
				.parseInt(request.getParameter("currNo")) : 1;
		// 每页显示多少条
		int pageNum = (request.getParameter("pageNum") != null) ? Integer
				.parseInt(request.getParameter("pageNum")) : 5;
		PageBean pages = new PageBean(pageNum, currNo);// 位置一定要放对，因为此方法每次都会传进来两个值，一个是当前页和每页显示的条数，根据两个值求出其他参数
		// 总页数
		int totalPage = (totalNum % pageNum == 0) ? (totalNum / pageNum)
				: (totalNum / pageNum) + 1;
		// 查询下标位置，limit 中的第一个参数
		int pageIndex = pageNum * (currNo - 1);
		// 请求地址, recycle/list.action
		String url = "/log_info/logInfoList.action";

		// 页面条件查询参数拼接
		StringBuffer sbf = new StringBuffer();
		if (StringUtils.isNotBlank(logInfo.getCreateStartTime())) {
			sbf.append("&createStartTime=")
					.append(logInfo.getCreateStartTime());
		}
		if (StringUtils.isNotBlank(logInfo.getCreateEndTime())) {
			sbf.append("&createEndTime=").append(logInfo.getCreateEndTime());
		}
		if (StringUtils.isNotBlank(logInfo.getLogInfo())) {
			sbf.append("&logInfo=").append(logInfo.getLogInfo());
		}
		if (StringUtils.isNotBlank(logInfo.getIpAddress())) {
			sbf.append("&ipAddress=").append(logInfo.getIpAddress());
		}
		if (StringUtils.isNotBlank(logInfo.getUserName())) {
			sbf.append("&userName=").append(logInfo.getUserName());
		}
		if (StringUtils.isNotBlank(logInfo.getProjName())) {
			sbf.append("&projName=").append(logInfo.getProjName());
		}

		// 初始化page,用于放入session传输前端
		PageBean page = null;
		// 拿到当前的登录人
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		// 拿到当前登录人对应的角色
		String userRoleCode = (String) request.getSession().getAttribute("Rolecodes");
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		if (userRoleCode.indexOf("admin")!=-1) {
			map.put("logInfo", logInfo);
			//admin进来查询其对应的项目
			List<ProjectInfo>   findAllProList = projectInfoService.findAllPro(map);
			 //把查询出来的项目集合放入session中方便前端拾到
			   request.getSession().setAttribute("findAllProList",findAllProList); 
			// 查询总条数
			totalNum = logInfoService.findLogInfoSum1(map);
			pages.setTotalNum(totalNum);
			if(request.getParameter("flag") != null){
				pages.setPageNum(9999);
				map.put("page", pages);	
			}else{
				map.put("page", pages);	
			}
			
			// 查询所对应的日志信息
			List<LogInfo> list = logInfoService.findLogInfo1(map);
			for (int i = 0; i < list.size(); i++) {// 遍历此集合，遍历一次拿到他的创建人，由此查询出userName，再set到logInfo里面的userName里
				list.get(i).getCreateBy();
				String userName = userInfoService.userNameByUserId(list.get(i)
						.getCreateBy());
				list.get(i).setUserName(userName);
			}
			page = new PageBean(pageNum, totalNum, currNo, totalPage,
					pageIndex, list, url, sbf.toString());
		} else {
			map.put("userId", userInfo.getUserId());
			  List<ProjectInfo> findAllProList  = projectInfoService.findOneselfPro(map); 
			  //把查询出来的项目集合放入session中方便前端拾到
			   request.getSession().setAttribute("findAllProList",findAllProList); 
			
			map.put("logInfo", logInfo);
			// 查询总条数
			totalNum = logInfoService.findLogInfoSum2(map);
			pages.setTotalNum(totalNum);
			if(request.getParameter("flag") != null){
				page.setPageNum(999999);
				map.put("page", pages);
			}else{
				map.put("page", pages);
			}
			
			// 查询所对应的日志信息
			List<LogInfo> list = logInfoService.findLogInfo2(map);
			for (int i = 0; i < list.size(); i++) {// 遍历此集合，遍历一次拿到他的创建人，由此查询出userName，再set到logInfo里面的userName里
				list.get(i).getCreateBy();
				String userName = userInfoService.userNameByUserId(list.get(i).getCreateBy());
				list.get(i).setUserName(userName);
			}
			page = new PageBean(pageNum, totalNum, currNo, totalPage,
					pageIndex, list, url, sbf.toString());
		}
		


		
		if (request.getParameter("flag") != null) {
			ModelAndView mod = new ModelAndView("logInfo-downLoad");
			mod.addObject("listsize", page.getResultList().size());
			mod.addObject("page", page);
			
			//导出后删除
			/*List<LogInfo> resultList = (List<LogInfo>) page.getResultList();
			for(int i = 0; i < resultList.size(); i++){
			int re=logInfoService.clearLog(resultList.get(i).getLogId());
			}*/
			return mod;
			
		} else {
			ModelAndView mod = new ModelAndView("logInfo-list");
			mod.addObject("listsize", page.getResultList().size());
			mod.addObject("page",page);
			return mod;
		}

	}
}

/**
 * @filename LogInfoController.java
 * @author Administrator
 * @date 2018年9月7日 下午3:13:21
 * @version 1.0 Copyright (C) 2018
 */
