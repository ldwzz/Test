/**
 * @filename projectTeamService.java
 * @author Administrator
 * @date 2018年9月7日 下午11:17:47
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;









import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.TeamMember;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.utils.PageBean;

public interface ProjectTeamService {
	 /**
	   * 
	   * @comment 查看项目组信息
	   * @param request
	   * @param projectTeam
	   * @return  PageBean
	   * @version 1.0
	   */
	  public PageBean findProjectTeams(HttpServletRequest request,ProjectTeam projectTeam);
      /**
       * 
       * @comment 查看项目组成员信息
       * @param request
       * @param teamMember
       * @return PageBean
       * @version 1.0
       */
	  public PageBean findTeamMembers(HttpServletRequest request,TeamMember teamMember);
	  /**
	   * 
	   * @comment 项目负责人自动补全
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteProjTeamChief(String name);
	  /**
	   * 
	   * @comment 新建项目组
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectTeam(ProjectTeam ProjectTeam);
	  /**
	   * 
	   * @comment 项目组code唯一校验 
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam> findProjectTeamCode(ProjectTeam ProjectTeam);
	  /**
	   * 
	   * @comment 查询所有的项目
	   * @return 
	   * @version 1.0
	   */
	  public  List<ProjectInfo> findAllProjects(); 
	 /**
	  * 
	  * @comment 项目组成员名称自动补全
	  * @return 
	  * @version 1.0
	  */
	  public String findAutoCompleteProjTeamUserName(TeamMember teamMember);
	  /**
	   * 
	   * @comment 新建项目组成员
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectTeamMember(TeamMember teamMember);
	  /**
	   * 
	   * @comment 查询除了自身所属项目组外其他项目组
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam>findAllotPeople(TeamMember teamMember);
	  /**
	   * 
	   * @comment 判断（任务进度是否>0,实际消耗时间>0）
	   * @return 
	   * @version 1.0
	   */
	  public int findJudgeTask(TeamMember teamMember);
	  /**
	   * 
	   * @comment 分配用户组
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjectTeam(HttpServletRequest request);
}

