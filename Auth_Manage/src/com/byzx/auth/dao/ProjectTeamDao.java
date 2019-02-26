/**
 * @filename projectTeamDao.java
 * @author Administrator
 * @date 2018��9��7�� ����11:15:32
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.TeamMember;
import com.byzx.auth.bean.UserInfo;

public interface ProjectTeamDao {
	/**
	 * 
	 * @comment �鿴������Ŀ����Ϣ
	 * @param request
	 * @param projectInfo
	 * @return ������Ŀ����
	 * @version 1.0
	 */
	public List<ProjectTeam> findAllProjectTeam(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment �鿴��Ŀ������
	 * @param
	 * @return ��Ŀ����
	 * @version 1.0
	 */
	public int findAllProjectTeamNum(ProjectTeam projectTeam);

	/**
	 * 
	 * @comment ��Ŀ����鿴������Ŀ����Ϣ
	 * @param request
	 * @param projectInfo
	 * @return ������Ŀ����
	 * @version 1.0
	 */
	public List<ProjectTeam> findSomeProjectTeam(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment ��Ŀ����鿴��Ŀ������
	 * @param
	 * @return ��Ŀ����
	 * @version 1.0
	 */
	public int findSomeProjectTeamNum(HashMap<Object, Object> map);
	/**
	 * 
	 * @comment ��Ŀ�鳤�鿴������Ŀ����Ϣ
	 * @param request
	 * @param 
	 * @return ������Ŀ����
	 * @version 1.0
	 */
	public List<ProjectTeam> findOnlyProjectTeam(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment ��Ŀ�鳤�鿴��Ŀ������
	 * @param
	 * @return ��Ŀ����
	 * @version 1.0
	 */
	public int findOnlyProjectTeamNum(ProjectTeam projectTeam);

	/**
	 * 
	 * @comment �鿴������Ŀ��Ա
	 * @return
	 * @version 1.0
	 */
	public List<TeamMember> findAllTeamMember(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment �鿴��Ŀ������
	 * @return
	 * @version 1.0
	 */
	public int findAllTeamMemberNum(TeamMember teamMember);

	/**
	 * 
	 * @comment ��Ŀ�鸺�����Զ���ȫ
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
	public List<ProjectInfo> findAllProjects();

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
	public List<ProjectTeam> findAllotPeople(TeamMember teamMember);

	/**
	 * 
	 * @comment �жϣ���������Ƿ�>0,ʵ������ʱ��>0��
	 * @return
	 * @version 1.0
	 */
	public int findJudgeTask(TeamMember teamMember);

	/**
	 * 
	 * @comment ִ��������Ϊ�գ������������޸�Ϊ����ͣ
	 * @return
	 * @version 1.0
	 */
	public int updateProjTask(String userName);

	/**
	 * 
	 * @comment �����û���
	 * @return
	 * @version 1.0
	 */
	public int updateProjectTeam(TeamMember teamMember);
}
