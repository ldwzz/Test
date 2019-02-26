/**
 * @filename ProjectInfoController.java
 * @author Administrator
 * @date 2018年9月3日 上午10:55:13
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.ProjectDynamic;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.ProjectVersion;
import com.byzx.auth.bean.ProjectWhiteList;
import com.byzx.auth.bean.Recycle;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.service.ProjectInfoService;
import com.byzx.auth.service.RecycleService;
import com.byzx.auth.utils.PageBean;


@Controller
@RequestMapping("/project")
public class ProjectInfoController {
	@Autowired ProjectInfoService projectInfoService;
	@Autowired
	private RecycleService recycleService;
	/**
	 * 
	 * @comment  查询项目
	 * @param request
	 * @param project
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllProjects(HttpServletRequest request,
		ProjectInfo project) {	
		
		PageBean page = projectInfoService.findProjects(request, project);
		// 将数据传至显示界面
		ModelAndView view = new ModelAndView("project-list");
		view.addObject("page", page);
		return view;
	}
	/**
	 * 
	 * @comment  根据项目id得到项目详情和项目前10 条动态以及回复
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/projectDetail")
	public ModelAndView getProjectDetail( int projId) {
		List<ProjectDynamic> lists =new ArrayList<ProjectDynamic>();
		Double a=projectInfoService.findConsumption(projId);	
		//查询该项目详情
	    ProjectInfo projectDetail = projectInfoService.findProjectInfoByProjId(projId);
	    //查询该项目的前10 条第一回复动态
	    List<ProjectDynamic> projectDynamic = projectInfoService.findProjectDynamic(projId);
	    
	    for (int i = 0; i < projectDynamic.size(); i++) {
	    ProjectDynamic projectDynamics=new ProjectDynamic();
	    List<ProjectDynamic> lists1=new ArrayList<ProjectDynamic>();
	    //查询每条动态底下的所有评论
	    List<ProjectDynamic> list = projectInfoService.findProjectDynamicByName(projectDynamic.get(i),lists1);
	    if(list!=null && list.size()>0){
	    	//将该条动态其底下的所有评论封装到一个对象，放入集合中
	    	projectDynamics.setComment(list); 
	    	projectDynamics.setUserName(projectDynamic.get(i).getUserName());
	    	projectDynamics.setDynamicDesc(projectDynamic.get(i).getDynamicDesc());
	    	projectDynamics.setCreateTime(projectDynamic.get(i).getCreateTime());
	    	projectDynamics.setDynamicId(projectDynamic.get(i).getDynamicId());
	        lists.add(projectDynamics);
	        
	    }else{
	    	projectDynamics.setUserName(projectDynamic.get(i).getUserName());
	    	projectDynamics.setDynamicDesc(projectDynamic.get(i).getDynamicDesc());
	    	projectDynamics.setCreateTime(projectDynamic.get(i).getCreateTime());
	    	projectDynamics.setDynamicId(projectDynamic.get(i).getDynamicId());
	        lists.add(projectDynamics);
	    	
	    }
 	 
		}
		ModelAndView view = new ModelAndView("project-detail");
		view.addObject("projectDetail", projectDetail);		
		view.addObject("lists", lists);
		view.addObject("consTime", a);
		return view;
	}
    /**
     * 
     * @comment  新建项目并发表动态
     * @return 
     * @version 1.0
     */
	@RequestMapping("/insertprojectInfo")
	@ResponseBody
	public JSONObject insertprojectInfo(HttpServletRequest request,ProjectInfo projectInfo) {
		int re=projectInfoService.insertProjectInfo(request,projectInfo);			
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
     * 
     * @comment  发表动态
     * @return 
     * @version 1.0
     */
	@RequestMapping("/insertDynamicMessage")
	@ResponseBody
	public JSONObject insertDynamicMessage(ProjectDynamic projectDynamic) {
		int re=projectInfoService.insertProjectDynamic(projectDynamic);
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
     * 
     * @comment  发表评论
     * @return 
     * @version 1.0
     */
	@RequestMapping("/insertDynamicMessages")
	@ResponseBody
	public JSONObject insertDynamicMessages(ProjectDynamic projectDynamic) {
		int re=projectInfoService.insertProjectDynamics(projectDynamic);
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
	 * @comment 分页展示项目动态
	 * @param projId
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/AllDynamicMessage")
	public ModelAndView AllDynamicMessage(HttpServletRequest request,ProjectDynamic projectDynamic){
		PageBean page = projectInfoService.findAllProjectDynamic(request, projectDynamic);
		ModelAndView view = new ModelAndView("dynamic-detail");
		view.addObject("page",page);
		view.addObject("projectDynamic",projectDynamic);
		return view;
	}
	/**
	 * 
	 * @comment 修改项目详情，并发布动态
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/updateProjectInfo")
	@ResponseBody
	public JSONObject updateProjectInfo(HttpServletRequest request,ProjectInfo projectInfo) {
		int re =projectInfoService.updateProjectDetail(request,projectInfo);
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
	 * 
	 * @comment 自动补全查询项目名称、项目编号、项目负责人
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoComplete")
	@ResponseBody
	public JSONObject findAutoComplete(ProjectInfo projectInfo) {
		String names =projectInfoService.findAutoCompleteByProjName(projectInfo);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// 将结果返回前端
		return json;
	}
	/**
	 * 
	 * @comment 自动补全(白名单)
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoCompleteWhite")
	@ResponseBody  
	public JSONObject findAutoCompleteWhite(HttpServletRequest request) {
		String names =projectInfoService.findAutoCompleteWhite(request);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// 将结果返回前端
		return json;
	}
	/**
	 * 
	 * @comment 提交白名单
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/submitAutoCompleteWhite")
	@ResponseBody  
	public JSONObject submitAutoCompleteWhite(HttpServletRequest request) {
		  int re=projectInfoService.insertProjectWhite(request);
		  JSONObject json = new JSONObject();		
              if(re>0){
            	  json.put("msg", "0");
              }else{
            	  json.put("msg", "1");
              }
		// 将结果返回前端
		return json;
	}	
	/**
	 * 
	 * @comment 根据项目id查询项目的白名单用户
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findProjectWhiteListByProjectId")
	@ResponseBody  
	public JSONObject findProjectWhiteListByProjectId(HttpServletRequest request) {		  
		 String userNames="";
		 String userIds="";
		 //拿到项目id
		 int projId=Integer.parseInt(request.getParameter("projId"));
		 //查询该项目所有白名单用户姓名和用户id的字符串拼接
		 List<ProjectWhiteList> projectWhiteList = projectInfoService.findProjectWhiteListByProjectId(projId);
		 for (ProjectWhiteList projectWhiteList2 : projectWhiteList) {
			userNames+=projectWhiteList2.getUserName()+",";
			userIds+=projectWhiteList2.getUserId()+",";
		}
		  JSONObject json = new JSONObject();		
		  json.put("msg1", userNames);
		  json.put("msg2", userIds);
		// 将结果返回前端
		return json;
	}
	
	/**
	 * 
	 * @comment 提交修改后的白名单
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/submitAutoCompleteWhiteUpdate")
	@ResponseBody  
	public JSONObject submitAutoCompleteWhiteUpdate(HttpServletRequest request) {
		  int re=projectInfoService.insertProjectWhiteByUpdate(request);
		  JSONObject json = new JSONObject();		
              if(re>0){
            	  json.put("msg", "0");
              }else{
            	  json.put("msg", "1");
              }
		// 将结果返回前端
		return json;
	}	
	/**
	 * 
	 * @comment 清除白名单
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/declearAutoCompleteWhiteUpdate")
	@ResponseBody  
	public JSONObject declearAutoCompleteWhiteUpdate(HttpServletRequest request) {
		  int projId=Integer.parseInt(request.getParameter("projId")); 
		  int re=projectInfoService.deleteProjectWhite(projId);
		  JSONObject json = new JSONObject();		
              if(re>0){
            	  json.put("msg", "0");
              }else{
            	  json.put("msg", "1");
              }
		// 将结果返回前端
		return json;
	}
	/**
	 * 
	 * @comment 自动补全查询动态名称、动态创建人、动态回复人
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoCompleteProjectDynamic")
	@ResponseBody
	public JSONObject findAutoCompleteProjectDynamic(ProjectDynamic projectDynamic) {
		String names =projectInfoService.findAutoCompleteByProjectDynamic(projectDynamic);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// 将结果返回前端
		return json;
	}
	/**
	 * 
	 * @comment 查询该项目绑定的所有项目组
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAllProjectTeamByProId")
	@ResponseBody  
	public JSONObject findAllProjectTeamByProId(ProjectInfo projectInfo) {
		JSONObject json = new JSONObject(); 
		  //查询该项目可分配的所有项目组
		  List<ProjectTeam> AllProjectTeam = projectInfoService.findAllProjectTeam(projectInfo);
		  //将项目组id和项目组name拼接成字符串集
		  Integer [] shu1 = new Integer[AllProjectTeam.size()];
		  String [] shu2 = new String[AllProjectTeam.size()];
		  for (int i = 0; i < AllProjectTeam.size(); i++) {
			  shu1[i]=AllProjectTeam.get(i).getTeamId();
			  shu2[i]=AllProjectTeam.get(i).getTeamName();
		}
		  //查询该项目已经绑定的项目组
		  List<ProjectTeam> list = projectInfoService.findAllProjectTeamByProId(projectInfo);
		  Integer [] shu = new Integer[list.size()];
			for (int i = 0; i < list.size(); i++) {
				shu[i] = list.get(i).getTeamId();
			}
		
			//System.out.println(shu.toString());
			json.put("shu", shu);
			json.put("shu1", shu1);
			json.put("shu2", shu2);
			return json;
	}
	/**
	 * 分配项目组 
	 */
	@RequestMapping("/insertProjectTeamToPro")
	@ResponseBody
	public JSONObject insertProjectTeamToPro(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		 int a= projectInfoService.insertProjectTeamToPro(request);
		
		if (a > 0) {
			json.put("res", "2");
		}

		return json;
	}
	/**
	 * 查看所有可用的项目名称
	 */
	@RequestMapping("/findAllProjectName")
	@ResponseBody
	public JSONObject findAllProjectName() {
		JSONObject json = new JSONObject();
	List<SysClass> findProjectName = projectInfoService.findProjectName();
	  SysClass sysClass=new SysClass();
	  //查询所有可用项目名称  将类id和name拼成字符串集
	  Integer [] shu1 = new Integer[findProjectName.size()];
	  String [] shu2 = new String[findProjectName.size()];
	  for (int i = 0; i < findProjectName.size(); i++) {
		  shu1[i]=findProjectName.get(i).getClassId();
		  shu2[i]=findProjectName.get(i).getClassName();
	}

	    json.put("shu1", shu1);
		json.put("shu2", shu2);
		return json;
	}
	
	/**
	 * 删除动态
	 */
	@RequestMapping("/deleteProjectDynamic")
	@ResponseBody
	public JSONObject deleteProjectDynamic(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		 Recycle recycle=new Recycle();
		UserInfo userInfo= (UserInfo) request.getSession().getAttribute("userInfo");
		 //动态描述
		 String dynamicDesc= request.getParameter("dynamicDesc");
		 //项目Id
		 int projId=Integer.parseInt(request.getParameter("projId")) ;
		 //动态Id
		 int dynamicId= Integer.parseInt( request.getParameter("dynamicId"));
		 
		  recycle.setKeyName("dynamic_id");
		  recycle.setKeyValue(dynamicId);
		  recycle.setDbUser("autho_manage");
		  int sc=projectInfoService.findClassId(projId);
		  int classId=projectInfoService.findClassIdss(projId);
		  System.out.println(classId);
		  recycle.setClassId(classId);
		  recycle.setIntro(dynamicDesc);
		  recycle.setCreateBy(userInfo.getUserId());
		  recycle.setTableName("proj_dynamic");
		  //判断项目底下是否有删除动态模块
		  if(sc==0){
			  json.put("msg", 0);
		  }else{
			  
			 int a= recycleService.delData(recycle);
			 if(a>0){
				 json.put("msg", 1);
			 }
		  } 
		return json;
	}
	/**
	 * 
	 * @comment  查询项目版本
	 * @param request
	 * @param 
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/projectVession")
	public ModelAndView projectVession(HttpServletRequest request,
			ProjectVersion projectVersion) {	
		
		PageBean page = projectInfoService.findProjectVersions(request, projectVersion);
		// 将数据传至显示界面
		ModelAndView view = new ModelAndView("projectVession");
		view.addObject("page", page);
		view.addObject("projId", projectVersion.getProjId());
		return view;
	}
	/**
	 * 修改版本信息 
	 */
	@RequestMapping("/updateVersion")
	@ResponseBody
	public JSONObject updateVersion(ProjectVersion projectVersion) {
		JSONObject json = new JSONObject();
		 int a= projectInfoService.updateProjVersin(projectVersion);
		
		if (a > 0) {
			json.put("res", "2");
		}

		return json;
	}
	/**
	 * 添加版本信息 
	 */
	@RequestMapping("/insertVersion")
	@ResponseBody
	public JSONObject insertVersion(HttpServletRequest request, ProjectVersion projectVersion) {
		JSONObject json = new JSONObject();
		String versionNum1= projectInfoService.findVersionNum(projectVersion);
		String versionNum=projectVersion.getVersionNum();	
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("userInfo");
		projectVersion.setCreateBy(userInfo.getUserId());
		//判断是否有项目版本
		 if (versionNum1!=null && versionNum1!="" ){
			 int a= Integer.parseInt(versionNum);
				int b=Integer.parseInt(versionNum1);
		   //判断添加的版本是否大于原来的版本
		   if( a>b){				
	            int c=projectInfoService.insertVersion(projectVersion);
	            if(c>0){
	            	json.put("res", "0");
	            }
			}else{
				json.put("res", "1");
			}
			
		}else{
			 int c=projectInfoService.insertVersion(projectVersion);
			 if(c>0){
	            	json.put("res", "0");
	            }
		}
		

		return json;
	}
	/**
	 * 删除版本
	 */
	@RequestMapping("/deleteVersion")
	@ResponseBody
	public JSONObject deleteVersion(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		 Recycle recycle=new Recycle();
		UserInfo userInfo= (UserInfo) request.getSession().getAttribute("userInfo");
		 //版本描述
		 String versionDesc= request.getParameter("versionDesc");
		 //项目Id
		 int projId=Integer.parseInt(request.getParameter("projId")) ;
		 //版本Id
		 int versionId= Integer.parseInt( request.getParameter("versionId"));
		 
		  recycle.setKeyName("version_id");
		  recycle.setKeyValue(versionId);
		  recycle.setDbUser("autho_manage");
		  int sc=projectInfoService.findVersionClassId(projId);
		  //查询模块类id
		  int classId=projectInfoService.findVersionClassIdss(projId);
		  recycle.setClassId(classId);
		  recycle.setIntro(versionDesc);
		  recycle.setCreateBy(userInfo.getUserId());
		  recycle.setTableName("proj_version");
		  //判断项目底下是否有删除版本模块
		  if(sc==0){
			  json.put("msg", 0);
		  }else{
			  
			 int a= recycleService.delData(recycle);
			 if(a>0){
				 json.put("msg", 1);
			 }
		  } 
		return json;
	}
}

