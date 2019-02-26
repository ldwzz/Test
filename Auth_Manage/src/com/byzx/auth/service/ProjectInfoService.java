/**
 * @filename ProjectInfoService.java
 * @author Administrator
 * @date 2018年9月4日 上午10:18:31
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.ProjectDynamic;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.ProjectVersion;
import com.byzx.auth.bean.ProjectWhiteList;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.utils.PageBean;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018年9月4日 上午10:18:56
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:18:56
 *@modifyDesc  TODO
 *@version TODO
 */
public interface ProjectInfoService {
	 /**
	   * 
	   * @comment 查看项目信息
	   * @param request
	   * @param projectInfo
	   * @return  PageBean
	   * @version 1.0
	   */
	  public PageBean findProjects(HttpServletRequest request,ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment  通过项目id查询项目详情
	   * @param projId
	   * @return 项目详情
	   * @version 1.0
	   */
	  public ProjectInfo findProjectInfoByProjId(int projId);
	  /**
	   * 
	   * @comment  通过项目id查询该项目前10条动态
	   * @param map
	   * @return  动态集合
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamic(int projId);	  
	  /**
	   * 
	   * @comment 通过回复人名字查询动态
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamicByName(ProjectDynamic projectDynamic,List<ProjectDynamic> lists);
	  /**
	   * 
	   * @comment 新建项目 并且发表项目动态
	   * @return 1：成功，0：失败
	   * @version 1.0
	   */
	  public int insertProjectInfo(HttpServletRequest request,ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment 自动发表项目动态
	   * @return  1：成功，0：失败
	   * @version 1.0
	   */
	  public int insertProjectDynamic(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment 评论动态
	   * @return  1：成功，0：失败
	   * @version 1.0
	   */
	  public int insertProjectDynamics(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment  分页查询该项目所有动态
	   * @param map
	   * @return  动态集合
	   * @version 1.0
	   */
	  public PageBean findAllProjectDynamic(HttpServletRequest request,ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment 修改项目详情
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjectDetail(HttpServletRequest request,ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment 自动补全查询项目名称、项目编号、项目负责人
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteByProjName(ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment 自动补全查询白名单用户
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteWhite(HttpServletRequest request);
	  /**
	   * 
	   * @comment 插入白名单
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectWhite(HttpServletRequest request);
	  /**
	   * 
	   * @comment 根据项目id查询项目的白名单用户
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectWhiteList> findProjectWhiteListByProjectId(int id);
	  /**
	   * 
	   * @comment 先根据项目id删除所有的白名单
	   * @return 
	   * @version 1.0
	   */
	  public int deleteProjectWhite(int id);
	  /**
	   * 
	   * @comment 添加修改后的白名单
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectWhiteByUpdate(HttpServletRequest request);
	  /**
	   * 
	   * @comment 自动补全查询动态名称、动态创建人、动态回复人
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteByProjectDynamic(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment 查询所有的项目组
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam> findAllProjectTeam(ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment 查询该项目绑定的项目组
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam> findAllProjectTeamByProId(ProjectInfo projectInfo);
      /**
       * 
       * @comment 先清空项目的项目组
       * @return 
       * @version 1.0
       */
	  public int deleteProjectTeamToPro(ProjectTeam projectTeam);
	  /**
	   * 
	   * @comment 给项目分配项目组
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectTeamToPro(HttpServletRequest request);
	  /**
	   * 
	   * @comment 发现可用的项目名称
	   * @return 
	   * @version 1.0
	   */
	  public List<SysClass> findProjectName();
	  /**
	   * 
	   * @comment 查询项目消耗时间
	   * @return 
	   * @version 1.0
	   */
	  public Double  findConsumption(int projId);
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除动态模块id存在
	   * @return 
	   * @version 1.0
	   */
	  public  int findClassId(int projId);
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除动态模块id 
	   * @return 
	   * @version 1.0
	   */
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除版本模块id 存不存在
	   * @return 
	   * @version 1.0
	   */
	  public  int findVersionClassId(int projId);
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除版本模块id 
	   * @return 
	   * @version 1.0
	   */
	  public  int findVersionClassIdss(int projId);
	  public  int findClassIdss(int projId);
	  /**
	   * 
	   * @comment 查看项目版本信息
	   * @param request
	   * @param 
	   * @return  
	   * @version 1.0
	   */
	  public PageBean findProjectVersions(HttpServletRequest request,ProjectVersion projectVersion);
	  /**
	   * 
	   * @comment 修改版本信息
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjVersin(ProjectVersion projectVersion);
	  /**
	   * 
	   * @comment 验证版本号
	   * @return 
	   * @version 1.0
	   */
	  public String findVersionNum(ProjectVersion projectVersion);
	  /**
	   * 
	   * @comment 新建版本
	   * @return 
	   * @version 1.0
	   */
	  public int insertVersion(ProjectVersion projectVersion);
	  //**************************************************
	  /**
		 * @comment 查询当前登录这所拥有的权限（他所能看到的项目对应的内容）1.当前登录人admin
		 * @param map
		 * @return List<ProjectInfo>
		 * @version 1.0
		 */
		public List<ProjectInfo> findAllPro(Map map);

		/**
		 * @comment 查询当前登录这个人所拥有的权限（他所能看到的项目对应的内容）2.当前登录人项目经理project_manager、
		 *          项目组长project_leader、研发人员project_member
		 * @param map
		 * @return List<ProjectInfo>
		 * @version 1.0
		 */
		public List<ProjectInfo> findOneselfPro(Map map);
		/**
		 * @comment 根据projName模糊查询出对应的projIds（可能多个）  
		 * @param projName
		 * @return String
		 * @version 1.0
		 */
		public  String projIdsByProjName(String projName);
		
		
}

