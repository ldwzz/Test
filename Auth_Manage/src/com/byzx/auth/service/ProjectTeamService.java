/**
 * @filename projectTeamService.java
 * @author Administrator
 * @date 2018��9��7�� ����11:17:47
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
	   * @comment �鿴��Ŀ����Ϣ
	   * @param request
	   * @param projectTeam
	   * @return  PageBean
	   * @version 1.0
	   */
	  public PageBean findProjectTeams(HttpServletRequest request,ProjectTeam projectTeam);
      /**
       * 
       * @comment �鿴��Ŀ���Ա��Ϣ
       * @param request
       * @param teamMember
       * @return PageBean
       * @version 1.0
       */
	  public PageBean findTeamMembers(HttpServletRequest request,TeamMember teamMember);
	  /**
	   * 
	   * @comment ��Ŀ�������Զ���ȫ
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteProjTeamChief(String name);
	  /**
	   * 
	   * @comment �½���Ŀ��
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectTeam(ProjectTeam ProjectTeam);
	  /**
	   * 
	   * @comment ��Ŀ��codeΨһУ�� 
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam> findProjectTeamCode(ProjectTeam ProjectTeam);
	  /**
	   * 
	   * @comment ��ѯ���е���Ŀ
	   * @return 
	   * @version 1.0
	   */
	  public  List<ProjectInfo> findAllProjects(); 
	 /**
	  * 
	  * @comment ��Ŀ���Ա�����Զ���ȫ
	  * @return 
	  * @version 1.0
	  */
	  public String findAutoCompleteProjTeamUserName(TeamMember teamMember);
	  /**
	   * 
	   * @comment �½���Ŀ���Ա
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectTeamMember(TeamMember teamMember);
	  /**
	   * 
	   * @comment ��ѯ��������������Ŀ����������Ŀ��
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam>findAllotPeople(TeamMember teamMember);
	  /**
	   * 
	   * @comment �жϣ���������Ƿ�>0,ʵ������ʱ��>0��
	   * @return 
	   * @version 1.0
	   */
	  public int findJudgeTask(TeamMember teamMember);
	  /**
	   * 
	   * @comment �����û���
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjectTeam(HttpServletRequest request);
}

