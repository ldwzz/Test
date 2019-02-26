/**
 * @filename ProjectInfoService.java
 * @author Administrator
 * @date 2018��9��4�� ����10:18:31
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
 *@date 2018��9��4�� ����10:18:56
 *@modifyUser Administrator
 *@modifyDate 2018��9��4�� ����10:18:56
 *@modifyDesc  TODO
 *@version TODO
 */
public interface ProjectInfoService {
	 /**
	   * 
	   * @comment �鿴��Ŀ��Ϣ
	   * @param request
	   * @param projectInfo
	   * @return  PageBean
	   * @version 1.0
	   */
	  public PageBean findProjects(HttpServletRequest request,ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment  ͨ����Ŀid��ѯ��Ŀ����
	   * @param projId
	   * @return ��Ŀ����
	   * @version 1.0
	   */
	  public ProjectInfo findProjectInfoByProjId(int projId);
	  /**
	   * 
	   * @comment  ͨ����Ŀid��ѯ����Ŀǰ10����̬
	   * @param map
	   * @return  ��̬����
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamic(int projId);	  
	  /**
	   * 
	   * @comment ͨ���ظ������ֲ�ѯ��̬
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamicByName(ProjectDynamic projectDynamic,List<ProjectDynamic> lists);
	  /**
	   * 
	   * @comment �½���Ŀ ���ҷ�����Ŀ��̬
	   * @return 1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	  public int insertProjectInfo(HttpServletRequest request,ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment �Զ�������Ŀ��̬
	   * @return  1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	  public int insertProjectDynamic(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment ���۶�̬
	   * @return  1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	  public int insertProjectDynamics(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment  ��ҳ��ѯ����Ŀ���ж�̬
	   * @param map
	   * @return  ��̬����
	   * @version 1.0
	   */
	  public PageBean findAllProjectDynamic(HttpServletRequest request,ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment �޸���Ŀ����
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjectDetail(HttpServletRequest request,ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment �Զ���ȫ��ѯ��Ŀ���ơ���Ŀ��š���Ŀ������
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteByProjName(ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment �Զ���ȫ��ѯ�������û�
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteWhite(HttpServletRequest request);
	  /**
	   * 
	   * @comment ���������
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectWhite(HttpServletRequest request);
	  /**
	   * 
	   * @comment ������Ŀid��ѯ��Ŀ�İ������û�
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectWhiteList> findProjectWhiteListByProjectId(int id);
	  /**
	   * 
	   * @comment �ȸ�����Ŀidɾ�����еİ�����
	   * @return 
	   * @version 1.0
	   */
	  public int deleteProjectWhite(int id);
	  /**
	   * 
	   * @comment ����޸ĺ�İ�����
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectWhiteByUpdate(HttpServletRequest request);
	  /**
	   * 
	   * @comment �Զ���ȫ��ѯ��̬���ơ���̬�����ˡ���̬�ظ���
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteByProjectDynamic(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment ��ѯ���е���Ŀ��
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam> findAllProjectTeam(ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment ��ѯ����Ŀ�󶨵���Ŀ��
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectTeam> findAllProjectTeamByProId(ProjectInfo projectInfo);
      /**
       * 
       * @comment �������Ŀ����Ŀ��
       * @return 
       * @version 1.0
       */
	  public int deleteProjectTeamToPro(ProjectTeam projectTeam);
	  /**
	   * 
	   * @comment ����Ŀ������Ŀ��
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectTeamToPro(HttpServletRequest request);
	  /**
	   * 
	   * @comment ���ֿ��õ���Ŀ����
	   * @return 
	   * @version 1.0
	   */
	  public List<SysClass> findProjectName();
	  /**
	   * 
	   * @comment ��ѯ��Ŀ����ʱ��
	   * @return 
	   * @version 1.0
	   */
	  public Double  findConsumption(int projId);
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ����̬ģ��id����
	   * @return 
	   * @version 1.0
	   */
	  public  int findClassId(int projId);
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ����̬ģ��id 
	   * @return 
	   * @version 1.0
	   */
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ���汾ģ��id �治����
	   * @return 
	   * @version 1.0
	   */
	  public  int findVersionClassId(int projId);
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ���汾ģ��id 
	   * @return 
	   * @version 1.0
	   */
	  public  int findVersionClassIdss(int projId);
	  public  int findClassIdss(int projId);
	  /**
	   * 
	   * @comment �鿴��Ŀ�汾��Ϣ
	   * @param request
	   * @param 
	   * @return  
	   * @version 1.0
	   */
	  public PageBean findProjectVersions(HttpServletRequest request,ProjectVersion projectVersion);
	  /**
	   * 
	   * @comment �޸İ汾��Ϣ
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjVersin(ProjectVersion projectVersion);
	  /**
	   * 
	   * @comment ��֤�汾��
	   * @return 
	   * @version 1.0
	   */
	  public String findVersionNum(ProjectVersion projectVersion);
	  /**
	   * 
	   * @comment �½��汾
	   * @return 
	   * @version 1.0
	   */
	  public int insertVersion(ProjectVersion projectVersion);
	  //**************************************************
	  /**
		 * @comment ��ѯ��ǰ��¼����ӵ�е�Ȩ�ޣ������ܿ�������Ŀ��Ӧ�����ݣ�1.��ǰ��¼��admin
		 * @param map
		 * @return List<ProjectInfo>
		 * @version 1.0
		 */
		public List<ProjectInfo> findAllPro(Map map);

		/**
		 * @comment ��ѯ��ǰ��¼�������ӵ�е�Ȩ�ޣ������ܿ�������Ŀ��Ӧ�����ݣ�2.��ǰ��¼����Ŀ����project_manager��
		 *          ��Ŀ�鳤project_leader���з���Աproject_member
		 * @param map
		 * @return List<ProjectInfo>
		 * @version 1.0
		 */
		public List<ProjectInfo> findOneselfPro(Map map);
		/**
		 * @comment ����projNameģ����ѯ����Ӧ��projIds�����ܶ����  
		 * @param projName
		 * @return String
		 * @version 1.0
		 */
		public  String projIdsByProjName(String projName);
		
		
}

