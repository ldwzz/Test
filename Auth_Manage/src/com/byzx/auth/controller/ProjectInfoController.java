/**
 * @filename ProjectInfoController.java
 * @author Administrator
 * @date 2018��9��3�� ����10:55:13
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
	 * @comment  ��ѯ��Ŀ
	 * @param request
	 * @param project
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllProjects(HttpServletRequest request,
		ProjectInfo project) {	
		
		PageBean page = projectInfoService.findProjects(request, project);
		// �����ݴ�����ʾ����
		ModelAndView view = new ModelAndView("project-list");
		view.addObject("page", page);
		return view;
	}
	/**
	 * 
	 * @comment  ������Ŀid�õ���Ŀ�������Ŀǰ10 ����̬�Լ��ظ�
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/projectDetail")
	public ModelAndView getProjectDetail( int projId) {
		List<ProjectDynamic> lists =new ArrayList<ProjectDynamic>();
		Double a=projectInfoService.findConsumption(projId);	
		//��ѯ����Ŀ����
	    ProjectInfo projectDetail = projectInfoService.findProjectInfoByProjId(projId);
	    //��ѯ����Ŀ��ǰ10 ����һ�ظ���̬
	    List<ProjectDynamic> projectDynamic = projectInfoService.findProjectDynamic(projId);
	    
	    for (int i = 0; i < projectDynamic.size(); i++) {
	    ProjectDynamic projectDynamics=new ProjectDynamic();
	    List<ProjectDynamic> lists1=new ArrayList<ProjectDynamic>();
	    //��ѯÿ����̬���µ���������
	    List<ProjectDynamic> list = projectInfoService.findProjectDynamicByName(projectDynamic.get(i),lists1);
	    if(list!=null && list.size()>0){
	    	//��������̬����µ��������۷�װ��һ�����󣬷��뼯����
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
     * @comment  �½���Ŀ������̬
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
		// ���������ǰ��
		return json;
	}
	 /**
     * 
     * @comment  ����̬
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
		// ���������ǰ��
		return json;
	}
	 /**
     * 
     * @comment  ��������
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
		// ���������ǰ��
		return json;
	}
	/**
	 * @comment ��ҳչʾ��Ŀ��̬
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
	 * @comment �޸���Ŀ���飬��������̬
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
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @comment �Զ���ȫ��ѯ��Ŀ���ơ���Ŀ��š���Ŀ������
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoComplete")
	@ResponseBody
	public JSONObject findAutoComplete(ProjectInfo projectInfo) {
		String names =projectInfoService.findAutoCompleteByProjName(projectInfo);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @comment �Զ���ȫ(������)
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoCompleteWhite")
	@ResponseBody  
	public JSONObject findAutoCompleteWhite(HttpServletRequest request) {
		String names =projectInfoService.findAutoCompleteWhite(request);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @comment �ύ������
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
		// ���������ǰ��
		return json;
	}	
	/**
	 * 
	 * @comment ������Ŀid��ѯ��Ŀ�İ������û�
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findProjectWhiteListByProjectId")
	@ResponseBody  
	public JSONObject findProjectWhiteListByProjectId(HttpServletRequest request) {		  
		 String userNames="";
		 String userIds="";
		 //�õ���Ŀid
		 int projId=Integer.parseInt(request.getParameter("projId"));
		 //��ѯ����Ŀ���а������û��������û�id���ַ���ƴ��
		 List<ProjectWhiteList> projectWhiteList = projectInfoService.findProjectWhiteListByProjectId(projId);
		 for (ProjectWhiteList projectWhiteList2 : projectWhiteList) {
			userNames+=projectWhiteList2.getUserName()+",";
			userIds+=projectWhiteList2.getUserId()+",";
		}
		  JSONObject json = new JSONObject();		
		  json.put("msg1", userNames);
		  json.put("msg2", userIds);
		// ���������ǰ��
		return json;
	}
	
	/**
	 * 
	 * @comment �ύ�޸ĺ�İ�����
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
		// ���������ǰ��
		return json;
	}	
	/**
	 * 
	 * @comment ���������
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
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @comment �Զ���ȫ��ѯ��̬���ơ���̬�����ˡ���̬�ظ���
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoCompleteProjectDynamic")
	@ResponseBody
	public JSONObject findAutoCompleteProjectDynamic(ProjectDynamic projectDynamic) {
		String names =projectInfoService.findAutoCompleteByProjectDynamic(projectDynamic);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @comment ��ѯ����Ŀ�󶨵�������Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAllProjectTeamByProId")
	@ResponseBody  
	public JSONObject findAllProjectTeamByProId(ProjectInfo projectInfo) {
		JSONObject json = new JSONObject(); 
		  //��ѯ����Ŀ�ɷ����������Ŀ��
		  List<ProjectTeam> AllProjectTeam = projectInfoService.findAllProjectTeam(projectInfo);
		  //����Ŀ��id����Ŀ��nameƴ�ӳ��ַ�����
		  Integer [] shu1 = new Integer[AllProjectTeam.size()];
		  String [] shu2 = new String[AllProjectTeam.size()];
		  for (int i = 0; i < AllProjectTeam.size(); i++) {
			  shu1[i]=AllProjectTeam.get(i).getTeamId();
			  shu2[i]=AllProjectTeam.get(i).getTeamName();
		}
		  //��ѯ����Ŀ�Ѿ��󶨵���Ŀ��
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
	 * ������Ŀ�� 
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
	 * �鿴���п��õ���Ŀ����
	 */
	@RequestMapping("/findAllProjectName")
	@ResponseBody
	public JSONObject findAllProjectName() {
		JSONObject json = new JSONObject();
	List<SysClass> findProjectName = projectInfoService.findProjectName();
	  SysClass sysClass=new SysClass();
	  //��ѯ���п�����Ŀ����  ����id��nameƴ���ַ�����
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
	 * ɾ����̬
	 */
	@RequestMapping("/deleteProjectDynamic")
	@ResponseBody
	public JSONObject deleteProjectDynamic(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		 Recycle recycle=new Recycle();
		UserInfo userInfo= (UserInfo) request.getSession().getAttribute("userInfo");
		 //��̬����
		 String dynamicDesc= request.getParameter("dynamicDesc");
		 //��ĿId
		 int projId=Integer.parseInt(request.getParameter("projId")) ;
		 //��̬Id
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
		  //�ж���Ŀ�����Ƿ���ɾ����̬ģ��
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
	 * @comment  ��ѯ��Ŀ�汾
	 * @param request
	 * @param 
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/projectVession")
	public ModelAndView projectVession(HttpServletRequest request,
			ProjectVersion projectVersion) {	
		
		PageBean page = projectInfoService.findProjectVersions(request, projectVersion);
		// �����ݴ�����ʾ����
		ModelAndView view = new ModelAndView("projectVession");
		view.addObject("page", page);
		view.addObject("projId", projectVersion.getProjId());
		return view;
	}
	/**
	 * �޸İ汾��Ϣ 
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
	 * ��Ӱ汾��Ϣ 
	 */
	@RequestMapping("/insertVersion")
	@ResponseBody
	public JSONObject insertVersion(HttpServletRequest request, ProjectVersion projectVersion) {
		JSONObject json = new JSONObject();
		String versionNum1= projectInfoService.findVersionNum(projectVersion);
		String versionNum=projectVersion.getVersionNum();	
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("userInfo");
		projectVersion.setCreateBy(userInfo.getUserId());
		//�ж��Ƿ�����Ŀ�汾
		 if (versionNum1!=null && versionNum1!="" ){
			 int a= Integer.parseInt(versionNum);
				int b=Integer.parseInt(versionNum1);
		   //�ж���ӵİ汾�Ƿ����ԭ���İ汾
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
	 * ɾ���汾
	 */
	@RequestMapping("/deleteVersion")
	@ResponseBody
	public JSONObject deleteVersion(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		 Recycle recycle=new Recycle();
		UserInfo userInfo= (UserInfo) request.getSession().getAttribute("userInfo");
		 //�汾����
		 String versionDesc= request.getParameter("versionDesc");
		 //��ĿId
		 int projId=Integer.parseInt(request.getParameter("projId")) ;
		 //�汾Id
		 int versionId= Integer.parseInt( request.getParameter("versionId"));
		 
		  recycle.setKeyName("version_id");
		  recycle.setKeyValue(versionId);
		  recycle.setDbUser("autho_manage");
		  int sc=projectInfoService.findVersionClassId(projId);
		  //��ѯģ����id
		  int classId=projectInfoService.findVersionClassIdss(projId);
		  recycle.setClassId(classId);
		  recycle.setIntro(versionDesc);
		  recycle.setCreateBy(userInfo.getUserId());
		  recycle.setTableName("proj_version");
		  //�ж���Ŀ�����Ƿ���ɾ���汾ģ��
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

