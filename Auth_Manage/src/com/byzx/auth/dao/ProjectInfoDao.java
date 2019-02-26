/**
 * @filename ProjectInfoDao.java
 * @author Administrator
 * @date 2018��9��4�� ����10:16:16
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.byzx.auth.bean.ProjectDynamic;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.ProjectVersion;
import com.byzx.auth.bean.ProjectWhiteList;
import com.byzx.auth.bean.SysClass;

/**
 * 
 *@comment
 *@author Administrator
 *@date 2018��9��4�� ����10:17:12
 *@modifyUser Administrator
 *@modifyDate 2018��9��4�� ����10:17:12
 *@modifyDesc  TODO
 *@version TODO
 */
public interface ProjectInfoDao {
	  /**
	   * 
	   * @comment ����Ա��½ �鿴������Ŀ��Ϣ
	   * @param request
	   * @param projectInfo
	   * @return  ������Ŀ����
	   * @version 1.0
	   */
	  public List<ProjectInfo> findAllProject(HashMap<Object, Object> map);
      /**
       * 
       * @comment  ����Ա��½ �鿴��Ŀ����
       * @param projectInfo
       * @return  ��Ŀ����
       * @version 1.0
       */
	  public int findAllProjectNum(ProjectInfo projectInfo);
	  /**
	   * 
	   * @comment ��Ŀ�����½ �鿴������Ŀ��Ϣ
	   * @param request
	   * @param projectInfo
	   * @return  ������Ŀ����
	   * @version 1.0
	   */
	  public List<ProjectInfo> findSomeProject(HashMap<Object, Object> map);
      /**
       * 
       * @comment  ��Ŀ�����½ �鿴��Ŀ����
       * @param projectInfo
       * @return  ��Ŀ����
       * @version 1.0
       */
	  public int findSomeProjectNum(HashMap<Object, Object> map);
	  /**
	   * 
	   * @comment ��Ŀ�鳤�Լ���Ŀ��Ա��½ �鿴������Ŀ��Ϣ
	   * @param request
	   * @param projectInfo
	   * @return  ������Ŀ����
	   * @version 1.0
	   */
	  public List<ProjectInfo> findOnlyProject(HashMap<Object, Object> map);
      /**
       * 
       * @comment ��Ŀ�鳤�Լ���Ŀ��Ա��½ �鿴��Ŀ����
       * @param projectInfo
       * @return  ��Ŀ����
       * @version 1.0
       */
	  public int findOnlyProjectNum(HashMap<Object, Object> map);
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
	   * @param 
	   * @return  ��̬����
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamic(int projId);
	  /**
	   * 
	   * @comment  ͨ����Ŀid��ѯ����Ŀ��������̬
	   * @param 
	   * @return  ��̬����
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamics(int projId);
	  /**
	   * 
	   * @comment ��ѯ��̬������������
	   * @return 
	   * @version 1.0
	   */
	  public List<ProjectDynamic> findProjectDynamicByName(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment �Զ�������Ŀ��̬
	   * @return  1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	  public int insertProjectDynamic(ProjectDynamic projectDynamic);	  
	  /**
	   * 
	   * @comment �½���Ŀ
	   * @return 1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	  public int insertProjectInfo(ProjectInfo projectInfo);
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
	  public List<ProjectDynamic> findAllProjectDynamic(HashMap<Object, Object> map);
	  /**
	   *   
	   * @comment   ��ѯ��Ŀ��̬��Ŀ
	   * @return 
	   * @version 1.0
	   */
	  public int findAllProjectDynamicNum(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment �޸���Ŀ����
	   * @return 
	   * @version 1.0
	   */
	  public int updateProjectDetail(ProjectInfo projectInfo);
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
	  public String findAutoCompleteWhite(HashMap<Object, Object> map);
	  /**
	   * 
	   * @comment ���������
	   * @return 
	   * @version 1.0
	   */
	  public int insertProjectWhite(ProjectWhiteList projectWhiteList);	  
	  /**
	   * 
	   * @comment �����û������û�id
	   * @return 
	   * @version 1.0
	   */
	  public int findUserIdByUserName(String userName);
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
	  public int insertProjectWhiteByUpdate(ProjectWhiteList projectWhiteList);
	  /**
	   * 
	   * @comment �Զ���ȫ��ѯ��̬���ơ���̬�����ˡ���̬�ظ���
	   * @return 
	   * @version 1.0
	   */
	  public String findAutoCompleteByProjectDynamic(ProjectDynamic projectDynamic);
	  /**
	   * 
	   * @comment ��ѯ����Ŀ���е���Ŀ��
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
	  public int insertProjectTeamToPro(ProjectTeam projectTeam);
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
	  public Double findConsumption(int projId);
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ����̬ģ��id �治����
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
	  public  int findClassIdss(int projId);
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
	  /**
	   * 
	   * @comment �鿴������Ŀ�汾��Ϣ
	   * @param request
	   * @param
	   * @return  ������Ŀ�汾��Ϣ����
	   * @version 1.0
	   */
	  public List<ProjectVersion> findAllProjectVession(HashMap<Object, Object> map);
      /**
       * 
       * @comment �鿴��Ŀ�汾����
       * @param projectInfo
       * @return  ��Ŀ�汾����
       * @version 1.0
       */
	  public int findAllProjectVessionNum(ProjectVersion projectVersion);
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
	  //*****************************************
	  /**
		 * @comment ��ѯ��ǰ��¼�������ӵ�е�Ȩ�ޣ������ܿ�������Ŀ��Ӧ�����ݣ�1.��ǰ��¼��admin
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

