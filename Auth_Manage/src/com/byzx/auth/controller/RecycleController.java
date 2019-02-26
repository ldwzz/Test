

package com.byzx.auth.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;




import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.ProjectInfoService;
import com.byzx.auth.service.RecycleService;
import com.byzx.auth.service.SysClassService;
import com.byzx.auth.service.UserInfoService;
import com.byzx.auth.utils.PageBean;
/**
 *@comment 回收站controller层
 *@author tyz
 *@date 2018年9月2日 上午11:40:01
*@modifyUser Administrator
*@modifyDate 2018年9月2日 上午11:40:01
*@modifyDesc  TODO
 *@version TODO
 */


@Controller
@RequestMapping("/recycle")
public class RecycleController {
	@Autowired
	private RecycleService recycleService;
	@Autowired
	private ProjectInfoService  projectInfoService;
	@Autowired
	private  SysClassService  sysClassService;
	@Autowired
	private UserInfoService userInfoService;
	
	
	

	
	
/**	
 * @comment 根据不同的用户登录进来查看自己的回收站显示的信息
 * @param request
 * @param recycle
 * @return
 * @version 1.0
 */
@RequestMapping("/list")
public ModelAndView getAllRecycle(HttpServletRequest request,Recycle recycle,ProjectInfo projectInfo,SysClass sysClass) {
	            // 初始化总条数
				int totalNum =0;
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
				String url = "/recycle/list.action";

				//页面条件查询参数拼接
				StringBuffer sbf =new StringBuffer();
				if (StringUtils.isNotBlank(recycle.getIntro())) {
					sbf.append("&intro").append(recycle.getIntro());
				}
				if (StringUtils.isNotBlank(recycle.getCreateStartTime())) {
					sbf.append("&createStartTime=").append(recycle.getCreateStartTime());
				}
				if (StringUtils.isNotBlank(recycle.getCreateEndTime())) {
					sbf.append("&createEndTime=").append(recycle.getCreateEndTime());
				}
	
				ModelAndView mod = new ModelAndView("recycle-list");
	//拿到当前的登录人
		UserInfo userInfo =(UserInfo)request.getSession().getAttribute("userInfo");
	//拿到当前登录人对应的角色
	   String userRoleCode = (String) request.getSession().getAttribute("Rolecodes");
		
	   HashMap<Object, Object>  map=new HashMap<Object, Object>();
	            map.put("projectInfo", projectInfo);
	            map.put("userId", userInfo.getUserId());
	          //初始化根据不同人进来查询其对应的项目集合
	            List<ProjectInfo>  findAllProList=null; 
	          //初始化根据不同项目查询出来的项目模块集合
	            List<SysClass> findSysClassByPidList2=null;
	            
	            
	            
	  if(userRoleCode.indexOf("admin")!=-1){//admin进来查询其对应的项目
		   findAllProList = projectInfoService.findAllPro(map);           
	   }else{//项目经理、组长、组员等查询其对应的项目
		    findAllProList = projectInfoService.findOneselfPro(map);  
	   }
	   //把查询出来的项目集合放入session中方便前端拾到
	   request.getSession().setAttribute("findAllProList",findAllProList); 
	  
	   
	   
	  if(sysClass.getClassId()==null){//判断第一次进来的话
		  sysClass.setClassId( findAllProList.get(0).getClassId());
	      findSysClassByPidList2 = sysClassService.findSysClassByPid(sysClass); 
	  }else{//否则的话就是你每次手动点击项目下拉框进来
		   findSysClassByPidList2 = sysClassService.findSysClassByPid(sysClass);
             }
	  //把查询出来的项目模块集合存入session中方便前端拾到
	  request.getSession().setAttribute("findSysClassByPidList", findSysClassByPidList2);
	   
	  //此时要做的是 根据项目模块查询回收站表，展示给用户 
	 String n=request.getParameter("n");//用于区分是第一次进来还是手动选择项目模块出现的值
	 String cids=request.getParameter("classIds");
	   
		   if("".equals(n) || n==null){
				if(findSysClassByPidList2 !=null && findSysClassByPidList2.size()>0){
					   int	classIds=findSysClassByPidList2.get(0).getClassId();
		    	         map.put("classIds", classIds);
				}   
		   }else{
			   if(!"".equals(cids) && cids!=null){
				   if(cids=="0"){//此时我查询出该项目对应下的所有模块信息
						  cids= sysClassService.findIdsByPid(sysClass.getClassId());
						   map.put("classIds", cids);
					   }else{
						   map.put("classIds", Integer.parseInt(cids)); 
					   }
			   }
			  
		   }
		   
		   map.put("userId", userInfo.getUserId());
		    map.put("recycle", recycle);
		    //查询总条数
		    totalNum = recycleService.findRecycleSum(map);	
		       map.put("page", pages); 	
		 
		    
		     //查询所有数据
		   List<Recycle> list = recycleService.findRecycle(map);
		      for (int i=0;i<list.size();i++) {//遍历这个集合，遍历一次让list.setUserName.传进来一个值
		    	  if(list.get(i)!=null){
		    		  String userName = userInfoService.userNameByUserId(list.get(i).getCreateBy());//根据创建人id查询出创建人名字
		    		  list.get(i).setUserName(userName);
		    	  }
		    	  
		    	  
                          }
		 	// 请求参数(搜索条件参数的封装), &userCode=admin&userType=1&userState=1
	   		PageBean page = new PageBean(pageNum, totalNum, currNo, totalPage,
	   				pageIndex, list, url, sbf.toString());
		 
	   		request.setAttribute("page", page);

	   
	   return mod; 
        }
//回收站之删除
@RequestMapping("/deleteRecycle")
@ResponseBody
    public JSONObject  deleteRecycle(HttpServletRequest request,Recycle recycle){
	JSONObject json = new JSONObject();
	int re = recycleService.delRecycle(recycle);
	 if(re>0){
		 json.put("re", 1);
	 }else{
		 json.put("re", 0);
	 }
	 return json;
}
//回收站之恢复
@RequestMapping("/recoverData")
@ResponseBody
    public JSONObject  recoverData(HttpServletRequest request,Recycle recycle){
	JSONObject json = new JSONObject();
	Recycle  recyByRecyId = recycleService.findRecyByRecyId(recycle);//返回的是一个对象通过recyId查询出来的
	     recycle.setTableName(recyByRecyId.getTableName());
	     recycle.setKeyName(recyByRecyId.getKeyName());
	     recycle.setKeyValue(recyByRecyId.getKeyValue());
	
	int re = recycleService.recoverData(recycle);
	 if(re>0){//恢复完之后删除该行信息
		 recycleService.delRecycle(recycle);
		 json.put("re", 1);
	 }else{
		 json.put("re", 0);
	 }
	 return json;
}
	
	
	
}

/**
 * @filename RecycleController.java
 * @author Administrator
 * @date 2018年8月31日 上午9:16:29
 * @version 1.0
 * Copyright (C) 2018 
 */
